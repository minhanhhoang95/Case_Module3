package com.codegym.wine_manager.model;

import java.time.Instant;

public class Wine {
    private int id;
    private String title;
    private int quantity;
    private double price;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    public Wine(){

    }

    public Wine(int id, String title, int quantity, double price, String description) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public Wine(String title, int quantity, double price, String description) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
