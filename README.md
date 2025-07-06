# Step 4: Full Integration Testing (100% Coverage)

In this step, we introduce **comprehensive integration tests** for the backend using Spring Boot’s `@SpringBootTest` and `MockMvc`. These tests simulate real API behavior and verify the correctness of the application across controllers, services, and repositories.

---

## ✅ What Was Added in This Step

- A full `ProductApiIntegrationTest` class covering:
    - **All CRUD endpoints**
    - **Validation errors**
    - **Not-found and invalid ID scenarios**
- Achieved **100% test coverage** for the `/api/products` endpoint
- Preserved separation of test concerns via a dedicated test package

---

## 📁 Folder Structure at This Step
```
src/
├── main/
│   └── java/dev/rono/rest/
│       ├── controllers/
│       ├── models/
│       ├── services/
│       ├── repositories/
│       └── exceptions/
└── test/
└── java/dev/rono/rest/
├── ProductApiIntegrationTest.java     ← NEW: Full integration tests
└── RestApplicationTests.java          ← Basic context load test
```
---

## 🧪 How to Run the Tests

Run all tests via Maven:

```bash
./mvnw test
```
or

```bash
mvn test
```

Generate and view test coverage using JaCoCo:(Optional)
```bash
./mvnw clean test jacoco:report
```
The HTML coverage report will be generated at:
```
target/site/jacoco/index.html
```
Open this file in your browser to explore line-by-line coverage across your codebase.
```bash
open target/site/jacoco/index.html
```
---


## 🧪 Coverage Highlights
- **100% coverage** for the `/api/products` endpoint
- Tested all CRUD operations:
    - 	✅ GET /api/products
    -	✅ POST /api/products
    -	✅ GET /api/products/{id}
   -	✅ PUT /api/products/{id}
   -	✅ DELETE /api/products/{id}

Tested both valid and invalid scenarios for each endpoint:
-	Missing fields
-	Invalid prices
-	Negative or non-existent IDs
-	Validation error structure
-	Empty database behavior

---
## 📝 Key Features of the Integration Tests
- **Full API Simulation**: Tests mimic real HTTP requests/responses using `MockMvc`.
- **Validation Checks**: Ensures proper error messages for invalid inputs.
- **Database State Management**: Uses `@BeforeEach` to reset the database before each test, ensuring isolation.
- **Error Handling**: Validates that appropriate error messages are returned for invalid operations.
- **Separation of Concerns**: Tests are organized in a dedicated package to keep the main application code clean.
- **Comprehensive Coverage**: Achieved 100% coverage for the product API, ensuring all paths are tested.
- **Realistic Scenarios**: Tests cover both happy paths and edge cases, ensuring robustness.
- **Dependency Injection**: Uses `@Autowired` to inject necessary components like `MockMvc`, `ObjectMapper`, and `ProductRepository`.
- **JSON Serialization**: Utilizes `ObjectMapper` for converting objects to/from JSON, ensuring correct data formats.

---
## 🔐 Example Validations Tested
- **POST with invalid data:**
     - Empty name → "Name is Mandatory"
     - Negative or missing price → "Price must be positive" / "Price must be provided"
- **GET/PUT/DELETE with missing ID:**
    - Response: "Product not found with ID: ..."

---

## 🛠️ Tools Used
- **@SpringBootTest** to load full context
-	**@AutoConfigureMockMvc** to mock HTTP interactions
-	**MockMvc** for real request/response simulation
-	**ObjectMapper** for JSON serialization
-	**@BeforeEach** to clear DB before each test

⸻

▶️ Run the App
