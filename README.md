# payment-service
Payment Service is responsible for processing student fee payments and managing transaction records.

## Features
- Process student payments
- Generate transaction reference
- Store payment details
- Retrieve payments by student
- Retrive payment by id

## Tech Stack
- Spring Boot 3.5.6
- Spring Data JPA
- H2 Database
- Swagger OpenAPI
- JUnit & Mockito

# Running the Service

mvn spring-boot:run

Runs on: http://localhost:8082

# API Endpoints
Method	Endpoint	Description
POST	/payments/makePayment	Make payment
GET	/payments/{id}	Get payment
GET	/payments/student/{studentId}	Get payments by student

# Sample Request
{
  "studentId": 1,
  "fee": 100,
  "feeType": "Tution Fees",
  "cardNumber": "1234567812341234",
  "cardType": "VISA"
}

# Swagger UI

http://localhost:8082/swagger-ui/index.html

# Notes

Payment status is set to SUCCESS (demo purpose)


