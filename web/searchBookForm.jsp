<%-- 
    Document   : searchBookByNameForm
    Created on : May 15, 2021, 12:27:25 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Book By Name Form</title>
    </head>
    <body>
        <form action="Dispatcher" method="POST">
            Search Book By Name: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" required="required"/>
            <input type="submit" value="SEARCH_BY_NAME" name="Action" />
        </form><br/>

        <c:set var="listCategory" value="${requestScope.LIST_CATEGORY}"/>
        <c:if test="${not empty listCategory}">
            <form action="Dispatcher" method="POST">
                Search By Category: 
                <select name="categoryID">
                    <c:forEach var="dto" items="${listCategory}">
                        <option value="${dto.categoryID}">${dto.categoryName}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="SEARCH_BY_CATEGORY" name="Action" />
            </form><br/>
        </c:if>

        <form action="Dispatcher" method="POST">
            Search By Range Of Money:<br/>
            Min Money <input type="text" name="txtMinMoney" value="${param.txtMinMoney}" required="required" /><br/>
            Max Money <input type="text" name="txtMaxMoney" value="${param.txtMaxMoney}" required="required"/><br/>
            <input type="submit" value="SEARCH_BY_RANGE_OF_MONEY" name="Action"/>
        </form>    
    </body>
</html>
