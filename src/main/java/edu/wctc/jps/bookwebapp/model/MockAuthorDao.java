/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jps.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author John
 */
@Dependent
@Alternative
public class MockAuthorDao implements AuthorDaoStrategy,Serializable {
    private DBStrategy db;
    Author first = new Author(001,"James Brown",new Date(62,4,5));
    Author second = new Author(002,"Brett Favre",new Date(89,9,3));
    Author third = new Author(003,"Eric Dickerson",new Date(-91,4,2));
    List <Author> authors = new ArrayList();
    
    
   

    @Override
    public List<Author> getAuthorList() throws SQLException, ClassNotFoundException {
    authors.add(first);
    authors.add(second);
    authors.add(third);
        return authors;
    }
    @Override
    public int deleteAuthorById(Object id){
        return 1;
    }
    


    

    @Override
    public int insertAuthor(Author author) throws SQLException, ClassNotFoundException {
        return 1;
    }

    @Override
    public DBStrategy getDb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDb(DBStrategy db) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initDao(String driver, String url, String user, String pwd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDriver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDriver(String driver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUser(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPwd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPwd(String pwd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updatebyID(Integer authorId, String name) throws SQLException, ClassNotFoundException {
        return 1;
    }
    
}
