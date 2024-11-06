FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/kaddem-0.0.3.jar td.jar
ENTRYPOINT ["java","-jar","/td.jar"]
