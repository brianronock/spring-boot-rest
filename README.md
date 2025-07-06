# Step 2: Core API Ready

This step introduces the basic product model and the CRUD REST API.

### ✅ What's included:

- `Product` model with JPA and validation
- `ProductController` with basic CRUD
- `ProductRepo` using Spring Data JPA
- Basic error handling
- MySQL database setup

### ▶️ How to run:

1. **Clone the repository**: If you haven't already, clone the repository to your local machine.

```bash
git clone
git branch step-2-core-api-ready
```

2. **Navigate to the project directory**:

```bash
cd your-project-directory
```

3. **Install dependencies**: Make sure you have Maven installed and run the following command to install the necessary dependencies.

```bash
mvn clean install
```

4. **Ensure you have MySQL running**: Make sure your MySQL server is up and running.
5. **Create a MySQL database**: Create a database for your application, e.g., `product_db`.

```sql
CREATE DATABASE product_db;
```

6. **Update application.properties**(under /src/main/resources): Configure your Spring Boot application to connect to the MySQL database.

```bash
# Example:
# spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
# spring.datasource.username=your_username
# spring.datasource.password=your_password
```

7. **Run the application**: Use Maven to run the Spring Boot application.

```bash
mvn spring-boot:run
```
