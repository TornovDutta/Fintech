Write-Host "Starting Microservices Ecosystem..." -ForegroundColor Cyan

# 1. Start Discovery Server first
Write-Host "Starting Discovery Server (Port 8761)..." -ForegroundColor Green
Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "spring-boot:run" -WorkingDirectory ".\discovery-server" -WindowStyle Normal

# Wait a few seconds for Eureka to spin up
Start-Sleep -Seconds 15

# 2. Start the rest of the services
Write-Host "Starting API Gateway (Port 8080)..." -ForegroundColor Green
Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "spring-boot:run" -WorkingDirectory ".\api-gateway" -WindowStyle Normal

Write-Host "Starting Auth Service (Port 8081)..." -ForegroundColor Green
Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "spring-boot:run" -WorkingDirectory ".\auth-service" -WindowStyle Normal

Write-Host "Starting Account Service (Port 8082)..." -ForegroundColor Green
Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "spring-boot:run" -WorkingDirectory ".\account-service" -WindowStyle Normal

Write-Host "Starting Transaction Service (Port 8083)..." -ForegroundColor Green
Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "spring-boot:run" -WorkingDirectory ".\transaction-service" -WindowStyle Normal

Write-Host "All services have been launched in separate windows!" -ForegroundColor Cyan
Write-Host "You can access the gateway at: http://localhost:8080" -ForegroundColor Yellow
Write-Host "You can view the Eureka Dashboard at: http://localhost:8761" -ForegroundColor Yellow
