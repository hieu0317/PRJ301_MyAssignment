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
                <a class="Home" href="../view/home/StudentHome.jsp">Home</a>
                <p>View Schedule</p>
                <div class="dropdown">
                    <img id="dropbtn" src="../img/128-1280406_view-user-icon-png-user-circle-icon-png.jpg">
                    <div class="dropdown-content">
                        <a href="#">${requestScope.s.lname} ${s.fname}</a>
                        <a href="#">${requestScope.s.sRollNumber} </a>
                        <a href="#">
                            <c:if test="${requestScope.s.gender}">
                             Male
                            </c:if>
                             <c:if test="${requestScope.s.gender eq false}">
                             Female
                            </c:if>
                        </a>
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
                                            <c:if test="${ses.attendance.getStatus() eq null}">
                                                <font color="red">(not yet) </font>
                                            </c:if >
                                                <c:if test="${ses.attendance.getStatus() ne null}">
                                                    <c:if test="${ses.attendance.getStatus()}">
                                                    <font color="green">(attended)</font>
                                                </c:if>
                                                <c:if test="${ses.attendance.getStatus() eq false}">
                                                    <font color="red">(absent)</font>
                                                </c:if>
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
            <h2>More note/Chú thích thêm:</h2>
            <p> <span class="attend">(attended)</span>: HieuVMHE172039 had attended this activity / Vũ Minh Hiếu đã tham gia
                hoạt động này</p>
            <p> <span class="absent">(absent)</span>: HieuVMHE172039 had NOT attended this activity / Vũ Minh Hiếu đã vắng
                mặt buổi này</p>
            <p> (-): no data was given / chưa có dữ liệu</p>
        </div>
        <div class="rooter">
            <p> Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: <span>dichvusinhvien@fe.edu.vn.</span> Điện
                thoại: <span class="PhoneNumber">(024)7308.13.13</span></p>
            <p> © Powered by <span>University</span> | <span>CMS</span> | <span>library</span> | <span>books24x7</span></p>
        </div>
    
    </body>
</html>
