/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jps.bookwebapp.controller;

import edu.wctc.jps.bookwebapp.model.Author;
import edu.wctc.jps.bookwebapp.model.AuthorService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author John
 */
@WebServlet(name = "AuthorGenerator", urlPatterns = {"/AuthorGenerator"})
public class AuthorGenerator extends HttpServlet {

    private String driverClass;
    private String url;
    private String userName;
    private String password;

    @Inject
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
        configDbConnection();

        String buttonType = request.getParameter("action");

        if (buttonType.equals("add")) {
            String authorName = request.getParameter("authorName");
            
            Author author = new Author(authorName);
            as.insertAuthor(author);
            getListOfAuthors(request, as);
            RequestDispatcher view = request.getRequestDispatcher("/authorsResonpse.jsp");
            view.forward(request, response);
        } else if (buttonType.equals("delete")) {
            authorIds = request.getParameterValues("authorId");
            for (String id : authorIds) {
                as.deleteAuthorById(id);
            }
            getListOfAuthors(request, as);
            RequestDispatcher view = request.getRequestDispatcher("/authorsResonpse.jsp");
            view.forward(request, response);
        } else if (buttonType.equals("listDelete")) {
            getListOfAuthors(request, as);
            RequestDispatcher view = request.getRequestDispatcher("/delete.jsp");
            view.forward(request, response);
        } else if (buttonType.equals("listEdit")) {
            getListOfAuthors(request, as);
            RequestDispatcher view = request.getRequestDispatcher("/editRecord.jsp");
            view.forward(request, response);
        } else if (buttonType.equals("find")) {
            getListOfAuthors(request, as);
            RequestDispatcher view = request.getRequestDispatcher("/authorsResonpse.jsp");
            view.forward(request, response);
        } else if (buttonType.equals("edit")) {
             authorIds = request.getParameterValues("authorId");
             
            request.setAttribute("authorId", authorIds[0]);
            getListOfAuthors(request, as);
            RequestDispatcher view = request.getRequestDispatcher("/edit.jsp");
            view.forward(request, response);
        }else if (buttonType.equals("change")) {
            authorIds = request.getParameterValues("authorId");
            String authorName = request.getParameter("authorName");
            
            as.updateAuthorbyId(authorIds[0],authorName);
            
            getListOfAuthors(request, as);
            RequestDispatcher view = request.getRequestDispatcher("/authorsResonpse.jsp");
            view.forward(request, response);
        }

    }

    public void getListOfAuthors(HttpServletRequest request, AuthorService as) throws SQLException, ClassNotFoundException {
        List<Author> authors = as.getAuthorList();
        request.setAttribute("authors", authors);

    }

    private void configDbConnection() {
        as.getDao().initDao(driverClass, url, userName, password);
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
        // get init params from web.xml file
        driverClass = getServletContext().getInitParameter("db.driver.class");
        url = getServletContext().getInitParameter("db.url");
        userName = getServletContext().getInitParameter("db.username");
        password = getServletContext().getInitParameter("db.password");
    }

}
