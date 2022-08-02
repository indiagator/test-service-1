FROM openjdk:8-jdk-alpine
MAINTAINER indiagator
COPY target/test-service-1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]