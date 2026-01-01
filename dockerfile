
FROM amazoncorretto:21-alpine

# Create a volume for temporary files used by Spring Boot
VOLUME /tmp

# Define build-time argument for the jar file
ARG JAR_FILE=target/*.jar

# Copy the jar file into the container
COPY ${JAR_FILE} app.jar

# Set the command to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app.jar"]