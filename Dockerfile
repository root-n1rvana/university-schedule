# Build stage
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn package -DskipTests

# Final stage
FROM openjdk
WORKDIR /app
COPY --from=builder /app/target/*.jar web_jar_kocherga.jar
ENTRYPOINT ["java", "-jar", "web_jar_kocherga.jar"]