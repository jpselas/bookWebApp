/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jps.bookwebapp.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;


/**
 *
 * @author John
 */
@Dependent 
public class MySqlDBStrategy implements DBStrategy,Serializable{
    
    private Connection conn;
    
    
    public MySqlDBStrategy(String driverClass, String url, String username,String password) throws ClassNotFoundException, SQLException{
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url,username,password);
        
    }

    public MySqlDBStrategy() {
       
    }
    @Override
    public void openConnection(String driverClass, String url, String username,String password) throws ClassNotFoundException, SQLException{
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url,username,password);
        
    }
    @Override
    public void closeConnection() throws SQLException{
      conn.close();  
    
    }
    @Override
    public int insertRecord(String tableName, List <String> colNames, List <Object>colValues) throws SQLException{ 
        int recordsInserted = 0;
        PreparedStatement prepStmt = null;
      
        prepStmt = buildInsertStatement(conn, tableName, colNames);
        
        final Iterator i = colValues.iterator();
        int index = 1; 
            

            
            while (i.hasNext()) {
                final Object obj = i.next();
                prepStmt.setObject(index++, obj);
            }

            recordsInserted = prepStmt.executeUpdate();
            
            prepStmt.close();
            conn.close();
            
        return recordsInserted;
    }
    /**Make sure you open and close connection when using this method
     * Future optimization may include change the return type an array
     * @param tableName
     * @return 
     */
    @Override
    public int updateRecordById(String tableName, List<String> colNames,List<Object> colValues, String primaryKey,Object primaryKeyValue) throws SQLException{
            
        PreparedStatement pstmt = null;
        int recsUpdated = 0;

        // do this in an excpetion handler so that we can depend on the
        // finally clause to close the connection
        try {
                    pstmt = buildUpdateStatement(conn,tableName,colNames,primaryKey);

                    final Iterator i=colValues.iterator();
                    int index = 1;
                    Object obj = null;

                    // set params for column values
                    while( i.hasNext()) {
                        obj = i.next();
                        pstmt.setObject(index++, obj);
                    }
                    // and finally set param for wehere value
                    pstmt.setObject(index,primaryKeyValue);
                    
                    recsUpdated = pstmt.executeUpdate();

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
                    try {
                            pstmt.close();
                            conn.close();
                    } catch(SQLException e) {
                            throw e;
                    } // end try
        } // end finally

        return recsUpdated;
        
    }
    
    
    
    
    
    
    
    
    
    @Override
    public List<Map<String,Object>> findAllRecordsForTable(String tableName, int maxRecords) throws SQLException{
        String sql;
        if(maxRecords  < 1){
            sql = "select * from " + tableName;
        }else{
            sql = ("select * from " + tableName + " limit " + maxRecords);
        }
    
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        List<Map<String, Object>> records = new ArrayList<>();
        
        while(rs.next()){
            Map<String, Object> record = new HashMap<>();
            for(int colNo = 1;colNo <= columnCount;colNo++){
                Object colData = rs.getObject(colNo);
                String colName = rsmd.getColumnName(colNo);
                record.put(colName, colData);
            }
            records.add(record);
        }
        return records;
    }
     @Override
    public int deleteRecordbyPrimaryKey(String tableName, String primaryKey, Object primaryKeyValue) throws SQLException {
        int recordsDeleted = 0;
        PreparedStatement prepStmt = null;
        
        
        final String sqlDeleteStmt = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?";

        prepStmt = conn.prepareStatement(sqlDeleteStmt);
        
        if (primaryKey != null) {
                if (primaryKeyValue instanceof String) {
                    prepStmt.setString(1, (String) primaryKeyValue);
                } else {
                    prepStmt.setObject(1, primaryKeyValue);
                }

            }
        
        recordsDeleted = prepStmt.executeUpdate();

        return recordsDeleted;
    }
    /*
	 * Builds a java.sql.PreparedStatement for an sql insert
	 * @param conn - a valid connection
	 * @param tableName - a <code>String</code> representing the table name
	 * @param colDescriptors - a <code>List</code> containing the column descriptors for
	 * the fields that can be inserted.
	 * @return java.sql.PreparedStatement
	 * @throws SQLException
	 */
    private PreparedStatement buildUpdateStatement(Connection conn_loc, String tableName,List colNames, String primaryKey)
	throws SQLException {
		StringBuffer sql = new StringBuffer("UPDATE ");
		(sql.append(tableName)).append(" SET ");
		final Iterator i=colNames.iterator();
		while( i.hasNext() ) {
			(sql.append( (String)i.next() )).append(" = ?, ");
		}
		sql = new StringBuffer( (sql.toString()).substring( 0,(sql.toString()).lastIndexOf(", ") ) );
		((sql.append(" WHERE ")).append(primaryKey)).append(" = ?");
		final String finalSQL=sql.toString();
		return conn_loc.prepareStatement(finalSQL);
	}
    private PreparedStatement buildInsertStatement(Connection conn,String tableName,List colNames) throws SQLException{ //no values needed because they are provided from the list
        
         StringBuffer sql = new StringBuffer("Insert Into " + tableName + " (");
         final Iterator i=colNames.iterator();
		while( i.hasNext() ) {
                        sql.append(i.next() + ", ");
                }
              
         sql = new StringBuffer((sql.toString()).substring(0,(sql.toString()).lastIndexOf(", ") ) + ") Values (" ); 
         for (int m = 0; m < colNames.size();m++){
             sql.append("?, "); 
         }
         final String finalSQL = ((sql.toString()).substring(0,(sql.toString()).lastIndexOf(", ") ) + ")" );
         return conn.prepareStatement(finalSQL);
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
         List<Map<String,Object>> rawData = db.findAllRecordsForTable("author",0);
         db.closeConnection();
         // update records
         db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
         List<String> colNames = Arrays.asList("author_name","date_added");
         List<Object> colValues = Arrays.asList("Lucifer","2000-04-09");
         int result = db.updateRecordById("author", colNames, colValues, "author_id", 1);
         System.out.println("Records changes: " + result);
         
         
         // deleting records 
//         db.deleteRecordbyPrimaryKey("author","author_id",3);
//         List<Map<String,Object>> rawData2 = db.findAllRecordsForTable("author",0);
         db.closeConnection();
         
         db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
         List<Map<String,Object>> rawData2 = db.findAllRecordsForTable("author",0);
         db.closeConnection();
         
         System.out.println(rawData2);
    }
    
    
    
}
