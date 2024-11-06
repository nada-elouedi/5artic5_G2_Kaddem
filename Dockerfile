FROM openjdk:17-jdk-alpine
EXPOSE 8089
ARG NEXUS_USERNAME=admin
ARG NEXUS_PASSWORD=nexus
ARG JAR_VERSION=0.0.3
ARG NEXUS_REPO_URL=http://192.168.56.2:8081/repository/kaddem_repository
RUN apk add --no-cache curl && \
    curl -u $NEXUS_USERNAME:$NEXUS_PASSWORD -o /td.jar \
    "$NEXUS_REPO_URL/tn/esprit/spring/kaddem/$JAR_VERSION/kaddem-$JAR_VERSION.jar" \
ENTRYPOINT ["java", "-jar", "/td.jar"]