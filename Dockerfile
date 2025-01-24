FROM amazoncorretto:21-alpine-jdk

# Set the working directory
WORKDIR /app

# Install Maven
RUN apk add --no-cache maven

# Copy project files
COPY . .

# Install dependencies
RUN mvn dependency:go-offline -B

# Build the project
RUN mvn clean package -DskipTests

ENTRYPOINT java -jar target/hotelprauriu-1.0.0.jar