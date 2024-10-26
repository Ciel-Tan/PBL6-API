# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy the entire source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Copy the built JAR file to the final image
COPY target/*.jar app.jar

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]