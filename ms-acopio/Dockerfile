FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-acopio.jar
ENTRYPOINT ["java","-jar","/ms-acopio.jar"]