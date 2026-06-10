# Task Description

Create a multi-stage Dockerfile for a Spring Boot application. Stage 1 (Build): FROM maven:3.9-eclipse-temurin-17 AS builder, COPY pom.xml and src, RUN mvn clean package -DskipTests. Stage 2 (Runtime): FROM eclipse-temurin:17-jre-alpine, COPY --from=builder /app/target/*.jar app.jar, EXPOSE 8080, ENTRYPOINT ["java", "-jar", "app.jar"]. Explain why multi-stage builds reduce image size (no Maven, no source code, no build dependencies in final image). Show commands: docker build -t myapp:1.0 ., docker run -p 8080:8080 myapp:1.0.
