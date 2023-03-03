<%-- 
    Document   : login
    Created on : Feb 18, 2023, 3:48:54 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="login-container">
            <label for="email"> Email:</label> <br/>
            <input type="text" placeholder="Enter your school emaill"> <br/>
            <label for="password"> Password: </label> <br/>
            <input type="password" placeholder="Enter your password"> <br/>
            <label>
                <input type="checkbox" checked="checked" name="remember"> Remember me
            </label> <br/>
            <button type="submit">Login</button>           
        </div>
    </body>
</html>
