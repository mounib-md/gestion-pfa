# Étape 1 : Construire l'application avec Maven
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : Lancer l'application
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/GestionPfaBack-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]