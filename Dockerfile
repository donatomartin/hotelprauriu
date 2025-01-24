FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

COPY ./target/*.jar /app/*.jar

CMD ["java", "-jar", "*.jar"]