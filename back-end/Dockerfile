FROM openjdk:17-alpine3.14
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} students_test.jar
ENTRYPOINT ["java","-jar","/students_test.jar"]