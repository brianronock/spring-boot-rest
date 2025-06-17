package dev.rono.rest.repositories;

import dev.rono.rest.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    // This interface extends JpaRepository, which provides CRUD operations for the Product entity
    // No additional methods are defined here, but you can add custom query methods if needed
}
