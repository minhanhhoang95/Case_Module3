package com.codegym.wine_manager.model;

import javax.management.relation.Role;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.Instant;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private int role;
    private Instant createdAt;
    private Instant updatedAt;

    public User() {

    }

    public User(int id, String username, String password, String fullName, String phone, String address, String email, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.role = role;
    }

    public User(int id, String username, String password, String fullName, String phone, String address, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public User(String username, String password, String fullName, String phone, String address, String email, int role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty
    //>8 ko kí tự đặc biệt
    @Pattern(regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", message = "Format username not right")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty
// >8 gồm chữ và số
    @Pattern(regexp = "^([a-zA-Z0-9]{8,}$)", message = "Format password not right")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty
//chữ Hoa đầu tiên
    @Pattern(regexp = "^([A-Z]+[a-z]*[ ]?)+$", message = "Format password not right")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @NotEmpty
//số 0 đầu số thứ 2 lớn hơn 0
    @Pattern(regexp = "^[0][1-9][0-9]{8}$", message = "Format phone not right")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotEmpty
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$", message = "Format phone not right")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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
        return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                id,
                username,
                password,
                fullName,
                phone,
                address,
                email,
                role,
                createdAt,
                updatedAt
        );
    }
}
