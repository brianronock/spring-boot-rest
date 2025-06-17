package dev.rono.rest.controllers;


import dev.rono.rest.exceptions.ResourceNotFoundException;
import dev.rono.rest.models.Product;
import dev.rono.rest.repositories.ProductRepo;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController // This annotation indicates that this class is a RESTful controller
@RequestMapping("/api/products") // This annotation maps HTTP requests to the methods in this controller
public class ProductController {
    // This class will handle HTTP requests related to products
    // It uses Spring's @RestController annotation to indicate that it is a RESTful controller

    @Autowired // Autowire the ProductRepo to access the database
    // This annotation allows Spring to automatically inject the ProductRepo bean into this controller
    private ProductRepo productRepo;

    // 1. GET all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // 2. GET one product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOpt = productRepo.findById(id);
        if (productOpt.isEmpty()) {
            // Throw an exception or return a 404 Not Found response
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
        Product product = productOpt.get();
        return ResponseEntity.ok(product);
    }

    // 3. POST create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        // Validate the product object using @Valid annotation
        // @RequestBody annotation binds the request body to the product parameter
        Product savedProduct = productRepo.save(product);
        return ResponseEntity.status(201).body(savedProduct); // Return 201 Created status
    }

    // 4. PUT update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid Product productDetails) {

        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        // Update the existing product with new details
        existingProduct.setName(productDetails.getName());
        existingProduct.setPrice(productDetails.getPrice());
        Product updatedProduct = productRepo.save(existingProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    // 5. DELETE a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        productRepo.delete(existingProduct);
        return ResponseEntity.noContent().build(); // Return 204 No Content status
    }
}
