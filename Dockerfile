FROM openjdk:1.8-jdk-alpine
EXPOSE 8089
ADD target/kaddem-0.0.1-SNAPSHOT.jar td.jar
ENTRYPOINT ["java","-jar","/td.jar"]
