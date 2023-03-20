<%-- 
    Document   : StudentAttendanceReport
    Created on : Mar 20, 2023, 1:23:46 PM
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
                        <a href="#">${requestScope.student.lname} ${student.fname}</a>
                        <a href="#">${requestScope.student.sRollNumber} </a>
                        <a href="#">
                            <c:if test="${requestScope.student.gender}">
                             Male
                            </c:if>
                             <c:if test="${requestScope.student.gender eq false}">
                             Female
                            </c:if>
                        </a>
                    </div>
                </div>
            </div>
        </div>                      
        <h1>Report</h1>
        <a href="reportAttend?cid=1">JPD123</a> ,
        <a href="reportAttend?cid=2">IOT102</a> ,
        <a href="reportAttend?cid=3">MAS291</a> ,
        <a href="reportAttend?cid=4">PRJ301</a>
        
        <c:if test="${requestScope.ses ne null}">
        <table>
            <tr>
                <th>
                    No
                </th>
                <th>
                    Date
                </th>
                <th>
                    Slot
                </th>
                <th>
                    Room
                </th>
                <th>
                    Instructor
                </th>
                <th>
                    Group Name
                </th>
                <th>
                    Attendance Status
                </th>
                <th>
                    Instructor's Comment
                </th>
                
            </tr>
             
                    <c:forEach items="${requestScope.ses}" var="ses" varStatus="loop">
                         <tr>
                            <td>
                                ${loop.index + 1}
                            </td>
                            <td>
                                ${ses.date}
                            </td>
                            <td>
                                 ${ses.timeSlot.tid}
                            </td>
                            <td>
                                 ${ses.room.rid}
                            </td>
                            <td>
                                 ${ses.instructor.iname}
                            </td>
                            <td>
                                 ${ses.group.gname}
                            </td>
                            <td>
                                <c:if test="${ses.attendance.getStatus() eq null}">
                                    <p>Future</p>
                                </c:if >
                                <c:if test="${ses.attendance.getStatus() ne null}">
                                    <c:if test="${ses.attendance.getStatus()}">
                                        <font color="green">(attended)</font>
                                    </c:if>
                                    <c:if test="${ses.attendance.getStatus() eq false}">
                                        <font color="red">(absent)</font>
                                    </c:if>
                                </c:if>
                            </td>
                            <td>
                                 ${ses.attendance.description}
                            </td>
                        </tr>   
                    </c:forEach>   
           

        </table>
        </c:if>
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
