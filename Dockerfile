FROM openjdk:21-slim-bullseye as build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

FROM openjdk:21-slim-bullseye
ARG DEPENDENCY=target/dependency
RUN apt-get update && \
    apt-get install -y --no-install-recommends fonts-liberation fontconfig && \
    fc-cache -f -v && \
    rm -rf /var/lib/apt/lists/*

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.brimstone.car_rest_service.CarRestServiceApplication"]
