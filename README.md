# POS Transaction Service

A Spring Boot backend application that simulates a Point-of-Sale (POS) transaction workflow.

## Features
- Create transactions
- Retrieve transactions
- Update transactions
- Delete transactions
- Discount and tax calculation
- Payment method handling
- Validation and exception handling
- Spring Security with JWT token generation

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Spring Security
- JWT
- Maven
- Postman

## API Endpoints

### Create Transaction
POST /transactions

### Get Transactions
GET /transactions

### Update Transaction
PUT /transactions/{id}

### Delete Transaction
DELETE /transactions/{id}

### Generate JWT Token
GET /generate-token

## Run the Application
1. Clone the repository
2. Update database credentials in `application.properties`
3. Run `PosTransactionServiceApplication`
4. Test APIs in Postman