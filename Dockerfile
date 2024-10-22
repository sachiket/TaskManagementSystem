# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper files and build configuration files first (for caching dependencies)
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

# Ensure gradlew has executable permissions
RUN chmod +x ./gradlew

# Cache dependencies by running a dummy build
RUN ./gradlew build --no-daemon --parallel --stacktrace || return 0

# Now copy the rest of the project files
COPY . .

# Build the project using Gradle with parallel tasks
RUN ./gradlew build --no-daemon --parallel --stacktrace

# Expose the port on which the app will run
EXPOSE 8081

# Set an environment variable to control mode (app or test)
ENV APP_MODE=app

# CMD will conditionally run tests or the application
CMD if [ "$APP_MODE" = "test" ]; then ./gradlew test --no-daemon; else java -jar build/libs/TaskManagementService-0.0.1-SNAPSHOT.jar; fi
