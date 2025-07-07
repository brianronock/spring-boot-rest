# Start from an official Java runtime base image(Alpine Linux with OpenJDK 17)
FROM bellsoft/liberica-openjdk-alpine:17

# Set the working directory in the container
WORKDIR /app

# Argument for the JAR file location (when building the image)
ARG JAR_FILE=target/*.jar

# Copy the JAR file from the host to the container
COPY ${JAR_FILE} app.jar

#Expose port 8080 (optional, for documentation; actual binding is done at runtime)
EXPOSE 8080

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
