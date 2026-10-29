package py.com.zeus.web;

import java.util.HashMap;
import java.util.Map;

/**
 * Consultas que pueden pedir las ventanas (lo que en el legacy hacian TblThread,
 * CboThreadGT, CargarCombo, etc. con SQL armado concatenando texto).
 * Solo se ejecutan estas sentencias, con parametros nombrados y tipados:
 *   :nombre  -> parametro del request (tipo segun TIPOS, vacio = NULL)
 *   :_suc / :_emp -> sucursal / funcionario de la sesion
 */
final class Consultas {

    static final Map<String, String> SQL = new HashMap<>();

    /** tipo de cada parametro: i entero, s texto, d fecha */
    static final Map<String, String> TIPOS = Map.of(
            "cod", "i", "mes", "i", "anho", "i", "suc", "i", "est", "s", "q", "s",
            "ciu", "i", "dep", "i", "mat", "i", "ot", "i");

    // filtros de listado comunes a los movimientos (Filtrar Por mes / año / estado / sucursal)
    private static String filtro(String fecha) {
        return " (:mes::int IS NULL OR extract(month FROM " + fecha + ") = :mes)"
             + " AND (:anho::int IS NULL OR extract(year FROM " + fecha + ") = :anho)"
             + " AND (:est::text IS NULL OR est_cod = :est)";
    }

    private static void q(String nombre, String sql) {
        SQL.put(nombre, sql);
    }

    static {
        // ------------------------------- combos --------------------------------
        q("cbo_nac", "SELECT nac_cod AS cod, nac_des AS des FROM v_nac WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_ciu", "SELECT ciu_cod AS cod, ciu_des AS des FROM v_ciu WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_bar", "SELECT bar_cod AS cod, bar_des AS des FROM v_bar WHERE est_cod = 'VIG' AND ciu_cod = :ciu ORDER BY 2");
        q("cbo_suc", "SELECT suc_cod AS cod, suc_des AS des FROM v_suc WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_suc_otras", "SELECT suc_cod AS cod, suc_des AS des FROM v_suc WHERE est_cod = 'VIG' AND suc_cod <> :_suc ORDER BY 2");
        q("cbo_car", "SELECT car_cod AS cod, car_des AS des FROM v_car WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_niv", "SELECT * FROM (VALUES (1, '1 - ADMINISTRADOR'), (2, '2 - GERENTE'), (3, '3 - ENCARGADO'), (4, '4 - OPERADOR')) n(cod, des)");
        q("cbo_un", "SELECT un_cod AS cod, un_cod || ' - ' || un_des AS des FROM v_un WHERE est_cod = 'VIG' ORDER BY 1");
        q("cbo_tmot", "SELECT tmot_cod AS cod, tmot_des AS des FROM v_tmot ORDER BY 1");
        q("cbo_mar", "SELECT mar_cod AS cod, mar_des AS des FROM v_mar WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_mod", "SELECT mod_cod AS cod, mar_des || ' ' || mod_des AS des FROM v_mod WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_col", "SELECT col_cod AS cod, col_des AS des FROM v_col WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_tveh", "SELECT tveh_cod AS cod, tveh_des AS des FROM v_tveh WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_seg", "SELECT seg_cod AS cod, seg_des AS des FROM v_seg WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_cli", "SELECT cli_ci AS cod, cli || ' (C.I. ' || cli_ci || ')' AS des FROM v_cli WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_func_usr", "SELECT emp_ci AS cod, emp || ' - ' || car_des AS des FROM v_func WHERE est_cod = 'VIG' AND car_user ORDER BY 2");
        q("cbo_func", "SELECT emp_ci AS cod, emp || ' - ' || car_des AS des FROM v_func WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_func_suc", "SELECT emp_ci AS cod, emp || ' - ' || car_des AS des FROM v_func WHERE est_cod = 'VIG' AND suc_cod = :_suc ORDER BY 2");
        q("cbo_prov", "SELECT prov_cod AS cod, prov_des || ' (' || prov_ruc || ')' AS des FROM v_prov WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_tpag", "SELECT tip_cod AS cod, tip_des AS des FROM v_tpag WHERE est_cod = 'VIG' ORDER BY 1");
        q("cbo_mat", "SELECT mat_cod AS cod, mat_des AS des, un_cod, mat_precio FROM v_mat WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_dep", "SELECT dep_cod AS cod, dep_des AS des FROM v_dep WHERE est_cod = 'VIG' AND suc_cod = coalesce(:suc, :_suc) ORDER BY 2");
        q("cbo_mot", "SELECT mot_cod AS cod, mot_des || ' - ' || tmot_des AS des, tmot_signo FROM v_mot WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_pza", "SELECT pie_cod AS cod, pie_des AS des, pie_precio FROM v_pzas WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_dam", "SELECT dam_cod AS cod, dam_des AS des, dam_porc FROM v_dam WHERE est_cod = 'VIG' ORDER BY dam_porc");
        q("cbo_trab", "SELECT trab_cod AS cod, trab_des AS des, trab_precio FROM v_trab WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_comb", "SELECT comb_cod AS cod, comb_des AS des FROM v_comb ORDER BY 1");
        q("cbo_motg", "SELECT motg_cod AS cod, motg_des AS des FROM v_motg WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_caj", "SELECT caj_cod AS cod, caj_des AS des FROM v_caj WHERE est_cod = 'VIG' AND suc_cod = :_suc ORDER BY 2");
        q("cbo_fcob", "SELECT fcob_cod AS cod, fcob_des AS des, fcob_ban FROM v_fcob ORDER BY 1");
        q("cbo_ban", "SELECT ban_cod AS cod, ban_des AS des FROM v_ban WHERE est_cod = 'VIG' ORDER BY 2");
        q("cbo_veh", "SELECT veh_cod AS cod, veh || ' - ' || cli AS des, cli, seg_des, veh_poliza, tveh_des, veh_chasis "
                + "FROM v_veh WHERE est_cod = 'VIG' ORDER BY 2");
        q("estados", "SELECT est_cod AS cod, est_des AS des FROM estados ORDER BY 2");
        q("stock_uno", "SELECT coalesce((SELECT cant FROM stock WHERE mat_cod = :mat AND dep_cod = :dep), 0) AS cant");

        // ---------------------------- Mod. Compra ------------------------------
        q("ped_lista", "SELECT ped_cod, suc_des, date_, emp, emp_apr, est_cod FROM v_ped WHERE"
                + filtro("ped_datetime") + " AND suc_cod = coalesce(:suc, :_suc) ORDER BY 1 DESC");
        q("ped_det", "SELECT mat_cod, det_cant, un_cod, mat, det_ord, est_cod FROM v_dped WHERE ped_cod = :cod ORDER BY mat");
        q("ped_para_orden", "SELECT ped_cod AS cod, 'PEDIDO Nº ' || ped_cod || ' - ' || suc_des || ' - ' || date_ || ' - ' || emp AS des "
                + "FROM v_ped WHERE est_cod IN ('APR', 'ORD.P') ORDER BY 1 DESC");
        q("ped_det_pend", "SELECT d.mat_cod, d.mat, d.un_cod, d.det_cant, m.mat_precio FROM v_dped d JOIN materiales m USING (mat_cod) "
                + "WHERE d.ped_cod = :cod AND d.est_cod = 'APR' AND NOT d.det_ord ORDER BY d.mat");
        q("ord_lista", "SELECT ord_nro, ped_cod, prov, suc_des, tip_des, date_, total, est_cod FROM v_ordc WHERE"
                + filtro("ord_date") + " AND suc_cod = coalesce(:suc, :_suc) ORDER BY 1 DESC");
        q("ord_det", "SELECT mat_cod, det_cant, mat, det_precio, total FROM v_dordc WHERE ord_nro = :cod ORDER BY mat");
        q("ord_para_compra", "SELECT ord_nro AS cod, 'ORDEN Nº ' || ord_nro || ' - ' || prov || ' - ' || tip_des || ' - ' || date_ AS des, "
                + "suc_cod FROM v_ordc WHERE est_cod = 'VIG' AND suc_cod = :_suc ORDER BY 1 DESC");
        q("com_lista", "SELECT com_cod, prov, ped_cod, ord_nro, com_nrofact, date_, suc_des, total, est_cod FROM v_com WHERE"
                + filtro("com_date") + " AND suc_cod = coalesce(:suc, :_suc) ORDER BY 1 DESC");
        q("com_det", "SELECT com_cant, mat, dep, com_precio, total FROM v_dcom WHERE com_cod = :cod ORDER BY mat");
        q("aju_lista", "SELECT aju_cod, date_, suc_des, emp, est_cod FROM v_ajum WHERE"
                + filtro("aju_date") + " AND suc_cod = coalesce(:suc, :_suc) ORDER BY 1 DESC");
        q("aju_det", "SELECT mat, dep, mot, tmot_des, cant FROM v_dajum WHERE aju_cod = :cod ORDER BY mat");
        q("rem_lista", "SELECT rem_cod, date_, suc_o_des, suc_d_des, resp, est_cod, suc_o, suc_d FROM v_rem WHERE"
                + filtro("rem_date") + " AND (suc_o = :_suc OR suc_d = :_suc) ORDER BY 1 DESC");
        q("rem_det", "SELECT det_cant, mat, dep_o_des, dep_d_des FROM v_drem WHERE rem_cod = :cod ORDER BY mat");
        q("stock", "SELECT mat_des, suc_des, dep_des, un_cod, mat_min, cant, cant_no_disp, bajo_min FROM v_stock_m "
                + "WHERE suc_cod = coalesce(:suc, :_suc) AND (:q::text IS NULL OR mat_des ILIKE '%' || :q || '%') "
                + "ORDER BY mat_des, dep_des");

        // ---------------------------- Mod. Servicio ----------------------------
        q("ent_lista", "SELECT ent_cod, date_, seg_des, veh, cli, ent_sin, est_cod FROM v_ent WHERE"
                + filtro("ent_date") + " AND suc_cod = :_suc"
                + " AND (:q::text IS NULL OR veh || ' ' || cli || ' ' || seg_des || ' ' || coalesce(ent_sin, '') ILIKE '%' || :q || '%')"
                + " ORDER BY 1 DESC");
        q("ent_uno", "SELECT * FROM v_ent WHERE ent_cod = :cod AND suc_cod = :_suc");
        q("ent_det", "SELECT pie_cod, pie_des, dam_cod, dam_des FROM v_dent WHERE ent_cod = :cod ORDER BY pie_des");
        q("ent_para_prep", "SELECT ent_cod AS cod, 'RECEPCION Nº ' || ent_cod || ' - ' || veh || ' - ' || cli AS des, "
                + "seg_des, ent_sin FROM v_ent WHERE est_cod = 'REC' AND suc_cod = :_suc ORDER BY 1 DESC");
        q("prep_lista", "SELECT prep_cod, ent_cod, seg_des, cli, veh, date_, to_char(prep_vto, 'DD/MM/YYYY') AS vto, total, est_cod "
                + "FROM v_prep WHERE" + filtro("prep_date") + " AND suc_cod = :_suc ORDER BY 1 DESC");
        q("prep_uno", "SELECT prep_cod, ent_cod, prep_vto FROM v_prep WHERE prep_cod = :cod AND suc_cod = :_suc");
        q("prep_det_pie", "SELECT pie_cod, pie_des, dam_cod, dam_des, det_precio FROM v_dprep_pie WHERE prep_cod = :cod ORDER BY pie_des");
        q("prep_det_trab", "SELECT trab_cod, trab_des, det_precio FROM v_dprep_trab WHERE prep_cod = :cod ORDER BY trab_des");
        q("prep_para_ot", "SELECT prep_cod AS cod, 'PRESUPUESTO Nº ' || prep_cod || ' - ' || veh || ' - ' || cli AS des, total "
                + "FROM v_prep WHERE est_cod = 'APR' AND suc_cod = :_suc ORDER BY 1 DESC");
        q("ot_lista", "SELECT ot_cod, prep_cod, seg_des, veh, cli, date_, to_char(ot_fin, 'DD/MM/YYYY') AS fin, total, est_cod "
                + "FROM v_ot WHERE" + filtro("ot_date") + " AND suc_cod = :_suc ORDER BY 1 DESC");
        q("ot_uno", "SELECT ot_cod, prep_cod, ot_fin, ot_obs, veh, cli FROM v_ot WHERE ot_cod = :cod AND suc_cod = :_suc");
        q("ot_det", "SELECT pie_cod, pie_des, emp_ci, emp, car_des, est_cod FROM v_dot WHERE ot_cod = :cod ORDER BY pie_des");
        q("ot_pie", "SELECT d.pie_cod AS cod, d.pie_des || ' (' || d.dam_des || ')' AS des FROM v_dprep_pie d "
                + "JOIN ordenes_trabajo o USING (prep_cod) WHERE o.ot_cod = :cod ORDER BY 2");
        q("ot_en_proceso", "SELECT ot_cod AS cod, 'O.T. Nº ' || ot_cod || ' - ' || veh || ' - ' || cli AS des "
                + "FROM v_ot WHERE est_cod = 'VIG' AND suc_cod = :_suc ORDER BY 1 DESC");
        q("ot_terminadas", "SELECT ot_cod AS cod, 'O.T. Nº ' || ot_cod || ' - ' || veh || ' - ' || cli AS des "
                + "FROM v_ot WHERE est_cod IN ('TER', 'ENT') AND suc_cod = :_suc ORDER BY 1 DESC");
        q("con_lista", "SELECT con_cod, mat, dep, emp, date_, con_cant, un_cod, acum, est_cod FROM v_con "
                + "WHERE ot_cod = :ot ORDER BY con_cod DESC");
        q("pasos", "SELECT paso_cod, paso_etapa, paso_des FROM pasos_control ORDER BY paso_cod");
        q("cc_lista", "SELECT cc_cod, ot_cod, veh, date_, promedio, cc_res, emp, est_cod FROM v_cc WHERE"
                + filtro("cc_date") + " AND suc_cod = :_suc ORDER BY 1 DESC");
        q("cc_det", "SELECT paso_etapa, paso_des, det_cal FROM v_dcc WHERE cc_cod = :cod ORDER BY paso_cod");
        q("gar_lista", "SELECT gar_cod, ot_cod, veh, cli, motg_des, date_, gar_obs, est_cod FROM v_gar WHERE"
                + filtro("gar_date") + " AND suc_cod = :_suc ORDER BY 1 DESC");

        // ----------------------------- Mod. Cobros -----------------------------
        q("ape_lista", "SELECT ape_cod, caj_des, emp, date_, ape_monto, cie_date_, cie_monto, cobrado, est_cod FROM v_ape WHERE"
                + filtro("ape_datetime") + " AND suc_cod = :_suc ORDER BY 1 DESC");
        q("ape_mia", "SELECT ape_cod, caj_des, date_, ape_monto, cobrado FROM v_ape WHERE emp_ci = :_emp AND est_cod = 'ABI'");
        q("ape_det", "SELECT cob_cod, fact_nro, cliente, date_, total, est_cod FROM v_cob WHERE ape_cod = :cod ORDER BY 1");
        q("fact_lista", "SELECT fact_cod, fact_nro, cliente, veh, ent_sin, date_, tip_des, total, iva, cobrado, est_cod FROM v_fact WHERE"
                + filtro("fact_date") + " AND suc_cod = :_suc ORDER BY 1 DESC");
        q("fact_det", "SELECT item, item_des, det_cant, det_precio, imp_des, total FROM v_dfact WHERE fact_cod = :cod ORDER BY item");
        q("fact_uno", "SELECT * FROM v_fact WHERE fact_cod = :cod AND suc_cod = :_suc");
        q("ot_facturables", "SELECT o.ot_cod AS cod, 'O.T. Nº ' || o.ot_cod || ' - ' || o.veh || ' - ' || o.cli AS des, "
                + "o.prep_cod, o.seg_cod, o.seg_des, o.cli, o.ent_sin, o.total FROM v_ot o WHERE o.est_cod IN ('TER', 'ENT') AND o.suc_cod = :_suc "
                + "AND NOT EXISTS (SELECT 1 FROM facturas f WHERE f.ot_cod = o.ot_cod AND f.est_cod <> 'ANU') ORDER BY 1 DESC");
        q("fact_pend", "SELECT fact_cod AS cod, fact_nro || ' - ' || cliente || ' - Saldo: ' || to_char(total - cobrado, 'FM999G999G999G999') AS des, "
                + "total, cobrado, total - cobrado AS saldo FROM v_fact WHERE est_cod = 'PEN' AND suc_cod = :_suc ORDER BY 1");
        q("cob_lista", "SELECT cob_cod, fact_nro, cliente, caj_des, date_, emp, total, est_cod FROM v_cob WHERE"
                + filtro("cob_date") + " AND suc_cod = :_suc ORDER BY 1 DESC");
        q("cob_det", "SELECT fcob_des, ban_des, det_ref, det_monto FROM v_dcob WHERE cob_cod = :cod ORDER BY item");

        // ------------------------------ Informes -------------------------------
        q("inf_stock", "SELECT suc_des, dep_des, mat_des, un_cod, mat_min, cant, cant_no_disp, bajo_min FROM v_stock_m "
                + "WHERE (:suc::int IS NULL OR suc_cod = :suc) ORDER BY suc_des, dep_des, mat_des");
        q("inf_mov_mes", "SELECT 'PEDIDOS' AS mov, count(*) AS cant, NULL::numeric AS total FROM pedidos "
                + "WHERE extract(month FROM ped_datetime) = :mes AND extract(year FROM ped_datetime) = :anho AND est_cod <> 'ANU' "
                + "UNION ALL SELECT 'ORDENES DE COMPRA', count(*), sum(total) FROM v_ordc "
                + "WHERE extract(month FROM ord_date) = :mes AND extract(year FROM ord_date) = :anho AND est_cod <> 'ANU' "
                + "UNION ALL SELECT 'FACTURAS DE COMPRA', count(*), sum(total) FROM v_com "
                + "WHERE extract(month FROM com_date) = :mes AND extract(year FROM com_date) = :anho AND est_cod <> 'ANU' "
                + "UNION ALL SELECT 'RECEPCIONES', count(*), NULL FROM recepciones "
                + "WHERE extract(month FROM ent_date) = :mes AND extract(year FROM ent_date) = :anho AND est_cod <> 'ANU' "
                + "UNION ALL SELECT 'PRESUPUESTOS', count(*), sum(total) FROM v_prep "
                + "WHERE extract(month FROM prep_date) = :mes AND extract(year FROM prep_date) = :anho AND est_cod <> 'ANU' "
                + "UNION ALL SELECT 'ORDENES DE TRABAJO', count(*), sum(total) FROM v_ot "
                + "WHERE extract(month FROM ot_date) = :mes AND extract(year FROM ot_date) = :anho AND est_cod <> 'ANU' "
                + "UNION ALL SELECT 'FACTURAS DE VENTA', count(*), sum(total) FROM v_fact "
                + "WHERE extract(month FROM fact_date) = :mes AND extract(year FROM fact_date) = :anho AND est_cod <> 'ANU' "
                + "UNION ALL SELECT 'COBROS', count(*), sum(total) FROM v_cob "
                + "WHERE extract(month FROM cob_date) = :mes AND extract(year FROM cob_date) = :anho AND est_cod <> 'ANU'");
    }

    private Consultas() {}
}
