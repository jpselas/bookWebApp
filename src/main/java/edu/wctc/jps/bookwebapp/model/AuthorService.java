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

/**
 *
 * @author John
 */
public class AuthorService {
    private AuthorDaoStrategy dao = new AuthorDao();
    
    public List<Author> getAuthorList() throws SQLException, ClassNotFoundException{
        
        return dao.getAuthorList();
    }
    
    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException{
        return dao.deleteAuthorById(id);
        
    }
    
    
    public int insertAuthor(Author author) throws SQLException, ClassNotFoundException{
        
        return dao.insertAuthor(author);
    }
    
    public int updateRecordsById(Author author) throws ClassNotFoundException, SQLException{
        return dao.updateRecordsById(author);
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        AuthorService srv = new AuthorService();
        
        List<Author> authors = srv.getAuthorList();
        System.out.println(authors);
        
    }
    
}
