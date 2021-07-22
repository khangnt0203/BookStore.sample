<%-- 
    Document   : adminHome
    Created on : May 13, 2021, 6:57:37 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Home Page</title>
    </head>
    <body>
        <font color="red"><h1>Welcome, ${sessionScope.USER_NAME}</h1></font> <a href="LogoutServlet">Logout</a><br/>

        <a href="Dispatcher?Action=MOVE_INSERT_PAGE">Insert New Book</a><br/>
        <%@include file="searchBookForm.jsp" %>
        <c:set var="listBook" value="${requestScope.LIST_BOOK}"/>
        <c:if test="${not empty listBook}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Book ID</th>
                        <th>Category</th>
                        <th>Book Title</th>
                        <th>Book Description</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>Image</th>
                        <th>Status</th>
                        <th>Amount</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${listBook}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.bookID}</td>
                            <td>${dto.categoryName}</td>
                            <td>${dto.bookTitle}</td>
                            <td>${dto.bookDescription}</td>
                            <td>${dto.author}</td>
                            <td>${dto.price}</td>
                            <td>
                                <img src="${dto.image}" width="60px" height="60px"/>
                            </td>
                            <td>
                                <c:if test="${dto.status == true}">
                                    Available
                                </c:if>
                                <c:if test="${dto.status == false}">
                                    UnAvailable
                                </c:if>
                            </td>
                            <td>${dto.amount}</td>
                            <td>
                                <form action="Dispatcher" method="POST">
                                    <input type="hidden" name="bookID" value="${dto.bookID}" />
                                    <input type="submit" value="DELETE" name="Action" />
                                </form>
                            </td>
                            <td>
                                <form action="Dispatcher" method="POST">
                                    <input type="hidden" name="bookID" value="${dto.bookID}" />
                                    <input type="submit" value="UPDATE" name="Action" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </body>
</html>
