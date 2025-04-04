# Use a base image with JDK 17
FROM eclipse-temurin:17-jdk AS builder

# Set working directory
WORKDIR /app

# Install sbt
RUN apt-get update && apt-get install -y curl gnupg2 && \
    echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list && \
    curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x99E82A75642AC823" | apt-key add - && \
    apt-get update && apt-get install -y sbt

# Copy project files
COPY . .

# Build the application
RUN sbt compile

# Create a runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built application from the builder stage
COPY --from=builder /app /app

# Define the entry point
CMD ["java", "-jar", "target/scala-3.4.2/foresttwin-assembly.jar"]
