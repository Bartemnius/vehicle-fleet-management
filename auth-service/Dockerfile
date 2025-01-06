
#start wiath a base image of java
FROM openjdk:17-jdk-slim

MAINTAINER bartemnius

#add aplication jar to image
COPY target/auth-service-0.0.1-SNAPSHOT.jar auth-service-0.0.1-SNAPSHOT.jar

#command used to run app
ENTRYPOINT ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]