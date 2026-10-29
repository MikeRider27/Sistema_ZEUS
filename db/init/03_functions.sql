-- =====================================================================
--  FUNCIONES abm_*  (mismo contrato que el legacy: bandera 1=Agregar,
--  2=Editar, 3=Anular; 4/5 = acciones propias de cada movimiento)
--  Devuelven el codigo afectado. Los errores se informan con
--  RAISE EXCEPTION y la aplicacion los muestra en un dialogo.
-- =====================================================================

-- proximo codigo, serializado por tabla (el legacy hacia MAX()+1 desde el
-- cliente y dos usuarios podian obtener el mismo codigo)
CREATE FUNCTION fn_next(p_tabla text, p_campo text) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE v integer;
BEGIN
    PERFORM pg_advisory_xact_lock(hashtext(p_tabla));
    EXECUTE format('SELECT coalesce(max(%I), 0) + 1 FROM %I', p_campo, p_tabla) INTO v;
    RETURN v;
END $$;

CREATE FUNCTION fn_ban_invalida(ban integer) RETURNS void
LANGUAGE plpgsql AS $$
BEGIN
    RAISE EXCEPTION 'Operacion no valida (bandera %)', ban;
END $$;

-- movimiento de stock con control de existencia
CREATE FUNCTION fn_stock(p_mat integer, p_dep integer, p_cant numeric, p_no_disp numeric DEFAULT 0)
RETURNS void LANGUAGE plpgsql AS $$
DECLARE r record;
BEGIN
    INSERT INTO stock (mat_cod, dep_cod) VALUES (p_mat, p_dep) ON CONFLICT DO NOTHING;
    SELECT * INTO r FROM stock WHERE mat_cod = p_mat AND dep_cod = p_dep FOR UPDATE;
    IF r.cant + p_cant < 0 THEN
        RAISE EXCEPTION 'Existencia insuficiente de "%" en el deposito "%" (disponible: %, requerido: %)',
            (SELECT mat_des FROM materiales WHERE mat_cod = p_mat),
            (SELECT dep_des FROM depositos WHERE dep_cod = p_dep), r.cant, -p_cant;
    END IF;
    IF r.cant_no_disp + p_no_disp < 0 THEN
        RAISE EXCEPTION 'Existencia no disponible inconsistente para el material %', p_mat;
    END IF;
    UPDATE stock SET cant = cant + p_cant, cant_no_disp = cant_no_disp + p_no_disp
     WHERE mat_cod = p_mat AND dep_cod = p_dep;
END $$;

-- ---------------------------------------------------------------------
--  LOGIN (bcrypt; el legacy comparaba MD5 sin sal)
-- ---------------------------------------------------------------------
CREATE FUNCTION fn_login(p_usr text, p_pass text) RETURNS SETOF v_log
LANGUAGE sql STABLE AS $$
    SELECT * FROM v_log WHERE usr_des = upper(p_usr) AND usr_pass = crypt(p_pass, usr_pass);
$$;

-- =====================================================================
--  REFERENCIALES SIMPLES (cod, des)
--  Se genera una funcion por tabla con el mismo cuerpo.
-- =====================================================================
DO $gen$
DECLARE
    d text[];
    defs text[][] := ARRAY[
        ARRAY['abm_nac',  'nacionalidades',   'nac_cod',  'nac_des'],
        ARRAY['abm_ciu',  'ciudades',         'ciu_cod',  'ciu_des'],
        ARRAY['abm_ban',  'bancos',           'ban_cod',  'ban_des'],
        ARRAY['abm_mar',  'marcas',           'mar_cod',  'mar_des'],
        ARRAY['abm_col',  'colores',          'col_cod',  'col_des'],
        ARRAY['abm_tveh', 'tipos_vehiculo',   'tveh_cod', 'tveh_des'],
        ARRAY['abm_motg', 'motivos_garantia', 'motg_cod', 'motg_des'],
        ARRAY['abm_tpag', 'tipos_pago',       'tip_cod',  'tip_des']
    ];
BEGIN
    FOREACH d SLICE 1 IN ARRAY defs LOOP
        EXECUTE format($f$
CREATE FUNCTION %1$I(ban integer, cod integer, des varchar) RETURNS integer
LANGUAGE plpgsql AS $b$
BEGIN
    IF ban = 1 THEN
        cod := fn_next(%2$L, %3$L);
        INSERT INTO %2$I (%3$I, %4$I) VALUES (cod, upper(trim(des)));
    ELSIF ban = 2 THEN
        UPDATE %2$I SET %4$I = upper(trim(des)) WHERE %3$I = cod AND est_cod = 'VIG';
        IF NOT FOUND THEN RAISE EXCEPTION 'El registro %% no existe o esta anulado', cod; END IF;
    ELSIF ban = 3 THEN
        UPDATE %2$I SET est_cod = 'ANU' WHERE %3$I = cod AND est_cod = 'VIG';
        IF NOT FOUND THEN RAISE EXCEPTION 'El registro %% no existe o ya esta anulado', cod; END IF;
    ELSIF ban = 4 THEN
        UPDATE %2$I SET est_cod = 'VIG' WHERE %3$I = cod AND est_cod = 'ANU';
    ELSE PERFORM fn_ban_invalida(ban);
    END IF;
    RETURN cod;
END $b$;$f$, d[1], d[2], d[3], d[4]);
    END LOOP;
END $gen$;

-- bandera 4 en todos los referenciales = Reactivar un registro anulado
CREATE FUNCTION fn_ref_estado(p_tabla text, p_campo text, ban integer, cod integer) RETURNS boolean
LANGUAGE plpgsql AS $$
DECLARE n integer;
BEGIN
    IF ban = 3 THEN
        EXECUTE format('UPDATE %I SET est_cod = ''ANU'' WHERE %I = $1 AND est_cod = ''VIG''', p_tabla, p_campo) USING cod;
    ELSIF ban = 4 THEN
        EXECUTE format('UPDATE %I SET est_cod = ''VIG'' WHERE %I = $1 AND est_cod = ''ANU''', p_tabla, p_campo) USING cod;
    ELSE
        RETURN false;
    END IF;
    GET DIAGNOSTICS n = ROW_COUNT;
    IF n = 0 THEN RAISE EXCEPTION 'El registro % no existe o ya tiene ese estado', cod; END IF;
    RETURN true;
END $$;

-- ---------------------------------------------------------------------
CREATE FUNCTION abm_bar(ban integer, cod integer, p_ciu integer, des varchar) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('barrios', 'bar_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('barrios', 'bar_cod');
        INSERT INTO barrios VALUES (cod, p_ciu, upper(trim(des)));
    ELSIF ban = 2 THEN
        UPDATE barrios SET ciu_cod = p_ciu, bar_des = upper(trim(des)) WHERE bar_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_suc(ban integer, cod integer, des varchar, tel varchar, dir varchar, mail varchar) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('sucursales', 'suc_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('sucursales', 'suc_cod');
        INSERT INTO sucursales VALUES (cod, upper(trim(des)), tel, upper(dir), lower(mail));
    ELSIF ban = 2 THEN
        UPDATE sucursales SET suc_des = upper(trim(des)), suc_tel = tel, suc_dir = upper(dir), suc_mail = lower(mail)
         WHERE suc_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_dep(ban integer, cod integer, p_suc integer, des varchar) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF ban = 3 AND EXISTS (SELECT 1 FROM stock WHERE dep_cod = cod AND (cant > 0 OR cant_no_disp > 0)) THEN
        RAISE EXCEPTION 'No se puede anular un deposito con existencias';
    END IF;
    IF fn_ref_estado('depositos', 'dep_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('depositos', 'dep_cod');
        INSERT INTO depositos VALUES (cod, p_suc, upper(trim(des)));
    ELSIF ban = 2 THEN
        UPDATE depositos SET suc_cod = p_suc, dep_des = upper(trim(des)) WHERE dep_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_car(ban integer, cod integer, des varchar, niv integer, usr boolean) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('cargos', 'car_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('cargos', 'car_cod');
        INSERT INTO cargos VALUES (cod, upper(trim(des)), niv, coalesce(usr, false));
    ELSIF ban = 2 THEN
        UPDATE cargos SET car_des = upper(trim(des)), car_niv = niv, car_user = coalesce(usr, false) WHERE car_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

-- en funcionarios el codigo es la C.I. (la ingresa el usuario)
CREATE FUNCTION abm_func(ban integer, cod integer, p_nac integer, p_car integer, p_suc integer,
                         nom varchar, ape varchar, cel varchar, dir varchar, fnac date,
                         ing date, egr date) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF ban = 3 THEN
        UPDATE usuarios SET est_cod = 'ANU' WHERE emp_ci = cod;
        UPDATE funcionarios SET date_egr = coalesce(date_egr, current_date) WHERE emp_ci = cod;
    END IF;
    IF fn_ref_estado('funcionarios', 'emp_ci', ban, cod) THEN RETURN cod; END IF;
    IF cod IS NULL OR cod <= 0 THEN RAISE EXCEPTION 'Debe ingresar un Nro. de C.I. valido'; END IF;
    IF ban = 1 THEN
        IF EXISTS (SELECT 1 FROM funcionarios WHERE emp_ci = cod) THEN
            RAISE EXCEPTION 'Ya existe un funcionario con la C.I. %', cod;
        END IF;
        INSERT INTO funcionarios (emp_ci, nac_cod, car_cod, suc_cod, emp_nom, emp_ape, emp_cel, emp_dir, emp_fnac, date_ing, date_egr)
        VALUES (cod, p_nac, p_car, p_suc, upper(trim(nom)), upper(trim(ape)), cel, upper(dir), fnac, coalesce(ing, current_date), egr);
    ELSIF ban = 2 THEN
        UPDATE funcionarios SET nac_cod = p_nac, car_cod = p_car, suc_cod = p_suc, emp_nom = upper(trim(nom)),
               emp_ape = upper(trim(ape)), emp_cel = cel, emp_dir = upper(dir), emp_fnac = fnac,
               date_ing = coalesce(ing, date_ing), date_egr = egr
         WHERE emp_ci = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    IF fnac IS NOT NULL AND fnac > current_date - interval '16 years' THEN
        RAISE EXCEPTION 'La fecha de nacimiento no es valida';
    END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_usr(ban integer, cod integer, des varchar, pass varchar, p_emp integer) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('usuarios', 'usr_cod', ban, cod) THEN RETURN cod; END IF;
    IF NOT coalesce((SELECT car_user FROM v_func WHERE emp_ci = p_emp AND est_cod = 'VIG'), false) THEN
        RAISE EXCEPTION 'El cargo del funcionario no permite tener usuario de sistema';
    END IF;
    IF pass IS NOT NULL AND length(pass) < 6 THEN
        RAISE EXCEPTION 'La contrasena debe tener al menos 6 caracteres';
    END IF;
    IF ban = 1 THEN
        IF pass IS NULL THEN RAISE EXCEPTION 'Debe ingresar una contrasena'; END IF;
        cod := fn_next('usuarios', 'usr_cod');
        INSERT INTO usuarios VALUES (cod, upper(trim(des)), crypt(pass, gen_salt('bf', 8)), p_emp);
    ELSIF ban = 2 THEN
        UPDATE usuarios SET usr_des = upper(trim(des)), emp_ci = p_emp,
               usr_pass = CASE WHEN pass IS NULL THEN usr_pass ELSE crypt(pass, gen_salt('bf', 8)) END
         WHERE usr_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION fn_cambiar_pass(p_usr integer, p_actual varchar, p_nueva varchar) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF length(p_nueva) < 6 THEN RAISE EXCEPTION 'La contrasena debe tener al menos 6 caracteres'; END IF;
    UPDATE usuarios SET usr_pass = crypt(p_nueva, gen_salt('bf', 8))
     WHERE usr_cod = p_usr AND usr_pass = crypt(p_actual, usr_pass);
    IF NOT FOUND THEN RAISE EXCEPTION 'La contrasena actual es incorrecta'; END IF;
    RETURN p_usr;
END $$;

CREATE FUNCTION abm_prov(ban integer, cod integer, des varchar, ruc varchar, dir varchar, tel varchar, mail varchar) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('proveedores', 'prov_cod', ban, cod) THEN RETURN cod; END IF;
    IF ruc !~ '^[0-9]+-[0-9]$' THEN RAISE EXCEPTION 'El RUC debe tener el formato 1234567-8'; END IF;
    IF ban = 1 THEN
        cod := fn_next('proveedores', 'prov_cod');
        INSERT INTO proveedores VALUES (cod, upper(trim(des)), ruc, upper(dir), tel, lower(mail));
    ELSIF ban = 2 THEN
        UPDATE proveedores SET prov_des = upper(trim(des)), prov_ruc = ruc, prov_dir = upper(dir),
               prov_tel = tel, prov_mail = lower(mail) WHERE prov_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_mat(ban integer, cod integer, un varchar, des varchar, min numeric, precio numeric) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('materiales', 'mat_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('materiales', 'mat_cod');
        INSERT INTO materiales VALUES (cod, un, upper(trim(des)), coalesce(min, 0), coalesce(precio, 0));
    ELSIF ban = 2 THEN
        UPDATE materiales SET un_cod = un, mat_des = upper(trim(des)), mat_min = coalesce(min, 0),
               mat_precio = coalesce(precio, 0) WHERE mat_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_mot(ban integer, cod integer, des varchar, tmot integer) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('motivos_ajuste', 'mot_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('motivos_ajuste', 'mot_cod');
        INSERT INTO motivos_ajuste VALUES (cod, tmot, upper(trim(des)));
    ELSIF ban = 2 THEN
        UPDATE motivos_ajuste SET mot_des = upper(trim(des)), tmot_cod = tmot WHERE mot_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_mod(ban integer, cod integer, p_mar integer, des varchar) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('modelos', 'mod_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('modelos', 'mod_cod');
        INSERT INTO modelos VALUES (cod, p_mar, upper(trim(des)));
    ELSIF ban = 2 THEN
        UPDATE modelos SET mar_cod = p_mar, mod_des = upper(trim(des)) WHERE mod_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_seg(ban integer, cod integer, des varchar, dir varchar, tel varchar, mail varchar) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('seguros', 'seg_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('seguros', 'seg_cod');
        INSERT INTO seguros VALUES (cod, upper(trim(des)), upper(dir), tel, lower(mail));
    ELSIF ban = 2 THEN
        UPDATE seguros SET seg_des = upper(trim(des)), seg_dir = upper(dir), seg_tel = tel, seg_mail = lower(mail)
         WHERE seg_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

-- en asegurados el codigo es la C.I.
CREATE FUNCTION abm_cli(ban integer, cod integer, p_nac integer, p_ciu integer, p_bar integer,
                        nom varchar, ape varchar, cel varchar, dir varchar, fnac date) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('asegurados', 'cli_ci', ban, cod) THEN RETURN cod; END IF;
    IF cod IS NULL OR cod <= 0 THEN RAISE EXCEPTION 'Debe ingresar un Nro. de C.I. valido'; END IF;
    IF p_bar IS NOT NULL AND NOT EXISTS (SELECT 1 FROM barrios WHERE bar_cod = p_bar AND ciu_cod = p_ciu) THEN
        RAISE EXCEPTION 'El barrio no pertenece a la ciudad seleccionada';
    END IF;
    IF ban = 1 THEN
        IF EXISTS (SELECT 1 FROM asegurados WHERE cli_ci = cod) THEN
            RAISE EXCEPTION 'Ya existe un asegurado con la C.I. %', cod;
        END IF;
        INSERT INTO asegurados VALUES (cod, p_nac, p_ciu, p_bar, upper(trim(nom)), upper(trim(ape)), cel, upper(dir), fnac);
    ELSIF ban = 2 THEN
        UPDATE asegurados SET nac_cod = p_nac, ciu_cod = p_ciu, bar_cod = p_bar, cli_nom = upper(trim(nom)),
               cli_ape = upper(trim(ape)), cli_cel = cel, cli_dir = upper(dir), cli_fnac = fnac
         WHERE cli_ci = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_veh(ban integer, cod integer, p_cli integer, p_seg integer, p_mod integer, p_col integer,
                        p_tveh integer, anho integer, chapa varchar, chasis varchar, poliza varchar) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('vehiculos', 'veh_cod', ban, cod) THEN RETURN cod; END IF;
    IF p_seg IS NOT NULL AND coalesce(trim(poliza), '') = '' THEN
        RAISE EXCEPTION 'Un vehiculo asegurado debe tener Nro. de poliza';
    END IF;
    IF ban = 1 THEN
        cod := fn_next('vehiculos', 'veh_cod');
        INSERT INTO vehiculos VALUES (cod, p_cli, p_seg, p_mod, p_col, p_tveh, anho, upper(trim(chapa)), upper(chasis), upper(poliza));
    ELSIF ban = 2 THEN
        UPDATE vehiculos SET cli_ci = p_cli, seg_cod = p_seg, mod_cod = p_mod, col_cod = p_col, tveh_cod = p_tveh,
               veh_anho = anho, veh_chapa = upper(trim(chapa)), veh_chasis = upper(chasis), veh_poliza = upper(poliza)
         WHERE veh_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_pza(ban integer, cod integer, des varchar, precio numeric) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('piezas', 'pie_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('piezas', 'pie_cod');
        INSERT INTO piezas VALUES (cod, upper(trim(des)), coalesce(precio, 0));
    ELSIF ban = 2 THEN
        UPDATE piezas SET pie_des = upper(trim(des)), pie_precio = coalesce(precio, 0) WHERE pie_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_dam(ban integer, cod integer, des varchar, porc numeric) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('danhos', 'dam_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('danhos', 'dam_cod');
        INSERT INTO danhos VALUES (cod, upper(trim(des)), porc);
    ELSIF ban = 2 THEN
        UPDATE danhos SET dam_des = upper(trim(des)), dam_porc = porc WHERE dam_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_trab(ban integer, cod integer, des varchar, precio numeric) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('trabajos', 'trab_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('trabajos', 'trab_cod');
        INSERT INTO trabajos VALUES (cod, upper(trim(des)), coalesce(precio, 0));
    ELSIF ban = 2 THEN
        UPDATE trabajos SET trab_des = upper(trim(des)), trab_precio = coalesce(precio, 0) WHERE trab_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_caj(ban integer, cod integer, p_suc integer, des varchar) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF ban = 3 AND EXISTS (SELECT 1 FROM aperturas_cierres WHERE caj_cod = cod AND est_cod = 'ABI') THEN
        RAISE EXCEPTION 'No se puede anular una caja abierta';
    END IF;
    IF fn_ref_estado('cajas', 'caj_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('cajas', 'caj_cod');
        INSERT INTO cajas VALUES (cod, p_suc, upper(trim(des)));
    ELSIF ban = 2 THEN
        UPDATE cajas SET suc_cod = p_suc, caj_des = upper(trim(des)) WHERE caj_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_imp(ban integer, cod integer, des varchar, porc numeric) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('impuestos', 'imp_cod', ban, cod) THEN RETURN cod; END IF;
    IF ban = 1 THEN
        cod := fn_next('impuestos', 'imp_cod');
        INSERT INTO impuestos VALUES (cod, upper(trim(des)), porc);
    ELSIF ban = 2 THEN
        UPDATE impuestos SET imp_des = upper(trim(des)), imp_porc = porc WHERE imp_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

CREATE FUNCTION abm_tim(ban integer, cod integer, nro integer, ini date, vto date, est varchar, pexp varchar) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF fn_ref_estado('timbrados', 'tim_cod', ban, cod) THEN RETURN cod; END IF;
    IF vto <= ini THEN RAISE EXCEPTION 'La fecha de vencimiento debe ser posterior a la de inicio'; END IF;
    IF ban = 1 THEN
        cod := fn_next('timbrados', 'tim_cod');
        INSERT INTO timbrados (tim_cod, tim_nro, tim_ini, tim_vto, tim_est, tim_pexp)
        VALUES (cod, nro, ini, vto, lpad(est, 3, '0'), lpad(pexp, 3, '0'));
    ELSIF ban = 2 THEN
        IF (SELECT tim_ult FROM timbrados WHERE tim_cod = cod) > 0 THEN
            RAISE EXCEPTION 'No se puede modificar un timbrado con facturas emitidas';
        END IF;
        UPDATE timbrados SET tim_nro = nro, tim_ini = ini, tim_vto = vto,
               tim_est = lpad(est, 3, '0'), tim_pexp = lpad(pexp, 3, '0') WHERE tim_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

-- =====================================================================
--  MOVIMIENTOS
--  Firma uniforme: abm_x(ban, cod, emp_ci, suc_cod, cab jsonb, det jsonb)
--  emp_ci / suc_cod son los de la sesion (antes Login.empCi / Login.sucCod)
-- =====================================================================

-- ------------------------------ PEDIDOS ------------------------------
-- 1 agregar, 3 anular, 4 aprobar/rechazar detalles (det: mat_cod, cant, est)
CREATE FUNCTION abm_ped(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE r record; v_est varchar;
BEGIN
    IF ban = 1 THEN
        IF jsonb_array_length(coalesce(det, '[]')) = 0 THEN
            RAISE EXCEPTION 'No se puede guardar un Pedido sin detalles';
        END IF;
        cod := fn_next('pedidos', 'ped_cod');
        INSERT INTO pedidos (ped_cod, suc_cod, emp_ci) VALUES (cod, p_suc, p_emp);
        INSERT INTO det_pedidos (ped_cod, mat_cod, det_cant)
        SELECT cod, (x->>'mat_cod')::int, (x->>'cant')::numeric FROM jsonb_array_elements(det) x;
    ELSE
        SELECT * INTO r FROM pedidos WHERE ped_cod = cod FOR UPDATE;
        IF NOT FOUND THEN RAISE EXCEPTION 'El pedido % no existe', cod; END IF;
        IF ban = 3 THEN
            IF r.est_cod NOT IN ('VIG', 'APR') THEN
                RAISE EXCEPTION 'Solo se pueden anular pedidos Vigentes o Aprobados sin ordenes de compra';
            END IF;
            UPDATE pedidos SET est_cod = 'ANU' WHERE ped_cod = cod;
            UPDATE det_pedidos SET est_cod = 'ANU' WHERE ped_cod = cod;
        ELSIF ban = 4 THEN
            IF r.est_cod <> 'VIG' THEN RAISE EXCEPTION 'Solo se pueden aprobar pedidos Vigentes'; END IF;
            IF (SELECT car_niv FROM v_func WHERE emp_ci = p_emp) > 3 THEN
                RAISE EXCEPTION 'No posee los permisos necesarios para aprobar pedidos';
            END IF;
            UPDATE det_pedidos d SET est_cod = x->>'est', det_cant = (x->>'cant')::numeric
              FROM jsonb_array_elements(det) x
             WHERE d.ped_cod = cod AND d.mat_cod = (x->>'mat_cod')::int;
            v_est := CASE WHEN EXISTS (SELECT 1 FROM det_pedidos WHERE ped_cod = cod AND est_cod = 'APR')
                          THEN 'APR' ELSE 'RCH' END;
            IF EXISTS (SELECT 1 FROM det_pedidos WHERE ped_cod = cod AND est_cod NOT IN ('APR', 'RCH')) THEN
                RAISE EXCEPTION 'Debe aprobar o rechazar todos los detalles';
            END IF;
            UPDATE pedidos SET est_cod = v_est, emp_apr = p_emp WHERE ped_cod = cod;
        ELSE PERFORM fn_ban_invalida(ban); END IF;
    END IF;
    RETURN cod;
END $$;

-- recalcula el estado del pedido segun lo ordenado
CREATE FUNCTION fn_estado_pedido(p_ped integer) RETURNS void
LANGUAGE plpgsql AS $$
BEGIN
    UPDATE pedidos SET est_cod =
        CASE WHEN NOT EXISTS (SELECT 1 FROM det_pedidos WHERE ped_cod = p_ped AND est_cod = 'APR' AND det_ord) THEN 'APR'
             WHEN EXISTS (SELECT 1 FROM det_pedidos WHERE ped_cod = p_ped AND est_cod = 'APR' AND NOT det_ord) THEN 'ORD.P'
             ELSE 'ORD.T' END
     WHERE ped_cod = p_ped;
END $$;

-- -------------------------- ORDEN DE COMPRA --------------------------
-- cab: ped_cod, prov_cod, tip_cod   det: mat_cod, cant, precio
CREATE FUNCTION abm_ordc(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE r record; v_ped integer := (cab->>'ped_cod')::int; x jsonb;
BEGIN
    IF ban = 1 THEN
        IF (SELECT est_cod FROM pedidos WHERE ped_cod = v_ped FOR UPDATE) NOT IN ('APR', 'ORD.P') THEN
            RAISE EXCEPTION 'El pedido % no esta Aprobado o ya fue ordenado en su totalidad', v_ped;
        END IF;
        IF jsonb_array_length(coalesce(det, '[]')) = 0 THEN
            RAISE EXCEPTION 'No se puede guardar una Orden sin detalles';
        END IF;
        cod := fn_next('ordenes_compra', 'ord_nro');
        INSERT INTO ordenes_compra (ord_nro, ped_cod, prov_cod, suc_cod, tip_cod, emp_ci)
        VALUES (cod, v_ped, (cab->>'prov_cod')::int, (SELECT suc_cod FROM pedidos WHERE ped_cod = v_ped),
                (cab->>'tip_cod')::int, p_emp);
        FOR x IN SELECT * FROM jsonb_array_elements(det) LOOP
            UPDATE det_pedidos SET det_ord = true
             WHERE ped_cod = v_ped AND mat_cod = (x->>'mat_cod')::int AND est_cod = 'APR' AND NOT det_ord;
            IF NOT FOUND THEN
                RAISE EXCEPTION 'El material % no esta aprobado en el pedido o ya fue ordenado', x->>'mat_cod';
            END IF;
            INSERT INTO det_ordenes VALUES (cod, (x->>'mat_cod')::int, (x->>'cant')::numeric, (x->>'precio')::numeric);
        END LOOP;
        PERFORM fn_estado_pedido(v_ped);
    ELSIF ban = 3 THEN
        SELECT * INTO r FROM ordenes_compra WHERE ord_nro = cod FOR UPDATE;
        IF r.est_cod <> 'VIG' THEN RAISE EXCEPTION 'Solo se pueden anular ordenes Vigentes sin factura'; END IF;
        UPDATE ordenes_compra SET est_cod = 'ANU' WHERE ord_nro = cod;
        UPDATE det_pedidos d SET det_ord = false FROM det_ordenes o
         WHERE o.ord_nro = cod AND d.ped_cod = r.ped_cod AND d.mat_cod = o.mat_cod;
        PERFORM fn_estado_pedido(r.ped_cod);
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

-- ------------------------- FACTURA DE COMPRA -------------------------
-- cab: ord_nro, com_nrofact, com_date   det: mat_cod, dep_cod, cant, precio
CREATE FUNCTION abm_com(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE o record; r record; x jsonb;
BEGIN
    IF ban = 1 THEN
        SELECT * INTO o FROM ordenes_compra WHERE ord_nro = (cab->>'ord_nro')::int FOR UPDATE;
        IF NOT FOUND OR o.est_cod <> 'VIG' THEN
            RAISE EXCEPTION 'La orden de compra no existe o no esta Vigente';
        END IF;
        IF jsonb_array_length(coalesce(det, '[]')) = 0 THEN
            RAISE EXCEPTION 'No se puede guardar una Factura sin detalles';
        END IF;
        IF coalesce(cab->>'com_nrofact', '') !~ '^[0-9]{3}-[0-9]{3}-[0-9]{7}$' THEN
            RAISE EXCEPTION 'El Nro. de factura debe tener el formato 001-001-0000001';
        END IF;
        cod := fn_next('compras', 'com_cod');
        INSERT INTO compras (com_cod, ord_nro, prov_cod, suc_cod, tip_cod, com_nrofact, com_date, emp_ci)
        VALUES (cod, o.ord_nro, o.prov_cod, o.suc_cod, o.tip_cod, cab->>'com_nrofact',
                coalesce((cab->>'com_date')::date, current_date), p_emp);
        FOR x IN SELECT * FROM jsonb_array_elements(det) LOOP
            IF NOT EXISTS (SELECT 1 FROM depositos WHERE dep_cod = (x->>'dep_cod')::int AND suc_cod = o.suc_cod) THEN
                RAISE EXCEPTION 'El deposito no pertenece a la sucursal de la orden';
            END IF;
            INSERT INTO det_compras VALUES (cod, (x->>'mat_cod')::int, (x->>'dep_cod')::int,
                                            (x->>'cant')::numeric, (x->>'precio')::numeric);
            PERFORM fn_stock((x->>'mat_cod')::int, (x->>'dep_cod')::int, (x->>'cant')::numeric);
        END LOOP;
        UPDATE ordenes_compra SET est_cod = 'COM' WHERE ord_nro = o.ord_nro;
    ELSIF ban = 3 THEN
        SELECT * INTO r FROM compras WHERE com_cod = cod FOR UPDATE;
        IF r.est_cod <> 'VIG' THEN RAISE EXCEPTION 'La factura ya esta anulada'; END IF;
        FOR x IN SELECT to_jsonb(d) FROM det_compras d WHERE com_cod = cod LOOP
            PERFORM fn_stock((x->>'mat_cod')::int, (x->>'dep_cod')::int, -(x->>'com_cant')::numeric);
        END LOOP;
        UPDATE compras SET est_cod = 'ANU' WHERE com_cod = cod;
        UPDATE ordenes_compra SET est_cod = 'VIG' WHERE ord_nro = r.ord_nro;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

-- ------------------------------ AJUSTES ------------------------------
-- det: mat_cod, dep_cod, mot_cod, cant
CREATE FUNCTION abm_aju(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE x record;
BEGIN
    IF ban = 1 THEN
        IF jsonb_array_length(coalesce(det, '[]')) = 0 THEN
            RAISE EXCEPTION 'No se puede guardar un Ajuste sin detalles';
        END IF;
        cod := fn_next('ajustes', 'aju_cod');
        INSERT INTO ajustes (aju_cod, suc_cod, emp_ci) VALUES (cod, p_suc, p_emp);
        INSERT INTO det_ajustes
        SELECT cod, (j->>'mat_cod')::int, (j->>'dep_cod')::int, (j->>'mot_cod')::int, (j->>'cant')::numeric
          FROM jsonb_array_elements(det) j;
        FOR x IN SELECT * FROM v_dajum WHERE aju_cod = cod LOOP
            PERFORM fn_stock(x.mat_cod, x.dep_cod, x.cant * x.tmot_signo);
        END LOOP;
    ELSIF ban = 3 THEN
        UPDATE ajustes SET est_cod = 'ANU' WHERE aju_cod = cod AND est_cod = 'VIG';
        IF NOT FOUND THEN RAISE EXCEPTION 'El ajuste no existe o ya esta anulado'; END IF;
        FOR x IN SELECT * FROM v_dajum WHERE aju_cod = cod LOOP
            PERFORM fn_stock(x.mat_cod, x.dep_cod, -x.cant * x.tmot_signo);
        END LOOP;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

-- ----------------------------- REMISIONES ----------------------------
-- cab: suc_d, emp_resp   det: mat_cod, dep_o, dep_d, cant
-- 1 enviar (sale del origen, queda "no disponible" en destino), 4 recibir, 3 anular
CREATE FUNCTION abm_rem(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE r record; x record;
BEGIN
    IF ban = 1 THEN
        IF jsonb_array_length(coalesce(det, '[]')) = 0 THEN
            RAISE EXCEPTION 'No se puede guardar una Remision sin detalles';
        END IF;
        cod := fn_next('remisiones', 'rem_cod');
        INSERT INTO remisiones (rem_cod, suc_o, suc_d, emp_ci, emp_resp)
        VALUES (cod, p_suc, (cab->>'suc_d')::int, p_emp, (cab->>'emp_resp')::int);
        INSERT INTO det_remisiones
        SELECT cod, (j->>'mat_cod')::int, (j->>'dep_o')::int, (j->>'dep_d')::int, (j->>'cant')::numeric
          FROM jsonb_array_elements(det) j;
        FOR x IN SELECT d.*, o.suc_cod AS so, de.suc_cod AS sd FROM det_remisiones d
                   JOIN depositos o ON o.dep_cod = d.dep_o JOIN depositos de ON de.dep_cod = d.dep_d
                  WHERE rem_cod = cod LOOP
            IF x.so <> p_suc OR x.sd <> (cab->>'suc_d')::int THEN
                RAISE EXCEPTION 'Los depositos no corresponden a las sucursales de origen y destino';
            END IF;
            PERFORM fn_stock(x.mat_cod, x.dep_o, -x.det_cant);
            PERFORM fn_stock(x.mat_cod, x.dep_d, 0, x.det_cant);
        END LOOP;
    ELSE
        SELECT * INTO r FROM remisiones WHERE rem_cod = cod FOR UPDATE;
        IF r.est_cod <> 'VIG' THEN RAISE EXCEPTION 'La remision no esta En Transito'; END IF;
        IF ban = 4 THEN
            IF r.suc_d <> p_suc THEN RAISE EXCEPTION 'Solo la sucursal de destino puede recibir la remision'; END IF;
            FOR x IN SELECT * FROM det_remisiones WHERE rem_cod = cod LOOP
                PERFORM fn_stock(x.mat_cod, x.dep_d, x.det_cant, -x.det_cant);
            END LOOP;
            UPDATE remisiones SET est_cod = 'REC' WHERE rem_cod = cod;
        ELSIF ban = 3 THEN
            FOR x IN SELECT * FROM det_remisiones WHERE rem_cod = cod LOOP
                PERFORM fn_stock(x.mat_cod, x.dep_o, x.det_cant);
                PERFORM fn_stock(x.mat_cod, x.dep_d, 0, -x.det_cant);
            END LOOP;
            UPDATE remisiones SET est_cod = 'ANU' WHERE rem_cod = cod;
        ELSE PERFORM fn_ban_invalida(ban); END IF;
    END IF;
    RETURN cod;
END $$;

-- ----------------------------- RECEPCION -----------------------------
-- cab: veh_cod, comb_cod, ent_km, ent_sin, ent_obs, ent_key..ent_ext
-- det: pie_cod, dam_cod      2 editar (solo REC), 3 anular, 4 entregar
CREATE FUNCTION abm_ent(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE v record; r record;
BEGIN
    IF ban IN (1, 2) THEN
        SELECT * INTO v FROM vehiculos WHERE veh_cod = (cab->>'veh_cod')::int AND est_cod = 'VIG';
        IF NOT FOUND THEN RAISE EXCEPTION 'Debe seleccionar un vehiculo vigente'; END IF;
        IF EXISTS (SELECT 1 FROM recepciones WHERE veh_cod = v.veh_cod AND est_cod NOT IN ('ENT', 'ANU')
                      AND ent_cod IS DISTINCT FROM cod) THEN
            RAISE EXCEPTION 'El vehiculo ya se encuentra en el taller';
        END IF;
        IF jsonb_array_length(coalesce(det, '[]')) = 0 THEN
            RAISE EXCEPTION 'Debe registrar al menos una pieza danada';
        END IF;
    END IF;
    IF ban = 1 THEN
        cod := fn_next('recepciones', 'ent_cod');
        INSERT INTO recepciones (ent_cod, veh_cod, cli_ci, seg_cod, suc_cod, emp_ci, comb_cod, ent_km, ent_sin, ent_obs,
                                 ent_key, ent_cat, ent_bal, ent_aux, ent_taz, ent_her, ent_rad, ent_ext)
        VALUES (cod, v.veh_cod, v.cli_ci, v.seg_cod, p_suc, p_emp, (cab->>'comb_cod')::int, (cab->>'ent_km')::int,
                upper(nullif(trim(cab->>'ent_sin'), '')), upper(cab->>'ent_obs'),
                coalesce((cab->>'ent_key')::boolean, false), coalesce((cab->>'ent_cat')::boolean, false),
                coalesce((cab->>'ent_bal')::boolean, false), coalesce((cab->>'ent_aux')::boolean, false),
                coalesce((cab->>'ent_taz')::boolean, false), coalesce((cab->>'ent_her')::boolean, false),
                coalesce((cab->>'ent_rad')::boolean, false), coalesce((cab->>'ent_ext')::boolean, false));
    ELSE
        SELECT * INTO r FROM recepciones WHERE ent_cod = cod FOR UPDATE;
        IF NOT FOUND THEN RAISE EXCEPTION 'La recepcion % no existe', cod; END IF;
        IF ban = 2 THEN
            IF r.est_cod <> 'REC' THEN RAISE EXCEPTION 'Solo se pueden editar recepciones sin presupuesto'; END IF;
            UPDATE recepciones SET veh_cod = v.veh_cod, cli_ci = v.cli_ci, seg_cod = v.seg_cod,
                   comb_cod = (cab->>'comb_cod')::int, ent_km = (cab->>'ent_km')::int,
                   ent_sin = upper(nullif(trim(cab->>'ent_sin'), '')), ent_obs = upper(cab->>'ent_obs'),
                   ent_key = coalesce((cab->>'ent_key')::boolean, false), ent_cat = coalesce((cab->>'ent_cat')::boolean, false),
                   ent_bal = coalesce((cab->>'ent_bal')::boolean, false), ent_aux = coalesce((cab->>'ent_aux')::boolean, false),
                   ent_taz = coalesce((cab->>'ent_taz')::boolean, false), ent_her = coalesce((cab->>'ent_her')::boolean, false),
                   ent_rad = coalesce((cab->>'ent_rad')::boolean, false), ent_ext = coalesce((cab->>'ent_ext')::boolean, false)
             WHERE ent_cod = cod;
            DELETE FROM det_recepciones WHERE ent_cod = cod;
        ELSIF ban = 3 THEN
            IF r.est_cod <> 'REC' THEN RAISE EXCEPTION 'Solo se pueden anular recepciones sin presupuesto vigente'; END IF;
            UPDATE recepciones SET est_cod = 'ANU' WHERE ent_cod = cod;
            RETURN cod;
        ELSIF ban = 4 THEN
            IF r.est_cod <> 'TER' THEN RAISE EXCEPTION 'El vehiculo aun no tiene el trabajo terminado'; END IF;
            IF NOT EXISTS (SELECT 1 FROM facturas f JOIN ordenes_trabajo o USING (ot_cod)
                             JOIN presupuestos p USING (prep_cod)
                            WHERE p.ent_cod = cod AND f.est_cod <> 'ANU') THEN
                RAISE EXCEPTION 'No se puede entregar el vehiculo sin factura emitida';
            END IF;
            UPDATE recepciones SET est_cod = 'ENT', ent_date_fin = now() WHERE ent_cod = cod;
            UPDATE ordenes_trabajo o SET est_cod = 'ENT' FROM presupuestos p
             WHERE p.prep_cod = o.prep_cod AND p.ent_cod = cod AND o.est_cod = 'TER';
            RETURN cod;
        ELSE PERFORM fn_ban_invalida(ban); END IF;
    END IF;
    INSERT INTO det_recepciones
    SELECT cod, (j->>'pie_cod')::int, (j->>'dam_cod')::int FROM jsonb_array_elements(det) j;
    RETURN cod;
END $$;

-- ---------------------------- PRESUPUESTO ----------------------------
-- cab: ent_cod, prep_vto    det: {piezas:[{pie_cod,dam_cod,precio}], trabajos:[{trab_cod,precio}]}
-- 1 agregar, 2 editar, 3 anular, 4 aprobar, 5 rechazar
CREATE FUNCTION abm_prep(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE r record; v_ent integer := (cab->>'ent_cod')::int;
BEGIN
    IF ban = 1 THEN
        IF (SELECT est_cod FROM recepciones WHERE ent_cod = v_ent FOR UPDATE) IS DISTINCT FROM 'REC' THEN
            RAISE EXCEPTION 'La recepcion no existe o ya tiene un presupuesto vigente';
        END IF;
        cod := fn_next('presupuestos', 'prep_cod');
        INSERT INTO presupuestos (prep_cod, ent_cod, emp_ci, prep_vto)
        VALUES (cod, v_ent, p_emp, coalesce((cab->>'prep_vto')::date, current_date + 15));
        UPDATE recepciones SET est_cod = 'PRE' WHERE ent_cod = v_ent;
    ELSE
        SELECT * INTO r FROM presupuestos WHERE prep_cod = cod FOR UPDATE;
        IF NOT FOUND THEN RAISE EXCEPTION 'El presupuesto % no existe', cod; END IF;
        IF ban = 2 THEN
            IF r.est_cod <> 'VIG' THEN RAISE EXCEPTION 'Solo se pueden editar presupuestos Vigentes'; END IF;
            UPDATE presupuestos SET prep_vto = coalesce((cab->>'prep_vto')::date, prep_vto) WHERE prep_cod = cod;
            DELETE FROM det_prep_piezas WHERE prep_cod = cod;
            DELETE FROM det_prep_trabajos WHERE prep_cod = cod;
        ELSIF ban IN (3, 5) THEN
            IF r.est_cod NOT IN ('VIG', 'APR') THEN
                RAISE EXCEPTION 'Solo se pueden anular/rechazar presupuestos Vigentes o Aprobados sin O.T.';
            END IF;
            UPDATE presupuestos SET est_cod = CASE ban WHEN 3 THEN 'ANU' ELSE 'RCH' END WHERE prep_cod = cod;
            UPDATE recepciones SET est_cod = 'REC' WHERE ent_cod = r.ent_cod;
            RETURN cod;
        ELSIF ban = 4 THEN
            IF r.est_cod <> 'VIG' THEN RAISE EXCEPTION 'Solo se pueden aprobar presupuestos Vigentes'; END IF;
            IF r.prep_vto < current_date THEN RAISE EXCEPTION 'El presupuesto esta vencido'; END IF;
            UPDATE presupuestos SET est_cod = 'APR' WHERE prep_cod = cod;
            RETURN cod;
        ELSE PERFORM fn_ban_invalida(ban); END IF;
    END IF;
    IF jsonb_array_length(coalesce(det->'piezas', '[]')) + jsonb_array_length(coalesce(det->'trabajos', '[]')) = 0 THEN
        RAISE EXCEPTION 'No se puede guardar un Presupuesto sin detalles';
    END IF;
    INSERT INTO det_prep_piezas
    SELECT cod, (j->>'pie_cod')::int, (j->>'dam_cod')::int, (j->>'precio')::numeric
      FROM jsonb_array_elements(coalesce(det->'piezas', '[]')) j;
    INSERT INTO det_prep_trabajos
    SELECT cod, (j->>'trab_cod')::int, (j->>'precio')::numeric
      FROM jsonb_array_elements(coalesce(det->'trabajos', '[]')) j;
    RETURN cod;
END $$;

-- ------------------------- ORDEN DE TRABAJO --------------------------
-- cab: prep_cod, ot_fin, ot_obs    det: pie_cod, emp_ci (asignacion interna)
-- 1 registrar, 2 reasignar (Emitir O.T. Interna), 3 anular
CREATE FUNCTION abm_ot(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE r record; p record;
BEGIN
    IF ban = 1 THEN
        SELECT * INTO p FROM presupuestos WHERE prep_cod = (cab->>'prep_cod')::int FOR UPDATE;
        IF NOT FOUND OR p.est_cod <> 'APR' THEN
            RAISE EXCEPTION 'El presupuesto no existe o no esta Aprobado';
        END IF;
        cod := fn_next('ordenes_trabajo', 'ot_cod');
        INSERT INTO ordenes_trabajo (ot_cod, prep_cod, emp_ci, ot_fin, ot_obs)
        VALUES (cod, p.prep_cod, p_emp, (cab->>'ot_fin')::date, upper(cab->>'ot_obs'));
        UPDATE presupuestos SET est_cod = 'OT' WHERE prep_cod = p.prep_cod;
        UPDATE recepciones SET est_cod = 'TAL' WHERE ent_cod = p.ent_cod;
    ELSE
        SELECT * INTO r FROM ordenes_trabajo WHERE ot_cod = cod FOR UPDATE;
        IF NOT FOUND THEN RAISE EXCEPTION 'La O.T. % no existe', cod; END IF;
        IF r.est_cod <> 'VIG' THEN RAISE EXCEPTION 'La O.T. no esta En Proceso'; END IF;
        IF ban = 2 THEN
            UPDATE ordenes_trabajo SET ot_fin = coalesce((cab->>'ot_fin')::date, ot_fin),
                   ot_obs = coalesce(upper(cab->>'ot_obs'), ot_obs) WHERE ot_cod = cod;
            DELETE FROM det_ot WHERE ot_cod = cod;
        ELSIF ban = 3 THEN
            IF EXISTS (SELECT 1 FROM consumos WHERE ot_cod = cod AND est_cod = 'VIG') THEN
                RAISE EXCEPTION 'No se puede anular una O.T. con materiales consumidos';
            END IF;
            UPDATE ordenes_trabajo SET est_cod = 'ANU' WHERE ot_cod = cod;
            UPDATE presupuestos SET est_cod = 'APR' WHERE prep_cod = r.prep_cod;
            UPDATE recepciones SET est_cod = 'PRE' WHERE ent_cod = (SELECT ent_cod FROM presupuestos WHERE prep_cod = r.prep_cod);
            RETURN cod;
        ELSE PERFORM fn_ban_invalida(ban); END IF;
    END IF;
    INSERT INTO det_ot (ot_cod, pie_cod, emp_ci)
    SELECT cod, (j->>'pie_cod')::int, (j->>'emp_ci')::int FROM jsonb_array_elements(coalesce(det, '[]')) j;
    IF EXISTS (SELECT 1 FROM det_ot d WHERE d.ot_cod = cod AND NOT EXISTS (
                 SELECT 1 FROM det_prep_piezas x JOIN ordenes_trabajo o USING (prep_cod)
                  WHERE o.ot_cod = cod AND x.pie_cod = d.pie_cod)) THEN
        RAISE EXCEPTION 'Solo se pueden asignar piezas incluidas en el presupuesto';
    END IF;
    RETURN cod;
END $$;

-- ------------------------ GESTIONAR MATERIALES -----------------------
-- cab: ot_cod, mat_cod, dep_cod, emp_ret, cant
CREATE FUNCTION abm_con(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE r record;
BEGIN
    IF ban = 1 THEN
        IF (SELECT est_cod FROM ordenes_trabajo WHERE ot_cod = (cab->>'ot_cod')::int) IS DISTINCT FROM 'VIG' THEN
            RAISE EXCEPTION 'La O.T. no existe o no esta En Proceso';
        END IF;
        IF NOT EXISTS (SELECT 1 FROM depositos WHERE dep_cod = (cab->>'dep_cod')::int AND suc_cod = p_suc) THEN
            RAISE EXCEPTION 'El deposito no pertenece a esta sucursal';
        END IF;
        cod := fn_next('consumos', 'con_cod');
        INSERT INTO consumos (con_cod, ot_cod, mat_cod, dep_cod, emp_ci, con_cant)
        VALUES (cod, (cab->>'ot_cod')::int, (cab->>'mat_cod')::int, (cab->>'dep_cod')::int,
                coalesce((cab->>'emp_ret')::int, p_emp), (cab->>'cant')::numeric);
        PERFORM fn_stock((cab->>'mat_cod')::int, (cab->>'dep_cod')::int, -(cab->>'cant')::numeric);
    ELSIF ban = 3 THEN
        SELECT c.*, o.est_cod AS est_ot INTO r FROM consumos c JOIN ordenes_trabajo o USING (ot_cod)
         WHERE con_cod = cod FOR UPDATE;
        IF r.est_cod <> 'VIG' OR r.est_ot <> 'VIG' THEN
            RAISE EXCEPTION 'Solo se pueden anular consumos vigentes de O.T. en proceso';
        END IF;
        UPDATE consumos SET est_cod = 'ANU' WHERE con_cod = cod;
        PERFORM fn_stock(r.mat_cod, r.dep_cod, r.con_cant);
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

-- ------------------------- CONTROL DE CALIDAD ------------------------
-- cab: ot_cod, cc_obs    det: paso_cod, cal (1..5). Aprobado si todos >= 3
CREATE FUNCTION abm_cc(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE r record; v_ot integer := (cab->>'ot_cod')::int; v_apr boolean;
BEGIN
    IF ban = 1 THEN
        IF (SELECT est_cod FROM ordenes_trabajo WHERE ot_cod = v_ot FOR UPDATE) IS DISTINCT FROM 'VIG' THEN
            RAISE EXCEPTION 'La O.T. no existe o no esta En Proceso';
        END IF;
        IF jsonb_array_length(coalesce(det, '[]')) <> (SELECT count(*) FROM pasos_control) THEN
            RAISE EXCEPTION 'Debe calificar todos los pasos de control';
        END IF;
        SELECT bool_and((j->>'cal')::int >= 3) INTO v_apr FROM jsonb_array_elements(det) j;
        cod := fn_next('controles_calidad', 'cc_cod');
        INSERT INTO controles_calidad (cc_cod, ot_cod, emp_ci, cc_obs, cc_apr)
        VALUES (cod, v_ot, p_emp, upper(cab->>'cc_obs'), v_apr);
        INSERT INTO det_controles SELECT cod, (j->>'paso_cod')::int, (j->>'cal')::int FROM jsonb_array_elements(det) j;
        IF v_apr THEN
            UPDATE ordenes_trabajo SET est_cod = 'TER' WHERE ot_cod = v_ot;
            UPDATE det_ot SET est_cod = 'TER' WHERE ot_cod = v_ot;
            UPDATE recepciones SET est_cod = 'TER'
             WHERE ent_cod = (SELECT p.ent_cod FROM presupuestos p JOIN ordenes_trabajo o USING (prep_cod) WHERE o.ot_cod = v_ot);
        END IF;
    ELSIF ban = 3 THEN
        SELECT c.*, o.est_cod AS est_ot INTO r FROM controles_calidad c JOIN ordenes_trabajo o USING (ot_cod)
         WHERE cc_cod = cod FOR UPDATE;
        IF r.est_cod <> 'VIG' THEN RAISE EXCEPTION 'El control ya esta anulado'; END IF;
        IF r.cc_apr THEN
            IF r.est_ot <> 'TER' OR EXISTS (SELECT 1 FROM facturas WHERE ot_cod = r.ot_cod AND est_cod <> 'ANU') THEN
                RAISE EXCEPTION 'No se puede anular: la O.T. ya fue facturada o entregada';
            END IF;
            UPDATE ordenes_trabajo SET est_cod = 'VIG' WHERE ot_cod = r.ot_cod;
            UPDATE det_ot SET est_cod = 'VIG' WHERE ot_cod = r.ot_cod;
            UPDATE recepciones SET est_cod = 'TAL'
             WHERE ent_cod = (SELECT p.ent_cod FROM presupuestos p JOIN ordenes_trabajo o USING (prep_cod) WHERE o.ot_cod = r.ot_cod);
        END IF;
        UPDATE controles_calidad SET est_cod = 'ANU' WHERE cc_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

-- ------------------------------ GARANTIA -----------------------------
-- cab: ot_cod, motg_cod, gar_obs     1 registrar, 3 anular, 4 cerrar (atendida)
CREATE FUNCTION abm_gar(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
BEGIN
    IF ban = 1 THEN
        IF (SELECT est_cod FROM ordenes_trabajo WHERE ot_cod = (cab->>'ot_cod')::int) NOT IN ('TER', 'ENT') THEN
            RAISE EXCEPTION 'Solo se registran garantias de trabajos terminados';
        END IF;
        IF (SELECT ot_date FROM ordenes_trabajo WHERE ot_cod = (cab->>'ot_cod')::int) < now() - interval '6 months' THEN
            RAISE EXCEPTION 'La garantia del trabajo esta vencida (6 meses)';
        END IF;
        cod := fn_next('garantias', 'gar_cod');
        INSERT INTO garantias (gar_cod, ot_cod, motg_cod, emp_ci, gar_obs)
        VALUES (cod, (cab->>'ot_cod')::int, (cab->>'motg_cod')::int, p_emp, upper(cab->>'gar_obs'));
    ELSIF ban IN (3, 4) THEN
        UPDATE garantias SET est_cod = CASE ban WHEN 3 THEN 'ANU' ELSE 'TER' END
         WHERE gar_cod = cod AND est_cod = 'VIG';
        IF NOT FOUND THEN RAISE EXCEPTION 'La garantia no existe o no esta Vigente'; END IF;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

-- ------------------------- APERTURA / CIERRE -------------------------
-- cab: caj_cod, monto     1 abrir, 4 cerrar (monto = arqueo)
CREATE FUNCTION abm_ape(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE r record;
BEGIN
    IF ban = 1 THEN
        IF NOT EXISTS (SELECT 1 FROM cajas WHERE caj_cod = (cab->>'caj_cod')::int AND suc_cod = p_suc AND est_cod = 'VIG') THEN
            RAISE EXCEPTION 'La caja no pertenece a esta sucursal o esta anulada';
        END IF;
        IF EXISTS (SELECT 1 FROM aperturas_cierres WHERE emp_ci = p_emp AND est_cod = 'ABI') THEN
            RAISE EXCEPTION 'Ya tiene una caja abierta';
        END IF;
        IF EXISTS (SELECT 1 FROM aperturas_cierres WHERE caj_cod = (cab->>'caj_cod')::int AND est_cod = 'ABI') THEN
            RAISE EXCEPTION 'La caja ya se encuentra abierta';
        END IF;
        cod := fn_next('aperturas_cierres', 'ape_cod');
        INSERT INTO aperturas_cierres (ape_cod, caj_cod, emp_ci, ape_monto)
        VALUES (cod, (cab->>'caj_cod')::int, p_emp, coalesce((cab->>'monto')::numeric, 0));
    ELSIF ban = 4 THEN
        SELECT * INTO r FROM aperturas_cierres WHERE ape_cod = cod FOR UPDATE;
        IF r.est_cod <> 'ABI' THEN RAISE EXCEPTION 'La caja no esta abierta'; END IF;
        IF r.emp_ci <> p_emp AND (SELECT car_niv FROM v_func WHERE emp_ci = p_emp) > 2 THEN
            RAISE EXCEPTION 'Solo el cajero o un gerente puede cerrar la caja';
        END IF;
        UPDATE aperturas_cierres SET est_cod = 'CER', cie_datetime = now(),
               cie_monto = coalesce((cab->>'monto')::numeric, 0) WHERE ape_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

-- ----------------------------- FACTURACION ---------------------------
-- cab: ot_cod, tip_cod, a_seguro (bool)   Los items se generan del presupuesto y consumos
CREATE FUNCTION abm_fact(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE o record; t record; v_ape integer; v_iva integer;
BEGIN
    IF ban = 1 THEN
        SELECT ape_cod INTO v_ape FROM aperturas_cierres WHERE emp_ci = p_emp AND est_cod = 'ABI';
        IF v_ape IS NULL THEN RAISE EXCEPTION 'Debe abrir una caja antes de facturar'; END IF;
        SELECT * INTO o FROM v_ot WHERE ot_cod = (cab->>'ot_cod')::int;
        IF NOT FOUND OR o.est_cod NOT IN ('TER', 'ENT') THEN
            RAISE EXCEPTION 'Solo se facturan O.T. terminadas (con control de calidad aprobado)';
        END IF;
        IF EXISTS (SELECT 1 FROM facturas WHERE ot_cod = o.ot_cod AND est_cod <> 'ANU') THEN
            RAISE EXCEPTION 'La O.T. % ya fue facturada', o.ot_cod;
        END IF;
        IF coalesce((cab->>'a_seguro')::boolean, false) AND o.seg_cod IS NULL THEN
            RAISE EXCEPTION 'El vehiculo no tiene compania de seguros';
        END IF;
        SELECT * INTO t FROM timbrados WHERE est_cod = 'VIG' AND current_date BETWEEN tim_ini AND tim_vto
         ORDER BY tim_vto LIMIT 1 FOR UPDATE;
        IF NOT FOUND THEN RAISE EXCEPTION 'No hay un timbrado vigente'; END IF;
        SELECT imp_cod INTO v_iva FROM impuestos WHERE imp_porc = 10 AND est_cod = 'VIG' LIMIT 1;
        IF v_iva IS NULL THEN RAISE EXCEPTION 'No existe el impuesto IVA 10%%'; END IF;
        UPDATE timbrados SET tim_ult = tim_ult + 1 WHERE tim_cod = t.tim_cod;
        cod := fn_next('facturas', 'fact_cod');
        INSERT INTO facturas (fact_cod, tim_cod, fact_nro, ape_cod, ot_cod, cli_ci, seg_cod, tip_cod, emp_ci)
        VALUES (cod, t.tim_cod, t.tim_est || '-' || t.tim_pexp || '-' || lpad((t.tim_ult + 1)::text, 7, '0'),
                v_ape, o.ot_cod,
                CASE WHEN coalesce((cab->>'a_seguro')::boolean, false) THEN NULL ELSE o.cli_ci END,
                CASE WHEN coalesce((cab->>'a_seguro')::boolean, false) THEN o.seg_cod END,
                (cab->>'tip_cod')::int, p_emp);
        INSERT INTO det_facturas
        SELECT cod, row_number() OVER (ORDER BY ord, des), des, cant, precio, v_iva FROM (
            SELECT 'REPARACION ' || pie_des || ' (' || dam_des || ')' AS des, 1::numeric AS cant, det_precio AS precio, 1 AS ord
              FROM v_dprep_pie WHERE prep_cod = o.prep_cod
            UNION ALL
            SELECT trab_des, 1, det_precio, 2 FROM v_dprep_trab WHERE prep_cod = o.prep_cod
            UNION ALL
            SELECT 'MATERIAL ' || m.mat_des, sum(c.con_cant), m.mat_precio, 3
              FROM consumos c JOIN materiales m USING (mat_cod)
             WHERE c.ot_cod = o.ot_cod AND c.est_cod = 'VIG' AND m.mat_precio > 0
             GROUP BY m.mat_des, m.mat_precio) x;
    ELSIF ban = 3 THEN
        IF (SELECT est_cod FROM facturas WHERE fact_cod = cod FOR UPDATE) <> 'PEN' THEN
            RAISE EXCEPTION 'Solo se pueden anular facturas Pendientes de cobro (anule primero los cobros)';
        END IF;
        IF EXISTS (SELECT 1 FROM recepciones r JOIN presupuestos p USING (ent_cod) JOIN ordenes_trabajo o USING (prep_cod)
                    JOIN facturas f USING (ot_cod) WHERE f.fact_cod = cod AND r.est_cod = 'ENT') THEN
            RAISE EXCEPTION 'No se puede anular la factura de un vehiculo ya entregado';
        END IF;
        UPDATE facturas SET est_cod = 'ANU' WHERE fact_cod = cod;
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;

-- ------------------------------- COBROS ------------------------------
-- cab: fact_cod    det: fcob_cod, ban_cod, ref, monto
CREATE FUNCTION abm_cob(ban integer, cod integer, p_emp integer, p_suc integer, cab jsonb, det jsonb) RETURNS integer
LANGUAGE plpgsql AS $$
DECLARE f record; v_ape integer; v_total numeric; r record;
BEGIN
    IF ban = 1 THEN
        SELECT ape_cod INTO v_ape FROM aperturas_cierres WHERE emp_ci = p_emp AND est_cod = 'ABI';
        IF v_ape IS NULL THEN RAISE EXCEPTION 'Debe abrir una caja antes de cobrar'; END IF;
        PERFORM 1 FROM facturas WHERE fact_cod = (cab->>'fact_cod')::int FOR UPDATE;
        SELECT * INTO f FROM v_fact WHERE fact_cod = (cab->>'fact_cod')::int;
        IF NOT FOUND OR f.est_cod <> 'PEN' THEN RAISE EXCEPTION 'La factura no existe o no esta Pendiente'; END IF;
        IF jsonb_array_length(coalesce(det, '[]')) = 0 THEN RAISE EXCEPTION 'Debe ingresar al menos una forma de cobro'; END IF;
        IF EXISTS (SELECT 1 FROM jsonb_array_elements(det) j JOIN formas_cobro fc ON fc.fcob_cod = (j->>'fcob_cod')::int
                    WHERE fc.fcob_ban AND (j->>'ban_cod' IS NULL OR coalesce(j->>'ref', '') = '')) THEN
            RAISE EXCEPTION 'Los cobros con cheque/tarjeta/transferencia requieren banco y Nro. de referencia';
        END IF;
        SELECT sum((j->>'monto')::numeric) INTO v_total FROM jsonb_array_elements(det) j;
        IF v_total > f.total - f.cobrado THEN
            RAISE EXCEPTION 'El monto cobrado (%) supera el saldo de la factura (%)', v_total, f.total - f.cobrado;
        END IF;
        cod := fn_next('cobros', 'cob_cod');
        INSERT INTO cobros (cob_cod, fact_cod, ape_cod, emp_ci) VALUES (cod, f.fact_cod, v_ape, p_emp);
        INSERT INTO det_cobros
        SELECT cod, row_number() OVER (), (j->>'fcob_cod')::int, (j->>'ban_cod')::int,
               upper(nullif(j->>'ref', '')), (j->>'monto')::numeric
          FROM jsonb_array_elements(det) j;
        IF v_total = f.total - f.cobrado THEN
            UPDATE facturas SET est_cod = 'COB' WHERE fact_cod = f.fact_cod;
        END IF;
    ELSIF ban = 3 THEN
        SELECT b.*, a.est_cod AS est_ape INTO r FROM cobros b JOIN aperturas_cierres a USING (ape_cod)
         WHERE cob_cod = cod FOR UPDATE;
        IF r.est_cod <> 'VIG' THEN RAISE EXCEPTION 'El cobro ya esta anulado'; END IF;
        IF r.est_ape <> 'ABI' THEN RAISE EXCEPTION 'No se puede anular un cobro de una caja cerrada'; END IF;
        UPDATE cobros SET est_cod = 'ANU' WHERE cob_cod = cod;
        UPDATE facturas SET est_cod = 'PEN' WHERE fact_cod = r.fact_cod AND est_cod = 'COB';
    ELSE PERFORM fn_ban_invalida(ban); END IF;
    RETURN cod;
END $$;
