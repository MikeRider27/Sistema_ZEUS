package py.com.zeus.web;

import java.util.List;

/** Barra de menu del escritorio, igual a la del frames.Menu del legacy. */
public final class Menu {

    /** url null = submenu; url que empieza con "#" = accion del escritorio (bloquear, salir...) */
    public record Item(String label, String icono, String url, boolean soloAdmin, List<Item> items) {
        public boolean isSubmenu() { return items != null && !items.isEmpty(); }
    }

    static Item it(String label, String icono, String url) { return new Item(label, icono, url, false, List.of()); }
    static Item adm(String label, String icono, String url) { return new Item(label, icono, url, true, List.of()); }
    static Item sub(String label, String icono, Item... items) { return new Item(label, icono, null, false, List.of(items)); }
    static Item sep() { return new Item("-", null, null, false, List.of()); }

    static Item ref(String key) {
        Referenciales.Ref r = Referenciales.get(key);
        return new Item(r.titulo(), r.icono(), "/ref/" + key, r.soloAdmin(), List.of());
    }

    public static final List<Item> BARRA = List.of(
        sub("Mod. Compra", "mod_compra",
            it("Registrar Pedidos", "pedido", "/mov/ped"),
            it("Generar Orden de Compra", "ordc", "/mov/ordc"),
            it("Registrar Fact. Compra", "compra", "/mov/com"),
            sep(),
            it("Registrar Ajustes del Stock de Materiales", "material", "/mov/aju"),
            it("Registrar Remisiones de Materiales", "remision", "/mov/rem"),
            sep(),
            it("Consultar las Exist. de Materiales", "stock", "/mov/stock")),
        sub("Mod. Servicio", "servicio",
            it("Reg. Recepcion de Vehiculos", "recepcion", "/mov/ent"),
            it("Generar Presupuesto", "presupuesto", "/mov/prep"),
            it("Registrar Orden de Trabajo", "ot", "/mov/ot"),
            it("Emitir O. de Trabajo (Interna)", "empleado", "/mov/oti"),
            it("Gestionar Materiales", "material", "/mov/con"),
            sep(),
            it("Registrar Control de Calidad", "calidad", "/mov/cc"),
            it("Registrar Garantia", "garantia", "/mov/gar")),
        sub("Mod. Gest. Cobros", "cobranza",
            it("Registrar Apertura/Cierre", "caja", "/mov/ape"),
            it("Emitir Facturacion", "cobranza", "/mov/fact"),
            it("Registrar Cobro", "cobro", "/mov/cob")),
        sub("Gest. Referenciales", "referenciales",
            sub("Mantener Ref. Mod. Compra", "mod_compra",
                ref("materiales"), ref("proveedores"), ref("depositos"), ref("motivos_ajuste"), ref("tipos_pago")),
            sub("Mantener Ref. Mod. Servicio", "servicio",
                sub("Mant. Ref. Rel. con Asegurado", "asegurado",
                    ref("asegurados"), ref("barrios"), ref("ciudades"), ref("nacionalidades")),
                sub("Mant. Ref. Rel. con Vehiculo", "vehiculo",
                    ref("vehiculos"), ref("marcas"), ref("modelos"), ref("tipos_vehiculo"), ref("colores"), ref("seguros")),
                ref("piezas"), ref("danhos"), ref("trabajos"), ref("motivos_garantia")),
            sub("Mantener Ref. Mod. Gest. Cobros", "cobranza",
                ref("cajas"), ref("impuestos"), ref("timbrados")),
            sub("Mantener Ref. Generales", "generales",
                ref("sucursales"), ref("cargos"), ref("funcionarios"), ref("usuarios"), ref("bancos"))),
        sub("Gest. Informes", "informes",
            it("Informe de Existencias de Materiales", "stock", "/inf/stock"),
            it("Gest. Informes simples de Ref.", "informe", "/inf/ref"),
            it("Resumen de Movimientos del Mes", "informes", "/inf/mov")),
        sub("Sistema", "sistema",
            it("Calendario", "calendario", "/sis/calendario"),
            it("Calculadora", "calculadora", "/sis/calculadora"),
            sep(),
            it("Bloquear", "bloquear", "#bloquear"),
            it("Cambiar Contraseña", "sesion", "/sis/pass"),
            it("Cerrar Sesion", "sesion", "#logout"),
            it("Maximizar", "maximizar", "#maximizar"),
            sep(),
            adm("Copia de Seguridad", "backup", "/sis/backup"),
            sep(),
            it("Salir del Sistema", "exit", "#logout")),
        sub("Ayuda", "info",
            it("Manual de Ayuda", "ayuda", "/sis/ayuda"),
            it("Acerca del Sistema", "info", "/sis/acerca")));

    private Menu() {}
}
