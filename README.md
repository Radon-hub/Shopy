# Shopy Microservices Platform

A **microservices e-commerce backend system** built with **Spring Boot**, using **gRPC**, **Kafka**, **PostgreSQL**, **Redis**, and **JWT authentication**. This platform consists of four separate services that communicate via gRPC and Kafka, each with its own Docker setup.  

## Repository Structure

```bash

/shopy # Main user service (REST APIs for users)

/shopy-vendor # Vendor service (REST APIs for vendors)

/shopy-order # Cart and order service (gRPC only)

/shopy-kafka # Kafka broker and event system

```
Each service can run **independently**, with Docker handling all dependencies automatically (PostgreSQL, Redis, Kafka, Prometheus, Grafana, etc.).  

---

## Services Overview

### 1. Shopy (User Service)

- **URL:** `http://localhost:8080`
- **Features:**  
  - User registration and login  
  - Address management  
  - Browse vendors and products  
  - Add products to cart and submit orders  
- **Authentication:** JWT with **access & refresh tokens**, passwords stored using **BCrypt**.  
- **Database:** PostgreSQL  
- **Cache:** Redis  
- **Communication:** gRPC to Shopy-Order for cart & order management, Kafka events for user registration.  

### 2. Shopy-Vendor (Vendor Service)

- **URL:** `http://localhost:8085`
- **Features:**  
  - Vendor registration and login  
  - Add/update products  
  - Manage inventory  
- **Authentication:** JWT with **access & refresh tokens**, passwords stored using **BCrypt**.  
- **Database:** PostgreSQL  
- **Cache:** Redis  
- **Communication:** gRPC and Kafka events with other services.  

### 3. Shopy-Order (Cart & Order Service)

- **gRPC only** (no REST API for users)  
- Handles user carts and orders.  
- Communicates with Shopy and Shopy-Vendor via **gRPC**.  
- Sends and consumes Kafka events (e.g., order expiration).  

### 4. Shopy-Kafka

- Dedicated Kafka broker and event system.  
- Used by all services for asynchronous communication.  

---

## Running the Services

Each service has its **own Dockerfile and docker-compose.yml**, so you can run them independently. Docker handles all dependencies automatically.  

Example commands:  

```bash
# Shopy
cd shopy
docker-compose up -d

# Shopy-Vendor
cd shopy-vendor
docker-compose up -d

# Shopy-Order
cd shopy-order
docker-compose up -d

# Shopy-Kafka
cd shopy-kafka
docker-compose -f docker-compose.kafka.yml up -d
```

## Access user and vendor services in your browser:

**User service:** http://localhost:8080

**Vendor service:** http://localhost:8085

## Authentication Flow
Users and vendors login with JWT-based authentication.

**- Access token:** Short-lived token for requests

**- Refresh token:** Used to generate a new access token

- Passwords are hashed using BCrypt.

## API Documentation

- REST APIs (Shopy & Shopy-Vendor): Use the provided Postman collection.

- gRPC APIs (Shopy-Order): Also included in the Postman collection (with gRPC support).

You can import the Postman collection from:

```bash

/docs/Shopy.postman_collection.json

```
## Architecture Overview
Microservices communicate using **gRPC** for synchronous operations and **Kafka** for asynchronous events.

- Each service has its own **PostgreSQL** and **Redis** instances.

- Observability via **Prometheus & Grafana**, and **Spring Actuator** endpoints.

## Workflow Example:

- User adds a product to cart via **Shopy REST API**.

- Shopy communicates with Shopy-Order via **gRPC** to update the cart.

- Order expiration or user registration triggers **Kafka events**, which other services can consume.

## Contributing
- Clone the repo

- Run each service with Docker Compose

- Import Postman collection to test APIs

- Generate gRPC clients if needed (protos are included in each service)
