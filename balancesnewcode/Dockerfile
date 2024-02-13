FROM registry.access.redhat.com/ubi9/openjdk-21:latest
WORKDIR /app
COPY . .
RUN mvn clean install -Dmaven.clean.failOnError=false
ADD /**/balances-0.0.1-SNAPSHOT.jar balances-app-service.jar
CMD ["java", "-jar", "balances-app-service.jar"
