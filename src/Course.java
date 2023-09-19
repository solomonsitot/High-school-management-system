import java.sql.Connection;;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Course {
    String courseId;
    String courseName;
    int grade;

//    Result resultId;

    public Course(String courseId, String courseName, int grade) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
//        this.resultId = resultId;
    }

    public Course(String courseId) {
        this.courseId = courseId;
    }

    void insertCourse(){
        Conn c = new Conn();
       Connection conn =   c.connect();
       String query = "insert into courses values(?,?,?)";
        PreparedStatement pStmt;
        try {
            pStmt = conn.prepareStatement(query);
            pStmt.setString(1,courseId);
            pStmt.setString(2,courseName);
            pStmt.setInt(3,grade);
            int row = pStmt.executeUpdate();
            if(row>0){
                System.out.println(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void deleteCourse(){
        Conn c = new Conn();
        Connection conn =   c.connect();
        String query = "delete from courses where courseid = ?";
        PreparedStatement pStmt;
        try {
            pStmt = conn.prepareStatement(query);
            pStmt.setString(1,courseId);
            int row = pStmt.executeUpdate();
            if(row>0){
                System.out.println(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
