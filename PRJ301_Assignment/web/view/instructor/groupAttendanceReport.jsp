<%-- 
    Document   : groupAttendanceReport
    Created on : Mar 21, 2023, 12:12:18 AM
    Author     : admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        
        <c:if test="${requestScope.ses ne null}">
        <table>
            <tr>
                <th>
                    No
                </th>
                <th>
                    Group
                </th>
                <th>
                    Student Roll Number
                </th>
                <th>
                    Student Name
                </th>
                <c:forEach items="${requestScope.ses}" var="ses" varStatus="loop">
                    <th>
                        Slot ${loop.index + 1}
                    </th>
                </c:forEach>
                <th>
                    Percent Absent
                </th>
                
                
            </tr>
             
                    <c:forEach items="${requestScope.students}" var="s" varStatus="loop">
                        <c:forEach items="${requestScope.s.groups}" var="gr">
                         <tr>
                            <td>
                                ${loop.index + 1}
                            </td>
                            <td>
                                ${gr.gname}
                            </td>
                            <td>
                                 ${s.sRollNumber}
                            </td>
                            <td>
                                 ${s.lname} ${s.fname}
                            </td>
                            <c:set var="slot" value="0"/>
                            <c:forEach items="${requestScope.atts}" var="atts">
                                <c:if test="${s.sid eq atts.student.sid}">
                                    <td> <c:set var="t" value="${atts.status}"/>
                                        <span ${t eq  "absent" ? 'style="color: red"': t eq  "attended" ? 'style="color: green"': 'style="color: black"'}> ${atts.status eq null ? '-': atts.status eq "attended" ? 'P' : atts.status eq "absent" ? 'A':''}</span></td>
                                        <c:if test="${atts.status eq 'absent'}">
                                            <c:set var="p" value="${p+1}"/>
                                        </c:if>
                                    </c:if>
                            </c:forEach>
                            <c:set var="size" value="${requestScope.ses.size()}"/>
                            <fmt:formatNumber var="aa" value="${p/size*100}" pattern="##"/>
                            <td>
                                 ${aa gt 10 ? 'style="color:red"':''} >${aa}%
                            </td>         
                        </tr>
                        </c:forEach>
                    </c:forEach>   
        </table>
        </c:if>
        <div class="rooter">
            <p> Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: <span>dichvusinhvien@fe.edu.vn.</span> Điện
                thoại: <span class="PhoneNumber">(024)7308.13.13</span></p>
            <p> © Powered by <span>University</span> | <span>CMS</span> | <span>library</span> | <span>books24x7</span></p>
        </div>
    
    </body>
    
</html>
