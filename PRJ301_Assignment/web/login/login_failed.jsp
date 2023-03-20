<%-- 
    Document   : logout
    Created on : Mar 13, 2023, 7:48:05 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
            <meta charset="UTF-8">
            <title>Login</title>
            <link rel="stylesheet"  href="${pageContext.request.contextPath}/CSS/login.css">
        </head>
        <body>
            <div class="login-container">
                <h2>Login</h2>
                <form action="../login" method="POST">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <select name="campus" id="">
                        <option value="">Hòa Lạc</option>
                        <option value="">Hồ Chí Minh</option>
                    </select> </br>
                    <font color="red">Wrong password or user name</font> </br>
                    <button type="submit">Login</button>
                </form>
            </div>
            <img src="../img/trung-dung-ki-tuc-xa-sinh-vien-_dh_fpt.jpg" alt="Background Image">
        </body>
</html>
