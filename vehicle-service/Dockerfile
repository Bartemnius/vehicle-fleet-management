FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="bartemnius"
COPY target/vehicle-service-0.0.1-SNAPSHOT.jar /app/vehicle-service.jar
WORKDIR /app
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "vehicle-service.jar"]
