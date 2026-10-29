# Sistema ZEUS

Sistema de gestión de taller de chapería y pintura: compras de materiales, servicio
(recepción → presupuesto → orden de trabajo → control de calidad), facturación y cobros.

- **v1 (2015-2016)**: aplicación de escritorio Java Swing + JDBC. Se conserva en [legacy/](legacy/).
- **v2**: aplicación web Spring Boot + PostgreSQL, todo en Docker. Mantiene el estilo del legacy:
  escritorio con barra de menú y ventanas internas, pestañas *Edición / Listado*, botonera
  *Agregar / Editar / Anular / Guardar / Cancelar / Salir* y la lógica en la base de datos
  (vistas `v_*` y funciones `abm_*(bandera, cod, ...)`).

## Puesta en marcha

```bash
cp .env.example .env        # cambiar las contraseñas
docker compose up -d --build
```

Abrir http://localhost:8080 (o el `ZEUS_PORT` del `.env`) e ingresar con **ADMIN** y la
contraseña de `ZEUS_ADMIN_PASSWORD`.

| Servicio | Imagen | Notas |
|----------|--------|-------|
| `db`  | `postgres:16-alpine` | Sin puerto publicado: solo accesible desde la red interna de compose. Volumen `zeus_db`. |
| `app` | build de `app/` (Maven → JRE 17) | Usuario de BD propio (`zeus_app`) sin privilegios de superusuario. Healthcheck en `/actuator/health`. |

La base se crea sola en el primer arranque con los scripts de [db/init/](db/init/)
(esquema, vistas, funciones, datos iniciales y usuarios). Para recrearla desde cero:
`docker compose down -v && docker compose up -d`.

Variables (`.env`): `POSTGRES_PASSWORD`, `ZEUS_DB_USER`, `ZEUS_DB_PASSWORD`, `ZEUS_ADMIN_PASSWORD`,
`ZEUS_PORT`, `ZEUS_BLOQUEO_MINUTOS` (bloqueo de pantalla por inactividad, 0 = desactivado) y
`ZEUS_SUBNET` (subred de la red interna; se fija explícitamente para no depender de los pools de Docker).

### Copia de seguridad

Menú *Sistema → Copia de Seguridad* (solo administradores) descarga un `pg_dump` completo. Para restaurar:

```bash
docker compose exec -T db psql -U postgres -d zeus < zeus_AAAAMMDD_HHMM.sql
```

## Estructura

```
db/init/
  01_schema.sql      tablas (convención del legacy: mar_cod, mar_des, est_cod...)
  02_views.sql       vistas v_* usadas por las ventanas
  03_functions.sql   abm_* (referenciales y movimientos), fn_login, fn_stock
  04_seed.sql        estados, referenciales y datos de ejemplo
  05_usuarios.sh     rol de la aplicación y usuario ADMIN (desde variables de entorno)
app/src/main/java/py/com/zeus/
  db/ConectarDB.java          equivalente a config.conectarDB (pool, parámetros, errores → mensajes)
  config/SecurityConfig.java  login contra fn_login() (antes frames.Login)
  web/Menu.java               barra de menú (antes frames.Menu)
  web/Referenciales.java      definición de las ventanas "Mantener ..." (antes paquete referenciales)
  web/Movimientos.java        ventanas de Compra / Servicio / Cobros
  web/Consultas.java          lista blanca de consultas de las ventanas (antes TblThread / CboThreadGT)
app/src/main/resources/
  templates/ref.html          plantilla única de referenciales
  templates/mov/*.html        una plantilla por movimiento
  static/js/zeus.js           JOptionPane, JTable, ManageButtons, pestañas... en el navegador
  static/js/escritorio.js     JDesktopPane: ventanas internas, barra de tareas, bloqueo
  static/img/                 íconos originales del legacy
```

## Módulos

| Menú | Ventanas |
|------|----------|
| Mod. Compra | Pedidos (con aprobación por detalle), Orden de Compra, Factura de Compra (ingresa stock), Ajustes de Stock, Remisiones entre sucursales (envío / recepción), Consulta de Existencias |
| Mod. Servicio | Recepción de Vehículos (checklist y daños), Presupuesto (piezas × nivel de daño + trabajos; aprobar / rechazar), Orden de Trabajo, O.T. Interna (asignación a funcionarios, imprimible), Gestionar Materiales (consumo por O.T.), Control de Calidad, Garantía |
| Mod. Gest. Cobros | Apertura / Cierre de Caja (con arqueo), Facturación (timbrado, IVA, al asegurado o a la cía. de seguros, imprimible), Cobros (múltiples formas, parciales) |
| Gest. Referenciales | 27 referenciales; los anulados pueden reactivarse |
| Gest. Informes | Existencias de materiales, listados de referenciales, resumen de movimientos del mes |
| Sistema / Ayuda | Calendario, Calculadora, Bloquear, Cambiar Contraseña, Cerrar Sesión, Copia de Seguridad, Manual, Acerca de |

Estados del vehículo: `REC` recibido → `PRE` presupuestado → `TAL` en taller → `TER` terminado (control de calidad aprobado) → `ENT` entregado (requiere factura).

Niveles de permiso (por cargo): 1 Administrador, 2 Gerente, 3 Encargado (aprueba y anula), 4 Operador.

## Qué se corrigió respecto al legacy

| Legacy | v2 |
|--------|----|
| SQL armado concatenando lo que escribía el usuario (`LIKE '%"+txt+"%'`, `ped_cod="+txt`) → inyección SQL | Solo consultas fijas de una lista blanca, con parámetros tipados |
| Conexión siempre como `postgres` / `root` hardcodeado ("poor login") | Rol `zeus_app` sin superusuario; credenciales por variables de entorno; BD sin puerto expuesto |
| Contraseñas en MD5 sin sal | bcrypt (`pgcrypto`), mínimo 6 caracteres, cambio de contraseña |
| Códigos con `MAX()+1` calculado en el cliente (dos usuarios obtenían el mismo) | Código asignado dentro de la función con bloqueo por tabla |
| Cabecera y detalles en llamadas separadas con `executeBatch` (podía quedar a medias) | Un movimiento = una función atómica con cabecera y detalles en `jsonb` |
| Stock sin control (podía quedar negativo) | `fn_stock` valida existencia; remisiones con stock "no disponible" en tránsito |
| Sintaxis MySQL en Postgres (`MONTH()`, `CURDATE()`, `IFNULL`): Presupuesto no funcionaba | Todo en PostgreSQL |
| `est_cod='VIG'` y `est_des='VIG'` mezclados: listados vacíos | Vistas uniformes con `est_cod` y `est_des` |
| `System.exit(1)` ante cualquier error SQL | Mensaje en diálogo; restricciones traducidas (duplicados, referencias, formatos) |
| Permisos rotos (`... && per!=0 \|\| per!=1` siempre verdadero) y `per` = código de cargo | Nivel de permiso explícito en el cargo, validado en servidor y en la BD |
| Módulos de Servicio y Cobros incompletos (`Integer.parseInt(null)`, `UPDATE facturacion SET fact_null='1'`...) | Circuito completo recepción → presupuesto → O.T. → calidad → factura → cobro → entrega |
| 3 intentos fallidos cerraban la aplicación | Bloqueo del usuario por 1 minuto |
| Un archivo de 500–1200 líneas por referencial | Definición declarativa + una plantilla |

## Desarrollo

Requiere Java 17 y Maven, con una base PostgreSQL inicializada con `db/init`:

```bash
cd app
ZEUS_DB_PASSWORD=... mvn spring-boot:run
```
