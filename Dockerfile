FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8082

ARG JAR_FILE
ADD app.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]