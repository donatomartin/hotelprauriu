# Base stage: Build application
FROM amazoncorretto:21-alpine-jdk AS builder

# Set working directory
WORKDIR /app

# Copy only Maven wrapper & POM files (to leverage Docker caching)
COPY .mvn .mvn
COPY mvnw pom.xml ./

# Cache dependencies by resolving them first
RUN ./mvnw dependency:go-offline

# Copy the full source code only after dependencies are cached
COPY src ./src

# Build the application
RUN ./mvnw clean package

# Final stage: Run application
FROM amazoncorretto:21-alpine-jdk

ENV LANG=es
WORKDIR /app

# Copy only the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Define entrypoint script
CMD ["sh", "entrypoint.sh"]
