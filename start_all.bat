@echo off
echo Starting Microservices Ecosystem...

echo Starting Discovery Server (Port 8761)...
start cmd /k "cd discovery-server && ..\mvnw.cmd spring-boot:run"

echo Waiting for Eureka Discovery Server to start...
timeout /t 15

echo Starting API Gateway (Port 8080)...
start cmd /k "cd api-gateway && ..\mvnw.cmd spring-boot:run"

echo Starting Auth Service (Port 8081)...
start cmd /k "cd auth-service && ..\mvnw.cmd spring-boot:run"

echo Starting Account Service (Port 8082)...
start cmd /k "cd account-service && ..\mvnw.cmd spring-boot:run"

echo Starting Transaction Service (Port 8083)...
start cmd /k "cd transaction-service && ..\mvnw.cmd spring-boot:run"

echo All services have been launched in separate windows!
echo You can access the gateway at: http://localhost:8080
echo You can view the Eureka Dashboard at: http://localhost:8761
echo You can view Swagger UI for each service at:
echo - API Gateway: http://localhost:8080/swagger-ui.html
echo - Account Service: http://localhost:8082/swagger-ui/index.html
echo - Transaction Service: http://localhost:8083/swagger-ui/index.html
echo - Auth Service: http://localhost:8081/swagger-ui/index.html
