# Microservice Monorepo

Welcome to the Microservice Monorepo! This repository contains multiple microservices along with supporting services to create a complete microservices architecture. Below is a brief overview of the structure, technologies used, and instructions for setting up and running the services.

## Table of Contents

- [Microservices](#microservices)
  - [Jobs Microservice](#jobs-microservice)
  - [Company Microservice](#company-microservice)
  - [Review Microservice](#review-microservice)
- [Supporting Services](#supporting-services)
  - [Eureka Service Registry](#eureka-service-registry)
  - [Config Server](#config-server)
  - [API Gateway](#api-gateway)
- [Technologies Used](#technologies-used)
- [Setup and Running Instructions](#setup-and-running-instructions)
  - [Prerequisites](#prerequisites)
  - [Running the Services](#running-the-services)
- [Request Tracing with Zipkin](#request-tracing-with-zipkin)
- [Asynchronous Communication with RabbitMQ](#asynchronous-communication-with-rabbitmq)

## Microservices

### Jobs Microservice
Handles job-related operations such as posting jobs, searching jobs, and managing job applications.

### Company Microservice
Manages company information, including company profiles, and listings of available jobs from companies.

### Review Microservice
Allows users to leave reviews about companies and jobs, providing valuable feedback for other users.

## Supporting Services

### Eureka Service Registry
A service registry for discovering and registering microservices, enabling load balancing and failover.

### Config Server
A centralized configuration server to manage external properties for applications across all environments.

### API Gateway
Acts as an entry point for all client requests, routing them to appropriate microservices and providing security, monitoring, and logging.

## Technologies Used

- **Spring Boot**: Framework for building the microservices.
- **Eureka**: Service registry for service discovery.
- **Config Server**: Centralized configuration management.
- **API Gateway**: Routing and filtering client requests.
- **Zipkin**: Distributed tracing system for request tracking.
- **RabbitMQ**: Message broker for facilitating asynchronous communication between microservices.

## Setup and Running Instructions

### Prerequisites
- Java 17 or higher
- Maven
- Docker (for running RabbitMQ and Zipkin)

### Running the Services

1. **Clone the Repository**
   ```bash
   git clone https://github.com/smit-joshi814/Microservices.git
   cd microservice-monorepo
   ```

2. **Start RabbitMQ and Zipkin using Docker**
   ```bash
    docker run -d -p 9411:9411 openzipkin/zipkin

    docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management
   ```

3. **Build and Run the Config Server**
   ```bash
   cd config-server
   mvn clean install
   mvn spring-boot:run
   ```

4. **Build and Run the Eureka Service Registry**
   ```bash
   cd eureka-service-registry
   mvn clean install
   mvn spring-boot:run
   ```

5. **Build and Run the API Gateway**
   ```bash
   cd api-gateway
   mvn clean install
   mvn spring-boot:run
   ```

6. **Build and Run the Jobs Microservice**
   ```bash
   cd jobs-microservice
   mvn clean install
   mvn spring-boot:run
   ```

7. **Build and Run the Company Microservice**
   ```bash
   cd company-microservice
   mvn clean install
   mvn spring-boot:run
   ```

8. **Build and Run the Review Microservice**
   ```bash
   cd review-microservice
   mvn clean install
   mvn spring-boot:run
   ```

## Request Tracing with Zipkin
Zipkin is used for distributed tracing, allowing you to track the flow of requests across microservices. Zipkin will collect and visualize trace data, helping you diagnose latency issues and understand the behavior of the microservices.

To access the Zipkin dashboard:
```bash
http://localhost:9411
```

## Asynchronous Communication with RabbitMQ
RabbitMQ is used to handle asynchronous communication between microservices by providing message queues. This helps in decoupling services and improving the scalability and reliability of the system.

To access the RabbitMQ management interface:
```bash
http://localhost:15672
```
(Default username: `guest`, password: `guest`)


## Contact
For any questions or suggestions, please contact [https://www.linkedin.com/in/smit-joshi814/].

---

Happy Coding! 🚀