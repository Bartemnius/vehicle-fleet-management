
# 🚗 Vehicle Fleet Management System

A modular, microservices-based vehicle fleet management system built with Java, Spring Boot, and Docker. This project demonstrates a scalable architecture for managing vehicles, drivers, and reports, making it ideal for logistics, transportation, and fleet operations.

---

## 🧩 Architecture Overview

The system is composed of the following microservices:

- **Auth Service**: Handles user authentication and authorization.
- **Vehicle Service**: Manages vehicle data and operations.
- **Report Service**: Generates and manages reports related to fleet activities.

Each service is developed using Spring Boot and communicates with others through RESTful APIs. Docker is used for containerization, and Docker Compose orchestrates the multi-service environment.

---

## ⚙️ Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Maven**
- **MongoDb**

---

## 🚀 Getting Started

### Prerequisites

- Docker
- Docker Compose
- Java 17
- Maven

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/Bartemnius/vehicle-fleet-management.git
   cd vehicle-fleet-management
   ```

2. **Build the services:**

   ```bash
   mvn clean install
   ```

3. **Start the application:**

   ```bash
   docker-compose up --build
   ```

   This command will build and start all the services defined in the `docker-compose.yml` file.

---

## 📁 Project Structure

```
vehicle-fleet-management/
├── auth-service/        # Authentication and authorization service
├── vehicle-service/     # Vehicle management service
├── report-service/      # Reporting service
├── docker-compose.yml   # Docker Compose configuration
├── pom.xml              # Maven parent configuration
└── README.md            # Project documentation
```

---

## 🔐 Authentication

The Auth Service provides JWT-based authentication. Users must obtain a token by providing valid credentials, which must be included in the `Authorization` header for subsequent requests to secured endpoints.

---

## 📊 Reporting

The Report Service allows for the generation of various reports related to fleet operations. Reports can be customized based on parameters such as date ranges, vehicle IDs, and driver IDs.

---

## 🛠️ Future Enhancements

- **Frontend Development**: Implement a user interface using React.
- **Monitoring**: Add tools like Prometheus and Grafana for system monitoring.

---

