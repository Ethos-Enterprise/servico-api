# Define a imagem base
FROM openjdk:17
WORKDIR /app
COPY target/servico-app.jar .
CMD ["java", "-jar", "servico-app.jar"]
