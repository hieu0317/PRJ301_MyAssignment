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
public class InstructorDBContext extends DBContext<Instructor>{

    public Instructor getTimeTable(int InsID, Date from, Date to) {
        Instructor instructor = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT distinct ses.SessionID,ses.Date,g.GroupID,g.GName,c.CourseID,c.CName,c.Code,r.RoomID,r.RName,"
                    + "i.InstructorID,i.InstructorRollNumber,i.InstructorName,t.TimeSlotID,t.TimeFrom,t.TimeTo\n"
                    + "           FROM Student s INNER JOIN StudentGroup sg ON s.StudentID = sg.StudentID\n"
                    + "                INNER JOIN [Group] g ON g.GroupID = sg.GroupID\n"
                    + "                INNER JOIN [Course] c ON g.CourseID = c.CourseID\n"
                    + "                INNER JOIN [Session] ses ON g.GroupID = ses.GroupID\n"
                    + "                INNER JOIN [TimeSlot] t ON t.TimeSlotID = ses.TimeSlotID\n"
                    + "                INNER JOIN [Room] r ON r.RoomID = ses.RoomID\n"
                    + "                INNER JOIN Instructor i ON i.InstructorID = ses.InstructorID\n"
                    + "                WHERE i.InstructorID = ? AND ses.Date>= ? AND ses.Date <= ? ORDER BY g.GroupID,ses.Date";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, InsID);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            while (rs.next()) {
                if(instructor == null)
                {
                    instructor = new Instructor();
                    instructor.setIname(rs.getString("InstructorName"));
                    instructor.setiId(rs.getInt("InstructorID"));
                    instructor.setiRollNumber(rs.getString("InstructorRollNumber"));
                }
//                int gid = rs.getInt("GroupID");
//                if(gid != currentGroup.getGid())
//                {
//                    currentGroup = new Group();
//                    currentGroup.setGid(rs.getInt("GroupID"));
//                    currentGroup.setGname(rs.getString("GName"));
//                    Course c = new Course();
//                    c.setCid(rs.getInt("CourseID"));
//                    c.setCname(rs.getString("CName"));
//                    c.setCode(rs.getString("Code"));
//                    currentGroup.setCourse(c);
//                    instructor.getGroups().add(currentGroup);
//                }
                Session ses = new Session();
                ses.setSesid(rs.getInt("SessionID"));
                ses.setDate(rs.getDate("Date"));
                
                Group group = new Group();
                group.setGid(rs.getInt("GroupID"));
                group.setGname(rs.getString("GName"));
                ses.setGroup(group); 
                
                Course c = new Course();
                c.setCid(rs.getInt("CourseID"));
                c.setCname(rs.getString("CName"));
                c.setCode(rs.getString("Code"));
                group.setCourse(c);
                
                Room r = new Room();
                r.setRid(rs.getInt("RoomID"));
                r.setRname(rs.getString("RName"));
                ses.setRoom(r);
                
                TimeSlot t = new TimeSlot();
                t.setTid(rs.getInt("TimeSlotID"));
                t.setTfrom(rs.getString("TimeFrom"));
                t.setTto(rs.getString("TimeTo"));
                ses.setTimeSlot(t);
                
                group.getSessions().add(ses);
                instructor.getGroups().add(group);

            }
        } catch (SQLException ex) {
            Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instructor;
    }
    
    @Override
    public void insert(Instructor model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Instructor model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Instructor model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Instructor get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Instructor> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
