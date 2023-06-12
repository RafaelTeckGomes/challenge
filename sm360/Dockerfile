FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/sm360-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
EXPOSE 9070
ENTRYPOINT ["java","-jar","/app.jar"]