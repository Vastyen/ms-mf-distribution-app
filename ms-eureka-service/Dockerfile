FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-eureka-service.jar
ENTRYPOINT ["java","-jar","/ms-eureka-service.jar"]