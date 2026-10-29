-- =====================================================================
--  VISTAS v_*  (todas exponen est_cod y est_des; el legacy mezclaba
--  est_cod='VIG' con est_des='VIG' y algunas consultas no devolvian nada)
-- =====================================================================

-- ------------------------- referenciales -----------------------------
CREATE VIEW v_nac AS
SELECT n.nac_cod, n.nac_des, n.est_cod, e.est_des
  FROM nacionalidades n JOIN estados e USING (est_cod);

CREATE VIEW v_ciu AS
SELECT c.ciu_cod, c.ciu_des, c.est_cod, e.est_des
  FROM ciudades c JOIN estados e USING (est_cod);

CREATE VIEW v_bar AS
SELECT b.bar_cod, b.bar_des, b.ciu_cod, c.ciu_des, b.est_cod, e.est_des
  FROM barrios b JOIN ciudades c USING (ciu_cod) JOIN estados e ON e.est_cod = b.est_cod;

CREATE VIEW v_suc AS
SELECT s.suc_cod, s.suc_des, s.suc_tel, s.suc_dir, s.suc_mail, s.est_cod, e.est_des
  FROM sucursales s JOIN estados e USING (est_cod);

CREATE VIEW v_dep AS
SELECT d.dep_cod, d.dep_des, d.suc_cod, s.suc_des, d.est_cod, e.est_des
  FROM depositos d JOIN sucursales s USING (suc_cod) JOIN estados e ON e.est_cod = d.est_cod;

CREATE VIEW v_car AS
SELECT c.car_cod, c.car_des, c.car_niv,
       CASE c.car_niv WHEN 1 THEN 'ADMINISTRADOR' WHEN 2 THEN 'GERENTE'
                      WHEN 3 THEN 'ENCARGADO' ELSE 'OPERADOR' END AS niv_des,
       c.car_user, CASE WHEN c.car_user THEN 'SI' ELSE 'NO' END AS car_user_des,
       c.est_cod, e.est_des
  FROM cargos c JOIN estados e USING (est_cod);

CREATE VIEW v_func AS
SELECT f.emp_ci, f.emp_nom, f.emp_ape, f.emp_nom || ' ' || f.emp_ape AS emp,
       f.emp_cel, f.emp_dir, f.emp_fnac, f.date_ing, f.date_egr,
       f.nac_cod, n.nac_des, f.car_cod, c.car_des, c.car_niv, c.car_user,
       f.suc_cod, s.suc_des, f.est_cod, e.est_des
  FROM funcionarios f
  JOIN nacionalidades n USING (nac_cod)
  JOIN cargos c         ON c.car_cod = f.car_cod
  JOIN sucursales s     ON s.suc_cod = f.suc_cod
  JOIN estados e        ON e.est_cod = f.est_cod;

CREATE VIEW v_usr AS
SELECT u.usr_cod, u.usr_des, u.emp_ci, f.emp, f.car_des, f.suc_des, u.est_cod, e.est_des
  FROM usuarios u JOIN v_func f USING (emp_ci) JOIN estados e ON e.est_cod = u.est_cod;

-- login: ya no se compara el hash en el cliente, ver fn_login()
CREATE VIEW v_log AS
SELECT u.usr_cod, u.usr_des, u.usr_pass, u.est_cod AS est_usr,
       f.emp_ci, f.emp, f.est_cod AS est_emp, f.car_cod, f.car_des, f.car_niv,
       f.suc_cod, f.suc_des
  FROM usuarios u JOIN v_func f USING (emp_ci);

CREATE VIEW v_ban AS
SELECT b.ban_cod, b.ban_des, b.est_cod, e.est_des
  FROM bancos b JOIN estados e USING (est_cod);

CREATE VIEW v_prov AS
SELECT p.prov_cod, p.prov_des, p.prov_ruc, p.prov_dir, p.prov_tel, p.prov_mail, p.est_cod, e.est_des
  FROM proveedores p JOIN estados e USING (est_cod);

CREATE VIEW v_un AS
SELECT u.un_cod, u.un_des, u.est_cod, e.est_des
  FROM unidades u JOIN estados e USING (est_cod);

CREATE VIEW v_mat AS
SELECT m.mat_cod, m.mat_des, m.un_cod, u.un_des, m.mat_min, m.mat_precio, m.est_cod, e.est_des
  FROM materiales m JOIN unidades u USING (un_cod) JOIN estados e ON e.est_cod = m.est_cod;

CREATE VIEW v_tmot AS
SELECT tmot_cod, tmot_des, tmot_signo FROM tipos_motivo;

CREATE VIEW v_mot AS
SELECT m.mot_cod, m.mot_des, m.tmot_cod, t.tmot_des, t.tmot_signo, m.est_cod, e.est_des
  FROM motivos_ajuste m JOIN tipos_motivo t USING (tmot_cod) JOIN estados e ON e.est_cod = m.est_cod;

CREATE VIEW v_tpag AS
SELECT t.tip_cod, t.tip_des, t.est_cod, e.est_des
  FROM tipos_pago t JOIN estados e USING (est_cod);

CREATE VIEW v_stock_m AS
SELECT s.mat_cod, m.mat_des, m.un_cod, m.mat_min, s.dep_cod, d.dep_des,
       d.suc_cod, d.suc_des, s.cant, s.cant_no_disp,
       (s.cant <= m.mat_min) AS bajo_min
  FROM stock s JOIN v_mat m USING (mat_cod) JOIN v_dep d ON d.dep_cod = s.dep_cod;

CREATE VIEW v_mar AS
SELECT m.mar_cod, m.mar_des, m.est_cod, e.est_des
  FROM marcas m JOIN estados e USING (est_cod);

CREATE VIEW v_mod AS
SELECT m.mod_cod, m.mod_des, m.mar_cod, a.mar_des, m.est_cod, e.est_des
  FROM modelos m JOIN marcas a USING (mar_cod) JOIN estados e ON e.est_cod = m.est_cod;

CREATE VIEW v_tveh AS
SELECT t.tveh_cod, t.tveh_des, t.est_cod, e.est_des
  FROM tipos_vehiculo t JOIN estados e USING (est_cod);

CREATE VIEW v_col AS
SELECT c.col_cod, c.col_des, c.est_cod, e.est_des
  FROM colores c JOIN estados e USING (est_cod);

CREATE VIEW v_seg AS
SELECT s.seg_cod, s.seg_des, s.seg_dir, s.seg_tel, s.seg_mail, s.est_cod, e.est_des
  FROM seguros s JOIN estados e USING (est_cod);

CREATE VIEW v_cli AS
SELECT c.cli_ci, c.cli_nom, c.cli_ape, c.cli_nom || ' ' || c.cli_ape AS cli,
       c.cli_cel, c.cli_dir, c.cli_fnac, c.nac_cod, n.nac_des,
       c.ciu_cod, ci.ciu_des, c.bar_cod, b.bar_des, c.est_cod, e.est_des
  FROM asegurados c
  JOIN nacionalidades n USING (nac_cod)
  JOIN ciudades ci      ON ci.ciu_cod = c.ciu_cod
  LEFT JOIN barrios b   ON b.bar_cod = c.bar_cod
  JOIN estados e        ON e.est_cod = c.est_cod;

CREATE VIEW v_veh AS
SELECT v.veh_cod, v.veh_chapa, v.veh_chasis, v.veh_anho, v.veh_poliza,
       v.cli_ci, c.cli, v.seg_cod, coalesce(s.seg_des, 'PARTICULAR') AS seg_des,
       m.mar_cod, m.mar_des, v.mod_cod, m.mod_des, v.col_cod, co.col_des,
       v.tveh_cod, t.tveh_des,
       m.mar_des || ' ' || m.mod_des || ' ' || co.col_des || ' (' || v.veh_chapa || ')' AS veh,
       v.est_cod, e.est_des
  FROM vehiculos v
  JOIN v_cli c          USING (cli_ci)
  LEFT JOIN seguros s   ON s.seg_cod = v.seg_cod
  JOIN v_mod m          ON m.mod_cod = v.mod_cod
  JOIN colores co       ON co.col_cod = v.col_cod
  JOIN tipos_vehiculo t ON t.tveh_cod = v.tveh_cod
  JOIN estados e        ON e.est_cod = v.est_cod;

CREATE VIEW v_pzas AS
SELECT p.pie_cod, p.pie_des, p.pie_precio, p.est_cod, e.est_des
  FROM piezas p JOIN estados e USING (est_cod);

CREATE VIEW v_dam AS
SELECT d.dam_cod, d.dam_des, d.dam_porc, d.est_cod, e.est_des
  FROM danhos d JOIN estados e USING (est_cod);

CREATE VIEW v_trab AS
SELECT t.trab_cod, t.trab_des, t.trab_precio, t.est_cod, e.est_des
  FROM trabajos t JOIN estados e USING (est_cod);

CREATE VIEW v_comb AS
SELECT comb_cod, comb_des FROM combustible;

CREATE VIEW v_motg AS
SELECT m.motg_cod, m.motg_des, m.est_cod, e.est_des
  FROM motivos_garantia m JOIN estados e USING (est_cod);

CREATE VIEW v_caj AS
SELECT c.caj_cod, c.caj_des, c.suc_cod, s.suc_des, c.est_cod, e.est_des
  FROM cajas c JOIN sucursales s USING (suc_cod) JOIN estados e ON e.est_cod = c.est_cod;

CREATE VIEW v_imp AS
SELECT i.imp_cod, i.imp_des, i.imp_porc, i.est_cod, e.est_des
  FROM impuestos i JOIN estados e USING (est_cod);

CREATE VIEW v_tim AS
SELECT t.tim_cod, t.tim_nro, t.tim_ini, t.tim_vto, t.tim_est, t.tim_pexp, t.tim_ult,
       to_char(t.tim_vto, 'DD/MM/YYYY') AS vto_des, t.est_cod, e.est_des
  FROM timbrados t JOIN estados e USING (est_cod);

CREATE VIEW v_fcob AS
SELECT fcob_cod, fcob_des, fcob_ban FROM formas_cobro;

-- --------------------------- compras ---------------------------------
CREATE VIEW v_ped AS
SELECT p.ped_cod, p.suc_cod, s.suc_des, p.ped_datetime,
       to_char(p.ped_datetime, 'DD/MM/YYYY HH24:MI') AS date_,
       p.emp_ci, f.emp, a.emp AS emp_apr, p.est_cod, e.est_des
  FROM pedidos p
  JOIN sucursales s     USING (suc_cod)
  JOIN v_func f         ON f.emp_ci = p.emp_ci
  LEFT JOIN v_func a    ON a.emp_ci = p.emp_apr
  JOIN estados e        ON e.est_cod = p.est_cod;

CREATE VIEW v_dped AS
SELECT d.ped_cod, d.mat_cod, m.mat_des AS mat, m.un_cod, d.det_cant, d.det_ord,
       d.est_cod, e.est_des
  FROM det_pedidos d JOIN materiales m USING (mat_cod) JOIN estados e ON e.est_cod = d.est_cod;

CREATE VIEW v_ordc AS
SELECT o.ord_nro, o.ped_cod, o.prov_cod, p.prov_des AS prov, o.suc_cod, s.suc_des,
       o.tip_cod, t.tip_des, o.ord_date, to_char(o.ord_date, 'DD/MM/YYYY HH24:MI') AS date_,
       o.emp_ci, f.emp,
       (SELECT coalesce(sum(det_cant * det_precio), 0) FROM det_ordenes d WHERE d.ord_nro = o.ord_nro) AS total,
       o.est_cod, e.est_des
  FROM ordenes_compra o
  JOIN proveedores p USING (prov_cod)
  JOIN sucursales s  ON s.suc_cod = o.suc_cod
  JOIN tipos_pago t  ON t.tip_cod = o.tip_cod
  JOIN v_func f      ON f.emp_ci = o.emp_ci
  JOIN estados e     ON e.est_cod = o.est_cod;

CREATE VIEW v_dordc AS
SELECT d.ord_nro, d.mat_cod, m.mat_des AS mat, m.un_cod, d.det_cant, d.det_precio,
       d.det_cant * d.det_precio AS total
  FROM det_ordenes d JOIN materiales m USING (mat_cod);

CREATE VIEW v_com AS
SELECT c.com_cod, c.ord_nro, o.ped_cod, c.prov_cod, p.prov_des AS prov, c.suc_cod, s.suc_des,
       c.tip_cod, t.tip_des, c.com_nrofact, c.com_date, to_char(c.com_date, 'DD/MM/YYYY') AS date_,
       c.emp_ci, f.emp,
       (SELECT coalesce(sum(com_cant * com_precio), 0) FROM det_compras d WHERE d.com_cod = c.com_cod) AS total,
       c.est_cod, e.est_des
  FROM compras c
  JOIN ordenes_compra o ON o.ord_nro = c.ord_nro
  JOIN proveedores p    ON p.prov_cod = c.prov_cod
  JOIN sucursales s     ON s.suc_cod = c.suc_cod
  JOIN tipos_pago t     ON t.tip_cod = c.tip_cod
  JOIN v_func f         ON f.emp_ci = c.emp_ci
  JOIN estados e        ON e.est_cod = c.est_cod;

CREATE VIEW v_dcom AS
SELECT d.com_cod, d.mat_cod, m.mat_des AS mat, d.dep_cod, dp.dep_des AS dep,
       d.com_cant, d.com_precio, d.com_cant * d.com_precio AS total
  FROM det_compras d JOIN materiales m USING (mat_cod) JOIN depositos dp ON dp.dep_cod = d.dep_cod;

CREATE VIEW v_ajum AS
SELECT a.aju_cod, a.suc_cod, s.suc_des, a.aju_date, to_char(a.aju_date, 'DD/MM/YYYY HH24:MI') AS date_,
       a.emp_ci, f.emp, a.est_cod, e.est_des
  FROM ajustes a JOIN sucursales s USING (suc_cod)
  JOIN v_func f ON f.emp_ci = a.emp_ci JOIN estados e ON e.est_cod = a.est_cod;

CREATE VIEW v_dajum AS
SELECT d.aju_cod, d.mat_cod, m.mat_des AS mat, d.dep_cod, dp.dep_des AS dep,
       d.mot_cod, mo.mot_des AS mot, mo.tmot_des, mo.tmot_signo, d.cant
  FROM det_ajustes d
  JOIN materiales m      USING (mat_cod)
  JOIN depositos dp      ON dp.dep_cod = d.dep_cod
  JOIN v_mot mo          ON mo.mot_cod = d.mot_cod;

CREATE VIEW v_rem AS
SELECT r.rem_cod, r.rem_date, to_char(r.rem_date, 'DD/MM/YYYY HH24:MI') AS date_,
       r.suc_o, so.suc_des AS suc_o_des, r.suc_d, sd.suc_des AS suc_d_des,
       r.emp_ci, f.emp, r.emp_resp, fr.emp AS resp, r.est_cod, e.est_des
  FROM remisiones r
  JOIN sucursales so ON so.suc_cod = r.suc_o
  JOIN sucursales sd ON sd.suc_cod = r.suc_d
  JOIN v_func f      ON f.emp_ci = r.emp_ci
  JOIN v_func fr     ON fr.emp_ci = r.emp_resp
  JOIN estados e     ON e.est_cod = r.est_cod;

CREATE VIEW v_drem AS
SELECT d.rem_cod, d.mat_cod, m.mat_des AS mat, d.det_cant,
       d.dep_o, o.dep_des AS dep_o_des, d.dep_d, de.dep_des AS dep_d_des
  FROM det_remisiones d JOIN materiales m USING (mat_cod)
  JOIN depositos o ON o.dep_cod = d.dep_o JOIN depositos de ON de.dep_cod = d.dep_d;

-- --------------------------- servicio --------------------------------
CREATE VIEW v_ent AS
SELECT r.ent_cod, r.ent_date, to_char(r.ent_date, 'DD/MM/YYYY HH24:MI') AS date_,
       r.veh_cod, v.veh, v.veh_chapa, r.cli_ci, c.cli, r.seg_cod, coalesce(s.seg_des, 'PARTICULAR') AS seg_des,
       r.suc_cod, su.suc_des, r.emp_ci, f.emp, r.comb_cod, cb.comb_des, r.ent_km, r.ent_sin, r.ent_obs,
       r.ent_key, r.ent_cat, r.ent_bal, r.ent_aux, r.ent_taz, r.ent_her, r.ent_rad, r.ent_ext,
       r.ent_date_fin, r.est_cod, e.est_des
  FROM recepciones r
  JOIN v_veh v        USING (veh_cod)
  JOIN v_cli c        ON c.cli_ci = r.cli_ci
  LEFT JOIN seguros s ON s.seg_cod = r.seg_cod
  JOIN sucursales su  ON su.suc_cod = r.suc_cod
  JOIN v_func f       ON f.emp_ci = r.emp_ci
  JOIN combustible cb ON cb.comb_cod = r.comb_cod
  JOIN estados e      ON e.est_cod = r.est_cod;

CREATE VIEW v_dent AS
SELECT d.ent_cod, d.pie_cod, p.pie_des, d.dam_cod, dm.dam_des
  FROM det_recepciones d JOIN piezas p USING (pie_cod) JOIN danhos dm ON dm.dam_cod = d.dam_cod;

CREATE VIEW v_prep AS
SELECT p.prep_cod, p.ent_cod, p.prep_date, to_char(p.prep_date, 'DD/MM/YYYY') AS date_, p.prep_vto,
       r.veh_cod, r.veh, r.cli_ci, r.cli, r.seg_cod, r.seg_des, r.ent_sin, r.suc_cod, r.suc_des,
       p.emp_ci, f.emp,
       (SELECT coalesce(sum(det_precio), 0) FROM det_prep_piezas   x WHERE x.prep_cod = p.prep_cod)
     + (SELECT coalesce(sum(det_precio), 0) FROM det_prep_trabajos y WHERE y.prep_cod = p.prep_cod) AS total,
       p.est_cod, e.est_des
  FROM presupuestos p
  JOIN v_ent r   USING (ent_cod)
  JOIN v_func f  ON f.emp_ci = p.emp_ci
  JOIN estados e ON e.est_cod = p.est_cod;

CREATE VIEW v_dprep_pie AS
SELECT d.prep_cod, d.pie_cod, p.pie_des, d.dam_cod, dm.dam_des, d.det_precio
  FROM det_prep_piezas d JOIN piezas p USING (pie_cod) JOIN danhos dm ON dm.dam_cod = d.dam_cod;

CREATE VIEW v_dprep_trab AS
SELECT d.prep_cod, d.trab_cod, t.trab_des, d.det_precio
  FROM det_prep_trabajos d JOIN trabajos t USING (trab_cod);

CREATE VIEW v_ot AS
SELECT o.ot_cod, o.prep_cod, o.ot_date, to_char(o.ot_date, 'DD/MM/YYYY') AS date_, o.ot_fin, o.ot_obs,
       p.ent_cod, p.veh_cod, p.veh, p.cli_ci, p.cli, p.seg_cod, p.seg_des, p.ent_sin,
       p.suc_cod, p.suc_des, p.total, o.emp_ci, f.emp, o.est_cod, e.est_des
  FROM ordenes_trabajo o
  JOIN v_prep p  USING (prep_cod)
  JOIN v_func f  ON f.emp_ci = o.emp_ci
  JOIN estados e ON e.est_cod = o.est_cod;

CREATE VIEW v_dot AS
SELECT d.ot_cod, d.pie_cod, p.pie_des, d.emp_ci, f.emp, f.car_des, d.est_cod, e.est_des
  FROM det_ot d JOIN piezas p USING (pie_cod)
  JOIN v_func f ON f.emp_ci = d.emp_ci JOIN estados e ON e.est_cod = d.est_cod;

CREATE VIEW v_con AS
SELECT c.con_cod, c.ot_cod, c.mat_cod, m.mat_des AS mat, m.un_cod, c.dep_cod, d.dep_des AS dep,
       c.emp_ci, f.emp, c.con_datetime, to_char(c.con_datetime, 'DD/MM/YYYY HH24:MI') AS date_,
       c.con_cant, sum(c.con_cant) FILTER (WHERE c.est_cod = 'VIG')
                     OVER (PARTITION BY c.ot_cod, c.mat_cod ORDER BY c.con_cod) AS acum,
       c.est_cod, e.est_des
  FROM consumos c JOIN materiales m USING (mat_cod)
  JOIN depositos d ON d.dep_cod = c.dep_cod
  JOIN v_func f ON f.emp_ci = c.emp_ci JOIN estados e ON e.est_cod = c.est_cod;

CREATE VIEW v_cc AS
SELECT c.cc_cod, c.ot_cod, o.veh, o.cli, c.cc_date, to_char(c.cc_date, 'DD/MM/YYYY HH24:MI') AS date_,
       c.cc_obs, c.cc_apr, CASE WHEN c.cc_apr THEN 'APROBADO' ELSE 'OBSERVADO' END AS cc_res,
       (SELECT round(avg(det_cal), 1) FROM det_controles d WHERE d.cc_cod = c.cc_cod) AS promedio,
       c.emp_ci, f.emp, o.suc_cod, c.est_cod, e.est_des
  FROM controles_calidad c JOIN v_ot o USING (ot_cod)
  JOIN v_func f ON f.emp_ci = c.emp_ci JOIN estados e ON e.est_cod = c.est_cod;

CREATE VIEW v_dcc AS
SELECT d.cc_cod, d.paso_cod, p.paso_etapa, p.paso_des, d.det_cal
  FROM det_controles d JOIN pasos_control p USING (paso_cod);

CREATE VIEW v_gar AS
SELECT g.gar_cod, g.ot_cod, o.veh, o.cli, g.motg_cod, m.motg_des, g.gar_date,
       to_char(g.gar_date, 'DD/MM/YYYY') AS date_, g.gar_obs, g.emp_ci, f.emp, o.suc_cod,
       g.est_cod, e.est_des
  FROM garantias g JOIN v_ot o USING (ot_cod) JOIN motivos_garantia m ON m.motg_cod = g.motg_cod
  JOIN v_func f ON f.emp_ci = g.emp_ci JOIN estados e ON e.est_cod = g.est_cod;

-- ---------------------------- cobros ---------------------------------
CREATE VIEW v_ape AS
SELECT a.ape_cod, a.caj_cod, c.caj_des, c.suc_cod, c.suc_des, a.emp_ci, f.emp,
       a.ape_datetime, to_char(a.ape_datetime, 'DD/MM/YYYY HH24:MI') AS date_,
       a.ape_monto, a.cie_datetime, to_char(a.cie_datetime, 'DD/MM/YYYY HH24:MI') AS cie_date_,
       a.cie_monto,
       (SELECT coalesce(sum(d.det_monto), 0) FROM cobros b JOIN det_cobros d USING (cob_cod)
         WHERE b.ape_cod = a.ape_cod AND b.est_cod = 'VIG') AS cobrado,
       a.est_cod, e.est_des
  FROM aperturas_cierres a JOIN v_caj c USING (caj_cod)
  JOIN v_func f ON f.emp_ci = a.emp_ci JOIN estados e ON e.est_cod = a.est_cod;

CREATE VIEW v_dfact AS
SELECT d.fact_cod, d.item, d.item_des, d.det_cant, d.det_precio, d.imp_cod, i.imp_des, i.imp_porc,
       d.det_cant * d.det_precio AS total,
       round(d.det_cant * d.det_precio * i.imp_porc / (100 + i.imp_porc)) AS iva
  FROM det_facturas d JOIN impuestos i USING (imp_cod);

CREATE VIEW v_fact AS
SELECT f.fact_cod, f.fact_nro, f.tim_cod, t.tim_nro, f.ape_cod, f.ot_cod, o.veh, o.ent_sin,
       f.cli_ci, f.seg_cod,
       coalesce(s.seg_des, c.cli) AS cliente, coalesce(s.seg_des, 'PARTICULAR') AS seg_des, c.cli,
       f.tip_cod, tp.tip_des, f.fact_date, to_char(f.fact_date, 'DD/MM/YYYY') AS date_,
       f.emp_ci, fu.emp, o.suc_cod, o.suc_des,
       (SELECT coalesce(sum(total), 0) FROM v_dfact d WHERE d.fact_cod = f.fact_cod) AS total,
       (SELECT coalesce(sum(iva), 0)   FROM v_dfact d WHERE d.fact_cod = f.fact_cod) AS iva,
       (SELECT coalesce(sum(d.det_monto), 0) FROM cobros b JOIN det_cobros d USING (cob_cod)
         WHERE b.fact_cod = f.fact_cod AND b.est_cod = 'VIG') AS cobrado,
       f.est_cod, e.est_des
  FROM facturas f
  JOIN timbrados t      USING (tim_cod)
  JOIN v_ot o           ON o.ot_cod = f.ot_cod
  LEFT JOIN seguros s   ON s.seg_cod = f.seg_cod
  LEFT JOIN v_cli c     ON c.cli_ci = f.cli_ci
  JOIN tipos_pago tp    ON tp.tip_cod = f.tip_cod
  JOIN v_func fu        ON fu.emp_ci = f.emp_ci
  JOIN estados e        ON e.est_cod = f.est_cod;

CREATE VIEW v_cob AS
SELECT b.cob_cod, b.fact_cod, f.fact_nro, f.cliente, b.ape_cod, a.caj_des, a.suc_cod,
       b.cob_date, to_char(b.cob_date, 'DD/MM/YYYY HH24:MI') AS date_, b.emp_ci, fu.emp,
       (SELECT coalesce(sum(det_monto), 0) FROM det_cobros d WHERE d.cob_cod = b.cob_cod) AS total,
       b.est_cod, e.est_des
  FROM cobros b JOIN v_fact f USING (fact_cod) JOIN v_ape a ON a.ape_cod = b.ape_cod
  JOIN v_func fu ON fu.emp_ci = b.emp_ci JOIN estados e ON e.est_cod = b.est_cod;

CREATE VIEW v_dcob AS
SELECT d.cob_cod, d.item, d.fcob_cod, f.fcob_des, d.ban_cod, b.ban_des, d.det_ref, d.det_monto
  FROM det_cobros d JOIN formas_cobro f USING (fcob_cod) LEFT JOIN bancos b ON b.ban_cod = d.ban_cod;
