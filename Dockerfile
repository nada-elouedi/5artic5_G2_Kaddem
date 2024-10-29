FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD kaddem-0.0.3.jar  /kaddem.jar
ENTRYPOINT ["java", "-jar", "/kaddem.jar"]
