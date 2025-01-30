@echo off
:: Leer las variables del archivo .env
for /f "tokens=* delims=" %%i in (.env) do (
    set %%i
)

:: Ejecutar la aplicación
java -jar target/hotelprauriu-1.0.0.jar