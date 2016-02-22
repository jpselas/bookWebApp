/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jps.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author John
 */
public interface AuthorDaoStrategy {

  abstract public List<Author> getAuthorList() throws SQLException, ClassNotFoundException;
  abstract  public int deleteAuthorById(Object id) throws ClassNotFoundException, SQLException;
  abstract public int updateRecordsById(Author author) throws ClassNotFoundException, SQLException;
  abstract public int insertAuthor(Author author) throws SQLException, ClassNotFoundException;
}
