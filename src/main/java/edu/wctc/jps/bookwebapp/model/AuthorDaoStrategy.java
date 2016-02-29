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
 
  abstract public int insertAuthor(Author author) throws SQLException, ClassNotFoundException;
  abstract public DBStrategy getDb();
  abstract public void setDb(DBStrategy db);
  public void initDao(String driver,String url,String user,String pwd);
  public int updatebyID(Integer authorId, String name) throws SQLException, ClassNotFoundException;
  public String getDriver();

    public void setDriver(String driver);

    public String getUrl();

    public void setUrl(String url);

    public String getUser();

    public void setUser(String user);

    public String getPwd();

    public void setPwd(String pwd);
}
