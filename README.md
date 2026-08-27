# Fintech API

A RESTful Fintech Application built with Java, Spring Boot, and MongoDB. This application allows users to create bank accounts, check balances, and securely transfer funds between accounts.

## Tech Stack

- **Java 17**
- **Spring Boot** (v4.1.1)
- **MongoDB** (Spring Data MongoDB)
- **Lombok**
- **Springdoc OpenAPI** (Swagger UI)
- **Maven**

## Features

- **Account Management**: Create accounts and retrieve account details/balances.
- **Transactions**: Transfer money between different accounts safely.
- **Validation**: Input validation for account creation and money transfers.
- **API Documentation**: Auto-generated Swagger documentation.

## Prerequisites

- Java 17 or higher
- Maven (included via Maven Wrapper)
- MongoDB (running locally or accessible via URI)

## Getting Started

### 1. Configure the Environment

The application configuration relies on a `.env` file located in the project root. Ensure it has the correct MongoDB URI and Server Port:

```env
MONGO_URI=mongodb://localhost:27017/fintechdb
SERVER_PORT=8080
```

### 2. Build and Run

You can run the application using the included Maven wrapper:

**On Linux/macOS:**
```bash
./mvnw clean install
./mvnw spring-boot:run
```

**On Windows:**
```cmd
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

The server will start on `http://localhost:8080` (or the port specified in your `.env` file).

## API Endpoints

Once the application is running, you can access the Swagger UI for interactive API documentation at:
`http://localhost:8080/swagger-ui.html`

### Accounts

- `POST /api/accounts`
  - Create a new account.
  - **Body Payload:**
    ```json
    {
      "customerName": "John Doe",
      "currency": "USD",
      "initialBalance": 1000.00
    }
    ```
- `GET /api/accounts`
  - Retrieve a list of all accounts.
- `GET /api/accounts/{id}`
  - Retrieve details for a specific account.

### Transactions

- `POST /api/transactions/transfer`
  - Transfer funds from one account to another.
  - **Body Payload:**
    ```json
    {
      "sourceAccountId": "64d...", 
      "destinationAccountId": "64e...",
      "amount": 150.50
    }
    ```
- `GET /api/transactions/account/{accountId}`
  - Retrieve all transactions (credits and debits) for a specific account.

## Project Structure

- `controller`: Contains the REST API endpoints (`AccountController`, `TransactionController`).
- `service`: Business logic for accounts and transactions.
- `model`: MongoDB document entities (`Account`, `Transaction`).
- `repository`: Spring Data MongoDB repositories.
- `dto`: Data Transfer Objects for API requests (`AccountRequest`, `TransferRequest`).
- `config`: Application configuration (e.g., `SwaggerConfig`).
