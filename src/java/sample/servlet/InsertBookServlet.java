/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.book.BookDAO;
import sample.book.BookDTO;
import sample.category.CategoryDAO;
import sample.category.CategoryDTO;
import sample.store.GobalStore;

/**
 *
 * @author ACER
 */
public class InsertBookServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = GobalStore.INSERT_BOOK_PAGE;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute(GobalStore.ADMIN_LOGIN_FLAG) != null) {
                String bookID = request.getParameter("bookID");
                String bookTile = request.getParameter("bookTitle");
                String bookDescription = request.getParameter("bookDescription");
                String author = request.getParameter("author");
                String txtPrice = request.getParameter("txtPrice");
                String txtAmount = request.getParameter("txtAmount");
                String categoryID = request.getParameter("categoryID");
                String image = request.getParameter("images");

                if (bookID != null && bookTile != null && bookDescription != null && author != null
                        && txtPrice != null && txtAmount != null && categoryID != null && image != null) {
                    float price = Float.parseFloat(txtPrice);
                    int amount = Integer.parseInt(txtAmount);
                    image = GobalStore.FILE_IMAGE_PATH + image;
                    BookDTO book = new BookDTO(bookID, bookTile, bookDescription, author, image, price, categoryID, amount);
                    BookDAO bookDAO = new BookDAO();
                    boolean checkInsert = bookDAO.insertNewBook(book);
                    if (checkInsert) {
                        request.setAttribute(GobalStore.CREAT_NEW_BOOK_SUCCESS, "Create book success!!!");
                    } else {
                        request.setAttribute(GobalStore.CREAT_NEW_BOOK_ERROR, "Create book failed!!!");
                    }

                }

            } else {
                url = GobalStore.LOGIN_PAGE;
            }
        } catch (SQLException | NamingException e) {
            if (e.getMessage().contains("duplicate")) {
                request.setAttribute(GobalStore.CREAT_NEW_BOOK_ERROR, "This book's ID is exited!!!");
            } else {
                e.printStackTrace();
            }
        } finally {
            try {
                CategoryDAO categoryDAO = new CategoryDAO();
                ArrayList<CategoryDTO> listCategory = categoryDAO.getAllCategory();
                if (listCategory != null) {
                    request.setAttribute(GobalStore.LIST_CATEGORY, listCategory);
                }

            } catch (NamingException | SQLException ex) {
                Logger.getLogger(InsertBookServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
