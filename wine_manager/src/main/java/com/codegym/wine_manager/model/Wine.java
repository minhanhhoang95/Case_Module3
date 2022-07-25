package com.codegym.wine_manager.model;

import org.hibernate.validator.constraints.Length;
import org.intellij.lang.annotations.Pattern;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

public class Wine {
    private int id;
    private String title;
    private int quantity;
    private double price;
    private String image;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    public Wine(){

    }

    public Wine(int id, String title, int quantity, double price,String image, String description) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.image=image;
        this.description = description;
    }

    public Wine(String title, int quantity, double price,String image, String description) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.image=image;
        this.description = description;
    }

    public Wine(int id, String title, int quantity, double price, String description, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
@NotEmpty(message = "Please don't it blank")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Min(value = 1 , message = " > 0")
    @Max(value = 1000000 , message = " < 999999")

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

@Min(value = 1 , message = " > 0")
@Max(value = 1000000 , message = " < 1000000")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,",
                id,
                title,
                price,
                quantity,
                description,
                createdAt,
                updatedAt);
    }
}
