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
public class MockAuthorDao implements AuthorDaoStrategy {
    
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
    
    
    
    
    
}
