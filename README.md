# Fintech Microservices Ecosystem

A robust, RESTful financial technology platform built on a microservices architecture using Java, Spring Boot, Spring Cloud, and MongoDB. This system provides secure user authentication, account management, and reliable fund transfer capabilities.

## Architecture Overview

The system is decomposed into specialized, loosely-coupled microservices that communicate securely and register with a central discovery server:

- **Discovery Server (Port 8761):** Netflix Eureka server acting as the service registry. All microservices register here to enable dynamic service discovery.
- **API Gateway (Port 8080):** Spring Cloud Gateway serving as the single entry point for the ecosystem. It routes external requests to the appropriate internal microservices and provides centralized routing.
- **Auth Service (Port 8081):** Manages user registration, authentication, and JWT (JSON Web Token) generation.
- **Account Service (Port 8082):** Handles core banking operations including account creation, balance inquiries, and account lifecycle management.
- **Transaction Service (Port 8083):** Manages secure fund transfers between accounts and maintains transaction history. Uses OpenFeign for inter-service communication with the Account Service.

## Technology Stack

- **Java 17+**
- **Spring Boot 3.2.x**
- **Spring Cloud 2023.0.x** (Eureka, Gateway, OpenFeign)
- **Spring Security & JWT** (Authentication and Authorization)
- **MongoDB** (NoSQL Database via Spring Data MongoDB)
- **Docker & Docker Compose** (Containerization & Local/Cloud Orchestration)
- **Lombok** (Boilerplate reduction)
- **Springdoc OpenAPI 2.4.0** (Swagger UI documentation)
- **Maven** (Dependency management and build tool)

## Prerequisites

Ensure the following tools are installed in your environment before proceeding:

- Java Development Kit (JDK) 17 or higher (or Docker)
- Docker & Docker Compose (Recommended) or MongoDB running locally
- Maven (optional, as the Maven Wrapper is included in the project)

## Running with Docker Compose (Recommended for AWS & Local Dev)

The easiest way to start all 5 microservices alongside a MongoDB database is using Docker Compose:

```bash
docker-compose up --build -d
```

To stop all services and keep data:
```bash
docker-compose down
```

To view logs:
```bash
docker-compose logs -f
```

## Running Manually

### 1. Build the Project

To compile the source code and download all dependencies, run:

**Windows:**
```cmd
.\mvnw.cmd clean package -DskipTests
```

**Linux / macOS:**
```bash
./mvnw clean package -DskipTests
```

### 2. Start the Microservices

You can start the entire ecosystem sequentially using the provided automated scripts:

**Windows Batch Script:**
```cmd
start_all.bat
```

**PowerShell Script:**
```powershell
.\start_all.ps1
```

*Alternatively, services can be run manually using `mvn spring-boot:run` within each respective subdirectory.*

## API Documentation and Access

Once the ecosystem is fully initialized, services can be interacted with via the API Gateway or documented directly through their respective Swagger UI endpoints.

- **API Gateway Access:** `http://localhost:8080`
- **Eureka Service Dashboard:** `http://localhost:8761`

### Swagger UI Endpoints

Each microservice generates its own OpenAPI documentation:

- **API Gateway:** `http://localhost:8080/swagger-ui.html`
- **Auth Service:** `http://localhost:8081/swagger-ui/index.html`
- **Account Service:** `http://localhost:8082/swagger-ui/index.html`
- **Transaction Service:** `http://localhost:8083/swagger-ui/index.html`

## Core API Routes

Requests should primarily be routed through the API Gateway (`http://localhost:8080`), which maps paths as follows:

- `/api/auth/**` -> Auth Service
- `/api/accounts/**` -> Account Service
- `/api/transactions/**` -> Transaction Service

### Example Payloads

**Create Account (Account Service):**
```json
{
  "customerName": "John Doe",
  "currency": "USD",
  "initialBalance": 1000.00
}
```

**Transfer Funds (Transaction Service):**
```json
{
  "sourceAccountId": "account_id_1", 
  "destinationAccountId": "account_id_2",
  "amount": 150.50
}
```

## Project Structure

The repository is structured as a Maven multi-module project:

- `fintech-microservices` (Root Project)
  - `discovery-server`: Service registry module.
  - `api-gateway`: Edge gateway and routing module.
  - `auth-service`: Security and identity module.
  - `account-service`: Core account management module.
  - `transaction-service`: Transaction and transfer module.
