FROM amazoncorretto:21-alpine-jdk

ENV LANG = fr

# Set the working directory
WORKDIR /app

COPY . .

CMD ["sh", "entrypoint.sh"]