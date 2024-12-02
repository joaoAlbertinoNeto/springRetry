# Use a imagem base do Java
FROM openjdk:17-jdk-slim

# Adicione o JAR do seu projeto
COPY target/springrtr-0.1.jar app.jar

# Comando para executar o JAR
ENTRYPOINT ["java","-jar","/app.jar"]