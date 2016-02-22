/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jps.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author John
 */
public class AuthorDao implements AuthorDaoStrategy {
    private DBStrategy db = new MySqlDBStrategy();
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/book";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";
    
    private static final String AUTHOR_ID = "author_id";
    private static final String AUTHOR_NAME = "author_name";
    private static final String DATE_ADDED = "date_added";
    private static final String TABLE_NAME="author";
    
    @Override
    public List<Author> getAuthorList() throws SQLException, ClassNotFoundException{
        db.openConnection(DRIVER, URL, USERNAME, PASSWORD);
        
        
        List<Map<String,Object>> rawData = db.findAllRecordsForTable("author",0);
        List<Author> authors = new ArrayList<>();
        
        for(Map rec : rawData){
            Author author = new Author();
            Integer id = new Integer(rec.get("author_id").toString());
            author.setAuthorId(id);
            String name = rec.get("author_name") == null ? "" : rec.get("author_name").toString();
            author.setAuthorName(name);
            Date date = rec.get("date_added") == null ? null : (Date)rec.get("date_added");
            author.setDateAdded(date);
            authors.add(author);
        }
        
        
        db.closeConnection();
        return authors;
    }
    @Override
    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException{
        db.openConnection(DRIVER, URL, USERNAME, PASSWORD);
        int result = db.deleteRecordbyPrimaryKey("author", "author_id", id);
        db.closeConnection();
        
        return result;
    }
    
    
    public int insertAuthor(Author author) throws SQLException, ClassNotFoundException{
        
            db.openConnection(DRIVER, URL, USERNAME, PASSWORD);
            List<String> authorColumns = Arrays.asList(AUTHOR_NAME, DATE_ADDED);;
            List<Object> authorValues = Arrays.asList(author.getAuthorName(), author.getDateAdded());
            int numAuthor = db.insertRecord(TABLE_NAME, authorColumns, authorValues);
            
        
            db.closeConnection();
            return numAuthor;
    }
    
    @Override
    public int updateRecordsById(Author author) throws ClassNotFoundException, SQLException{
        
        db.openConnection(DRIVER, URL, USERNAME, PASSWORD);
        List<String> colNames = Arrays.asList(AUTHOR_NAME, DATE_ADDED);;
        List<Object> colValues = Arrays.asList(author.getAuthorName(), author.getDateAdded());
        int primaryKeyValue = author.getAuthorId();
        int result = db.updateRecordById(TABLE_NAME, colNames,colValues,AUTHOR_ID, primaryKeyValue);
        
        db.closeConnection();
        return result;
    }
    
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AuthorDaoStrategy dao = new AuthorDao();
        List<Author>authors = dao.getAuthorList();
        System.out.println(authors);
    }
   
}
