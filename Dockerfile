FROM openjdk:17-jdk-alpine3.14 AS RUNTIME
WORKDIR /app/
COPY build/libs/data.lake.cosmosdb-0.0.1-SNAPSHOT.jar /app/mapeoCosmosdb-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/mapeoCosmosdb-0.0.1-SNAPSHOT.jar"]