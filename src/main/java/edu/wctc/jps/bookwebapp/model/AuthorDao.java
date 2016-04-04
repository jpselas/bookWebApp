/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jps.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author John
 */
@Dependent 
public class AuthorDao implements AuthorDaoStrategy,Serializable {
    @Inject
    private DBStrategy db;
    private  String driver;
    private  String url;
    private  String user;
    private  String pwd;
    
    private static  String AUTHOR_ID = "author_id";
    private static  String AUTHOR_NAME = "author_name";
    private static  String DATE_ADDED = "date_added";
    private static  String TABLE_NAME="author";
    
    @Override
    public List<Author> getAuthorList() throws SQLException, ClassNotFoundException{
        db.openConnection(driver, url, user, pwd);
        
        
        List<Map<String,Object>> rawData = db.findAllRecordsForTable("author",0);
        List<Author> authors = new ArrayList<>();
        
        for(Map rec : rawData){
            Author author = new Author();
            Integer id = new Integer(rec.get("author_id").toString());
            author.setAuthorId(id);
            String name = rec.get("author_name") == null ? "" : rec.get("author_name").toString();
            author.setAuthorName(name);
            Date date = rec.get("date_added") == null ? new Date() : (Date)rec.get("date_added");
            author.setDateAdded(date);
            authors.add(author);
        }
        
        
        db.closeConnection();
        return authors;
    }
    
    
    @Override
    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException{
        db.openConnection(driver, url, user, pwd);
        int result = db.deleteRecordbyPrimaryKey("author", "author_id", id);
        db.closeConnection();
        
        return result;
    }
    
    @Override
    public int insertAuthor(Author author) throws SQLException, ClassNotFoundException{
        
            db.openConnection(driver, url, user, pwd);
            List<String> authorColumns = Arrays.asList(AUTHOR_NAME, DATE_ADDED);;
            List<Object> authorValues = Arrays.asList(author.getAuthorName(), new Date());
            int numAuthor = db.insertRecord(TABLE_NAME, authorColumns, authorValues);
            
        
            db.closeConnection();
            return numAuthor;
    }
    
    @Override
    public int updatebyID(Integer authorId, String name) throws SQLException, ClassNotFoundException {
            db.openConnection(driver, url, user, pwd);
            int recsUpdated = db.updateRecordById("author", Arrays.asList("author_name"), 
                                       Arrays.asList(name),
                                       "author_id", authorId);
            return recsUpdated;
    }
    @Override
    public DBStrategy getDb() {
        return db;
    }
    @Override
    public void setDb(DBStrategy db) {
        this.db = db;
    }
    
    @Override
    public void initDao(String driver,String url,String user,String pwd){
        setDriver(driver);
        setUrl(url);
        setUser(user);
        setPwd(pwd);
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AuthorDaoStrategy dao = new AuthorDao();
        List<Author>authors = dao.getAuthorList();
        System.out.println(authors);
    }
   
}
