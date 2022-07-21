package com.codegym.wine_manager.dao;

import com.codegym.wine_manager.model.Role;

import java.sql.SQLException;
import java.util.List;

public interface IRoleDAO {
    public void insertRole(Role role) throws SQLException;

    public Role selectRole(int id);

    public List<Role> selectAllRole();

    public boolean deleteRole(int id) throws SQLException;

    public boolean updateRole(Role role) throws SQLException;
}
