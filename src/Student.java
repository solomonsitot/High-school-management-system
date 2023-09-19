import java.sql.*;
import java.util.Vector;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String dateOfBirth;

    private int grade;


    // for updating and storing
    public Student(String studentId, String firstName, String lastName, int age, String gender, String dateOfBirth, int grade) {
        this.studentId =  studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.grade = grade;

    }
   //for selecting single student
    public Student(String studentId) {
        this.studentId = studentId;
    }
     // for select all students
    public Student() {
    }

    public Student(int grade) {
        this.grade = grade;
    }
 //registering student
    void registerStudent(){
        System.out.println(firstName);
          Conn c = new Conn();
          Connection conn =   c.connect();
          String query = "insert into students values(?,?,?,?,?,?,?)";
          PreparedStatement pStmt;
          try {
              pStmt = conn.prepareStatement(query);
              pStmt.setString(1,"stud/" +studentId);
              pStmt.setString(2,firstName);
              pStmt.setString(3,lastName);
              pStmt.setInt(4,age);
              pStmt.setString(5,gender);
              pStmt.setString(6,dateOfBirth);
              pStmt.setInt(7,grade);
              int row = pStmt.executeUpdate();
              if(row>0){
                  System.out.println(row);
              }
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
      }
 //update student info
      void updateStudentInfo() {
          Conn c = new Conn();
          Connection conn = c.connect();
          String query = "update students set firstname = ?,lastname = ?,age = ?,gender = ?,dateofbirth = ?,grade = ? where studentid = ?";
          PreparedStatement pStmt;
          try {
              pStmt = conn.prepareStatement(query);

              pStmt.setString(1, firstName);
              pStmt.setString(2, lastName);
              pStmt.setInt(3, age);
              pStmt.setString(4, gender);
              pStmt.setString(5, dateOfBirth);
              pStmt.setInt(6, grade);
              pStmt.setString(7, studentId);

              int row = pStmt.executeUpdate();
              if (row > 0) {
                  System.out.println(row);
              }
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
      }
          void deleteStudent(){
              Conn c = new Conn();
              Connection conn =   c.connect();
              String query = "delete from students where studentid = ?";
              PreparedStatement pStmt;
              try {
                  pStmt = conn.prepareStatement(query);
                  pStmt.setString(1,studentId);
                  int row = pStmt.executeUpdate();
                  if(row>0){
                      System.out.println(row);
                  }
              } catch (SQLException e) {
                  throw new RuntimeException(e);
              }
      }
      // get student inf using id
      void getStudentInfo(){
          Conn c = new Conn();
          Connection conn = c.connect();
          String query = "select * from students where studentid = ?";
          PreparedStatement stmt;
          ResultSet resultSet;

          try {
              stmt = conn.prepareStatement(query);
              stmt.setString(1,studentId);
              resultSet = stmt.executeQuery();
              while (resultSet.next()){
                  new Student(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                             resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6),
                          resultSet.getInt(7));
              }
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }


      }
      //get all students info
    Vector<Student> getAllStudentsInfo(){
        Vector<Student> list = new Vector<>();
        Conn c = new Conn();
        Connection conn = c.connect();
        String query = "select * from students ";
        PreparedStatement stmt;
        ResultSet resultSet;

        try {
            stmt = conn.prepareStatement(query);

            resultSet = stmt.executeQuery();
            while (resultSet.next()){
                list.add( new Student(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
                   ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

      return list;
    }
   //get all students that are in the same grade
    Vector<Student> getSectionStudentsInfo(){
        Vector<Student> list = new Vector<>();
        Conn c = new Conn();
        Connection conn = c.connect();
        String query = "select * from students where grade = ? ";
        PreparedStatement stmt;
        ResultSet resultSet;

        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,grade);
            resultSet = stmt.executeQuery();
            while (resultSet.next()){
                list.add( new Student(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }


    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getGrade() {
        return grade;
    }

//    public String getCourseId() {
////        return courseId;
//    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGrade(int grade) {
        grade = grade;
    }

//    public void setCourseId(String courseId) {
//        this.courseId = courseId;
//    }


}
