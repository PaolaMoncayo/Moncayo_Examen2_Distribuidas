# Usar una imagen de OpenJDK
FROM openjdk:17-jdk-slim

# Setear el directorio de trabajo
WORKDIR /app

# Volumen montado
VOLUME /tmp

# Copiar el JAR del proyecto al contenedor
COPY target/micro-estudiantes-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del servicio
EXPOSE 8002

# Ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
