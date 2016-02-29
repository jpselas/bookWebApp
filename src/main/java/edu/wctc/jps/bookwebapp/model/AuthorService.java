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
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author John
 */
@SessionScoped
public class AuthorService implements Serializable{
    @Inject
    private AuthorDaoStrategy dao;

    public AuthorService() {
    }
    
    
    public List<Author> getAuthorList() throws SQLException, ClassNotFoundException{
        
        return dao.getAuthorList();
    }
    
    public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException{
        return dao.deleteAuthorById(id);
        
    }
    
    
    public int insertAuthor(Author author) throws SQLException, ClassNotFoundException{
        
        return dao.insertAuthor(author);
    }
    
    public void updateAuthorbyId(String authorId, String authorName) throws SQLException, ClassNotFoundException {
         Integer id = Integer.parseInt(authorId);
         dao.updatebyID(id,authorName);
    }

    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }
    
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        AuthorService srv = new AuthorService();
        
        List<Author> authors = srv.getAuthorList();
        System.out.println(authors);
        
    }
    
}
