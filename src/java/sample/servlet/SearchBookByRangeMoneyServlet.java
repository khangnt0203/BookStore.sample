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
import sample.validation.ValidateInput;

/**
 *
 * @author ACER
 */
public class SearchBookByRangeMoneyServlet extends HttpServlet {

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
        String url = GobalStore.CUSTOMER_HOME_PAGE;
        boolean isAmin = false;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute(GobalStore.ADMIN_LOGIN_FLAG) != null) {
                url = GobalStore.ADMIN_HOME_PAGE;
                isAmin = true;
            }

            String txtMinMoney = request.getParameter("txtMinMoney");
            String txtMaxMoney = request.getParameter("txtMaxMoney");

            if (txtMaxMoney != null && txtMinMoney != null) {
                String messErr = ValidateInput.checkInputRangeMoney(txtMinMoney, txtMaxMoney);
                if (messErr.isEmpty()) {
                    float minMoney = Float.parseFloat(txtMinMoney);
                    float maxMoney = Float.parseFloat(txtMaxMoney);

                    BookDAO bookDAO = new BookDAO();
                    ArrayList<BookDTO> listBook = bookDAO.searchBookByRangeMoney(minMoney, maxMoney, isAmin);
                    if (listBook != null) {
                        request.setAttribute(GobalStore.LIST_BOOK, listBook);
                    }
                } else {

                }

            }
            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<CategoryDTO> listCategory = categoryDAO.getAllCategory();
            if (listCategory != null) {
                request.setAttribute(GobalStore.LIST_CATEGORY, listCategory);
            }
        } catch (SQLException | NamingException e) {
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
