# Stage 1: Build the application
FROM maven:3.9.3-eclipse-temurin-17 AS builder

# Set the working directory inside the builder container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the entire source code and build the application
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-alpine

# Set the working directory in the runtime container
WORKDIR /app

# Copy the jar file from the builder stage to the runtime stage
COPY --from=builder /app/target/*.jar /app/app.jar

# Expose the port the app runs on
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]

