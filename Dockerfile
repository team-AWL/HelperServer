FROM openjdk:17-jdk-buster AS builder

WORKDIR /app

COPY . .
RUN ./mvnw package -DskipTests

FROM openjdk:17-jdk-buster

ARG JAR_FILE=/app/target/HelperServer-0.0.1-SNAPSHOT.jar
COPY --from=builder ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]