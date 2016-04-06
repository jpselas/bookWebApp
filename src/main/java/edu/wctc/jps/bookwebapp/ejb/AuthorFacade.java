/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jps.bookwebapp.ejb;

import edu.wctc.jps.bookwebapp.model.Author;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "edu.wctc.jps_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
     public void deleteAuthorbyID(String Id) {
        /* String jpql = "delete from Author a where a.authorId = ?1";
        TypedQuery q = getEntityManager().createQuery(jpql,Author.class);
        q.executeUpdate(); */ // doesn't like it this way Caused by: 
//                               java.lang.IllegalStateException: Query argument 1 not found in the list of parameters provided during query execution.

        Author author = this.find(new Integer(Id));
        this.remove(author);
    }

//     public void deleteAuthorsbyIDs(List<Object> primaryKeyValues) throws SQLException{
//        
//        this.removeMulti(primaryKeyValues);
//     
//    }
    public void saveAuthor(String id, String name) {
        Author author = new Author();
        if (id == null) {
            // must be a new record
            author.setAuthorName(name);
            author.setDateAdded(new Date());
        } else {
            // modify record
            author.setAuthorId(new Integer(id));
            author.setAuthorName(name);
        }
        this.getEntityManager().merge(author);
    }
}
