<%-- 
    Document   : insertBook
    Created on : May 16, 2021, 10:45:31 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Book Page</title>
    </head>
    <body>
        <font color="red"><h1>Welcome, ${sessionScope.USER_NAME}</h1></font> <a href="LogoutServlet">Logout</a><br/>
        <a href="WelcomeServlet">Back Home</a><br/>
        <c:set var="listCategory" value="${requestScope.LIST_CATEGORY}"/>
        <c:if test="${not empty listCategory}">
            <h1>Insert Book Form</h1><br/>
            <form action="Dispatcher" method="POST">
                Book's ID: <input type="text" name="bookID" value="" required=""/><br/>
                Book's Title: <input type="text" name="bookTitle" value="" required=""/><br/>
                Book's Description: <input type="text" name="bookDescription" value="" required=""/><br/>
                Book's Author: <input type="text" name="author" value="" required=""/><br/>
                Book's Price: <input type="number" name="txtPrice" value="" required=""/><br/>
                Book's Store Inventory: <input type="number" name="txtAmount" value="" required=""/><br/>
                Book's Category: 
                <select name="categoryID">
                    <c:forEach var="dto" items="${listCategory}">
                        <option value="${dto.categoryID}">${dto.categoryName}</option>
                    </c:forEach>
                </select><br/>

                <label for="image">Book's image:</label>
                <input type="file" id="image" name="images" accept="image/*" required=""><br/>

                <input type="submit" value="ISERT_NEW_BOOK" name="Action" /><br/>
                <font color="green">${requestScope.CREAT_NEW_BOOK_SUCCESS}</font>
                <font color="red">${requestScope.CREAT_NEW_BOOK_ERROR}</font>
            </form>
        </c:if>
    </body>
</html>
