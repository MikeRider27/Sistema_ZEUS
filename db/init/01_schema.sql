-- =====================================================================
--  SISTEMA ZEUS - Esquema de base de datos (PostgreSQL)
--  Reconstruido a partir del legacy (Swing/JDBC). Se mantiene la
--  convencion de nombres original: <prefijo>_cod, <prefijo>_des, est_cod
-- =====================================================================
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- ---------------------------------------------------------------------
--  ESTADOS (antes: literales sueltos 'VIG','ANU','APR','ORD.P'...)
-- ---------------------------------------------------------------------
CREATE TABLE estados (
    est_cod  varchar(6)  PRIMARY KEY,
    est_des  varchar(40) NOT NULL
);

-- ---------------------------------------------------------------------
--  REFERENCIALES GENERALES
-- ---------------------------------------------------------------------
CREATE TABLE nacionalidades (
    nac_cod  integer PRIMARY KEY,
    nac_des  varchar(60) NOT NULL UNIQUE,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE ciudades (
    ciu_cod  integer PRIMARY KEY,
    ciu_des  varchar(60) NOT NULL UNIQUE,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE barrios (
    bar_cod  integer PRIMARY KEY,
    ciu_cod  integer     NOT NULL REFERENCES ciudades,
    bar_des  varchar(60) NOT NULL,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados,
    UNIQUE (ciu_cod, bar_des)
);

CREATE TABLE sucursales (
    suc_cod  integer PRIMARY KEY,
    suc_des  varchar(60)  NOT NULL UNIQUE,
    suc_tel  varchar(30),
    suc_dir  varchar(120),
    suc_mail varchar(80),
    est_cod  varchar(6)   NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE depositos (
    dep_cod  integer PRIMARY KEY,
    suc_cod  integer     NOT NULL REFERENCES sucursales,
    dep_des  varchar(60) NOT NULL,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados,
    UNIQUE (suc_cod, dep_des)
);

-- car_niv: nivel de permiso (1 Administrador, 2 Gerente, 3 Encargado, 4 Operador)
-- (el legacy usaba el car_cod como permiso y la condicion siempre era verdadera)
CREATE TABLE cargos (
    car_cod  integer PRIMARY KEY,
    car_des  varchar(60) NOT NULL UNIQUE,
    car_niv  smallint    NOT NULL DEFAULT 4 CHECK (car_niv BETWEEN 1 AND 4),
    car_user boolean     NOT NULL DEFAULT false,   -- puede tener usuario de sistema
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE funcionarios (
    emp_ci   integer PRIMARY KEY,
    nac_cod  integer     NOT NULL REFERENCES nacionalidades,
    car_cod  integer     NOT NULL REFERENCES cargos,
    suc_cod  integer     NOT NULL REFERENCES sucursales,
    emp_nom  varchar(60) NOT NULL,
    emp_ape  varchar(60) NOT NULL,
    emp_cel  varchar(30),
    emp_dir  varchar(120),
    emp_fnac date,
    date_ing date        NOT NULL DEFAULT current_date,
    date_egr date,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

-- usr_pass: hash bcrypt (pgcrypto). El legacy usaba MD5 sin sal.
CREATE TABLE usuarios (
    usr_cod  integer PRIMARY KEY,
    usr_des  varchar(30) NOT NULL UNIQUE,
    usr_pass varchar(100) NOT NULL,
    emp_ci   integer     NOT NULL UNIQUE REFERENCES funcionarios,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE bancos (
    ban_cod  integer PRIMARY KEY,
    ban_des  varchar(60) NOT NULL UNIQUE,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE proveedores (
    prov_cod  integer PRIMARY KEY,
    prov_des  varchar(80) NOT NULL,
    prov_ruc  varchar(20) NOT NULL UNIQUE,
    prov_dir  varchar(120),
    prov_tel  varchar(30),
    prov_mail varchar(80),
    est_cod   varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

-- ---------------------------------------------------------------------
--  REFERENCIALES MOD. COMPRA
-- ---------------------------------------------------------------------
CREATE TABLE unidades (
    un_cod   varchar(6)  PRIMARY KEY,
    un_des   varchar(40) NOT NULL,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE materiales (
    mat_cod    integer PRIMARY KEY,
    un_cod     varchar(6)    NOT NULL REFERENCES unidades,
    mat_des    varchar(80)   NOT NULL UNIQUE,
    mat_min    numeric(12,2) NOT NULL DEFAULT 0 CHECK (mat_min >= 0),
    mat_precio numeric(14,0) NOT NULL DEFAULT 0 CHECK (mat_precio >= 0),
    est_cod    varchar(6)    NOT NULL DEFAULT 'VIG' REFERENCES estados
);

-- tmot_signo: -1 resta del stock, +1 suma al stock
CREATE TABLE tipos_motivo (
    tmot_cod   integer PRIMARY KEY,
    tmot_des   varchar(30) NOT NULL,
    tmot_signo smallint    NOT NULL CHECK (tmot_signo IN (-1, 1))
);

CREATE TABLE motivos_ajuste (
    mot_cod  integer PRIMARY KEY,
    tmot_cod integer     NOT NULL REFERENCES tipos_motivo,
    mot_des  varchar(60) NOT NULL UNIQUE,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE tipos_pago (
    tip_cod  integer PRIMARY KEY,
    tip_des  varchar(30) NOT NULL UNIQUE,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE stock (
    mat_cod      integer       NOT NULL REFERENCES materiales,
    dep_cod      integer       NOT NULL REFERENCES depositos,
    cant         numeric(12,2) NOT NULL DEFAULT 0 CHECK (cant >= 0),
    cant_no_disp numeric(12,2) NOT NULL DEFAULT 0 CHECK (cant_no_disp >= 0),
    PRIMARY KEY (mat_cod, dep_cod)
);

-- ---------------------------------------------------------------------
--  REFERENCIALES MOD. SERVICIO
-- ---------------------------------------------------------------------
CREATE TABLE marcas (
    mar_cod  integer PRIMARY KEY,
    mar_des  varchar(60) NOT NULL UNIQUE,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE modelos (
    mod_cod  integer PRIMARY KEY,
    mar_cod  integer     NOT NULL REFERENCES marcas,
    mod_des  varchar(60) NOT NULL,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados,
    UNIQUE (mar_cod, mod_des)
);

CREATE TABLE tipos_vehiculo (
    tveh_cod integer PRIMARY KEY,
    tveh_des varchar(40) NOT NULL UNIQUE,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE colores (
    col_cod  integer PRIMARY KEY,
    col_des  varchar(40) NOT NULL UNIQUE,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE seguros (
    seg_cod  integer PRIMARY KEY,
    seg_des  varchar(80)  NOT NULL UNIQUE,
    seg_dir  varchar(120),
    seg_tel  varchar(30),
    seg_mail varchar(80),
    est_cod  varchar(6)   NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE asegurados (
    cli_ci   integer PRIMARY KEY,
    nac_cod  integer     NOT NULL REFERENCES nacionalidades,
    ciu_cod  integer     NOT NULL REFERENCES ciudades,
    bar_cod  integer     REFERENCES barrios,
    cli_nom  varchar(60) NOT NULL,
    cli_ape  varchar(60) NOT NULL,
    cli_cel  varchar(30),
    cli_dir  varchar(120),
    cli_fnac date,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE vehiculos (
    veh_cod    integer PRIMARY KEY,
    cli_ci     integer     NOT NULL REFERENCES asegurados,
    seg_cod    integer     REFERENCES seguros,          -- NULL = particular
    mod_cod    integer     NOT NULL REFERENCES modelos,
    col_cod    integer     NOT NULL REFERENCES colores,
    tveh_cod   integer     NOT NULL REFERENCES tipos_vehiculo,
    veh_anho   smallint    CHECK (veh_anho BETWEEN 1900 AND 2100),
    veh_chapa  varchar(15) NOT NULL UNIQUE,
    veh_chasis varchar(30),
    veh_poliza varchar(30),
    est_cod    varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE piezas (
    pie_cod    integer PRIMARY KEY,
    pie_des    varchar(60)   NOT NULL UNIQUE,
    pie_precio numeric(14,0) NOT NULL DEFAULT 0 CHECK (pie_precio >= 0),  -- mano de obra base
    est_cod    varchar(6)    NOT NULL DEFAULT 'VIG' REFERENCES estados
);

-- dam_porc: factor sobre el precio base de la pieza (LEVE 50%, MEDIO 100%...)
CREATE TABLE danhos (
    dam_cod  integer PRIMARY KEY,
    dam_des  varchar(40)  NOT NULL UNIQUE,
    dam_porc numeric(5,2) NOT NULL DEFAULT 100 CHECK (dam_porc > 0),
    est_cod  varchar(6)   NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE trabajos (
    trab_cod    integer PRIMARY KEY,
    trab_des    varchar(60)   NOT NULL UNIQUE,
    trab_precio numeric(14,0) NOT NULL DEFAULT 0 CHECK (trab_precio >= 0),
    est_cod     varchar(6)    NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE combustible (
    comb_cod integer PRIMARY KEY,
    comb_des varchar(20) NOT NULL
);

CREATE TABLE motivos_garantia (
    motg_cod integer PRIMARY KEY,
    motg_des varchar(60) NOT NULL UNIQUE,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE pasos_control (
    paso_cod integer PRIMARY KEY,
    paso_etapa varchar(30) NOT NULL,
    paso_des   varchar(80) NOT NULL
);

-- ---------------------------------------------------------------------
--  REFERENCIALES MOD. GEST. COBROS
-- ---------------------------------------------------------------------
CREATE TABLE cajas (
    caj_cod  integer PRIMARY KEY,
    suc_cod  integer     NOT NULL REFERENCES sucursales,
    caj_des  varchar(40) NOT NULL,
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados,
    UNIQUE (suc_cod, caj_des)
);

CREATE TABLE impuestos (
    imp_cod  integer PRIMARY KEY,
    imp_des  varchar(30)  NOT NULL UNIQUE,
    imp_porc numeric(5,2) NOT NULL CHECK (imp_porc >= 0),
    est_cod  varchar(6)   NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE timbrados (
    tim_cod  integer PRIMARY KEY,
    tim_nro  integer     NOT NULL UNIQUE,
    tim_ini  date        NOT NULL,
    tim_vto  date        NOT NULL,
    tim_est  char(3)     NOT NULL DEFAULT '001',   -- establecimiento
    tim_pexp char(3)     NOT NULL DEFAULT '001',   -- punto de expedicion
    tim_ult  integer     NOT NULL DEFAULT 0,       -- ultimo numero emitido
    est_cod  varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados,
    CHECK (tim_vto > tim_ini)
);

CREATE TABLE formas_cobro (
    fcob_cod integer PRIMARY KEY,
    fcob_des varchar(30) NOT NULL,
    fcob_ban boolean     NOT NULL DEFAULT false    -- requiere banco/nro de referencia
);

-- =====================================================================
--  MOVIMIENTOS - MOD. COMPRA
-- =====================================================================
CREATE TABLE pedidos (
    ped_cod      integer PRIMARY KEY,
    suc_cod      integer    NOT NULL REFERENCES sucursales,
    emp_ci       integer    NOT NULL REFERENCES funcionarios,
    ped_datetime timestamp  NOT NULL DEFAULT now(),
    emp_apr      integer    REFERENCES funcionarios,
    est_cod      varchar(6) NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE det_pedidos (
    ped_cod  integer       NOT NULL REFERENCES pedidos,
    mat_cod  integer       NOT NULL REFERENCES materiales,
    det_cant numeric(12,2) NOT NULL CHECK (det_cant > 0),
    det_ord  boolean       NOT NULL DEFAULT false,      -- ya incluido en una orden
    est_cod  varchar(6)    NOT NULL DEFAULT 'VIG' REFERENCES estados,
    PRIMARY KEY (ped_cod, mat_cod)
);

CREATE TABLE ordenes_compra (
    ord_nro  integer PRIMARY KEY,
    ped_cod  integer    NOT NULL REFERENCES pedidos,
    prov_cod integer    NOT NULL REFERENCES proveedores,
    suc_cod  integer    NOT NULL REFERENCES sucursales,
    tip_cod  integer    NOT NULL REFERENCES tipos_pago,
    emp_ci   integer    NOT NULL REFERENCES funcionarios,
    ord_date timestamp  NOT NULL DEFAULT now(),
    est_cod  varchar(6) NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE det_ordenes (
    ord_nro    integer       NOT NULL REFERENCES ordenes_compra,
    mat_cod    integer       NOT NULL REFERENCES materiales,
    det_cant   numeric(12,2) NOT NULL CHECK (det_cant > 0),
    det_precio numeric(14,0) NOT NULL CHECK (det_precio >= 0),
    PRIMARY KEY (ord_nro, mat_cod)
);

CREATE TABLE compras (
    com_cod     integer PRIMARY KEY,
    ord_nro     integer     NOT NULL REFERENCES ordenes_compra,
    prov_cod    integer     NOT NULL REFERENCES proveedores,
    suc_cod     integer     NOT NULL REFERENCES sucursales,
    tip_cod     integer     NOT NULL REFERENCES tipos_pago,
    com_nrofact varchar(20) NOT NULL,
    com_date    date        NOT NULL DEFAULT current_date,
    emp_ci      integer     NOT NULL REFERENCES funcionarios,
    est_cod     varchar(6)  NOT NULL DEFAULT 'VIG' REFERENCES estados,
    UNIQUE (prov_cod, com_nrofact)
);

CREATE TABLE det_compras (
    com_cod    integer       NOT NULL REFERENCES compras,
    mat_cod    integer       NOT NULL REFERENCES materiales,
    dep_cod    integer       NOT NULL REFERENCES depositos,
    com_cant   numeric(12,2) NOT NULL CHECK (com_cant > 0),
    com_precio numeric(14,0) NOT NULL CHECK (com_precio >= 0),
    PRIMARY KEY (com_cod, mat_cod, dep_cod)
);

CREATE TABLE ajustes (
    aju_cod  integer PRIMARY KEY,
    suc_cod  integer    NOT NULL REFERENCES sucursales,
    emp_ci   integer    NOT NULL REFERENCES funcionarios,
    aju_date timestamp  NOT NULL DEFAULT now(),
    est_cod  varchar(6) NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE det_ajustes (
    aju_cod  integer       NOT NULL REFERENCES ajustes,
    mat_cod  integer       NOT NULL REFERENCES materiales,
    dep_cod  integer       NOT NULL REFERENCES depositos,
    mot_cod  integer       NOT NULL REFERENCES motivos_ajuste,
    cant     numeric(12,2) NOT NULL CHECK (cant > 0),
    PRIMARY KEY (aju_cod, mat_cod, dep_cod)
);

CREATE TABLE remisiones (
    rem_cod  integer PRIMARY KEY,
    suc_o    integer    NOT NULL REFERENCES sucursales,
    suc_d    integer    NOT NULL REFERENCES sucursales,
    emp_ci   integer    NOT NULL REFERENCES funcionarios,   -- quien registra
    emp_resp integer    NOT NULL REFERENCES funcionarios,   -- responsable del traslado
    rem_date timestamp  NOT NULL DEFAULT now(),
    est_cod  varchar(6) NOT NULL DEFAULT 'VIG' REFERENCES estados,  -- VIG en transito, REC recibida, ANU
    CHECK (suc_o <> suc_d)
);

CREATE TABLE det_remisiones (
    rem_cod  integer       NOT NULL REFERENCES remisiones,
    mat_cod  integer       NOT NULL REFERENCES materiales,
    dep_o    integer       NOT NULL REFERENCES depositos,
    dep_d    integer       NOT NULL REFERENCES depositos,
    det_cant numeric(12,2) NOT NULL CHECK (det_cant > 0),
    PRIMARY KEY (rem_cod, mat_cod, dep_o)
);

-- =====================================================================
--  MOVIMIENTOS - MOD. SERVICIO
-- =====================================================================
-- est_cod: REC recibido, PRE presupuestado, TAL en taller (con OT),
--          TER terminado, ENT entregado, ANU anulado
CREATE TABLE recepciones (
    ent_cod   integer PRIMARY KEY,
    veh_cod   integer     NOT NULL REFERENCES vehiculos,
    cli_ci    integer     NOT NULL REFERENCES asegurados,
    seg_cod   integer     REFERENCES seguros,
    suc_cod   integer     NOT NULL REFERENCES sucursales,
    emp_ci    integer     NOT NULL REFERENCES funcionarios,
    comb_cod  integer     NOT NULL REFERENCES combustible,
    ent_date  timestamp   NOT NULL DEFAULT now(),
    ent_km    integer     CHECK (ent_km >= 0),
    ent_sin   varchar(30),                 -- nro de siniestro
    ent_obs   varchar(500),
    ent_key   boolean NOT NULL DEFAULT false,   -- llaves
    ent_cat   boolean NOT NULL DEFAULT false,   -- gato
    ent_bal   boolean NOT NULL DEFAULT false,   -- baliza
    ent_aux   boolean NOT NULL DEFAULT false,   -- auxilio
    ent_taz   boolean NOT NULL DEFAULT false,   -- tazas
    ent_her   boolean NOT NULL DEFAULT false,   -- herramientas
    ent_rad   boolean NOT NULL DEFAULT false,   -- radio
    ent_ext   boolean NOT NULL DEFAULT false,   -- extintor
    ent_date_fin timestamp,                     -- fecha de entrega
    est_cod   varchar(6)  NOT NULL DEFAULT 'REC' REFERENCES estados
);

CREATE TABLE det_recepciones (
    ent_cod integer NOT NULL REFERENCES recepciones,
    pie_cod integer NOT NULL REFERENCES piezas,
    dam_cod integer NOT NULL REFERENCES danhos,
    PRIMARY KEY (ent_cod, pie_cod)
);

CREATE TABLE presupuestos (
    prep_cod  integer PRIMARY KEY,
    ent_cod   integer    NOT NULL REFERENCES recepciones,
    emp_ci    integer    NOT NULL REFERENCES funcionarios,
    prep_date timestamp  NOT NULL DEFAULT now(),
    prep_vto  date       NOT NULL DEFAULT current_date + 15,
    est_cod   varchar(6) NOT NULL DEFAULT 'VIG' REFERENCES estados   -- VIG, APR, OT, ANU, RCH
);

CREATE TABLE det_prep_piezas (
    prep_cod   integer       NOT NULL REFERENCES presupuestos,
    pie_cod    integer       NOT NULL REFERENCES piezas,
    dam_cod    integer       NOT NULL REFERENCES danhos,
    det_precio numeric(14,0) NOT NULL CHECK (det_precio >= 0),
    PRIMARY KEY (prep_cod, pie_cod)
);

CREATE TABLE det_prep_trabajos (
    prep_cod   integer       NOT NULL REFERENCES presupuestos,
    trab_cod   integer       NOT NULL REFERENCES trabajos,
    det_precio numeric(14,0) NOT NULL CHECK (det_precio >= 0),
    PRIMARY KEY (prep_cod, trab_cod)
);

CREATE TABLE ordenes_trabajo (
    ot_cod   integer PRIMARY KEY,
    prep_cod integer    NOT NULL UNIQUE REFERENCES presupuestos,
    emp_ci   integer    NOT NULL REFERENCES funcionarios,   -- quien emite
    ot_date  timestamp  NOT NULL DEFAULT now(),
    ot_fin   date,                                          -- fecha estimada de entrega
    ot_obs   varchar(500),
    est_cod  varchar(6) NOT NULL DEFAULT 'VIG' REFERENCES estados   -- VIG en proceso, TER, ENT, ANU
);

-- asignacion interna de piezas a funcionarios ("Emitir O. de Trabajo (Interna)")
CREATE TABLE det_ot (
    ot_cod  integer    NOT NULL REFERENCES ordenes_trabajo,
    pie_cod integer    NOT NULL REFERENCES piezas,
    emp_ci  integer    NOT NULL REFERENCES funcionarios,
    est_cod varchar(6) NOT NULL DEFAULT 'VIG' REFERENCES estados,     -- VIG, TER
    PRIMARY KEY (ot_cod, pie_cod)
);

-- "Gestionar Materiales": consumo de insumos por OT (descuenta stock)
CREATE TABLE consumos (
    con_cod      integer PRIMARY KEY,
    ot_cod       integer       NOT NULL REFERENCES ordenes_trabajo,
    mat_cod      integer       NOT NULL REFERENCES materiales,
    dep_cod      integer       NOT NULL REFERENCES depositos,
    emp_ci       integer       NOT NULL REFERENCES funcionarios,   -- quien retira
    con_datetime timestamp     NOT NULL DEFAULT now(),
    con_cant     numeric(12,2) NOT NULL CHECK (con_cant > 0),
    est_cod      varchar(6)    NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE controles_calidad (
    cc_cod   integer PRIMARY KEY,
    ot_cod   integer      NOT NULL REFERENCES ordenes_trabajo,
    emp_ci   integer      NOT NULL REFERENCES funcionarios,
    cc_date  timestamp    NOT NULL DEFAULT now(),
    cc_obs   varchar(500),
    cc_apr   boolean      NOT NULL,
    est_cod  varchar(6)   NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE det_controles (
    cc_cod   integer  NOT NULL REFERENCES controles_calidad,
    paso_cod integer  NOT NULL REFERENCES pasos_control,
    det_cal  smallint NOT NULL CHECK (det_cal BETWEEN 1 AND 5),
    PRIMARY KEY (cc_cod, paso_cod)
);

CREATE TABLE garantias (
    gar_cod  integer PRIMARY KEY,
    ot_cod   integer      NOT NULL REFERENCES ordenes_trabajo,
    motg_cod integer      NOT NULL REFERENCES motivos_garantia,
    emp_ci   integer      NOT NULL REFERENCES funcionarios,
    gar_date timestamp    NOT NULL DEFAULT now(),
    gar_obs  varchar(500),
    est_cod  varchar(6)   NOT NULL DEFAULT 'VIG' REFERENCES estados
);

-- =====================================================================
--  MOVIMIENTOS - MOD. GEST. COBROS
-- =====================================================================
CREATE TABLE aperturas_cierres (
    ape_cod      integer PRIMARY KEY,
    caj_cod      integer       NOT NULL REFERENCES cajas,
    emp_ci       integer       NOT NULL REFERENCES funcionarios,
    ape_datetime timestamp     NOT NULL DEFAULT now(),
    ape_monto    numeric(14,0) NOT NULL DEFAULT 0 CHECK (ape_monto >= 0),
    cie_datetime timestamp,
    cie_monto    numeric(14,0),
    est_cod      varchar(6)    NOT NULL DEFAULT 'ABI' REFERENCES estados   -- ABI, CER
);
-- una caja no puede tener dos aperturas abiertas
CREATE UNIQUE INDEX ux_caja_abierta ON aperturas_cierres (caj_cod) WHERE est_cod = 'ABI';

CREATE TABLE facturas (
    fact_cod  integer PRIMARY KEY,
    tim_cod   integer       NOT NULL REFERENCES timbrados,
    fact_nro  varchar(15)   NOT NULL UNIQUE,     -- 001-001-0000001
    ape_cod   integer       NOT NULL REFERENCES aperturas_cierres,
    ot_cod    integer       NOT NULL REFERENCES ordenes_trabajo,
    cli_ci    integer       REFERENCES asegurados,  -- se factura al asegurado...
    seg_cod   integer       REFERENCES seguros,     -- ...o a la cia. de seguros
    tip_cod   integer       NOT NULL REFERENCES tipos_pago,
    fact_date timestamp     NOT NULL DEFAULT now(),
    emp_ci    integer       NOT NULL REFERENCES funcionarios,
    est_cod   varchar(6)    NOT NULL DEFAULT 'PEN' REFERENCES estados,   -- PEN, COB, ANU
    CHECK ((cli_ci IS NULL) <> (seg_cod IS NULL))
);

CREATE TABLE det_facturas (
    fact_cod   integer       NOT NULL REFERENCES facturas,
    item       smallint      NOT NULL,
    item_des   varchar(120)  NOT NULL,
    det_cant   numeric(12,2) NOT NULL CHECK (det_cant > 0),
    det_precio numeric(14,0) NOT NULL CHECK (det_precio >= 0),
    imp_cod    integer       NOT NULL REFERENCES impuestos,
    PRIMARY KEY (fact_cod, item)
);

CREATE TABLE cobros (
    cob_cod  integer PRIMARY KEY,
    fact_cod integer    NOT NULL REFERENCES facturas,
    ape_cod  integer    NOT NULL REFERENCES aperturas_cierres,
    emp_ci   integer    NOT NULL REFERENCES funcionarios,
    cob_date timestamp  NOT NULL DEFAULT now(),
    est_cod  varchar(6) NOT NULL DEFAULT 'VIG' REFERENCES estados
);

CREATE TABLE det_cobros (
    cob_cod  integer       NOT NULL REFERENCES cobros,
    item     smallint      NOT NULL,
    fcob_cod integer       NOT NULL REFERENCES formas_cobro,
    ban_cod  integer       REFERENCES bancos,
    det_ref  varchar(30),
    det_monto numeric(14,0) NOT NULL CHECK (det_monto > 0),
    PRIMARY KEY (cob_cod, item)
);

-- indices de apoyo para los listados por fecha
CREATE INDEX ix_ped_fecha  ON pedidos (ped_datetime);
CREATE INDEX ix_ord_fecha  ON ordenes_compra (ord_date);
CREATE INDEX ix_com_fecha  ON compras (com_date);
CREATE INDEX ix_aju_fecha  ON ajustes (aju_date);
CREATE INDEX ix_rem_fecha  ON remisiones (rem_date);
CREATE INDEX ix_ent_fecha  ON recepciones (ent_date);
CREATE INDEX ix_prep_fecha ON presupuestos (prep_date);
CREATE INDEX ix_ot_fecha   ON ordenes_trabajo (ot_date);
CREATE INDEX ix_fact_fecha ON facturas (fact_date);
