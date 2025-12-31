# Order-Microservice


A **Spring Boot microservice** that provides RESTful endpoints for managing orders.  
This project demonstrates a platform-ready service with **OpenAPI documentation**, **Swagger UI**, and standard Spring Boot architecture.

---

## Features

- Built with **Spring Boot**
- RESTful endpoints for managing orders:
  - `GET /orders` — Retrieve all orders
  - `POST /orders` — Create a new order
  - `GET /orders/{id}` — Retrieve a specific order
  - `PUT /orders/{id}` — Update order status
  - `DELETE /orders/{id}` — Delete an order
- **OpenAPI 3.0 specification** for API contract (`openapi.yaml`)
- **Swagger UI** for interactive API testing
- Easy to extend with additional microservices

---

## Requirements

- Java 17+
- Maven 3+
- Optional: Docker/Kubernetes for containerized deployment
