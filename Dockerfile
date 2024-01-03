FROM openjdk:8-jdk-alpine

EXPOSE 8080

ARG JAR_FILE=target/grades_book.jar

ADD ${JAR_FILE} grades_book.jar

ENTRYPOINT ["java","-jar","/grades_book.jar"]