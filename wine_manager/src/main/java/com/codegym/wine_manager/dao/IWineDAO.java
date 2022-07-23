package com.codegym.wine_manager.dao;

import com.codegym.wine_manager.model.User;
import com.codegym.wine_manager.model.Wine;

import java.sql.SQLException;
import java.util.List;

public interface IWineDAO {
    public void insertWine(Wine wine) throws SQLException;

    public Wine selectWine(int id);

    public List<Wine> selectAllWine();

    public boolean deleteWine(int id) throws SQLException;

    public boolean updateWine(Wine wine) throws SQLException;
    List<Wine> getNumberPage(int offset, int noOfRecords)throws ClassNotFoundException,SQLException;
    public int getNoOfRecords();
}
