FROM registry.access.redhat.com/ubi9/openjdk-21:latest
USER root
RUN microdnf update -y && microdnf install gnutls unzip -y
WORKDIR /app
RUN curl -L https://services.gradle.org/distributions/gradle-8.6-bin.zip -o gradle.zip \
&& unzip gradle.zip \
&& rm gradle.zip
ENV PATH=$PATH:/app/gradle-8.6/bin
COPY . .
RUN gradle clean build
ADD build/libs/balances-0.0.1-SNAPSHOT.jar balances-app-service.jar
ENTRYPOINT ["java", "-jar", "balances-app-service.jar"]
