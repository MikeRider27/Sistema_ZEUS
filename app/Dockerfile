# ---------- build ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /src
COPY pom.xml .
RUN mvn -q -B dependency:go-offline
COPY src ./src
RUN mvn -q -B -DskipTests package

# ---------- runtime ----------
FROM eclipse-temurin:17-jre-alpine
# pg_dump para "Copia de Seguridad" (misma version mayor que el servidor)
RUN apk add --no-cache postgresql16-client curl \
 && addgroup -S zeus && adduser -S zeus -G zeus
WORKDIR /app
COPY --from=build /src/target/zeus.jar app.jar
USER zeus
EXPOSE 8080
HEALTHCHECK --interval=15s --timeout=5s --start-period=40s --retries=5 \
  CMD curl -fs http://localhost:8080/actuator/health/readiness || exit 1
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75 -Duser.timezone=America/Asuncion"
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
