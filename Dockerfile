FROM openjdk:17-jdk-alpine

ARG NEXUS_USERNAME=admin
ARG NEXUS_PASSWORD=nexus
ARG NEXUS_REPO_URL=http://192.168.56.2:8081/repository/kaddem_repository
ARG JAR_VERSION=0.0.3


RUN apk add --no-cache curl && \
    curl -u $NEXUS_USERNAME:$NEXUS_PASSWORD -o /td.jar \
    "$NEXUS_REPO_URL/tn/esprit/spring/kaddem/$JAR_VERSION/kaddem-$JAR_VERSION.jar"

EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/td.jar"]
