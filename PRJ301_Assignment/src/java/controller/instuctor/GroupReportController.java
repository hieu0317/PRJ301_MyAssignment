/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instuctor;

import controller.authentication.BasedRequiredTeacherAuthenticationController;
import dal.AttendanceDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Attendance;
import model.Session;
import model.Student;
import model.User;

/**
 *
 * @author admin
 */
public class GroupReportController extends BasedRequiredTeacherAuthenticationController{
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        int raw_gid = u.getInstructor().getiId();
        if(raw_gid == 4){
            raw_gid += 1;
        }
        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Session> sessions = sesDB.getAllByGroupID(raw_gid);
        req.setAttribute("ses", sessions);
   
        StudentDBContext stuDB = new StudentDBContext();
        ArrayList<Student> students = stuDB.studentByGroupID(raw_gid);
        req.setAttribute("students", students);
        
        AttendanceDBContext atDB = new AttendanceDBContext();
        ArrayList<Attendance> atts = atDB.getGroupStatus(raw_gid);
        req.setAttribute("atts", atts);

        req.getRequestDispatcher("../view/instructor/groupAttendanceReport.jsp").forward(req, resp);

    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        processRequest(req, resp, user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        processRequest(req, resp, user);
    }
    
}
