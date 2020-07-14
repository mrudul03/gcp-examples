FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
WORKDIR /startup/
ADD target/demo-products-service-0.0.1-SNAPSHOT.jar /startup/demo-products-service.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/startup/demo-products-service.jar"]