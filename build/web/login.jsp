<%-- 
    Document   : login
    Created on : May 12, 2021, 8:15:53 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form  action="Dispatcher" method="POST">
            <h1>Login Form</h1>
            <font color="red">${requestScope.LOGIN_ERR}</font><br/>

            Account: <input type="text" name="email" value="thangnguyen@gmail.com" /><br/>
            Password: <input type="password" name="password" value="123" /><br/>
            <input type="submit" value="LOGIN" name="Action" />
            <input type="reset" value="Reset" />

        </form>
    </body>
</html>
