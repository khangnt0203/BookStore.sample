<%-- 
    Document   : bookCart
    Created on : May 22, 2021, 10:38:18 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Cart Page</title>

        <script>
            function calPrice() {

                var table = document.getElementById("tableBook").getElementsByTagName("td");
                var sumVal = 0;
//                for (var i = 1; i < table.rows.length; i++)
//                {
////                    sumVal = parseFloat(table.rows[i].cells[2].innerHTML);
//                    console.log(table.rows[i].cells[2].innerHTML);
//                }
                for (var i = 0; i < table.length; i++) {
                    if (table[i].className == 'count-me') {
                        sumVal += isNaN(table[i].innerHTML) ? 0 : parseFloat(table[i].innerHTML);
                    }
                }
                document.getElementById("totalPrice").value = sumVal;
                document.getElementById("txtTotalPrice").value = sumVal
                console.log(sumVal);
            }


        </script>
    </head>
    <body onload="calPrice()">
        <c:if test="${not empty sessionScope.USER_NAME}">
            <font color="red"><h1>Welcome, ${sessionScope.USER_NAME}</h1></font><a href="LogoutServlet">Logout</a><br/>
        </c:if>

        <c:if test="${empty sessionScope.USER_NAME}">
            <a href="login.jsp">Login</a><br/>
        </c:if>

        <a href="WelcomeServlet">Continute Shopping</a>
        <c:set var="listBook" value="${requestScope.LIST_BOOK}"/>
        <font color="red">${requestScope.UPDATE_BOOK_AMOUNT_CART_ERROR}</font><br/>
        <c:if test="${not empty listBook}">
            <table border="1" id="tableBook">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Book Title</th>
                        <th>Price</th>
                        <th>Amount</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${listBook}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.bookTitle}</td>
                            <td class="count-me">${dto.price}</td>

                            <td>
                                <table border="0" class="col-lg-12">
                                    <thead>
                                        <tr>
                                            <th>
                                                <c:if test="${dto.amount > 1}">
                                                    <form action="Dispatcher" method="POST">
                                                        <input type="hidden" name="txtNegative" value="negative" />
                                                        <input type="hidden" name="txtBookID" value="${dto.bookID}" />
                                                        <input type="submit" value="-" name="Action" />
                                                    </form>
                                                </c:if>
                                                <c:if test="${dto.amount <= 1}">
                                                    <input type="submit" value="-" name="Action" disabled="true"/>
                                                </c:if> 
                                            </th>
                                            <th>${dto.amount}</th>
                                            <th>
                                                <form action="Dispatcher" method="POST">
                                                    <input type="hidden" name="txtPositive" value="positive" />
                                                    <input type="hidden" name="txtBookID" value="${dto.bookID}" />
                                                    <input type="submit" value="+" name="Action" />
                                                </form>
                                            </th>
                                        </tr>
                                    </thead>
                                </table>

                            </td>
                            <td>
                                <script>
                                    function removeBook(bookID) {
                                        var confirmRemove = confirm("Are you want to remove this book?");
                                        if (confirmRemove == true) {
                                            var txtConfirmRemove = document.getElementById("txtConfirmRemove" + bookID);
                                            txtConfirmRemove.value = "true";
                                            document.getElementById("btnRemoveCart" + bookID).click();
                                        }
                                    }
                                </script>
                                <input type="submit" value="REMOVE_FROM_CART" onclick="removeBook('${dto.bookID}')"/>
                                <form action="Dispatcher" method="POST" hidden="true">
                                    <input type="text" name="txtConfirmRemove" value="false" id="txtConfirmRemove${dto.bookID}"/>
                                    <input type="text" name="txtBookID" value="${dto.bookID}" />
                                    <input type="submit" value="REMOVE_FROM_CART" id ="btnRemoveCart${dto.bookID}" name="Action"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3">Total Price:</td>
                        <td colspan="1"> <input type="text" name="totalPrice" value="" id="totalPrice" readonly="readonly" /> </td>
                        <td colspan="1">
                            <script>
                                function confirmByProduct() {
                                    var confirmBy = confirm("Do you want by all books?");
                                    if (confirmBy == true) {
                                        var txtConfirmBy = document.getElementById("txtConfirmBy");
                                        txtConfirmBy.value = "true";
                                        document.getElementById("btnByBook").click();
                                    }
                                }

                                function checkLogin() {
                                    var confirmLogin = confirm("You're not login, login to payment for this bill ?");
                                    if (confirmLogin == true) {
                                        document.getElementById("linkLoginPage").click();
                                    }
                                }
                            </script>
                            <c:if test="${not empty sessionScope.USER_NAME}">
                                <input type="submit" value="Buy" onclick="confirmByProduct()"/>
                            </c:if>

                            <c:if test="${empty sessionScope.USER_NAME}">
                                <input type="submit" value="Buy" onclick="checkLogin()"/>
                            </c:if>
                            <a href="login.jsp" hidden="true" id="linkLoginPage">go login page</a>
                            <form action="Dispatcher" method="POST" hidden="true">
                                <input type="text" name="txtConfirmBy" value="false" id="txtConfirmBy" />
                                <input type="hidden" id="txtTotalPrice" name="txtTotalPrice" />
                                <input type="submit" value="BUY" id="btnByBook" name="Action" />
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
