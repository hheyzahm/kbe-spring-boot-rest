FROM openjdk:19-jdk-slim


ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

WORKDIR application

COPY ../../target/kbe-spring-boot-rest-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "kbe-spring-boot-rest-0.0.1-SNAPSHOT.jar"]