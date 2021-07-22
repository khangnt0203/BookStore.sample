<%-- 
    Document   : updateBook
    Created on : May 16, 2021, 1:42:05 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Book Page</title>
    </head>
    <body>
        <font color="red"><h1>Welcome, ${sessionScope.USER_NAME}</h1></font> <a href="LogoutServlet">Logout</a><br/>
        <a href="WelcomeServlet">Back Home</a><br/>
        <c:set var="listCategory" value="${requestScope.LIST_CATEGORY}"/>
        <c:set var="bookDetail" value="${requestScope.BOOK_DETAIL}"/>
        <c:if test="${not empty listCategory and not empty bookDetail}">
            <h1>Update Book Form</h1>
            <form action="Dispatcher" method="POST">
                Book's ID: <input type="text" name="bookID" value="${bookDetail.bookID}" readonly="readonly" required=""/><br/>
                Book's Title: <input type="text" name="bookTitle" value="${bookDetail.bookTitle}" required=""/><br/>
                Book's Description: <input type="text" name="bookDescription" value="${bookDetail.bookDescription}" required=""/><br/>
                Book's Author: <input type="text" name="author" value="${bookDetail.author}" required=""/><br/>
                Book's Price: <input type="number" name="txtPrice" value="${bookDetail.price}" required=""/><br/>
                Book's Store Inventory: <input type="number" name="txtAmount" value="${bookDetail.amount}" required=""/><br/>
                Book's Category: 
                <select name="categoryID">
                    <c:forEach var="dto" items="${listCategory}">
                        <option value="${dto.categoryID}" <c:if test="${dto.categoryID == bookDetail.categoryID}"> selected="selected" </c:if> >${dto.categoryName}</option>
                    </c:forEach>
                </select><br/>

                Book's Status: 
                <select name="txtStatus">
                    <option value="true" <c:if test="${bookDetail.status == true}">selected="selected"</c:if>>Available</option>
                    <option value="false" <c:if test="${bookDetail.status == false}">selected="selected"</c:if>>UnAvailable</option>
                    </select><br/>
                    Book's Image:
                    <img src="${bookDetail.image}" width="100px" height="100px"/><br/>
                <label for="image">Book's new image(Optional):</label>
                <input type="file" id="image" name="image" accept="image/png, image/jpeg"><br/>

                <input type="hidden" name="oldImage" value="${bookDetail.image}" />
                <input type="submit" value="UPDATE_BOOK" name="Action" /><br/>
                <font color="red">${requestScope.UPDATE_BOOK_FAILED}</font>
                <font color="green">${requestScope.UPDATE_BOOK_SUCCESS}</font>
            </form>
        </c:if>
    </body>
</html>
