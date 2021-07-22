<%-- 
    Document   : customerHome
    Created on : May 13, 2021, 6:57:57 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.USER_NAME}">
            <font color="red"><h1>Welcome, ${sessionScope.USER_NAME}</h1></font><a href="LogoutServlet">Logout</a><br/>
        </c:if>

        <c:if test="${empty sessionScope.USER_NAME}">
            <a href="login.jsp">Login</a><br/>
        </c:if>
        <a href="ViewBookCartServlet">View Cart</a><br/>
        <%@include file="searchBookForm.jsp" %>

        <c:set var="listBook" value="${requestScope.LIST_BOOK}"/>
        <c:if test="${not empty listBook}">
            <font color="green">${requestScope.ADD_PRODUCT_TO_CART_SUCCESS}</font><br/>
            <font color="red">${requestScope.ADD_PRODUCT_TO_CART_ERROR}</font><br/>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Category</th>
                        <th>Book Title</th>
                        <th>Book Description</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>Image</th>
                        <th>Amount</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${listBook}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.categoryName}</td>
                            <td>${dto.bookTitle}</td>
                            <td>${dto.bookDescription}</td>
                            <td>${dto.author}</td>
                            <td>${dto.price}</td>
                            <td>
                                <img src="${dto.image}" width="60px" height="60px"/>
                            </td>
                            <td>${dto.amount}</td>
                            <td>
                                <form action="Dispatcher" method="POST">
                                    <input type="hidden" name="txtBookID" value="${dto.bookID}" />
                                    <input type="hidden" name="txtQuantity" value="${dto.amount}" />
                                    <input type="submit" value="ADD_TO_CART" name="Action" />
                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </body>
</html>
