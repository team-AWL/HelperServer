FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17-jdk-buster

ARG JAR_FILE=/app/target/HelperServer-0.0.1-SNAPSHOT.jar
COPY --from=builder ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]