/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Course;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author admin
 */
public class SessionDBContext extends DBContext<Session>{
    
    public ArrayList<Session> getAllByGroupID(int gid){
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT SessionID, Date from Session where GroupID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            rs = stm.executeQuery();
            while (rs.next()) {
              
                Session ses = new Session();
                ses.setSesid(rs.getInt("SessionID"));
                ses.setDate(rs.getDate("Date"));     
                sessions.add(ses);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sessions;
    }
    
    public ArrayList<Session> getCourseReport(int sid, int cid){
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT distinct s.StudentID,s.LastName,s.FirstName,s.StudentRollNumber,s.Gender,s.Dob,ses.SessionID,ses.Date,a.aid,a.status,a.description,r.RoomID,r.RName,t.TimeSlotID,t.TimeFrom,t.TimeTo\n"
                    + "                ,gr.GroupID,gr.GName,c.CourseID,c.CName,c.Code,i.InstructorID,i.InstructorName\n"
                    + "                 FROM Session ses INNER JOIN [Group] gr on gr.GroupID = ses.GroupID\n"
                    + "							      INNER JOIN [TimeSlot] t on t.TimeSlotID = ses.TimeSlotID\n"
                    + "								  INNER JOIN [Room] r on r.RoomID = ses.RoomID\n"
                    + "								  INNER JOIN Instructor i on i.InstructorID = ses.InstructorID\n"
                    + "								  INNER JOIN [StudentGroup] sg on sg.GroupID = ses.GroupID\n"
                    + "								  INNER JOIN [Student] s on s.StudentID = sg.StudentID\n"
                    + "								  INNER JOIN [Course] c on c.CourseID = gr.CourseID\n"
                    + "								  INNER JOIN [Attendance] a on a.sesid = ses.SessionID and a.sid = s.StudentID\n"
                    + "								  WHERE s.StudentID=? and c.CourseID= ? ORDER BY gr.GroupID,ses.Date";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setInt(2, cid);
            rs = stm.executeQuery();
            while (rs.next()) {
              
                Session ses = new Session();
                ses.setSesid(rs.getInt("SessionID"));
                ses.setDate(rs.getDate("Date"));     
                
                Group gr = new Group();
                gr.setGid(rs.getInt("GroupID"));
                gr.setGname(rs.getString("GName"));
                Course c = new Course();
                c.setCid(rs.getInt("CourseID"));
                c.setCname(rs.getString("CName"));
                gr.setCourse(c);
                ses.setGroup(gr);
                
                Instructor i = new Instructor();
                i.setiId(rs.getInt("InstructorID"));
                i.setIname(rs.getString("InstructorName"));
                ses.setInstructor(i);
                
                Room r = new Room();
                r.setRid(rs.getInt("RoomID"));
                r.setRname(rs.getString("RName"));
                ses.setRoom(r);
                
                TimeSlot t = new TimeSlot();
                t.setTid(rs.getInt("TimeSlotID"));
                t.setTfrom(rs.getString("TimeFrom"));
                t.setTto(rs.getString("TimeTo"));
                ses.setTimeSlot(t);
                
                Attendance a = new Attendance();
                a.setId(rs.getInt("aid"));
                a.setDescription(rs.getString("description"));
                Boolean b = rs.getObject("Status") != null ? rs.getBoolean("Status") : null;
                a.setStatus(b);
                Student stu = new Student();
                stu.setSid(rs.getInt("StudentID"));
                stu.setLname(rs.getString("LastName"));
                stu.setFname(rs.getString("FirstName"));
                stu.setsRollNumber(rs.getString("StudentRollNumber"));
                stu.setDob(rs.getDate("Dob"));
                a.setStudent(stu);
                
                ses.setAttendance(a);
                
                sessions.add(ses);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sessions;
    }

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Session> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
