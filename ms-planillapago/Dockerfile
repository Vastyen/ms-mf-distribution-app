FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-planillapago-service.jar
ENTRYPOINT ["java","-jar","/ms-planillapago-service.jar"]