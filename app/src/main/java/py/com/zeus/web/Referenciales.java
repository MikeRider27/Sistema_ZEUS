package py.com.zeus.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Definicion de las ventanas "Mantener ..." (paquete referenciales del legacy).
 * Todas compartian la misma estructura: pestañas Edicion/Listado, botones
 * Agregar/Editar/Anular/Guardar/Cancelar/Salir y una llamada a abm_x(bandera, cod, ...).
 * Aqui cada ventana se describe con datos y se dibuja con una sola plantilla.
 */
public final class Referenciales {

    /** Los campos se declaran en el orden de los parametros de la funcion abm_x.
     *  Tipos de campo: s texto, i entero, n numero, d fecha, b check, p contraseña, c combo (entero), cs combo (texto) */
    public record Campo(String col, String label, String tipo, boolean req, String combo, String dependeDe, int max) {
        boolean isCombo() { return tipo.startsWith("c"); }
    }

    public record Col(String col, String titulo, int ancho, String fmt) {}

    public record Ref(String key, String titulo, String icono, String vista, String codCol, String codLabel,
                      boolean codManual, String funcion, String busqueda, boolean soloAdmin,
                      List<Col> cols, List<Campo> campos) {}

    private static final Map<String, Ref> REFS = new LinkedHashMap<>();

    static Campo txt(String col, String label, boolean req, int max) { return new Campo(col, label, "s", req, null, null, max); }
    static Campo txt(String col, String label) { return txt(col, label, true, 60); }
    static Campo ent(String col, String label, boolean req) { return new Campo(col, label, "i", req, null, null, 10); }
    static Campo num(String col, String label, boolean req) { return new Campo(col, label, "n", req, null, null, 15); }
    static Campo fec(String col, String label, boolean req) { return new Campo(col, label, "d", req, null, null, 10); }
    static Campo chk(String col, String label) { return new Campo(col, label, "b", false, null, null, 0); }
    static Campo cbo(String col, String label, String combo, boolean req) { return new Campo(col, label, "c", req, combo, null, 0); }
    static Campo cbo(String col, String label, String combo, boolean req, String depende) { return new Campo(col, label, "c", req, combo, depende, 0); }
    static Col col(String col, String titulo, int ancho) { return new Col(col, titulo, ancho, null); }
    static Col col(String col, String titulo, int ancho, String fmt) { return new Col(col, titulo, ancho, fmt); }

    private static void add(Ref r) { REFS.put(r.key(), r); }

    /** referencial simple: codigo + descripcion */
    private static void simple(String key, String titulo, String icono, String vista, String pre, String funcion) {
        add(new Ref(key, titulo, icono, vista, pre + "_cod", "Codigo", false, funcion, pre + "_des", false,
                List.of(col(pre + "_cod", "Codigo", 60, "n"), col(pre + "_des", "Descripcion", 400)),
                List.of(txt(pre + "_des", "Descripcion"))));
    }

    static {
        // ---------------------- Mant. Ref. Generales ----------------------
        add(new Ref("sucursales", "Mantener Sucursales", "sucursal", "v_suc", "suc_cod", "Codigo", false, "abm_suc", "suc_des", true,
                List.of(col("suc_cod", "Codigo", 50, "n"), col("suc_des", "Descripcion", 200), col("suc_tel", "Telefono", 100),
                        col("suc_dir", "Direccion", 200), col("suc_mail", "E-Mail", 150)),
                List.of(txt("suc_des", "Descripcion"), txt("suc_tel", "Telefono", false, 30),
                        txt("suc_dir", "Direccion", false, 120), txt("suc_mail", "E-Mail", false, 80))));
        add(new Ref("cargos", "Mantener Cargos", "cargo", "v_car", "car_cod", "Codigo", false, "abm_car", "car_des", true,
                List.of(col("car_cod", "Codigo", 50, "n"), col("car_des", "Descripcion", 250), col("niv_des", "Nivel de Permiso", 140),
                        col("car_user_des", "Usa el Sistema", 90)),
                List.of(txt("car_des", "Descripcion"), cbo("car_niv", "Nivel de Permiso", "cbo_niv", true),
                        chk("car_user", "Puede tener usuario de sistema"))));
        add(new Ref("funcionarios", "Mantener Funcionarios", "funcionario", "v_func", "emp_ci", "C.I. Nº", true, "abm_func", "emp", false,
                List.of(col("emp_ci", "C.I. Nº", 70, "n"), col("emp_nom", "Nombres", 140), col("emp_ape", "Apellidos", 140),
                        col("emp_cel", "Celular", 100), col("car_des", "Cargo", 140), col("suc_des", "Sucursal", 140)),
                List.of(cbo("nac_cod", "Nacionalidad", "cbo_nac", true), cbo("car_cod", "Cargo", "cbo_car", true),
                        cbo("suc_cod", "Sucursal", "cbo_suc", true), txt("emp_nom", "Nombres"), txt("emp_ape", "Apellidos"),
                        txt("emp_cel", "Celular", false, 30), txt("emp_dir", "Direccion", false, 120),
                        fec("emp_fnac", "F. de Nacimiento", false), fec("date_ing", "F. de Ingreso", true),
                        fec("date_egr", "F. de Egreso", false))));
        add(new Ref("usuarios", "Mantener Usuarios", "sesion", "v_usr", "usr_cod", "Codigo", false, "abm_usr", "usr_des", true,
                List.of(col("usr_cod", "Codigo", 50, "n"), col("usr_des", "Usuario", 120), col("emp", "Funcionario", 220),
                        col("car_des", "Cargo", 150), col("suc_des", "Sucursal", 150)),
                List.of(txt("usr_des", "Usuario", true, 30), new Campo("usr_pass", "Contraseña", "p", false, null, null, 60),
                        cbo("emp_ci", "Funcionario", "cbo_func_usr", true))));
        add(new Ref("bancos", "Mantener Entidades Bancarias", "banco", "v_ban", "ban_cod", "Codigo", false, "abm_ban", "ban_des", false,
                List.of(col("ban_cod", "Codigo", 60, "n"), col("ban_des", "Descripcion", 400)),
                List.of(txt("ban_des", "Descripcion"))));

        // ---------------------- Mant. Ref. Mod. Compra ----------------------
        add(new Ref("materiales", "Mantener Materiales", "material", "v_mat", "mat_cod", "Codigo", false, "abm_mat", "mat_des", false,
                List.of(col("mat_cod", "Codigo", 50, "n"), col("mat_des", "Descripcion", 250), col("un_cod", "U. de Medida", 80),
                        col("mat_min", "Exist. Min.", 80, "n"), col("mat_precio", "Precio", 90, "n")),
                List.of(new Campo("un_cod", "U. de Medida", "cs", true, "cbo_un", null, 0), txt("mat_des", "Descripcion", true, 80),
                        num("mat_min", "Existencia Minima", true), num("mat_precio", "Precio de Venta", true))));
        add(new Ref("proveedores", "Mantener Proveedores", "proveedor", "v_prov", "prov_cod", "Codigo", false, "abm_prov", "prov_des", false,
                List.of(col("prov_cod", "Codigo", 50, "n"), col("prov_des", "Razon Social", 200), col("prov_ruc", "R.U.C.", 90),
                        col("prov_dir", "Direccion", 180), col("prov_tel", "Telefono", 90), col("prov_mail", "E-Mail", 150)),
                List.of(txt("prov_des", "Razon Social", true, 80), txt("prov_ruc", "R.U.C.", true, 20),
                        txt("prov_dir", "Direccion", false, 120), txt("prov_tel", "Telefono", false, 30),
                        txt("prov_mail", "E-Mail", false, 80))));
        add(new Ref("depositos", "Mantener Depositos", "stock", "v_dep", "dep_cod", "Codigo", false, "abm_dep", "dep_des", false,
                List.of(col("dep_cod", "Codigo", 50, "n"), col("dep_des", "Descripcion", 250), col("suc_des", "Sucursal", 200)),
                List.of(cbo("suc_cod", "Sucursal", "cbo_suc", true), txt("dep_des", "Descripcion"))));
        add(new Ref("motivos_ajuste", "Mantener Motivo de Ajuste", "motivo", "v_mot", "mot_cod", "Codigo", false, "abm_mot", "mot_des", false,
                List.of(col("mot_cod", "Codigo", 50, "n"), col("tmot_des", "T. de Ajuste", 150), col("mot_des", "Descripcion", 300)),
                List.of(txt("mot_des", "Descripcion"), cbo("tmot_cod", "T. de Ajuste", "cbo_tmot", true))));
        simple("tipos_pago", "Mantener Tipos de Pago", "money", "v_tpag", "tip", "abm_tpag");

        // ---------------------- Mant. Ref. Mod. Servicio ----------------------
        add(new Ref("asegurados", "Mantener Asegurados", "asegurado", "v_cli", "cli_ci", "C.I. Nº", true, "abm_cli", "cli", false,
                List.of(col("cli_ci", "C.I. Nº", 70, "n"), col("cli_nom", "Nombres", 150), col("cli_ape", "Apellidos", 150),
                        col("cli_cel", "Celular", 100), col("ciu_des", "Ciudad", 130)),
                // el orden de los campos es el de los parametros de abm_cli
                List.of(cbo("nac_cod", "Nacionalidad", "cbo_nac", true), cbo("ciu_cod", "Ciudad", "cbo_ciu", true),
                        cbo("bar_cod", "Barrio", "cbo_bar", false, "ciu_cod"), txt("cli_nom", "Nombres"), txt("cli_ape", "Apellidos"),
                        txt("cli_cel", "Celular", false, 30), txt("cli_dir", "Direccion", false, 120),
                        fec("cli_fnac", "F. de Nacimiento", false))));
        add(new Ref("barrios", "Mantener Barrios", "barrio", "v_bar", "bar_cod", "Codigo", false, "abm_bar", "bar_des", false,
                List.of(col("bar_cod", "Codigo", 50, "n"), col("bar_des", "Descripcion", 250), col("ciu_des", "Ciudad", 200)),
                List.of(cbo("ciu_cod", "Ciudad", "cbo_ciu", true), txt("bar_des", "Descripcion"))));
        simple("ciudades", "Mantener Ciudades", "ciudad", "v_ciu", "ciu", "abm_ciu");
        simple("nacionalidades", "Mantener Nacionalidades", "nacionalidad", "v_nac", "nac", "abm_nac");
        add(new Ref("vehiculos", "Mantener Vehiculos", "vehiculo", "v_veh", "veh_cod", "Codigo", false, "abm_veh", "veh", false,
                List.of(col("veh_cod", "Codigo", 50, "n"), col("veh_chapa", "Chapa", 80), col("cli", "Asegurado", 160),
                        col("seg_des", "Cia. de Seguro", 140), col("mar_des", "Marca", 90), col("mod_des", "Modelo", 90),
                        col("col_des", "Color", 70), col("veh_anho", "Año", 50)),
                List.of(cbo("cli_ci", "Asegurado", "cbo_cli", true), cbo("seg_cod", "Cia. de Seguro", "cbo_seg", false),
                        cbo("mod_cod", "Marca / Modelo", "cbo_mod", true), cbo("col_cod", "Color", "cbo_col", true),
                        cbo("tveh_cod", "T. de Vehiculo", "cbo_tveh", true), ent("veh_anho", "Año", false),
                        txt("veh_chapa", "Chapa", true, 15), txt("veh_chasis", "Chasis", false, 30),
                        txt("veh_poliza", "Poliza Nº", false, 30))));
        simple("marcas", "Mantener Marcas", "marca", "v_mar", "mar", "abm_mar");
        add(new Ref("modelos", "Mantener Modelos", "modelo", "v_mod", "mod_cod", "Codigo", false, "abm_mod", "mod_des", false,
                List.of(col("mod_cod", "Codigo", 50, "n"), col("mar_des", "Marca", 200), col("mod_des", "Descripcion", 250)),
                List.of(cbo("mar_cod", "Marca", "cbo_mar", true), txt("mod_des", "Descripcion"))));
        simple("tipos_vehiculo", "Mantener T. de Vehiculos", "vehiculo", "v_tveh", "tveh", "abm_tveh");
        simple("colores", "Mantener Colores", "color", "v_col", "col", "abm_col");
        add(new Ref("seguros", "Mantener Cias. de Seguro", "seguro", "v_seg", "seg_cod", "Codigo", false, "abm_seg", "seg_des", false,
                List.of(col("seg_cod", "Codigo", 50, "n"), col("seg_des", "Descripcion", 200), col("seg_dir", "Direccion", 200),
                        col("seg_tel", "Telefono", 100), col("seg_mail", "E-Mail", 160)),
                List.of(txt("seg_des", "Descripcion", true, 80), txt("seg_dir", "Direccion", false, 120),
                        txt("seg_tel", "Telefono", false, 30), txt("seg_mail", "E-Mail", false, 80))));
        add(new Ref("piezas", "Mantener Piezas", "pieza", "v_pzas", "pie_cod", "Codigo", false, "abm_pza", "pie_des", false,
                List.of(col("pie_cod", "Codigo", 50, "n"), col("pie_des", "Descripcion", 300), col("pie_precio", "Precio M. de Obra", 120, "n")),
                List.of(txt("pie_des", "Descripcion"), num("pie_precio", "Precio M. de Obra", true))));
        add(new Ref("danhos", "Mantener Niveles de Daño", "danho", "v_dam", "dam_cod", "Codigo", false, "abm_dam", "dam_des", false,
                List.of(col("dam_cod", "Codigo", 50, "n"), col("dam_des", "Descripcion", 250), col("dam_porc", "% s/ Precio", 100, "n")),
                List.of(txt("dam_des", "Descripcion", true, 40), num("dam_porc", "% sobre Precio de Pieza", true))));
        add(new Ref("trabajos", "Mantener Trabajos", "trabajo", "v_trab", "trab_cod", "Codigo", false, "abm_trab", "trab_des", false,
                List.of(col("trab_cod", "Codigo", 50, "n"), col("trab_des", "Descripcion", 300), col("trab_precio", "Precio", 110, "n")),
                List.of(txt("trab_des", "Descripcion"), num("trab_precio", "Precio", true))));
        simple("motivos_garantia", "Mantener Motivos de Garantia", "garantia", "v_motg", "motg", "abm_motg");

        // ---------------------- Mant. Ref. Mod. Gest. Cobros ----------------------
        add(new Ref("cajas", "Mantener Cajas", "caja", "v_caj", "caj_cod", "Codigo", false, "abm_caj", "caj_des", false,
                List.of(col("caj_cod", "Codigo", 50, "n"), col("caj_des", "Descripcion", 200), col("suc_des", "Sucursal", 200)),
                List.of(cbo("suc_cod", "Sucursal", "cbo_suc", true), txt("caj_des", "Descripcion", true, 40))));
        add(new Ref("impuestos", "Mantener Impuestos", "money", "v_imp", "imp_cod", "Codigo", false, "abm_imp", "imp_des", true,
                List.of(col("imp_cod", "Codigo", 50, "n"), col("imp_des", "Descripcion", 250), col("imp_porc", "Porcentaje", 90, "n")),
                List.of(txt("imp_des", "Descripcion", true, 30), num("imp_porc", "Porcentaje", true))));
        add(new Ref("timbrados", "Mantener Nº de Timbrado", "informe", "v_tim", "tim_cod", "Codigo", false, "abm_tim", "tim_nro", true,
                List.of(col("tim_cod", "Codigo", 50, "n"), col("tim_nro", "Timbrado Nº", 100), col("tim_ini", "Inicio", 90, "d"),
                        col("tim_vto", "Vencimiento", 90, "d"), col("tim_est", "Estab.", 50), col("tim_pexp", "P. Exp.", 50),
                        col("tim_ult", "Ult. Nº", 70, "n")),
                List.of(ent("tim_nro", "Timbrado Nº", true), fec("tim_ini", "F. de Inicio", true), fec("tim_vto", "F. de Vencimiento", true),
                        txt("tim_est", "Establecimiento", true, 3), txt("tim_pexp", "Punto de Expedicion", true, 3))));
    }

    public static Ref get(String key) {
        return REFS.get(key);
    }

    public static Map<String, Ref> todos() {
        return REFS;
    }

    private Referenciales() {}
}
