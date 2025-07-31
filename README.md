# 🚗 CarHub - Used Car Marketplace (Microservices Architecture)

---

## Technologies & Tools

- **Languages & Frameworks:** Java, Spring Boot, Spring Security, Hibernate (JPA), RESTful APIs
- **Database:** MySQL
- **Caching:** Redis (used in Search Service)
- **Messaging:** Apache Kafka
- **Authentication:** JWT (role-based)
- **DevOps:** Docker, Jenkins, Terraform, AWS EKS, EC2, S3
- **Service Discovery:** Eureka Server
- **API Gateway:** Spring Cloud Gateway
- **Configuration Management:** Spring Cloud Config
- **API Testing/Docs:** Swagger, Postman
- **Version Control:** Git, GitHub

---

## Microservices Breakdown & My Role

### 1. **Auth Service**
- **Purpose:** User registration and login with role-based JWT authentication.
- **My Work:**
  - Developed secured login/signup endpoints.
  - Implemented Spring Security with custom filters for JWT validation.
  - Configured role-based access (Admin, Content Manager, CRM Team, User).
  - Stored encrypted passwords using BCryptPasswordEncoder.

---

### 2. **Car Inventory Service**
- **Purpose:** Manage car listings by Content Manager (manual add, VIN decoding, bulk Excel upload).
- **My Work:**
  - Built REST APIs for add/update/delete cars.
  - Integrated VIN decoder API; mapped decoded data to entities.
  - Handled bulk upload (Excel) using Apache POI.
  - Managed car status updates and approval flow.
  - Created entity relationships for brand, model, year, fuel type, and transmission.
  - Added Swagger for API documentation.

---

### 3. **Search Service**
- **Purpose:** Car search/filter functionality for users.
- **My Work:**
  - Used Feign Client to fetch car data from Inventory Service.
  - Implemented Redis caching for frequently searched parameters.
  - Optimized queries for multi-parameter filtering (brand, model, price range, etc.).
  - Configured cache eviction strategy for data consistency.

---

### 4. **Car Evaluation Service**
- **Purpose:** Schedule car evaluation visits and manage agent allocation.
- **My Work:**
  - Developed APIs to allocate agents and schedule visits.
  - Integrated location handling for nearest agent assignment.
  - Enabled photo uploads to AWS S3 during evaluation.
  - Designed entity structure for visit details, status, and photos.

---

### 5. **Notification Service**
- **Purpose:** Asynchronous SMS and email notifications.
- **My Work:**
  - Consumed Kafka topics for SMS (Twilio) and Email (SendGrid) triggers.
  - Configured Kafka producer in Evaluation Service to publish events.
  - Built consumer logic in Notification Service for event processing.
  - Handled failure scenarios with retry logic and logging.

---

### 6. **Eureka Server**
- **Purpose:** Service discovery for all microservices.
- **My Work:**
  - Configured service registration and discovery.
  - Enabled high availability setup for registry.

---

### 7. **API Gateway**
- **Purpose:** Central entry point with routing and security.
- **My Work:**
  - Defined routing rules for all services.
  - Implemented JWT validation filter at gateway level.
  - Configured global exception handling and fallback routes.

---

### 8. **Spring Cloud Config Server**
- **Purpose:** Centralized configuration management.
- **My Work:**
  - Externalized service configs to GitHub repo.
  - Enabled dynamic config refresh with Actuator endpoints.

---

## DevOps (My Contribution)

- Created **Dockerfiles** for each service and wrote `docker-compose.yml` for local orchestration.
- Provisioned **EKS cluster** using Terraform scripts.
- Deployed microservices to EKS as individual pods with LoadBalancer services.
- Managed Kubernetes manifests (Deployment, Service, ConfigMap, Secret) in `k8s-manifests/`.
- Set up **Jenkins CI/CD pipeline** on QA for automated build, test, and deploy.
- Stored evaluation photos securely in **AWS S3** from Car Evaluation Service.

---


