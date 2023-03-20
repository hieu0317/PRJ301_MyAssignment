/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
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
public class StudentDBContext extends DBContext<Student>{
    
     public ArrayList<Session> getCourseReport(int sid, int cid){
        ArrayList<Session> sessions = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT distinct ses.SessionID,ses.Date,a.aid,a.status,a.description\n" +
"                ,gr.GroupID,gr.GName,c.CourseID,c.CName,c.Code,i.InstructorName\n" +
"                 FROM Session ses INNER JOIN [Group] gr on gr.GroupID = ses.GroupID\n" +
"							      INNER JOIN [TimeSlot] t on t.TimeSlotID = ses.TimeSlotID\n" +
"								  INNER JOIN [Room] r on r.RoomID = ses.RoomID\n" +
"								  INNER JOIN Instructor i on i.InstructorID = ses.InstructorID\n" +
"								  INNER JOIN [StudentGroup] sg on sg.GroupID = ses.GroupID\n" +
"								  INNER JOIN [Student] s on s.StudentID = sg.StudentID\n" +
"								  INNER JOIN [Course] c on c.CourseID = gr.GroupID\n" +
"								  INNER JOIN [Attendance] a on a.sesid = ses.SessionID and a.sid = s.StudentID\n" +
"								  WHERE s.StudentID=? and c.CourseID= ? ORDER BY gr.GroupID,ses.Date;
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setInt(2, cid);
            rs = stm.executeQuery();
            while (rs.next()) {
              
//                Student stu = new Student();
//                stu.setSid(rs.getInt("StudentID"));
//                stu.setLname(rs.getString("LastName"));
//                stu.setFname(rs.getString("FirstName"));
//                stu.setsRollNumber(rs.getString("StudentRollNumber"));
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
    
     public Student getTimeTable(int sid, Date from, Date to) {
        Student student = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT distinct s.StudentID,s.LastName,s.FirstName,s.StudentRollNumber,s.Gender,s.Dob,ses.SessionID,ses.Date,a.aid,a.status,a.description\n"
                    + "                   ,g.GroupID,g.GName,c.CourseID,c.CName,c.Code,r.RoomID,r.RName,i.InstructorID,i.InstructorRollNumber,i.InstructorName\n"
                    + "                    ,t.TimeSlotID,t.TimeFrom,t.TimeTo\n"
                    + "                    FROM Student s INNER JOIN StudentGroup sg ON s.StudentID = sg.StudentID\n"
                    + "                                       INNER JOIN [Group] g ON g.GroupID = sg.GroupID\n"
                    + "                                      INNER JOIN [Course] c ON g.CourseID = c.CourseID\n"
                    + "                                      INNER JOIN [Session] ses ON g.GroupID = ses.GroupID\n"
                    + "                                       INNER JOIN [TimeSlot] t ON t.TimeSlotID = ses.TimeSlotID\n"
                    + "                                      INNER JOIN [Room] r ON r.RoomID = ses.RoomID\n"
                    + "                                      INNER JOIN [Attendance] a ON ses.SessionID = a.sesid and s.StudentID = a.sid\n"
                    + "                                      INNER JOIN Instructor i ON i.InstructorID = ses.InstructorID\n"
                    + "                    		  WHERE s.StudentID = ? AND ses.Date>= ? AND ses.Date <= ? ORDER BY s.StudentID,g.GroupID";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            Group currentGroup = new Group();
            currentGroup.setGid(-1);
            while (rs.next()) {
                if(student == null)
                {
                    student = new Student();
                    student.setSid(rs.getInt("StudentID"));
                    student.setLname(rs.getString("LastName"));
                    student.setFname(rs.getString("FirstName"));
                    student.setGender(rs.getBoolean("Gender"));
                    student.setDob(rs.getDate("Dob"));
                    student.setsRollNumber(rs.getString("StudentRollNumber"));
                }
                int gid = rs.getInt("GroupID");
                if(gid != currentGroup.getGid())
                {
                    currentGroup = new Group();
                    currentGroup.setGid(rs.getInt("GroupID"));
                    currentGroup.setGname(rs.getString("GName"));
                    Course c = new Course();
                    c.setCid(rs.getInt("CourseID"));
                    c.setCname(rs.getString("CName"));
                    c.setCode(rs.getString("Code"));
                    currentGroup.setCourse(c);
                    student.getGroups().add(currentGroup);
                }
                Session ses = new Session();
                ses.setSesid(rs.getInt("SessionID"));
                ses.setDate(rs.getDate("Date"));      
                ses.setGroup(currentGroup);
                
                Instructor i = new Instructor();
                i.setiId(rs.getInt("InstructorID"));
                i.setIname(rs.getString("InstructorName"));
                i.setiRollNumber(rs.getString("InstructorRollNumber"));
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
                ses.setAttendance(a);
                
                currentGroup.getSessions().add(ses);

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
        return student;
    }
     
    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select StudentID, StudentRollNumber, LastName, FirstName, Dob, Gender from Student\n"
                    + "where StudentID = ?";
            boolean a = connection.isClosed();
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setSid(rs.getInt("StudentID"));
                s.setsRollNumber(rs.getString("StudentRollNumber"));
                s.setLname(rs.getString("LastName"));
                s.setFname(rs.getString("FirstName"));
                s.setGender(rs.getBoolean("Gender"));
                s.setDob(rs.getDate("Dob"));
                return s;
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
        return null;
    }

    @Override
    public ArrayList<Student> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
