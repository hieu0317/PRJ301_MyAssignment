/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.authentication.BasedRequiredAuthenticationController;
import dal.StudentDBContext;
import dal.TimeSlotDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import model.Student;
import model.TimeSlot;
import model.User;
import util.DateTimeHelper;

/**
 *
 * @author admin
 */
public class TimeTableController extends HttpServlet{
    
  
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        Date from = null;
        Date to = null;
        if(from == null || to == null){
            Format formatter = new SimpleDateFormat("YYYY-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()+1);
            String firstDayOfWeek = formatter.format(calendar.getTime());
            from = Date.valueOf(firstDayOfWeek);
            calendar.add(Calendar.DATE, 6);
            String lastDayOfWeek = formatter.format(calendar.getTime());
            to = Date.valueOf(lastDayOfWeek);
        }else{
            from = Date.valueOf(req.getParameter("from"));
            to = Date.valueOf(req.getParameter("to"));
        }
        TimeSlotDBContext timeDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = timeDB.all();
        req.setAttribute("slots", slots);
        
        ArrayList<Date> dates = DateTimeHelper.getListDate(from, to);
        req.setAttribute("dates", dates);
        
        StudentDBContext stuDB = new StudentDBContext();
        Student student = stuDB.getTimeTable(1, from, to);
        req.setAttribute("s", student);
        
        req.getRequestDispatcher("../view/student/weeklyTimetable.jsp").forward(req, resp);
        
        
    } 
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
   
}  
