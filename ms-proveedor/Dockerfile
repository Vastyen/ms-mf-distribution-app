FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-proveedor-service.jar
ENTRYPOINT ["java","-jar","/ms-proveedor-service.jar"]