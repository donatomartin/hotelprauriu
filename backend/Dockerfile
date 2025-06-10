# Base stage: Build application
FROM amazoncorretto:21-alpine-jdk AS builder

ENV LANG=es
WORKDIR /app

COPY . .  

# Define entrypoint script
CMD ["sh", "entrypoint.sh"]
