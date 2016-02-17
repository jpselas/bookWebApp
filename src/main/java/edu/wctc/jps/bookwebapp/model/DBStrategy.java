/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jps.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author John
 */
public interface DBStrategy {
    public abstract void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;
    
    public abstract void closeConnection() throws SQLException;
    
    public abstract List<Map<String,Object>> findAllRecordsForTable(String tableName, int maxRecords) throws SQLException;

    public abstract int deleteRecordbyPrimaryKey(String tableName, String primaryKey, Object primaryKeyValue) throws SQLException;
}
