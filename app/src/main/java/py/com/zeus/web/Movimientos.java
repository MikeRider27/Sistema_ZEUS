package py.com.zeus.web;

import java.util.Map;
import java.util.Set;

/** Ventanas de los modulos Compra / Servicio / Gest. Cobros y la funcion abm_x que ejecutan. */
final class Movimientos {

    /** banderasAprob: banderas que requieren nivel de aprobador (en el legacy: Login.per < 4) */
    record Mov(String titulo, String icono, String funcion, Set<Integer> banderas, Set<Integer> banderasAprob) {}

    static final Map<String, Mov> MOVS = Map.ofEntries(
        Map.entry("ped",   new Mov("Registrar Pedidos de Materiales", "pedido", "abm_ped", Set.of(1, 3, 4), Set.of(4))),
        Map.entry("ordc",  new Mov("Generar Orden de Compra de Materiales", "ordc", "abm_ordc", Set.of(1, 3), Set.of(1, 3))),
        Map.entry("com",   new Mov("Registro de Facturas de Compra de Materiales", "compra", "abm_com", Set.of(1, 3), Set.of(3))),
        Map.entry("aju",   new Mov("Ajustes del Stock de Materiales", "material", "abm_aju", Set.of(1, 3), Set.of(1, 3))),
        Map.entry("rem",   new Mov("Registrar Remision de Materiales", "remision", "abm_rem", Set.of(1, 3, 4), Set.of())),
        Map.entry("stock", new Mov("Consultar las Existencias de Materiales", "stock", null, Set.of(), Set.of())),
        Map.entry("ent",   new Mov("Registro Recepcion de Vehiculos", "recepcion", "abm_ent", Set.of(1, 2, 3, 4), Set.of())),
        Map.entry("prep",  new Mov("Generar Presupuesto", "presupuesto", "abm_prep", Set.of(1, 2, 3, 4, 5), Set.of(4))),
        Map.entry("ot",    new Mov("Registrar Orden de Trabajo", "ot", "abm_ot", Set.of(1, 3), Set.of(3))),
        Map.entry("oti",   new Mov("Emitir Orden de Trabajo (Interna)", "empleado", "abm_ot", Set.of(2), Set.of())),
        Map.entry("con",   new Mov("Gestionar utilizacion de insumos", "material", "abm_con", Set.of(1, 3), Set.of())),
        Map.entry("cc",    new Mov("Registrar Control de Calidad", "calidad", "abm_cc", Set.of(1, 3), Set.of(1, 3))),
        Map.entry("gar",   new Mov("Registro Garantia", "garantia", "abm_gar", Set.of(1, 3, 4), Set.of())),
        Map.entry("ape",   new Mov("Registrar movimiento de Caja", "caja", "abm_ape", Set.of(1, 4), Set.of())),
        Map.entry("fact",  new Mov("Emitir Facturacion", "cobranza", "abm_fact", Set.of(1, 3), Set.of(3))),
        Map.entry("cob",   new Mov("Registrar Cobros", "cobro", "abm_cob", Set.of(1, 3), Set.of(3))));

    private Movimientos() {}
}
