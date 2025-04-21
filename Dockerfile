# Use the official Maven image to build the app
FROM maven:3.8.5-openjdk-17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src /app/src

# Download dependencies and build the application (skipping tests)
RUN mvn clean package -DskipTests

# Use an OpenJDK image as the base for the final image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/idea-collaborate-backend-1.0.0.jar app.jar

# Expose port 8080
EXPOSE 8080

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]