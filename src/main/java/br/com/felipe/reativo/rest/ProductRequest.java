package br.com.felipe.reativo.rest;

import br.com.felipe.reativo.domain.Product;

public class ProductRequest {

    private String brand;
    private String description;
    private String color;
    private long price;

    public ProductRequest() {
    }

    public ProductRequest(String brand, String description, String color, long price) {
        this.brand = brand;
        this.description = description;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Product toDomain() {
        return new Product(
                this.brand,
                this.description,
                this.color,
                this.price
        );
    }
}
