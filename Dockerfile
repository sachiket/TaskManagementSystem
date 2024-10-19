# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper files and permissions
COPY gradlew .
COPY gradle ./gradle

# Copy the rest of the project files
COPY . .

# Build the project using Gradle
RUN ./gradlew build --no-daemon

# Expose the port on which the app will run
EXPOSE 8081

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "build/libs/TaskManagementService-0.0.1-SNAPSHOT.jar"]
