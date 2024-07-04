# Stage 1: Build Stage
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
# Clone the Spring Boot application from Git
RUN apt-get update && apt-get install -y git
RUN git clone https://anilemagia:ATBBdHLakbywDLeWpQaPywCfUs72B4C51027@bitbucket.org/thecreditapplication/restdocker.git .
# Build the Spring Boot application
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw package
#RUN ./mvnw dependency:resolve

# Stage 2: Package Stage
FROM openjdk:17.0.2-slim
WORKDIR /app
# Copy the JAR file from the build stage to the current location
COPY --from=build /app/target/poc_a-v1.jar .
# Expose the port your Spring Boot app will run on
EXPOSE 8080
# Command to run the Spring Boot application
CMD ["java", "-jar", "poc_a-v1.jar"]
