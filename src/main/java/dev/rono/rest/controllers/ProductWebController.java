package dev.rono.rest.controllers;

import dev.rono.rest.exceptions.ResourceNotFoundException;
import dev.rono.rest.models.Product;
import dev.rono.rest.repositories.ProductRepo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


// This class will handle web requests related to products
// It uses Spring's @Controller annotation to indicate that it is a web controller
// @RequestMapping("/products") // This annotation maps HTTP requests to the methods in this controller
// The ProductWebController is designed to handle web requests, typically for rendering views
@Controller
@RequestMapping("/products")
public class ProductWebController {
   
    // It is not a RESTful controller like ProductController, but rather a traditional web controller
    // It uses the ProductRepo to access the database for product-related operations
    @Autowired
    private ProductRepo productRepo;
    // Additional methods for handling web requests will be added here


    // 1. GET all products for web view
    @GetMapping
    public String viewProductsPage(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        // This method handles GET requests to the "/products.html" URL
        // It retrieves all products from the database and adds them to the model
        return "products";
        // Thymeleaf will render the "products.html" template with the products data
    }

    // 2. Show form to create a new product
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "productForm";
    }

    // 3. Handle form submission to create a new product
    @PostMapping
    public String createProduct(@ModelAttribute @Valid Product product, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            // If there are validation errors, return to the form view
            return "productForm";
        }
        // If the product is valid, save it to the database
        productRepo.save(product);
        // Add a success message to the redirect attributes
        redirectAttributes.addFlashAttribute("message", "Product created successfully!");
        // Redirect to the products page after successful creation
        return "redirect:/products";
    }

    // 4. Show form to update/edit an existing product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        // Retrieve the product by ID and add it to the model
        // If the product is not found, throw a ResourceNotFoundException
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        model.addAttribute("product", product);
        return "productForm";
    }

    // 5. Handle form submission to update an existing product
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute @Valid Product productDetails, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            // If there are validation errors, return to the form view
            return "productForm";
        }
        // Retrieve the existing product by ID
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        // Update the existing product with new details
        existingProduct.setName(productDetails.getName());
        existingProduct.setPrice(productDetails.getPrice());
        // Save the updated product to the database
        productRepo.save(existingProduct);
        // Add a success message to the redirect attributes
        redirectAttributes.addFlashAttribute("message", "Product updated successfully!");
        // Redirect to the products page after successful update
        return "redirect:/products";
    }

    // 6. Handle product deletion
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Retrieve the product by ID
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        // Delete the product from the database
        productRepo.delete(existingProduct);
        // Add a success message to the redirect attributes
        redirectAttributes.addFlashAttribute("message", "Product deleted successfully!");
        // Redirect to the products page after successful deletion
        return "redirect:/products";
    }

}
