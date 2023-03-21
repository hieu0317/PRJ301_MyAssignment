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
import model.Group;
import model.Session;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author admin
 */
public class AttendanceDBContext extends DBContext<Attendance> {
    
    public ArrayList<Attendance> getGroupStatus(int gid) {
        ArrayList<Attendance> atts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
             String sql = " SELECT s.StudentID,s.FirstName,s.LastName,s.StudentRollNumber,gr.GroupID,gr.Gname,ses.TimeSlotID,ses.SessionId,a.aid,a.Status FROM Student s\n"
                    + "                                LEFT JOIN [StudentGroup] sg ON s.StudentID = sg.StudentID\n"
                    + "                                LEFT JOIN [Group] gr ON gr.GroupID = sg.GroupID\n"
                    + "                                LEFT JOIN Course c on c.CourseID = gr.CourseID\n"
                    + "                                LEFT JOIN [Session] ses ON ses.GroupID = gr.GroupID\n"
                    + "                                LEFT JOIN [Attendance] a ON ses.sessionid = a.sesid AND s.StudentID = a.sid\n"
                    + "                                LEFT JOIN Instructor i on ses.InstructorID = i.InstructorID\n"
                    + "                                WHERE gr.GroupID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setId(rs.getInt("aid"));
                Boolean b = rs.getObject("Status") != null ? rs.getBoolean("Status") : null;
                a.setStatus(b);

                Session ses = new Session();
                ses.setSesid(rs.getInt("SessionID"));
                
                TimeSlot t = new TimeSlot();
                t.setTid(rs.getInt("TimeSlotID"));
                ses.setTimeSlot(t);
                
                Group gr = new Group();
                gr.setGid(rs.getInt("GroupID"));
                Student stu = new Student();
                stu.setSid(rs.getInt("StudentID"));
                stu.setLname(rs.getString("LastName"));
                stu.setFname(rs.getString("FirstName"));
                stu.setsRollNumber(rs.getString("StudentRollNumber"));
                a.setStudent(stu);
                atts.add(a);
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
        return atts;

    }
    
    public ArrayList<Attendance> getAttendancesBySession(int sessionid) {
        String sql = "SELECT s.StudentID,s.LastName,s.FirstName,\n"
                + "                a.aid,\n"
                + "                ISNULL(a.status,0) as [status],\n"
                + "                ISNULL(a.description,'') as [description]\n"
                + "                FROM Student s LEFT JOIN [StudentGroup] sg ON s.StudentID = sg.StudentID\n"
                + "                LEFT JOIN [Group] g ON g.GroupID = sg.GroupID\n"
                + "                LEFT JOIN [Session] ses ON ses.GroupID = g.GroupID\n"
                + "                LEFT JOIN [Attendance] a ON ses.sessionid = a.sesid AND s.StudentID = a.sid\n"
                + "                WHERE ses.sessionid = ?";
        ArrayList<Attendance> atts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            stm = connection.prepareStatement(sql);
            stm.setInt(1, sessionid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setId(rs.getInt("aid"));
                a.setStatus(rs.getBoolean("status"));
                a.setDescription(rs.getString("description"));
                Student s = new Student();
                s.setSid(rs.getInt("StudentID"));
                s.setFname(rs.getString("FirstName"));
                s.setLname(rs.getString("LastName"));
                a.setStudent(s);
                atts.add(a);
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
        return atts;

    }

    public void update(ArrayList<Attendance> atts, int sessionid) {
        try {
            connection.setAutoCommit(false);
            String sql_update_session = "UPDATE [Session]\n"
                    + "   SET [Status] = 1\n"
                    + " WHERE [GroupID] = ?";
            PreparedStatement stm_update_session = connection.prepareStatement(sql_update_session);
            stm_update_session.setInt(1, sessionid);
            stm_update_session.executeUpdate();
            for (Attendance a : atts) {
                if (a.getId() == 0) {
                    //INSERT
                    String sql_insert_att = "INSERT INTO [Attendance]\n"
                            + "           ([sid]\n"
                            + "           ,[sesid]\n"
                            + "           ,[status]\n"
                            + "           ,[description])\n"
                            + "     VALUES\n"
                            + "           (?\n"
                            + "           ,?\n"
                            + "           ,?\n"
                            + "           ,?)";
                    PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                    stm_insert_att.setInt(1, a.getStudent().getSid());
                    stm_insert_att.setInt(2, a.getSession().getSesid());
                    stm_insert_att.setBoolean(3, a.getStatus());
                    stm_insert_att.setString(4, a.getDescription());
                    stm_insert_att.executeUpdate();
                } else {
                    //UPDATE
                    String sql_update_att = "UPDATE [Attendance]\n"
                            + "   SET \n"
                            + "	   [status] = ?\n"
                            + "      ,[description] = ?\n"
                            + " WHERE [aid] = ?";
                    PreparedStatement stm_update_att = connection.prepareStatement(sql_update_att);
                    stm_update_att.setBoolean(1, a.getStatus());
                    stm_update_att.setString(2, a.getDescription());
                    stm_update_att.setInt(3, a.getId());
                    stm_update_att.executeUpdate();
                }
            }

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(dal.StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(dal.StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void insert(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attendance> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}