<%-- 
    Document   : takeAttendance
    Created on : Mar 3, 2023, 7:40:59 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/TakeAttendance.css">
    </head>
    <body>
        <div>
            <header>
                <img src="../img/fpt-university.jpg" >
                <img src="../img/download-application-button-apple-app-store-free-vector.jpg">
                <img src="../img/download.jpg">
                <p>FAP mobile app (myFAP) is ready at</p>
            </header>
            <div class="header-bar">
                <a class="Home" href="../view/home/InstructorHome.jsp">Home</a>
                <p>Take Attendance</p>
                <div class="dropdown">
                    <img id="dropbtn" src="../img/128-1280406_view-user-icon-png-user-circle-icon-png.jpg" >
                    <div class="dropdown-content">
                      <a href="#">Link 1</a>
                      <a href="#">Link 2</a>
                      <a href="#">Link 3</a>
                    </div>
                  </div>
            </div>
        </div>
        <h2>Take Attend</h2>
        <form action="takeAttend" method="POST"> 
        <table border="1px">
                <tr>
                    <th>Seq</th>
                    <th>Student Id</th>
                    <th>Name</th>
                    <th>Present/Absent</th>
                    <th>Description</th>
                </tr>
                <c:forEach items="${requestScope.atts}" var="a" varStatus="loop">
                    <tr>

                        <td>${loop.index +1}</td>
                        <td>${a.student.sid}</td>
                        <td>${a.student.lname} ${a.student.fname}</td>
                        <td>
                            <input type="radio" 
                                   <c:if test="${!a.getStatus()}">
                                   checked="checked" 
                                   </c:if>
                                   name="status${a.student.sid}" value="absent"/> Absent
                            <input type="radio" 
                                   <c:if test="${a.getStatus()}">
                                   checked="checked" 
                                   </c:if>
                                   name="status${a.student.sid}" value="present" /> Present
                        </td>
                        <td>
                            <input type="hidden" name="sid" value="${a.student.sid}"/>
                            <input type="hidden" name="aid${a.student.sid}" value="${a.id}"/>
                            <input type="text" name="description${a.student.sid}" value="${a.description}"/></td>
                    </tr>    

                </c:forEach>
            </table>
            <div class="Save">
                <input type="hidden" name="sessionid" value="${param.id}"/>
                <input type="submit" value="Save"/>
                <a class="cancle" href="timetable">Cancle</a>
            </div>
        </form>
        <div class="rooter">
            <p> Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: <span>dichvusinhvien@fe.edu.vn.</span> Điện
                thoại: <span class="PhoneNumber">(024)7308.13.13</span></p>
            <p> © Powered by <span>University</span> | <span>CMS</span> | <span>library</span> | <span>books24x7</span></p>
        </div>   
    </body>
</html>
