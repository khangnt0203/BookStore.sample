/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.store;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class GobalStore implements Serializable {

    //Link Page
    public final static String LOGIN_PAGE = "login.jsp";
    public final static String ADMIN_HOME_PAGE = "adminHome.jsp";
    public final static String CUSTOMER_HOME_PAGE = "customerHome.jsp";
    public final static String INSERT_BOOK_PAGE = "insertBook.jsp";
    public final static String UPDATE_BOOK_PAGE = "updateBook.jsp";
    public final static String BOOK_CART_PAGE = "bookCart.jsp";

    //Link Servelet
    public final static String LOGIN_SERVLET = "LoginServlet";
    public final static String LOGOUT_SERVLET = "LogoutServlet";
    public final static String WELCOME_SERVLET = "WelcomeServlet";

    public final static String SEARCH_BOOK_BY_NAME_SERVLET = "SearchBookByNameServlet";
    public final static String SEARCH_BOOK_BY_CATEGORY_SERVLET = "SearchBookByCategoryServlet";
    public final static String SEARCH_BOOK_BY_RANGE_MONEY_SERVLET = "SearchBookByRangeMoneyServlet";

    public final static String DELETE_BOOK_SERVLET = "DeleteBookServlet";
    public final static String INSERT_BOOK_SERVLET = "InsertBookServlet";
    public final static String UPDATE_BOOK_SERVLET = "UpdateBookServlet";

    public final static String ADD_BOOK_TO_CART_SERVLET = "AddBookToCartServlet";
    public final static String BUY_BOOK_SERVLET = "BuyBookServlet";
    public final static String VIEW_BOOK_CART_SERVLET = "ViewBookCartServlet";
    
    public final static String REMOVE_BOOK_FROM_CART_SERVLET = "RemoveBookFromCartServlet";
    public final static String UPDATE_BOOK_AMOUNT_CART_SERVLET = "UpdateBookAmountCartServlet";

    //failed mess
    public final static String LOGIN_ERR = "LOGIN_ERR";
    public final static String ADD_PRODUCT_TO_CART_ERROR = "ADD_PRODUCT_TO_CART_ERROR";
    public final static String UPDATE_BOOK_AMOUNT_CART_ERROR = "UPDATE_BOOK_AMOUNT_CART_ERROR";
    
    public final static String BUY_BOOK_ERROR = "BUY_BOOK_ERROR";
    public final static String CREAT_NEW_BOOK_ERROR = "CREAT_NEW_BOOK_ERROR";
    public final static String UPDATE_BOOK_FAILED = "UPDATE_BOOK_FAILED";
    public final static String DELETE_BOOK_FAILED = "DELETE_BOOK_FAILED";

    //success mess
    public final static String ADD_PRODUCT_TO_CART_SUCCESS = "ADD_PRODUCT_TO_CART_SUCCESS";
    public final static String CREAT_NEW_BOOK_SUCCESS = "CREAT_NEW_BOOK_SUCCESS";
    public final static String UPDATE_BOOK_SUCCESS = "UPDATE_BOOK_SUCCESS";
    public final static String DELETE_BOOK_SUCCESS = "DELETE_BOOK_SUCCESS";

    //Varibale
    public final static int ADMIN_ROLE = 1;
    public final static int CUSTOMER_ROLE = 2;
    public final static String LIST_BOOK = "LIST_BOOK";
    public final static String LIST_CATEGORY = "LIST_CATEGORY";
    public final static String BOOK_DETAIL = "BOOK_DETAIL";
    public final static String FILE_IMAGE_PATH = "./images/";

    public final static String USER_NAME = "USER_NAME";
    public final static String USER_PROFILE = "USER_PROFILE";
    public final static String ADMIN_LOGIN_FLAG = "ADMIN_LOGIN_FLAG";
    public final static String CUSTOMER_LOGIN_FLAG = "CUSTOMER_LOGIN_FLAG";
    public final static String LIST_BOOK_CART = "LIST_BOOK_CART";

}
