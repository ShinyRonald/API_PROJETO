FROM openjdk:17-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY ./target/API_PROJETO-0.0.1-SNAPSHOT.jar API_PROJETO-0-0-1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-jar", "/API_PROJETO-0-0-1-SNAPSHOT.jar" ]
