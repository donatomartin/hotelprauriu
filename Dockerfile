FROM amazoncorretto:21-alpine-jdk

# Set the working directory
WORKDIR /app

COPY ./target ./target

ENTRYPOINT ["java", "-jar", "target/hotelprauriu-1.0.0.jar"]