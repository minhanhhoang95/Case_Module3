package com.codegym.wine_manager.dao;

import com.codegym.wine_manager.connect_MySQL.ConnectMySQL;
import com.codegym.wine_manager.model.User;
import com.codegym.wine_manager.model.Wine;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WineDAO implements IWineDAO {
    ConnectMySQL connectMySQL = new ConnectMySQL();

    private int noOfRecords;

    private static final String INSERT_WINE_SQL = "INSERT INTO wine" +
            "(title,quantity,price,description) VALUES " +
            " (?, ?, ?,?);";

    private static final String SELECT_WINE_BY_ID = "select title,quantity,price,description" +
            " from wine where id =?";
    private static final String SELECT_ALL_WINE = "select * from wine";
    private static final String DELETE_WINE_SQL = "delete from wine where id = ?;";
    private static final String UPDATE_WINE_SQL = "update wine set" +
            " title=?" +
            ",quantity=?" +
            ",price=?" +
            ",description=?" +
            " where id = ?;";

    public WineDAO() {

    }


    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public void insertWine(Wine wine) throws SQLException {
        System.out.println(INSERT_WINE_SQL);
        try (Connection connection = connectMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WINE_SQL)) {
            preparedStatement.setString(1, wine.getTitle());
            preparedStatement.setInt(2, wine.getQuantity());
            preparedStatement.setDouble(3, wine.getPrice());
            preparedStatement.setString(4, wine.getDescription());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Wine selectWine(int id) {
        Wine wine = null;
        try (Connection connection = connectMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WINE_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                wine=new Wine(title,quantity,price,description);

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return wine;
    }

    @Override
    public List<Wine> selectAllWine() {
        List<Wine> wines = new ArrayList<>();
        try (Connection connection = connectMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WINE)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                wines.add(new Wine(id,title,quantity,price,description));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return wines;
    }

    @Override
    public boolean deleteWine(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = connectMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_WINE_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean       updateWine(@NotNull Wine wine) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = connectMySQL.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_WINE_SQL);) {

            statement.setString(1,wine.getTitle());
            statement.setInt(2, wine.getQuantity());
            statement.setDouble(3, wine.getPrice());
            statement.setString(4, wine.getDescription());
            statement.setInt(5,wine.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public List<Wine> getNumberPage(int offset, int noOfRecords) throws ClassNotFoundException, SQLException {
        Connection connection = connectMySQL.getConnection();
//        System.out.println("numberpage");
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM wine limit " + offset + "," + noOfRecords;
        List<Wine> list = new ArrayList<>();

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Wine wine = new Wine();
            wine.setId(rs.getInt("id"));
            wine.setTitle(rs.getString("title"));
            wine.setQuantity(rs.getInt("quantity"));
            wine.setPrice(rs.getDouble("price"));
            wine.setDescription(rs.getString("description"));

            list.add(wine);
        }
        rs = ps.executeQuery("SELECT FOUND_ROWS()");
        if (rs.next()) {
            this.noOfRecords = rs.getInt(1);
        }

        connection.close();
        return list;
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
