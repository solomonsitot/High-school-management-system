import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class Result {

    String CourseId;
//    String EmployeId;
    String grade;



    public Result(String grade){
            this.grade = grade;
    }




    public Result(String courseId, String studentId, double midExam, double finalExam, double continiousAssesment) {
        CourseId = courseId;
        StudentId = studentId;
        this.midExam = midExam;
        this.finalExam = finalExam;
        this.continiousAssesment = continiousAssesment;
    }

    String StudentId;
    double midExam;
    double finalExam;
    double continiousAssesment;






    void InsertResult(){
        try {
            Conn conn = new Conn();
            double total;
            total = midExam+finalExam+continiousAssesment;
            //Here is the total of all the course.

            String AddResult = "INSERT INTO Result VALUES(?,?,?,?,?,?)";



            PreparedStatement ToAddResult = conn.connect().prepareStatement(AddResult);
            ToAddResult.setString(1, StudentId);
            ToAddResult.setString(2,CourseId);

            ToAddResult.setDouble(3,midExam);
            ToAddResult.setDouble(4,finalExam);
            ToAddResult.setDouble(5,continiousAssesment);
            ToAddResult.setDouble(6,total);
            ToAddResult.execute();


        }catch (SQLException e){
            System.out.println(e);
        }

    }

    void UpdateResult(){

        try {
            Conn conn = new Conn();
            String UpdateResult = "Update  Result set MidExam = ?,FinalExam = ? ,ContiniousAssesment = ? where  StudentId=? && CourseID=?";

            PreparedStatement ToAddResult = conn.connect().prepareStatement(UpdateResult);
            ToAddResult.setString(5,StudentId);
            ToAddResult.setString(4,CourseId);
            ToAddResult.setDouble(1, midExam);
            ToAddResult.setDouble(2, finalExam);
            ToAddResult.setDouble(3, continiousAssesment);
            ToAddResult.execute();
        }catch (SQLException q){
            System.out.println(q);

    }catch (Exception e) {
            System.out.println(e);}

    }

    void RetriveAllResult(){
        try {
            Conn conn = new Conn();
            String sql = "SELECT * FROM RESULT";

            Statement statement = conn.connect().createStatement();
            ResultSet resultset =  statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData  = resultset.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            while(resultset.next()){

            }


        }catch (SQLException e){
            System.out.println(e);
        }

    }




    void studentSummary() throws Exception {
        Conn conn = new Conn();

        String total = "INSERT INTO StudentSummary (studentid, coursestotalmark, average, `rank`) " +
                "SELECT r.StudentId, SUM(r.total) AS `total`, " +
                "SUM(r.total) / COUNT(DISTINCT r.CourseId) AS Average, " +
                "RANK() OVER (ORDER BY SUM(`total`) DESC) AS `Rank` " +
                "FROM Result r " +
                "JOIN Student s ON r.StudentId = s.StudentId " +
                "WHERE s.grade = ? " +
                "GROUP BY r.StudentId";
        System.out.println("Hello"+grade);
        PreparedStatement statement = conn.connect().prepareStatement(total);
        statement.setString(1, grade);
        statement.executeUpdate();
    }







//void RetriveSummary() throws Exception{
//    Conn conn = new Conn();
//    String retriveSumaary = "SELECT * FROM StudentSummary ";
//
//    Statement statement = conn.connect().createStatement();
//    ResultSet rs = statement.executeQuery(retriveSumaary);
//
//    while(rs.next()){
//        System.out.println(rs.getString(1)+"    "+rs.getDouble(2 )+"    "+rs.getDouble(3) +"     "+ rs.getInt(4));
//    }
//}



//
//void studentSummar() throws Exception {
//        Conn conn = new Conn();
//
//         String total = "INSERT INTO StudentSummary (studentid, coursestotalmark, average, `rank`) " +
//            "SELECT r.StudentId, SUM(r.total) AS `total`, " +
//            "SUM(r.total) / COUNT(DISTINCT r.CourseId) AS Average, " +
//            "RANK() OVER (ORDER BY SUM(`total`) DESC) AS `Rank` " +
//            "FROM Result r " +
//            "JOIN Student s ON r.StudentId = s.StudentId " +
//            "GROUP BY r.StudentId";
//
////    System.out.println("Hello"+grade);
//        Statement statement = conn.connect().createStatement();
////        statement.setString(1, grade);
//            statement.executeUpdate(total);
//    }






}

