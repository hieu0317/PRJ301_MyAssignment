/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import com.sun.beans.editors.IntegerEditor;
import controller.authentication.BasedRequiredAuthenticationController;
import dal.StudentDBContext;
import dal.TimeSlotDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Session;
import model.Student;
import model.User;

/**
 *
 * @author admin
 */
public class ReportController extends BasedRequiredAuthenticationController{
    
     protected void processRequest(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        int cid_raw = Integer.parseInt(req.getParameter("cid"));
   
        StudentDBContext stuDB = new StudentDBContext();
        Student student = stuDB.get(user.getStudent().getSid());
        req.setAttribute("student", student);
        ArrayList<Session> sessions = stuDB.getCourseReport(user.getStudent().getSid(), cid_raw);
        req.setAttribute("ses", sessions);

        req.getRequestDispatcher("../view/student/StudentAttendanceReport.jsp").forward(req, resp);

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
