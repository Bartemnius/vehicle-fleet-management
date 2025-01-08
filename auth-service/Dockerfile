FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="bartemnius"
COPY target/auth-service-0.0.1-SNAPSHOT.jar /app/auth-service.jar
WORKDIR /app
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "auth-service.jar"]
