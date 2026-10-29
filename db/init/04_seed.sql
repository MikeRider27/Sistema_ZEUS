-- =====================================================================
--  DATOS INICIALES
-- =====================================================================
INSERT INTO estados VALUES
    ('VIG',   'VIGENTE'),
    ('ANU',   'ANULADO'),
    ('APR',   'APROBADO'),
    ('RCH',   'RECHAZADO'),
    ('ORD.P', 'ORDENADO PARCIAL'),
    ('ORD.T', 'ORDENADO TOTAL'),
    ('COM',   'FACTURADO (COMPRA)'),
    ('REC',   'RECIBIDO'),
    ('PRE',   'PRESUPUESTADO'),
    ('OT',    'CON O.T.'),
    ('TAL',   'EN TALLER'),
    ('TER',   'TERMINADO'),
    ('ENT',   'ENTREGADO'),
    ('ABI',   'ABIERTA'),
    ('CER',   'CERRADA'),
    ('PEN',   'PENDIENTE'),
    ('COB',   'COBRADA');

INSERT INTO nacionalidades (nac_cod, nac_des) VALUES
    (1, 'PARAGUAYA'), (2, 'ARGENTINA'), (3, 'BRASILERA'), (4, 'URUGUAYA'), (5, 'BOLIVIANA');

INSERT INTO ciudades (ciu_cod, ciu_des) VALUES
    (1, 'ASUNCION'), (2, 'SAN LORENZO'), (3, 'LUQUE'), (4, 'FERNANDO DE LA MORA'), (5, 'LAMBARE');

INSERT INTO barrios (bar_cod, ciu_cod, bar_des) VALUES
    (1, 1, 'CENTRO'), (2, 1, 'VILLA MORRA'), (3, 1, 'SAJONIA'), (4, 2, 'BARCEQUILLO'), (5, 3, 'MBOCAYATY');

INSERT INTO sucursales (suc_cod, suc_des, suc_tel, suc_dir, suc_mail) VALUES
    (1, 'CASA CENTRAL', '021 000 000', 'AVDA. MCAL. LOPEZ 1234', 'central@zeus.com.py'),
    (2, 'SUCURSAL SAN LORENZO', '021 111 111', 'RUTA MCAL. ESTIGARRIBIA KM 10', 'sanlorenzo@zeus.com.py');

INSERT INTO depositos (dep_cod, suc_cod, dep_des) VALUES
    (1, 1, 'DEPOSITO PRINCIPAL'), (2, 1, 'PINTURERIA'), (3, 2, 'DEPOSITO SAN LORENZO');

INSERT INTO cargos (car_cod, car_des, car_niv, car_user) VALUES
    (1, 'ADMINISTRADOR DEL SISTEMA', 1, true),
    (2, 'GERENTE', 2, true),
    (3, 'ENCARGADO DE TALLER', 3, true),
    (4, 'CAJERO', 4, true),
    (5, 'CHAPERO', 4, false),
    (6, 'PINTOR', 4, false),
    (7, 'MECANICO', 4, false);

INSERT INTO funcionarios (emp_ci, nac_cod, car_cod, suc_cod, emp_nom, emp_ape, emp_cel, date_ing) VALUES
    (1000000, 1, 1, 1, 'ADMINISTRADOR', 'DEL SISTEMA', NULL, current_date),
    (1234567, 1, 3, 1, 'JUAN', 'BENITEZ', '0981 000 001', current_date),
    (2345678, 1, 5, 1, 'CARLOS', 'GONZALEZ', '0981 000 002', current_date),
    (3456789, 1, 6, 1, 'PEDRO', 'RAMIREZ', '0981 000 003', current_date),
    (4567890, 1, 4, 1, 'MARIA', 'LOPEZ', '0981 000 004', current_date);

INSERT INTO bancos (ban_cod, ban_des) VALUES
    (1, 'BANCO NACIONAL DE FOMENTO'), (2, 'BANCO CONTINENTAL'), (3, 'BANCO ITAU'), (4, 'BANCO BASA');

INSERT INTO proveedores (prov_cod, prov_des, prov_ruc, prov_dir, prov_tel, prov_mail) VALUES
    (1, 'PINTURAS DEL SUR S.A.', '80012345-6', 'AVDA. ARTIGAS 555', '021 222 333', 'ventas@pinturasdelsur.com.py'),
    (2, '3M PARAGUAY S.A.', '80054321-0', 'AVDA. EUSEBIO AYALA 2000', '021 444 555', 'ventas@3m.com.py');

INSERT INTO unidades (un_cod, un_des) VALUES
    ('UN', 'UNIDAD'), ('LT', 'LITRO'), ('GL', 'GALON'), ('KG', 'KILOGRAMO'), ('MT', 'METRO'), ('PLG', 'PLIEGO');

INSERT INTO materiales (mat_cod, un_cod, mat_des, mat_min, mat_precio) VALUES
    (1, 'LT',  'PINTURA POLIURETANO BLANCO', 5, 120000),
    (2, 'LT',  'PINTURA POLIURETANO NEGRO', 5, 120000),
    (3, 'LT',  'THINNER', 10, 25000),
    (4, 'KG',  'MASILLA PLASTICA', 3, 45000),
    (5, 'PLG', 'LIJA AL AGUA 400', 20, 5000),
    (6, 'PLG', 'LIJA AL AGUA 1200', 20, 6000),
    (7, 'UN',  'CINTA DE ENMASCARAR', 10, 15000),
    (8, 'LT',  'BARNIZ TRANSPARENTE', 4, 150000);

INSERT INTO tipos_motivo VALUES (1, 'NEGATIVO (SALIDA)', -1), (2, 'POSITIVO (ENTRADA)', 1);

INSERT INTO motivos_ajuste (mot_cod, tmot_cod, mot_des) VALUES
    (1, 1, 'ROTURA / DETERIORO'), (2, 1, 'VENCIMIENTO'), (3, 1, 'FALTANTE EN INVENTARIO'),
    (4, 2, 'SOBRANTE EN INVENTARIO'), (5, 2, 'CARGA INICIAL');

INSERT INTO tipos_pago (tip_cod, tip_des) VALUES (1, 'CONTADO'), (2, 'CREDITO');

INSERT INTO marcas (mar_cod, mar_des) VALUES
    (1, 'TOYOTA'), (2, 'NISSAN'), (3, 'CHEVROLET'), (4, 'VOLKSWAGEN'), (5, 'HYUNDAI'), (6, 'KIA');

INSERT INTO modelos (mod_cod, mar_cod, mod_des) VALUES
    (1, 1, 'COROLLA'), (2, 1, 'HILUX'), (3, 1, 'VITZ'), (4, 2, 'MARCH'), (5, 2, 'FRONTIER'),
    (6, 3, 'ONIX'), (7, 3, 'S10'), (8, 4, 'GOL'), (9, 4, 'AMAROK'), (10, 5, 'TUCSON'), (11, 6, 'PICANTO');

INSERT INTO tipos_vehiculo (tveh_cod, tveh_des) VALUES
    (1, 'SEDAN'), (2, 'HATCHBACK'), (3, 'CAMIONETA'), (4, 'SUV'), (5, 'FURGON');

INSERT INTO colores (col_cod, col_des) VALUES
    (1, 'BLANCO'), (2, 'NEGRO'), (3, 'GRIS'), (4, 'PLATA'), (5, 'ROJO'), (6, 'AZUL');

INSERT INTO seguros (seg_cod, seg_des, seg_dir, seg_tel, seg_mail) VALUES
    (1, 'MAPFRE PARAGUAY', 'AVDA. MCAL. LOPEZ 910', '021 617 2000', 'siniestros@mapfre.com.py'),
    (2, 'LA CONSOLIDADA', 'CHILE 719', '021 619 1000', 'siniestros@consolidada.com.py'),
    (3, 'ASEGURADORA DEL SUR', 'AVDA. MCAL. LOPEZ 2500', '021 211 911', 'siniestros@adelsur.com.py');

INSERT INTO asegurados (cli_ci, nac_cod, ciu_cod, bar_cod, cli_nom, cli_ape, cli_cel, cli_dir) VALUES
    (3333333, 1, 1, 2, 'ANA', 'MARTINEZ', '0982 111 222', 'SENADOR LONG 123'),
    (4444444, 1, 2, 4, 'LUIS', 'FERNANDEZ', '0983 333 444', 'CALLE 10 CASI 12');

INSERT INTO vehiculos (veh_cod, cli_ci, seg_cod, mod_cod, col_cod, tveh_cod, veh_anho, veh_chapa, veh_chasis, veh_poliza) VALUES
    (1, 3333333, 1, 1, 1, 1, 2019, 'ABC 123', '9BR53ZEC1K0000001', 'POL-0001'),
    (2, 4444444, NULL, 8, 5, 2, 2015, 'XYZ 789', '9BWAB45U0FT000002', NULL);

INSERT INTO piezas (pie_cod, pie_des, pie_precio) VALUES
    (1, 'PARAGOLPE DELANTERO', 400000), (2, 'PARAGOLPE TRASERO', 400000), (3, 'CAPOT', 500000),
    (4, 'GUARDABARRO DELANTERO IZQ.', 350000), (5, 'GUARDABARRO DELANTERO DER.', 350000),
    (6, 'PUERTA DELANTERA IZQ.', 450000), (7, 'PUERTA DELANTERA DER.', 450000),
    (8, 'PUERTA TRASERA IZQ.', 450000), (9, 'PUERTA TRASERA DER.', 450000),
    (10, 'TECHO', 700000), (11, 'TAPA DE BAUL', 450000), (12, 'ESPEJO RETROVISOR', 150000);

INSERT INTO danhos (dam_cod, dam_des, dam_porc) VALUES
    (1, 'LEVE', 50), (2, 'MODERADO', 100), (3, 'GRAVE', 150), (4, 'REEMPLAZO', 200);

INSERT INTO trabajos (trab_cod, trab_des, trab_precio) VALUES
    (1, 'PINTURA GENERAL', 3500000), (2, 'PULIDO Y ENCERADO', 300000),
    (3, 'ALINEACION DE CHASIS', 800000), (4, 'DESABOLLADURA SIN PINTURA', 250000);

INSERT INTO combustible VALUES (1, 'VACIO'), (2, '1/4'), (3, '1/2'), (4, '3/4'), (5, 'LLENO');

INSERT INTO motivos_garantia (motg_cod, motg_des) VALUES
    (1, 'DESPRENDIMIENTO DE PINTURA'), (2, 'DIFERENCIA DE TONALIDAD'), (3, 'RUIDOS / AJUSTE DE PIEZAS'), (4, 'FILTRACION DE AGUA');

INSERT INTO pasos_control VALUES
    (1, 'CHAPERIA', 'ALINEACION DE PIEZAS Y LUCES ENTRE PANELES'),
    (2, 'CHAPERIA', 'SUPERFICIE SIN ONDULACIONES'),
    (3, 'PINTURA',  'IGUALACION DE COLOR'),
    (4, 'PINTURA',  'AUSENCIA DE CHORREADOS, POROS O PIEL DE NARANJA'),
    (5, 'TERMINACION', 'PULIDO Y LIMPIEZA GENERAL'),
    (6, 'TERMINACION', 'FUNCIONAMIENTO DE CERRADURAS, LUCES Y ACCESORIOS');

INSERT INTO cajas (caj_cod, suc_cod, caj_des) VALUES (1, 1, 'CAJA 1'), (2, 1, 'CAJA 2'), (3, 2, 'CAJA 1');

INSERT INTO impuestos (imp_cod, imp_des, imp_porc) VALUES (1, 'IVA 10%', 10), (2, 'IVA 5%', 5), (3, 'EXENTA', 0);

INSERT INTO timbrados (tim_cod, tim_nro, tim_ini, tim_vto) VALUES
    (1, 12345678, date_trunc('year', current_date)::date, (date_trunc('year', current_date) + interval '1 year - 1 day')::date);

INSERT INTO formas_cobro VALUES
    (1, 'EFECTIVO', false), (2, 'CHEQUE', true), (3, 'TARJETA DE CREDITO', true),
    (4, 'TARJETA DE DEBITO', true), (5, 'TRANSFERENCIA', true);

-- existencias iniciales de ejemplo
INSERT INTO stock (mat_cod, dep_cod, cant) VALUES
    (1, 2, 20), (2, 2, 15), (3, 2, 40), (4, 1, 10), (5, 1, 100), (6, 1, 80), (7, 1, 30), (8, 2, 3);
