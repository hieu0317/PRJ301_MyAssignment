<%-- 
    Document   : takeAttendance
    Created on : Mar 3, 2023, 7:40:59 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
            <link rel="stylesheet" href="CSS/TakeAttendance.css">
    </head>
    <body>
        <div>
            <header>
                <img src="img/fpt-university.png" >
                <img src="img/download-application-button-apple-app-store-free-vector.png">
                <img src="img/download.png">
                <p>FAP mobile app (myFAP) is ready at</p>
            </header>
            <div class="header-bar">
                <a class="Home"href="">Home</a>
                <p>Take Attendance</p>
                <div class="dropdown">
                    <img id="dropbtn" src="img/128-1280406_view-user-icon-png-user-circle-icon-png.png" >
                    <div class="dropdown-content">
                      <a href="#">Link 1</a>
                      <a href="#">Link 2</a>
                      <a href="#">Link 3</a>
                    </div>
                  </div>
            </div>
        </div>
        <div class="title">
            <h2>COURSE:</h2>
            <h2>CLASS,LECTURE:</h2>
        </div> 
        <h2>Date:</h2>
        <table>
            <tr>
                <th>No</th>
                <th>ID</th>
                <th>Student Name</th>
                <th>Status</th>
                <th>Teacher Note</th>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td>
                </td>
                <td> 
                    Attended: <input type="checkbox">
                    Absent: <input type="checkbox">
                </td>
                <td>
                    <input type="text">
                </td>
            </tr>
        </table>
        <div class="Save">
            <button>Save</button>
            <button>Cancle</button>
        </div>
        <div class="rooter">
            <p> Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: <span>dichvusinhvien@fe.edu.vn.</span> Điện
                thoại: <span class="PhoneNumber">(024)7308.13.13</span></p>
            <p> © Powered by <span>University</span> | <span>CMS</span> | <span>library</span> | <span>books24x7</span></p>
        </div>   
    </body>
</html>
