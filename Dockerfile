FROM maven:3.8.5-openjdk-17-slim as build

WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn clean package -DskipTests

FROM amazoncorretto:17

COPY src/main/resources/static/cars.csv cars.csv

COPY --from=build /app/target/**.jar app.jar