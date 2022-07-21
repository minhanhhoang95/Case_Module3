package com.codegym.wine_manager.dao;

import com.codegym.wine_manager.connect_MySQL.ConnectMySQL;
import com.codegym.wine_manager.model.Role;
import com.codegym.wine_manager.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO implements IRoleDAO {

    private static final String INSERT_ROLE_SQL = "INSERT INTO role (role) VALUES (?);";
    private static final String SELECT_ROLE_BY_ID = "select * from role where id = ?;";
    private static final String SELECT_ALL_ROLE = "select * from role";
    private static final String DELETE_ROLE_SQL = "delete from role where id = ?;";
    private static final String UPDATE_ROLE_SQL = "update role set role = ? where id = ?;";
    private String jdbcURL = "jdbc:mysql://localhost:3306/case_m3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "230795";

    public RoleDAO() {

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertRole(Role role) throws SQLException {
        System.out.println(INSERT_ROLE_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROLE_SQL)) {

            preparedStatement.setString(1, role.getRole());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Role selectRole(int id) {
       Role role = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ID);) {
            preparedStatement.setInt(1, id);

            System.out.println(this.getClass() + " selectCountry: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name= rs.getString("role");


                role = new Role(id,name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return role;
    }

    @Override
    public List<Role> selectAllRole() {
        List<Role> listRole = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROLE);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String role = rs.getString("role");


                listRole.add(new Role(id, role));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listRole;
    }

    @Override
    public boolean deleteRole(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_ROLE_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateRole(Role role) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ROLE_SQL);) {
            statement.setString(1, role.getRole());
            statement.setInt(2, role.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
