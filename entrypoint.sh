#!/bin/bash
ls
chmod +x mvnw
sed -i 's/\r$//' mvnw
./mvnw spring-boot:run
