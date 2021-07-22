/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dispatcher;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.book.BookDAO;
import sample.book.BookDTO;
import sample.category.CategoryDAO;
import sample.category.CategoryDTO;
import sample.store.GobalStore;

/**
 *
 * @author ACER
 */
public class Dispatcher extends HttpServlet {

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
        String url = GobalStore.WELCOME_SERVLET;
        try {
            String button = request.getParameter("Action");
            if (button != null) {
                switch (button) {
                    case "LOGIN":
                        url = GobalStore.LOGIN_SERVLET;
                        break;
                    case "LOGOUT":
                        url = GobalStore.LOGOUT_SERVLET;
                        break;
                    case "SEARCH_BY_NAME":
                        url = GobalStore.SEARCH_BOOK_BY_NAME_SERVLET;
                        break;
                    case "SEARCH_BY_CATEGORY":
                        url = GobalStore.SEARCH_BOOK_BY_CATEGORY_SERVLET;
                        break;
                    case "SEARCH_BY_RANGE_OF_MONEY":
                        url = GobalStore.SEARCH_BOOK_BY_RANGE_MONEY_SERVLET;
                        break;
                    case "DELETE":
                        url = GobalStore.DELETE_BOOK_SERVLET;
                        break;
                    case "ISERT_NEW_BOOK":
                        url = GobalStore.INSERT_BOOK_SERVLET;
                        break;
                    case "MOVE_INSERT_PAGE":
                        url = GobalStore.INSERT_BOOK_PAGE;
                        CategoryDAO categoryDAO = new CategoryDAO();
                        ArrayList<CategoryDTO> listCategory = categoryDAO.getAllCategory();
                        if (listCategory != null) {
                            request.setAttribute(GobalStore.LIST_CATEGORY, listCategory);
                        }
                        break;
                    case "UPDATE":
                        url = GobalStore.UPDATE_BOOK_PAGE;
                        CategoryDAO categoryDAO_02 = new CategoryDAO();
                        ArrayList<CategoryDTO> listCategory_02 = categoryDAO_02.getAllCategory();
                        if (listCategory_02 != null) {
                            request.setAttribute(GobalStore.LIST_CATEGORY, listCategory_02);
                        }

                        String bookID = request.getParameter("bookID");
                        BookDAO bookDAO = new BookDAO();
                        BookDTO book = bookDAO.getBookDetail(bookID);
                        if (book != null) {
                            request.setAttribute(GobalStore.BOOK_DETAIL, book);
                        }
                        break;
                    case "UPDATE_BOOK":
                        url = GobalStore.UPDATE_BOOK_SERVLET;
                        break;
                    case "ADD_TO_CART":
                        url = GobalStore.ADD_BOOK_TO_CART_SERVLET;
                        break;
                    case "BUY":
                        url = GobalStore.BUY_BOOK_SERVLET;
                        break;
                    case "REMOVE_FROM_CART":
                        url = GobalStore.REMOVE_BOOK_FROM_CART_SERVLET;
                        break;
                    case "+":
                        url = GobalStore.UPDATE_BOOK_AMOUNT_CART_SERVLET;
                        break;
                    case "-":
                        url = GobalStore.UPDATE_BOOK_AMOUNT_CART_SERVLET;
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
