package dev.rono.rest.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // This annotation marks the class as a JPA entity
@Table(name = "products") // This annotation specifies the table name in the database
@Getter // Lombok annotations to generate getters and setters automatically
@Setter
@NoArgsConstructor
public class Product {

    @Id // This annotation marks the field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This annotation specifies that the primary key will be generated automatically
    private  Long id;

    @NotBlank(message = "Name is Mandatory") // Bean Validation annotation to ensure the name is not blank
    private String name;

    @Positive(message = "Price must be positive") // Bean Validation annotation to ensure the price is positive
    private double price;

    // Constructors, getters, and setters
//    public Product() { // Implemented by Lombok's @NoArgsConstructor
//    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
