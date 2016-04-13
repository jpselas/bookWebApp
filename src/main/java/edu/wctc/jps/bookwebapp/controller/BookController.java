/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jps.bookwebapp.controller;


import edu.wctc.jps.bookwebapp.ejb.AbstractFacade;
import edu.wctc.jps.bookwebapp.model.Author;
import edu.wctc.jps.bookwebapp.model.Book;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author jlombardo
 */
public class BookController extends HttpServlet {

    // NO MAGIC NUMBERS!
    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";
    private static final String LIST_PAGE = "/listBooks.jsp";
    private static final String ADD_EDIT_BOOKS_PAGE = "/addEditBooks.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_EDIT_DELETE_ACTION = "addEditDelete";
    private static final String SUBMIT_ACTION = "submit";
    private static final String ADD_EDIT_ACTION = "Add/Edit";
    private static final String ACTION_PARAM = "action";
    private static final String SAVE_ACTION = "Save";
    private static final String CANCEL_ACTION = "Cancel";

    @Inject
    private AbstractFacade<Book> bookService;
    @Inject
    private AbstractFacade<Author> authService;

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        String destination;
        String action = request.getParameter(ACTION_PARAM);
        Book book = null;

        
            switch (action) {
                case LIST_ACTION:
                    refreshBookList(request, bookService);
                    refreshAuthorList(request, authService);
                    destination = LIST_PAGE;
                    break;

                case ADD_EDIT_DELETE_ACTION:
                    String subAction = request.getParameter(SUBMIT_ACTION);

                    if (subAction.equals(ADD_EDIT_ACTION)) {
                        // must be add or edit, go to addEdit page
                        String[] bookIds = request.getParameterValues("bookId");
                        if (bookIds == null) {
                            // must be an add action, nothing to do but
                            // go to edit page
                            this.refreshAuthorList(request, authService);
                        } else {
                            // must be an edit action, need to get data
                            // for edit and then forward to edit page
                            
                            // Only process first row selected
                            String bookId = bookIds[0];
                            book = bookService.find(new Integer(bookId));
                            request.setAttribute("book", book);
                            this.refreshAuthorList(request, authService);
                        }

                        destination = ADD_EDIT_BOOKS_PAGE;

                    } else {
                        // must be DELETE
                        // get array based on records checked
                        String[] bookIds = request.getParameterValues("bookId");
                        for (String id : bookIds) {
                            book = bookService.find(new Integer(id));
                            bookService.remove(book);
                        }

                        this.refreshBookList(request, bookService);
                        this.refreshAuthorList(request, authService);
                        destination = LIST_PAGE;
                    }
                    break;
                    
                case SAVE_ACTION:
                    String title = request.getParameter("title");
                    String isbn = request.getParameter("isbn");
                    String authorId = request.getParameter("authorId");
                    String bookId = request.getParameter("bookId");
                    
                    if(bookId == null) {
                        // it must be new
                        book = new Book(0);
                        book.setTitle(title);
                        book.setIsbn(isbn);
                        Author author = null;
                        if(authorId != null) {
                            author = authService.find(new Integer(authorId));
                            book.setAuthorId(author);
                        }

                    } else {
                        // it must be an update
//                        book = new Book(new Integer(bookId));
//                        book.setTitle(title);
//                        book.setIsbn(isbn);
//                        Author author = null;
//                        if(authorId != null) {
//                            author = authService.find(new Integer(authorId));
//                            book.setAuthorId(author);
//                        }
                        
                        book = bookService.find(new Integer(bookId));
                        book.setTitle(title);
                        book.setIsbn(isbn);
                        Author author = null;
                        if(authorId != null) {
                            author = authService.find(new Integer(authorId));
                            book.setAuthorId(author);
                        }
                    }
                    
                    bookService.edit(book);
                    this.refreshBookList(request, bookService);
                    this.refreshAuthorList(request, authService);
                    destination = LIST_PAGE;
                    break;
                    
                case CANCEL_ACTION:
                    this.refreshBookList(request, bookService);
                    this.refreshAuthorList(request, authService);
                    destination = LIST_PAGE;
                    break;

                default:
                    // no param identified in request, must be an error
                    request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                    destination = LIST_PAGE;
                    break;
            }

       

        // Forward to destination page
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
        

    }

    // Avoid D-R-Y
    private void refreshBookList(HttpServletRequest request, AbstractFacade<Book> bookService) throws Exception {
        List<Book> books = bookService.findAll();
        request.setAttribute("books", books);
    }
    
    private void refreshAuthorList(HttpServletRequest request, AbstractFacade<Author> authService) throws Exception {
        List<Author> authors = authService.findAll();
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
        } catch (Exception ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Called after the constructor is called by the container. This is the
     * correct place to do one-time initialization.
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        
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

}
