package dev.rono.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.rono.rest.models.Product;
import dev.rono.rest.repositories.ProductRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// This class is an integration test for the Product API.
// It uses Spring Boot's testing framework to test the REST API endpoints for products.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// This annotation is used to indicate that this is a Spring Boot integration test
// and it should start the application context with a random port for testing.
// The webEnvironment attribute specifies that a web environment should be started,
// allowing us to test the REST API endpoints.
@AutoConfigureMockMvc
// This annotation is used to configure MockMvc, which allows us to perform HTTP requests
// and assertions on the responses in our integration tests.
// It provides a way to test the REST API endpoints without starting a full server.
public class ProductApiIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    // This field is used to perform HTTP requests and assertions in the integration tests.
    // It is autowired by Spring, allowing us to use it in our test methods.

    @Autowired
    private ObjectMapper objectMapper;
    // This field is used to convert Java objects to JSON and vice versa in the integration tests.
    // It is autowired by Spring, allowing us to use it in our test methods.

    @Autowired
    private ProductRepo productRepo;
    // This field is used to access the Product repository for database operations in the integration tests.
    // It is autowired by Spring, allowing us to use it in our test methods.

    @BeforeEach
    void setUp() {
        // This method is executed before each test method in this class.
        // It can be used to set up any necessary data or configurations for the tests.
        productRepo.deleteAll();
        // Clear the Product repository before each test to ensure a clean state.
    }

    // Additional test methods will be added here to test the Product API endpoints.
    // For example, we can add tests for GET, POST, PUT, and DELETE operations on products.
    // Each test method will use the MockMvc object to perform HTTP requests and assertions on the responses.

    @Test
    void testCreateAndGetProduct() throws Exception {
        // This method is a test case that will create a new product and then retrieve it.
        // It uses the MockMvc object to perform HTTP requests and assertions on the responses.

        // Prepare JSON data for the new product
        Product newProduct = new Product("IntegrationTestProd", 42.0);
        String json = objectMapper.writeValueAsString(newProduct);


        // Perform a POST request to create the new product
        mockMvc.perform(post("/api/products")
                    .contentType("application/json")
                    .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("IntegrationTestProd"))
                .andExpect(jsonPath("$.price").value(42.0));

        // Now the product should be in the database, so we can retrieve it abd verify its details
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1)) // Expect one product in the list
                .andExpect(jsonPath("$[0].name").value("IntegrationTestProd"));
    }

    @Test
    void testGetNonExistentProduct() throws Exception {
        // This method is a test case that will attempt to retrieve a product that does not exist.
        // It uses the MockMvc object to perform an HTTP GET request and expects a 404 Not Found response.

        long nonExistentId = 999L; // Assuming this ID does not exist in the database
        mockMvc.perform(get("/api/products/" + nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Product not found with ID: " + nonExistentId));
    }

    @Test
    void testCreateProductWithInvalidData() throws Exception {
        // This method is a test case that will attempt to create a product with invalid data.
        // It uses the MockMvc object to perform an HTTP POST request and expects a 400 Bad Request response.

        Product invalidProduct = new Product("", -10.0); // Invalid name and negative price
        String json = objectMapper.writeValueAsString(invalidProduct);

        mockMvc.perform(post("/api/products")
                    .contentType("application/json")
                    .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is Mandatory"))
                .andExpect(jsonPath("$.price").value("Price must be positive"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        // This method is a test case that will update an existing product.
        // It uses the MockMvc object to perform an HTTP PUT request and expects a 200 OK response.

        // First, create a product to update
        Product existingProduct = new Product("OldName", 20.0);
        Product savedProduct = productRepo.save(existingProduct);

        // Prepare updated product data
        Product updatedProduct = new Product("UpdatedName", 30.0);
        String json = objectMapper.writeValueAsString(updatedProduct);

        // Perform a PUT request to update the product
        mockMvc.perform(put("/api/products/" + savedProduct.getId())
                    .contentType("application/json")
                    .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedProduct.getId()))
                .andExpect(jsonPath("$.name").value("UpdatedName"))
                .andExpect(jsonPath("$.price").value(30.0));
    }

    @Test
    void testDeleteProduct() throws Exception {
        // This method is a test case that will delete an existing product.
        // It uses the MockMvc object to perform an HTTP DELETE request and expects a 204 No Content response.

        // First, create a product to delete
        Product existingProduct = new Product("ToBeDeleted", 15.0);
        Product savedProduct = productRepo.save(existingProduct);

        // Perform a DELETE request to delete the product
        mockMvc.perform(delete("/api/products/" + savedProduct.getId()))
                .andExpect(status().isNoContent());

        // Verify that the product has been deleted
        mockMvc.perform(get("/api/products/" + savedProduct.getId()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Product not found with ID: " + savedProduct.getId()));
    }

    @Test
    void testUpdateNonExistentProduct() throws Exception {
        // This method is a test case that will attempt to update a product that does not exist.
        // It uses the MockMvc object to perform an HTTP PUT request and expects a 404 Not Found response.

        long nonExistentId = 999L; // Assuming this ID does not exist in the database
        Product updatedProduct = new Product("UpdatedName", 30.0);
        String json = objectMapper.writeValueAsString(updatedProduct);

        mockMvc.perform(put("/api/products/" + nonExistentId)
                    .contentType("application/json")
                    .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Product not found with ID: " + nonExistentId));
    }

    @Test
    void testDeleteNonExistentProduct() throws Exception {
        // This method is a test case that will attempt to delete a product that does not exist.
        // It uses the MockMvc object to perform an HTTP DELETE request and expects a 404 Not Found response.

        long nonExistentId = 999L; // Assuming this ID does not exist in the database
        mockMvc.perform(delete("/api/products/" + nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Product not found with ID: " + nonExistentId));
    }

    @Test
    void testGetAllProductsWhenEmpty() throws Exception {
        // This method is a test case that will retrieve all products when the database is empty.
        // It uses the MockMvc object to perform an HTTP GET request and expects a 200 OK response with an empty list.

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0)); // Expect an empty list
    }

    @Test
    void testGetProductByIdWhenEmpty() throws Exception {
        // This method is a test case that will attempt to retrieve a product by ID when the database is empty.
        // It uses the MockMvc object to perform an HTTP GET request and expects a 404 Not Found response.

        long nonExistentId = 999L; // Assuming this ID does not exist in the database
        mockMvc.perform(get("/api/products/" + nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Product not found with ID: " + nonExistentId));
    }

    @Test
    void testCreateProductWithMissingFields() throws Exception {
        // This method is a test case that will attempt to create a product with missing fields.
        // It uses the MockMvc object to perform an HTTP POST request and expects a 400 Bad Request response.

        //Product invalidProduct = new Product(null, null); // Missing name and price
        String json = "{ \"name\": null, \"price\": null }";
        mockMvc.perform(post("/api/products")
                    .contentType("application/json")
                    .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is Mandatory"))
                .andExpect(jsonPath("$.price").value("Price must be provided"));
    }

    @Test
    void testCreateProductWithInvalidPrice() throws Exception {
        // This method is a test case that will attempt to create a product with an invalid price.
        // It uses the MockMvc object to perform an HTTP POST request and expects a 400 Bad Request response.

        Product invalidProduct = new Product("InvalidPriceProduct", -5.0); // Negative price
        String json = objectMapper.writeValueAsString(invalidProduct);

        mockMvc.perform(post("/api/products")
                    .contentType("application/json")
                    .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.price").value("Price must be positive"));
    }

    @Test
    void testUpdateProductWithInvalidData() throws Exception {
        // This method is a test case that will attempt to update a product with invalid data.
        // It uses the MockMvc object to perform an HTTP PUT request and expects a 400 Bad Request response.

        // First, create a product to update
        Product existingProduct = new Product("OldName", 20.0);
        Product savedProduct = productRepo.save(existingProduct);

        // Prepare updated product data with invalid fields
        Product updatedProduct = new Product("", -10.0); // Invalid name and negative price
        String json = objectMapper.writeValueAsString(updatedProduct);

        // Perform a PUT request to update the product
        mockMvc.perform(put("/api/products/" + savedProduct.getId())
                    .contentType("application/json")
                    .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is Mandatory"))
                .andExpect(jsonPath("$.price").value("Price must be positive"));
    }

    @Test
    void testGetProductByIdWithInvalidId() throws Exception {
        // This method is a test case that will attempt to retrieve a product by an invalid ID.
        // It uses the MockMvc object to perform an HTTP GET request and expects a 404 Not Found response.

        long invalidId = -1L; // Assuming negative IDs are invalid
        mockMvc.perform(get("/api/products/" + invalidId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Product not found with ID: " + invalidId));
    }

    @Test
    void testDeleteProductWithInvalidId() throws Exception {
        // This method is a test case that will attempt to delete a product with an invalid ID.
        // It uses the MockMvc object to perform an HTTP DELETE request and expects a 404 Not Found response.

        long invalidId = -1L; // Assuming negative IDs are invalid
        mockMvc.perform(delete("/api/products/" + invalidId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Product not found with ID: " + invalidId));
    }

    @Test
    void testGetAllProductsWithMultipleEntries() throws Exception {
        // This method is a test case that will retrieve all products when there are multiple entries in the database.
        // It uses the MockMvc object to perform an HTTP GET request and expects a 200 OK response with a list of products.

        // Create multiple products
        Product product1 = new Product("Product1", 10.0);
        Product product2 = new Product("Product2", 20.0);
        productRepo.save(product1);
        productRepo.save(product2);

        // Perform a GET request to retrieve all products
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)) // Expect two products in the list
                .andExpect(jsonPath("$[0].name").value("Product1"))
                .andExpect(jsonPath("$[1].name").value("Product2"));
    }

    @Test
    void testGetProductByIdWithValidId() throws Exception {
        // This method is a test case that will retrieve a product by a valid ID.
        // It uses the MockMvc object to perform an HTTP GET request and expects a 200 OK response with the product details.

        // First, create a product to retrieve
        Product existingProduct = new Product("ValidProduct", 25.0);
        Product savedProduct = productRepo.save(existingProduct);

        // Perform a GET request to retrieve the product by ID
        mockMvc.perform(get("/api/products/" + savedProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedProduct.getId()))
                .andExpect(jsonPath("$.name").value("ValidProduct"))
                .andExpect(jsonPath("$.price").value(25.0));
    }


}
