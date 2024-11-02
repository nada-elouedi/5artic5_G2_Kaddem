FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD target/kaddem-0.0.2.jar /kaddem.jar
ENTRYPOINT ["java", "-jar", "/kaddem.jar"]
