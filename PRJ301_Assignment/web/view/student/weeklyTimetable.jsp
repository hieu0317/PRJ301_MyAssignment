<%-- 
    Document   : weeklyTimetable
    Created on : Mar 3, 2023, 7:40:48 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/WeeklyTimetable.css">              
    </head>
    <body>
        <div>
            <header>
                <img src="../img/fpt-university.jpg">
                <img src="../img/download-application-button-apple-app-store-free-vector.jpg">
                <img src="../img/download.jpg">
                <p>FAP mobile app (myFAP) is ready at</p>
            </header>
            <div class="header-bar">
                <a class="Home" href="">Home</a>
                <p>View Schedule</p>
                <div class="dropdown">
                    <img id="dropbtn" src="../img/128-1280406_view-user-icon-png-user-circle-icon-png.jpg">
                    <div class="dropdown-content">
                        <a href="#">Link 1</a>
                        <a href="#">Link 2</a>
                        <a href="#">Link 3</a>
                    </div>
                </div>
            </div>
        </div>
        <h1>Activities for ....</h1>
        <form action="timetable" class="inputDate" method="GET">
            From:<input type="text" name="from"> - To:<input type="text" name="to">
            <input type="submit" value="Search">
        </form>
        <table>
            <tr>
                <th>
                        Year:2023 </br>
                   
                </th>
               <c:forEach items="${requestScope.dates}" var="d">
                    <th>${d}<br/><fmt:formatDate value="${d}" pattern="EEEE"/></th>
                </c:forEach>
            </tr>
             <c:forEach items="${requestScope.slots}" var="slot"> 
                <tr>
                    <td>
                        Slot: ${slot.tid} </br>
                        ${slot.tfrom} - ${slot.tto}
                    </td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.s.groups}" var="g">
                                <c:forEach items="${g.sessions}" var="ses">
                                    <c:if test="${ses.date eq d and ses.timeSlot.tid eq slot.tid}">
                                        
                                            ${g.gname}(${g.course.code}) <br/>
                                            ${ses.instructor.iname}-${ses.room.rname} <br/>
                                            <c:if test="${ses.status}">
                                                <font color="green">(attended)</font>
                                            </c:if>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>

        </table>
        <div class="Note">
            <h2>More note/Ch?? th??ch th??m:</h2>
            <p> <span class="attend">(attended)</span>: HieuVMHE172039 had attended this activity / V?? Minh Hi???u ???? tham gia
                ho???t ?????ng n??y</p>
            <p> <span class="absent">(absent)</span>: HieuVMHE172039 had NOT attended this activity / V?? Minh Hi???u ???? v???ng
                m???t bu???i n??y</p>
            <p> (-): no data was given / ch??a c?? d??? li???u</p>
        </div>
        <div class="rooter">
            <p> M???i g??p ??, th???c m???c xin li??n h???: Ph??ng d???ch v??? sinh vi??n: Email: <span>dichvusinhvien@fe.edu.vn.</span> ??i???n
                tho???i: <span class="PhoneNumber">(024)7308.13.13</span></p>
            <p> ?? Powered by <span>University</span> | <span>CMS</span> | <span>library</span> | <span>books24x7</span></p>
        </div>
    
    </body>
</html>
