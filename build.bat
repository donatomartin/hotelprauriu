echo "Running Maven clean package..."
call .\mvnw.cmd clean package
if %ERRORLEVEL% neq 0 (
    echo Maven build failed.
    exit /b %ERRORLEVEL%
)

echo "Running Docker buildx build..."
call docker buildx build --platform linux/amd64,linux/arm64 -t ghcr.io/dononitram/hotelprauriu/app:latest --push .
if %ERRORLEVEL% neq 0 (
    echo Docker build failed.
    exit /b %ERRORLEVEL%
)