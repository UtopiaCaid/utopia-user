FROM openjdk:8-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [“java”,“-jar”,“/app.jar”]

# 11-jdk-alpine
# ARG JAR_FILE=utopia-account-application/target/*.jar
