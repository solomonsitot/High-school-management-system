import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

    Connection connect(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdb.Driver");
        }catch (ClassNotFoundException e) {
           e.getStackTrace();
        }
            String db = "jdbc:mysql://localhost:3306/hms";
            String user = "root";
            String pass = "";
            try {
                conn = DriverManager.getConnection(db, user, pass);
                if(conn != null){
                    System.out.println("connect");
                }
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        return conn;
    }

}
