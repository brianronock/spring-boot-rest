# 🎨 Step 7 – Spring Boot + Thymeleaf Web UI

This branch adds a traditional web interface to the existing Spring Boot REST API using **Thymeleaf** templates.

---

## ✨ Features

- Web-based views for managing `Product` entities
- Bootstrap 5 styling
- Thymeleaf templates with fragments (`header`, `footer`)
- Form validation with error messages
- Reusable layout structure

---

## 🖼️ Web Pages

| Page            | URL                      | Description                |
|-----------------|--------------------------|----------------------------|
| Product List    | `/products`              | Displays all products      |
| Create Product  | `/products/new`          | Form to add new product    |
| Edit Product    | `/products/edit/{id}`    | Form to update a product   |
| Delete Product  | `/products/delete/{id}`  | Deletes selected product   |

---

## 📁 Templates Structure

Located in `src/main/resources/templates/`:

- `products.html` – Product list view
- `product_form.html` – Create/edit form
- `fragments.html` – Shared header and footer

---

## 🧩 Controller

- `ProductWebController.java` handles all web requests
- Uses `ProductRepo` for database access
- Includes Thymeleaf form binding and validation

---

## 🎨 Styling

- Bootstrap 5 (via CDN)
- Optional: Add `styles.css` in `/static/css/`

---

## ▶️ Run the App

Make sure Java 17+ and Maven are installed:

```bash
./mvnw spring-boot:run
# or
mvn spring-boot:run
```

Then open [http://localhost:8080/products](http://localhost:8080/products)

---
