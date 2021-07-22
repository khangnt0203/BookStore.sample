/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class UpdateBookServlet extends HttpServlet {

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
//        String url = GobalStore.ADMIN_HOME_PAGE;
        String url = GobalStore.UPDATE_BOOK_PAGE;
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
                String image = request.getParameter("image");
                String oldImage = request.getParameter("oldImage");
                String txtStatus = request.getParameter("txtStatus");

                if (bookID != null && bookTile != null && bookDescription != null && author != null
                        && txtPrice != null && txtAmount != null && categoryID != null && txtStatus != null
                        && oldImage != null & image != null) {
                    float price = Float.parseFloat(txtPrice);
                    int amount = Integer.parseInt(txtAmount);
                    BookDAO bookDAO = new BookDAO();

                    boolean status = false;
                    if (txtStatus.contains("true")) {
                        status = true;
                    }
                    if (!image.isEmpty()) {
                        String newImage = GobalStore.FILE_IMAGE_PATH + image;
                        BookDTO book = new BookDTO(bookID, bookTile, bookDescription, author, newImage, price, categoryID, status, amount);
                        boolean checkUpdate = bookDAO.updateBook(book);
                        if (checkUpdate) {
                            request.setAttribute(GobalStore.UPDATE_BOOK_SUCCESS, "Update Success!!!");
                        } else {
                            request.setAttribute(GobalStore.UPDATE_BOOK_FAILED, "Update Failed!!!");
                        }
                    } else {
                        BookDTO book = new BookDTO(bookID, bookTile, bookDescription, author, oldImage, price, categoryID, status, amount);
                        boolean checkUpdate = bookDAO.updateBook(book);
                        if (checkUpdate) {
                            request.setAttribute(GobalStore.UPDATE_BOOK_SUCCESS, "Update Success!!!");
                        } else {
                            request.setAttribute(GobalStore.UPDATE_BOOK_FAILED, "Update Failed!!!");
                        }
                    }
                } else {
                    url = "errprpahe.jsp";
                }
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                CategoryDAO categoryDAO = new CategoryDAO();
                ArrayList<CategoryDTO> listCategory = categoryDAO.getAllCategory();
                if (listCategory != null) {
                    request.setAttribute(GobalStore.LIST_CATEGORY, listCategory);
                }

                String bookID = request.getParameter("bookID");
                BookDAO bookDAO = new BookDAO();
                BookDTO book = bookDAO.getBookDetail(bookID);
                if (book != null) {
                    request.setAttribute(GobalStore.BOOK_DETAIL, book);
                }
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
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
