FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD http://192.168.2.18:8081/repository/maven-releases/tn/esprit/spring/kaddem/0.0.1/kaddem-0.0.1.jar kaddem-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/kaddem-0.0.1.jar"]
