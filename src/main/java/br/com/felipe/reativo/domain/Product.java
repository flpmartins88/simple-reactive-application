package br.com.felipe.reativo.domain;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

    @Id
    private String code;

    private String brand;
    private String description;
    private String color;

    private long price;

    public Product(String brand, String description, String color, long price) {
        this.code = UUID.randomUUID().toString();
        this.brand = brand;
        this.description = description;
        this.color = color;
        this.price = price;
    }

    private Product() {}

    public String getCode() {
        return code;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public long getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
