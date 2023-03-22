/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import com.sun.beans.editors.IntegerEditor;
import controller.authentication.BasedRequiredAuthenticationController;
import dal.SessionDBContext;
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
public class StudentReportController extends BasedRequiredAuthenticationController{
    
     protected void processRequest(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        String cid = req.getParameter("cid");
        int raw_cid;
        if(cid == null){
            raw_cid = 0;
        }else{
            raw_cid = Integer.parseInt(cid);
        }
   
        StudentDBContext stuDB = new StudentDBContext();
        SessionDBContext sesDB = new SessionDBContext();
        Student student = stuDB.get(user.getStudent().getSid());
        req.setAttribute("student", student);
        ArrayList<Session> sessions = sesDB.getCourseReport(user.getStudent().getSid(), raw_cid);
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
