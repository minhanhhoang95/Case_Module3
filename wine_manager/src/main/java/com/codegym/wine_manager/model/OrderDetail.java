package com.codegym.wine_manager.model;

public class OrderDetail {
    private int id;
    private double price;
    private int quantity;
    private long orderId;
    private int productId;
    private String productName;
    public OrderDetail(){

    }
    public OrderDetail(int id, double price, int quantity, long orderId, int productId, String productName, double total) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return id +
                "," + price +
                "," + quantity +
                "," + orderId +
                "," + productId +
                "," + productName
                ;
    }
}
