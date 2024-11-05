FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD target/kaddem-0.0.2.jar td.jar
ENTRYPOINT ["java","-jar","/td.jar"]
