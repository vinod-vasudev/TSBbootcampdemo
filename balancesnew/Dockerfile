# Use the Red Hat Universal Base Image (UBI) as the base image
FROM registry.access.redhat.com/ubi9/openjdk-21:latest
# Install Maven
#RUN yum install -y maven
# Set environment variables for Maven
#ENV MAVEN_HOME=/usr/share/maven
#ENV MAVEN_CONFIG=/root/.m2
# Set working directory
WORKDIR /app
# Copy the Maven project file (pom.xml) to the container
COPY . .

# Download dependencies and build project
RUN mvn clean install -DskipTests
# Copy the jar file from the target directory to the working directory
COPY /app/*/balances-0.0.1-SNAPSHOT.jar .
# Define the command to run when the container starts
CMD ["java", "-jar", "balances-0.0.1-SNAPSHOT.jar"]
