FROM openjdk:19-jdk-slim as builder
WORKDIR application
ADD target/kbe-spring-boot-rest-0.0.1-SNAPSHOT.jar ./
RUN java -Djarmode=layertools -jar kbe-spring-boot-rest-0.0.1-SNAPSHOT.jar extract

#ADD maven/KBE-SpingBootRest-0.0.1-SNAPSHOT.jar ./
#RUN java -Djarmode=layertools -jar KBE-SpingBootRest-0.0.1-SNAPSHOT.jar extract

# below is initail and worked one from running on commmad terminal
#ADD target/KBE-SpingBootRest-0.0.1-SNAPSHOT.jar ./
#RUN java -Djarmode=layertools -jar KBE-SpingBootRest-0.0.1-SNAPSHOT.jar extract


FROM openjdk:19-jdk-slim

WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]


