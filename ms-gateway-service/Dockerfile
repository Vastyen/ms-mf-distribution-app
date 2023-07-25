FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-gateway-service.jar
ENTRYPOINT ["java","-jar","/ms-gateway-service.jar"]