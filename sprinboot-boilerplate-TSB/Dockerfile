FROM azul/zulu-openjdk:17 as builder
WORKDIR application
ARG JAR_FILE=libs
COPY target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM azul/zulu-openjdk:17-jre
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

CMD ["java", "org.springframework.boot.loader.JarLauncher"]


