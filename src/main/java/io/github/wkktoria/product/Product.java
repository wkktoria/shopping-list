package io.github.wkktoria.product;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer quantity;
    private Boolean bought;

    public Product() {

    }

    public Product(String name, Integer quantity, Boolean bought) {
        this.name = name;
        this.quantity = quantity;
        this.bought = bought;
    }

    public Product(Integer id, String name, Integer quantity, Boolean bought) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.bought = bought;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getBought() {
        return bought;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
    }
}
