package com.codegym.wine_manager.dao;

import com.codegym.wine_manager.connect_MySQL.ConnectMySQL;
import com.codegym.wine_manager.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    ConnectMySQL connectMySQL = new ConnectMySQL();


    private static final String INSERT_USERS_SQL = "INSERT INTO user" +
            "(username,password,name,phone,address,email,Role) VALUES " +
            " (?, ?, ?,?,?,?,?);";

    private static final String SELECT_USER_BY_ID = "select username,password,name,phone,address,email,Role" +
            " from user where id =?";
    private static final String SELECT_ALL_USERS = "select * from user";
    private static final String DELETE_USERS_SQL = "delete from user where id = ?;";
    private static final String UPDATE_USERS_SQL = "update user set" +
            " username=?" +
            ",password=?" +
            ",name=?" +
            ",phone=?" +
            ",address=?" +
            ",email=?" +
            ",Role=?" +
            " where id = ?;";

    public UserDAO() {
    }



    @Override
    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        try (Connection connection = connectMySQL.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setInt(7, user.getRole());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }


    @Override
    public User selectUser(int id) {
        User user = null;
        try (Connection connection = connectMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int role = rs.getInt("Role");
                user = new User( username, password,  fullName,  phone, address,  email,  role);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)){
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int role = rs.getInt("role");
                users.add( new User(id, username, password, fullName, phone, address, email, role));
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return users;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = connectMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = connectMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getEmail());
            statement.setInt(7,user.getRole());
            statement.setInt(8,user.getId());

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
