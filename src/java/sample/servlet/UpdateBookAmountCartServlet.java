/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
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
import sample.store.GobalStore;

/**
 *
 * @author ACER
 */
public class UpdateBookAmountCartServlet extends HttpServlet {

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
        String url = GobalStore.VIEW_BOOK_CART_SERVLET;
        try {
            String txtPositve = request.getParameter("txtPositive");
            String txtNegative = request.getParameter("txtNegative");
            String txtBookID = request.getParameter("txtBookID");

            HttpSession session = request.getSession();
            if (session.getAttribute(GobalStore.LIST_BOOK_CART) != null) {
                Map<String, Integer> listBookCart = (Map<String, Integer>) session.getAttribute(GobalStore.LIST_BOOK_CART);

                if (txtNegative != null) {
                    for (Map.Entry<String, Integer> bookCart : listBookCart.entrySet()) {
                        if (bookCart.getKey().equals(txtBookID)) {
                            if (bookCart.getValue() > 1) {
                                int newAmount = bookCart.getValue() - 1;
                                listBookCart.replace(txtBookID, newAmount);
                                break;
                            }
                        }

                    }
                } else if (txtPositve != null) {
                    BookDAO bookDAO = new BookDAO();
                    int currentBookAmountInDB = bookDAO.getCurrentBookAmount(txtBookID);
                    if (currentBookAmountInDB > 0) {
                        for (Map.Entry<String, Integer> bookCart : listBookCart.entrySet()) {
                            if (bookCart.getKey().equals(txtBookID)) {
                                int newAmount = bookCart.getValue() + 1;
                                if (newAmount > currentBookAmountInDB) {
                                    request.setAttribute(GobalStore.UPDATE_BOOK_AMOUNT_CART_ERROR, "Sorry we out of scope your request!!!");
                                } else {
                                    listBookCart.replace(txtBookID, newAmount);
                                }
                                break;
                            }
                        }
                    } else {
                        request.setAttribute(GobalStore.UPDATE_BOOK_AMOUNT_CART_ERROR, "Sorry we out of scope your request!!!");
                    }
                }

                session.setAttribute(GobalStore.LIST_BOOK_CART, listBookCart);
            }

        } catch (SQLException | NamingException ex) {
            Logger.getLogger(UpdateBookAmountCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
