# Use the official OpenJDK 8 image from Docker Hub
FROM openjdk:8
# Set working directory inside the container
WORKDIR /app
# Copy the compiled Java application JAR file into the container
COPY user-management-service/target/user-management-service-1.0.2-SNAPSHOT.jar /app
# Expose the port the Spring Boot application will run on
EXPOSE 9091
# Command to run the application
CMD ["java", "-jar", "user-management-service-1.0.2-SNAPSHOT.jar"]