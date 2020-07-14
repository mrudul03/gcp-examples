FROM openjdk:11-jdk-slim
VOLUME /tmp
EXPOSE 8080
WORKDIR /startup/
ADD target/video-service-0.0.1-SNAPSHOT.jar /startup/video-service.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/startup/video-service.jar"]