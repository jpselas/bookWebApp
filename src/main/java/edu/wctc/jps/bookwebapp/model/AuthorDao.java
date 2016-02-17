/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jps.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
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
    
    
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AuthorDaoStrategy dao = new AuthorDao();
        List<Author>authors = dao.getAuthorList();
        System.out.println(authors);
    }
   
}
