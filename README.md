# Step 3: Basic API Testing with Postman

This step focuses on testing the core API endpoints using **Postman**. No major backend code changes were made, except the addition of basic global exception handling to improve API error responses.

---


## ✅ What Was Added in This Step

- A **Postman collection** for testing all API endpoints
- A **GlobalExceptionHandler** to return clean, standardized error messages
- Basic validation error handling using `@Valid` annotations in the Product model
- Updated `ProductController` to use `@Valid` for request validation
---

## 📂 Folder Structure at This Step

The backend structure remains unchanged from Step 2:
```
src/
└── main/
├── java/dev/rono/rest/
│    ├── controllers/
│    ├── models/
│    ├── services/
│    ├── repositories/
│    └── exceptions/       ← NEW: GlobalExceptionHandler added here
└── resources/
   └── application.properties
postman/
  └── step-3-api-tests.postman_collection.json
```
---

## 🧪 How to Test the API

1. **Import the Postman Collection**

    - Use the provided `postman_collection.json` (if available) or manually test the endpoints.
    - Test CRUD operations for the `/api/products` endpoint.
---

## 🧪 How to Test the API with Postman

### 1. Import the Postman Collection

- Download the file: [`step-3-api-tests.postman_collection.json`](../postman/step-3-api-tests.postman_collection.json)
- Open **Postman**
- Click **Import**, then select the `.json` file
- The collection will now appear in your sidebar

### 2. Make API Calls

You can now run all key endpoints:

| Method | Endpoint                  | Description              |
|--------|---------------------------|--------------------------|
| GET    | `/api/products`           | List all products        |
| POST   | `/api/products`           | Add a new product        |
| GET    | `/api/products/{id}`      | Get product by ID        |
| PUT    | `/api/products/{id}`      | Update a product         |
| DELETE | `/api/products/{id}`      | Delete a product         |

### 3. Try Validation

Send a `POST` request without a name or price to see how validation errors are now handled and returned in a clean JSON format.

---

## 🛡️ Global Exception Handling

A `@ControllerAdvice`-based `GlobalExceptionHandler` has been added to:

- Catch `MethodArgumentNotValidException` (validation errors)
- Handle `ResourceNotFoundException`
- Provide fallback responses for all uncaught exceptions

All errors are returned as structured JSON responses like:

```json
{
   "timestamp": "2025-06-22T12:34:56",
   "message": "Name is mandatory",
   "details": "/api/products"
}
```

## ▶️ Run the Application

```bash
./mvnw spring-boot:run
```
or if you have Maven installed:
```bash
mvn spring-boot:run
```
🌐 API Base URL

Ensure the Spring Boot server is running locally at:
```
http://localhost:8080
```
## 📚 Additional Resources
- [Postman Documentation](https://learning.postman.com/docs/getting-started/introduction/)
- [Spring Boot Exception Handling](https://spring.io/guides/gs/exception-handling/)
- [Spring Boot Validation](https://spring.io/guides/gs/validating-form-input/)
- [Spring Boot REST API Documentation](https://spring.io/guides/tutorials/rest-with-spring/)
- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Boot Global Exception Handling](https://www.baeldung.com/exception-handling-for-rest-with-spring)
- [Spring Boot Validation with Hibernate Validator](https://www.baeldung.com/spring-boot-bean-validation)
- [Postman Collections](https://learning.postman.com/docs/getting-started/collections/)
- [Postman Environments](https://learning.postman.com/docs/getting-started/environments-and-globals/)
- [Postman Variables](https://learning.postman.com/docs/getting-started/variables/)
- [Postman Testing](https://learning.postman.com/docs/writing-scripts/test-scripts/)
- [Postman API Documentation](https://learning.postman.com/docs/writing-scripts/api-documentation/)
