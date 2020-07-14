FROM openjdk:latest
VOLUME /root/.m2
EXPOSE 8080
WORKDIR /agb/
ADD target/gcp-helloworld-service-0.0.1-SNAPSHOT.jar /agb/gcp-helloworld-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev","-jar","/agb/gcp-helloworld-service.jar"]