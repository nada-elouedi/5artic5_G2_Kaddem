FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD kaddem-0.0.2.jar /kaddem.jar
ENTRYPOINT ["java", "-jar", "/kaddem.jar"]
