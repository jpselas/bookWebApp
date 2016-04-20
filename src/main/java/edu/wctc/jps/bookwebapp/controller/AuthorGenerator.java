/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jps.bookwebapp.controller;


import edu.wctc.jps.bookwebapp.entity.Author;
import edu.wctc.jps.bookwebapp.service.AuthorService;
import edu.wctc.jps.bookwebapp.service.BookService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author John
 */
@WebServlet(name = "AuthorGenerator", urlPatterns = {"/AuthorGenerator"})
public class AuthorGenerator extends HttpServlet {

   


    
    private AuthorService as;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        response.setContentType("text/html;charset=UTF-8");
        String[] authorIds = null;
        Author author = null;
       
        String buttonType = request.getParameter("action");

        if (buttonType.equals("add")) {
            String authorName = request.getParameter("authorName");
            author = new Author(0);
            author.setAuthorName(authorName);
            author.setDateAdded(new Date());
            as.edit(author);
            getListOfAuthors(request);
            RequestDispatcher view = request.getRequestDispatcher("/authorsResonpse.jsp");
            view.forward(request, response);
        } else if (buttonType.equals("delete")) {
            authorIds = request.getParameterValues("authorId");
            for (String id : authorIds) {
                author = as.findById(id);
                as.remove(author);
            }
            getListOfAuthors(request);
            RequestDispatcher view = request.getRequestDispatcher("/authorsResonpse.jsp");
            view.forward(request, response);
        } else if (buttonType.equals("listDelete")) {
            getListOfAuthors(request);
            RequestDispatcher view = request.getRequestDispatcher("/delete.jsp");
            view.forward(request, response);
        } else if (buttonType.equals("listEdit")) {
            getListOfAuthors(request);
            RequestDispatcher view = request.getRequestDispatcher("/editRecord.jsp");
            view.forward(request, response);
        } else if (buttonType.equals("find")) {
            getListOfAuthors(request);
            RequestDispatcher view = request.getRequestDispatcher("/authorsResonpse.jsp");
            view.forward(request, response);
        } else if (buttonType.equals("edit")) {
             authorIds = request.getParameterValues("authorId");
             
            request.setAttribute("authorId", authorIds[0]);
            getListOfAuthors(request);
            RequestDispatcher view = request.getRequestDispatcher("/edit.jsp");
            view.forward(request, response);
        }else if (buttonType.equals("change")) {
            String authorId = request.getParameter("authorId");
            String authorName = request.getParameter("authorName");
            author = as.findById(authorId);
                        author.setAuthorName(authorName);
            
            as.edit(author);
            getListOfAuthors(request);
            RequestDispatcher view = request.getRequestDispatcher("/authorsResonpse.jsp");
            view.forward(request, response);
        }

    }

    public void getListOfAuthors(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        List<Author> authors = as.findAll();
        request.setAttribute("authors", authors);

    }

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AuthorGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthorGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        // Ask Spring for object to inject
        ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        as = (AuthorService) ctx.getBean("authorService");
        
    }

}
