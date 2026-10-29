#!/bin/bash
# Crea el rol de base de datos de la aplicacion y el usuario administrador del sistema.
# (El legacy se conectaba siempre como postgres/root, hardcodeado en conectarDB.java)
set -euo pipefail

psql -v ON_ERROR_STOP=1 \
     --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" \
     -v app_user="$ZEUS_DB_USER" -v app_pass="$ZEUS_DB_PASSWORD" \
     -v admin_pass="$ZEUS_ADMIN_PASSWORD" <<'EOSQL'
CREATE ROLE :"app_user" LOGIN PASSWORD :'app_pass';
GRANT CONNECT ON DATABASE :"DBNAME" TO :"app_user";
GRANT USAGE ON SCHEMA public TO :"app_user";
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO :"app_user";
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA public TO :"app_user";

INSERT INTO usuarios (usr_cod, usr_des, usr_pass, emp_ci)
VALUES (1, 'ADMIN', crypt(:'admin_pass', gen_salt('bf', 8)), 1000000);
EOSQL
