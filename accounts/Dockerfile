FROM openjdk:21
EXPOSE 8080:8080
ADD target/accounts-0.0.1-SNAPSHOT.jar accounts-app-service.jar
ENTRYPOINT ["java","-jar","accounts-app-service.jar"]