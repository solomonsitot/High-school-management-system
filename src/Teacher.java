import javax.swing.plaf.nimbus.State;
import  java.sql.*;
import java.util.ArrayList;

public class Teacher extends Employee {
    private String CourseId;

    public Teacher(String employeID, String firstName, String lastName, String educationalBAckground, String gender, String dateOfBirth, String adress, String password, String age, String courseId) {
        super(employeID, firstName, lastName, educationalBAckground, gender, dateOfBirth, adress, password, age);
        CourseId = courseId;
    }

    public Teacher(String employeID) {
        super(employeID);
    }

    public Teacher() {
    }

    public String getCourseId() {
        return CourseId;
    }
    private boolean isDirector;
    public boolean isDirector() {
        return isDirector= false;
    }

    public ResultSet login(String userName, String password){
        Conn c = new Conn();
        ResultSet result;
        Connection conn = c.connect();

        String query = "select * from teacher where firstname = ? AND password = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,userName);
            stmt.setString(2,password);
             result = stmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void AddTeacher() {
        Conn c = new Conn();
        Connection conn = c.connect();
        String query = "INSERT INTO teacher VALUES(?,?,?,?,?,?,?,?,?,?,isdirector)";
        PreparedStatement stmt;

        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, getEmployeID());
            stmt.setString(2, getFirstName());
            stmt.setString(3, getLastName());
            stmt.setString(4, getEducationalBAckground());
            stmt.setString(5, getAge());
            stmt.setString(6, getGender());
            stmt.setString(7, getDateOfBirth());
            stmt.setString(8, getAdress());
            stmt.setString(9, getPassword());
            stmt.setString(10, getCourseId() );

           int rows = stmt.executeUpdate();
            System.out.println(rows);
           if(rows > 0){
               System.out.println(rows);
           }

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> getTeachersInfo(){
        ArrayList<ArrayList<String>> teachersData = new ArrayList<>();
        ArrayList<String> row = new ArrayList<>();
        Conn c = new Conn();
        ResultSet resultSet = null;
        Connection conn = c.connect();
        String query = "select * from teacher";
        try{            Statement stmt = conn.createStatement();
            resultSet =  stmt.executeQuery(query);

              while (resultSet.next()){

                  row.add(resultSet.getString(1));
                   row.add(resultSet.getString(2));
                    row.add(resultSet.getString(3));
                    row.add(resultSet.getString(4));
                    row.add(resultSet.getString(5));
                    row.add(resultSet.getString(6));
                    row.add(resultSet.getString(7));
                    row.add(resultSet.getString(8));
                    row.add(resultSet.getString(9));
                  row.add(resultSet.getString(10));


                  teachersData.add(row);
              }


            System.out.println(teachersData);
        }catch (SQLException ex){
            ex.getStackTrace();
        }
      return teachersData;
    }

    void deleteTeacher(){
        Conn c = new Conn();
        Connection conn =   c.connect();
        String query = "delete from teacher where teacherid = ?";
        PreparedStatement pStmt;
        try {
            pStmt = conn.prepareStatement(query);
            pStmt.setString(1,getEmployeID());
            int row = pStmt.executeUpdate();
            if(row>0){
                System.out.println(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }






