FROM openjdk:8-alpine
COPY ./target/application.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]