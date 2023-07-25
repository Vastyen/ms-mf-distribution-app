FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-grasas-service.jar
ENTRYPOINT ["java","-jar","/ms-grasas-service.jar"]