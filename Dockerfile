# Stage 1: Build Stage
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

# Install git
RUN apt-get update && apt-get install -y git

# Build arguments for GitHub credentials
ARG GITHUB_USER
ARG GITHUB_TOKEN

# Clone the Spring Boot application from Git
RUN git clone https://github.com/anlkmr/poc_a.git
# Change working directory to the cloned repository
WORKDIR /app/poc_a

# Ensure the Maven wrapper is executable
RUN chmod +x mvnw

# Fetch dependencies
RUN ./mvnw dependency:resolve

# Build the Spring Boot application
RUN ./mvnw package

# Stage 2: Package Stage
FROM openjdk:17.0.2-slim
WORKDIR /app

# Copy the JAR file from the build stage to the current location
COPY --from=build /app/poc_a/target/poc_a-v1.jar .

# Expose the port your Spring Boot app will run on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "poc_a-v1.jar"]
