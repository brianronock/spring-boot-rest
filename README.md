# Step 6: Dockerization

In this step, we containerize our Spring Boot REST API using Docker, allowing for easy deployment, consistent environments, and simplified sharing of the application.

This setup enables anyone to build and run the project without needing to install Java or configure their environment manually — just Docker is required.

---

### ✅ What Was Added in This Step

- **Dockerfile:** Added a minimal Dockerfile to package the application into a lightweight container.
- **Docker Build + Run Instructions:** Clearly defined how to build the image and run the container.
- **Host-to-Container Database Access:** Configured the app to connect to a MySQL database running on the host machine using host.docker.internal.
- **.dockerignore:** Prevents unnecessary files (like target/) from being copied into the image, reducing build size and time.
---

### 📂 Folder Structure at This Step

```plaintext
src/
└── main/
    ├── java/dev/rono/rest/
    │   ├── controllers/
    │   ├── models/
    │   ├── services/
    │   ├── repositories/
    │   └── exceptions/
    └── resources/
        └── application.properties
        └── application-local.properties
Dockerfile          ← ✅ Added to build the container
.dockerignore       ← ✅ Ignore unnecessary files
pom.xml             ← No changes from previous step

```

---

### 🐳 How to Build and Run the Docker Image

1. Package the JAR
```bash
./mvnw clean package -DskipTests
```
2. Build the Docker image
```bash
docker build -t rest:1.0 .
```
3. Run the Docker container
```bash
docker run -d -p 8080:8080 --name rest-container rest:1.0
```
4. Access the application at `http://localhost:8080/api/products`

Important: Ensure your MySQL database is running and accessible from the Docker container. The application will connect to the database using `host.docker.internal` as the hostname.

---

### ⚙️ Configuration Notes
To connect the containerized app to your local MySQL, you need to ensure that your `application.properties` file is configured correctly. Here’s an example configuration:

```properties
spring.datasource.host=host.docker.internal
spring.datasource.port=3306
spring.datasource.dbname=your_database_name

spring.datasource.url=jdbc:mysql://${spring.datasource.host}:${spring.datasource.port}/${spring.datasource.dbname}
```
Sensitive or environment-specific credentials (like DB username and password) should be placed in `application-local.properties, which is ignored by Git.

Structure
```plaintext
spring.datasource.username=your_username
spring.datasource.password=your_password
```

The database-name, username, and password should match your local MySQL setup.

---

### 🧼 Cleanup Commands

To stop and remove the container:
```bash
docker stop rest-container
docker rm rest-container
```
### To remove the Docker image:
```bash
docker rmi rest:1.0
```
---

### 📝 Additional Notes
- Ensure Docker is installed and running on your machine.
- If you encounter issues with MySQL connectivity, check your Docker network settings and ensure the database is accessible from the container.
- For production deployments, consider using environment variables or Docker secrets for sensitive configurations instead of hardcoding them in the properties files.
- For more advanced configurations, you can explore Docker Compose to manage multi-container applications, such as running both the REST API and MySQL in separate containers.
- This setup is designed for development and testing purposes. For production, consider optimizing the Dockerfile, using multi-stage builds, and implementing security best practices.
- For more information on Docker best practices, refer to the [official Docker documentation](https://docs.docker.com/).
- For troubleshooting Docker issues, check the [Docker troubleshooting guide](https://docs.docker.com/engine/troubleshoot/).
- For more advanced Docker configurations, consider exploring Docker Compose for orchestrating multi-container applications.
