# Use an OpenJDK base image with version 8
FROM openjdk:8

# Set the working directory in the container
WORKDIR /app

# Copy the WAR file into the container
COPY target/fibonacci-project-1.0-SNAPSHOT.war /app/app.war

# Install curl for downloading the Jetty runner JAR
RUN apt-get update && apt-get install -y curl

# Download the Jetty runner JAR
RUN curl -o jetty-runner.jar https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-runner/9.4.42.v20210604/jetty-runner-9.4.42.v20210604.jar

# Expose the port that your application listens on (assuming it's port 8080)
EXPOSE 8080

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "jetty-runner.jar", "app.war"]
