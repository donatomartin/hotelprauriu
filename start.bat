@echo off
:: Leer las variables del archivo .env
for /f "tokens=* delims=" %%i in (.env) do (
    set %%i
)

:: Ejecutar la aplicación 
./mvnw.cmd clean spring-boot:run