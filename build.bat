./mvnw.cmd clean package

echo "Now build"

docker buildx build --platform linux/amd64,linux/arm64 -t ghcr.io/dononitram/hotelprauriu/app:latest .