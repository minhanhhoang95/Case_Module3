package com.codegym.wine_manager.model;

public class Role {
    private int id;
    private Role role;
    public Role(){

    }

    public Role(int id, Role role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
