/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import sample.book.BookDTO;
import sample.store.GobalStore;
import sample.user.UserDTO;

/**
 *
 * @author ACER
 */
public class AddBookToCartServlet extends HttpServlet {

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
       try{
           String bookID = request.getParameter("txtBookID");
           String txtCurrentQuantityBook = request.getParameter("txtQuantity");
           
           if(bookID != null && txtCurrentQuantityBook != null){
               int maxQuantity = Integer.parseInt(txtCurrentQuantityBook);
               
               Map<String, Integer> listBookCart = null;
               HttpSession session = request.getSession();
               if(session.getAttribute(GobalStore.LIST_BOOK_CART) != null){
                   listBookCart = (HashMap<String, Integer>) session.getAttribute(GobalStore.LIST_BOOK_CART);
               }else{
                   listBookCart = new HashMap<>();
               }
               
               int defaultQuantity = 1;
               if(listBookCart.get(bookID) != null){
                   defaultQuantity = listBookCart.get(bookID) + 1;
                   if(defaultQuantity > maxQuantity){
                       request.setAttribute(GobalStore.ADD_PRODUCT_TO_CART_ERROR, "sorry our book is empty now");
                   }else{
                       listBookCart.replace(bookID, defaultQuantity);
                       request.setAttribute(GobalStore.ADD_PRODUCT_TO_CART_SUCCESS, "Added sucessful");
                   }
               }else{
                   listBookCart.put(bookID, defaultQuantity);
                   request.setAttribute(GobalStore.ADD_PRODUCT_TO_CART_SUCCESS, "Added sucessfull");
               }
               session.setAttribute(GobalStore.LIST_BOOK_CART, listBookCart);
           }
           
       }finally{
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
