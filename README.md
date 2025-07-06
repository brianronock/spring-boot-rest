# 🧩 Spring Boot REST API – Full Project

This is the main branch of the Spring Boot REST API project, combining all development steps:

1. 📦 Project Setup  
2. 🚀 Core API  
3. 🧪 API Testing (Postman)  
4. ✅ Integration Testing (100% Coverage)  
5. 📚 API Documentation (Swagger/OpenAPI)  

Each development step is preserved in its own dedicated Git branch (`step-1-initial`, `step-2-core-api`, etc.) for learning and incremental development.

---

## 🌱 What’s Inside

| Step                    | Description                                |
|-------------------------|--------------------------------------------|
| ✅ Step 1: Initial Setup | Basic Spring Boot structure                |
| ✅ Step 2: Core API      | CRUD for Product model                     |
| ✅ Step 3: API Testing   | Postman Collection + Exception Handling    |
| ✅ Step 4: Integration Tests | Full test suite with 100% coverage    |
| ✅ Step 5: API Docs      | Swagger UI via SpringDoc OpenAPI          |

---

## 🗂 Project Structure

```
src/
├── main/
│   ├── java/dev/rono/rest/
│   │   ├── controllers/         ← REST endpoints
│   │   ├── models/              ← Product entity
│   │   ├── repositories/        ← JPA Repository
│   │   ├── services/            ← Business logic
│   │   └── exceptions/          ← Global exception handling
│   └── resources/
│       └── application.properties
├── test/
│   └── java/dev/rono/rest/      ← Integration tests
postman/
└── step-3-api-tests.postman_collection.json
```

---

## ▶️ How to Run the App

Make sure Java 17+ and Maven are installed.

```bash
./mvnw spring-boot:run
# or
mvn spring-boot:run
```

---

## 📮 API Endpoints

Base URL: `http://localhost:8080/api/products`

| Method | Endpoint     | Description          |
|--------|--------------|----------------------|
| GET    | `/`          | List all products    |
| POST   | `/`          | Add a new product    |
| GET    | `/{id}`      | Get product by ID    |
| PUT    | `/{id}`      | Update a product     |
| DELETE | `/{id}`      | Delete a product     |

---

## 📬 Postman Collection

Found in `postman/step-3-api-tests.postman_collection.json`

🧪 Import it into Postman to test all API endpoints.

---

## 🧪 Integration Testing

- Tests use `@SpringBootTest` with `MockMvc`  
- 100% code coverage using **JaCoCo**  
- Run tests with:

```bash
./mvnw test
```

View coverage report at:  
`target/site/jacoco/index.html`

---

## 📚 Swagger API Docs

After running the app, visit:  
📄 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Generated automatically via `springdoc-openapi`.

---

## 🌿 Branch Navigation

To explore development step-by-step:

```bash
git checkout step-1-initial
git checkout step-2-core-api
git checkout step-3-api-tests
git checkout step-4-integration-tests
git checkout step-5-api-docs
```

---

## 💡 Next Steps

Planned next step:  
➡️ Step 6 – Dockerization

➡️ Step 7 – Adding Web UI with Thymleaf

➡️ Step 8 – Microservices with Spring Cloud

---
