FROM openjdk:8-jdk-alpine

ADD target/admin.jar /app.jar
EXPOSE 9527
ENTRYPOINT ["java","-jar","/app.jar"]