# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper files and permissions
COPY gradlew .
COPY gradle ./gradle

# Ensure gradlew has executable permissions
RUN chmod +x ./gradlew

# Copy the rest of the project files
COPY . .

# Build the project using Gradle
RUN ./gradlew build --no-daemon

# Expose the port on which the app will run
EXPOSE 80

# Set an environment variable to control mode (app or test)
ENV APP_MODE=app

# CMD will conditionally run tests or the application
CMD if [ "$APP_MODE" = "test" ]; then ./gradlew test --no-daemon; else java -jar build/libs/TaskManagementService-0.0.1-SNAPSHOT.jar; fi
