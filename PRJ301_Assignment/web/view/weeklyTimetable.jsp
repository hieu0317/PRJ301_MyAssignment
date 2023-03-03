<%-- 
    Document   : weeklyTimetable
    Created on : Mar 3, 2023, 7:40:48 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS/WeekylyTimetable.css">
    </head>
    <body>
        <div>
            <header>
                <img src="img/fpt-university.png">
                <img src="img/download-application-button-apple-app-store-free-vector.png">
                <img src="img/download.png">
                <p>FAP mobile app (myFAP) is ready at</p>
            </header>
            <div class="header-bar">
                <a class="Home" href="">Home</a>
                <p>View Schedule</p>
                <div class="dropdown">
                    <img id="dropbtn" src="img/128-1280406_view-user-icon-png-user-circle-icon-png.png">
                    <div class="dropdown-content">
                        <a href="#">Link 1</a>
                        <a href="#">Link 2</a>
                        <a href="#">Link 3</a>
                    </div>
                </div>
            </div>
        </div>
        <h1>Activities for ....</h1>
        <table>
            <tr>
                <th rowspan="2">
                    Year:2023 </br>
                    <label for="Week">Week:</label>
                    <select name="weeks" id="weeks">
                        <option value="">
                            week1
                        </option>
                        <option value="">w2</option>
                        <option value="">w3</option>
                    </select>
                </th>
                <th>Monday</th>
                <th>Tuesday</th>
                <th>Wednesday</th>
                <th>Thursday</th>
                <th>Friday</th>
                <th>Saturday</th>
                <th>Sunday</th>
            </tr>

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
