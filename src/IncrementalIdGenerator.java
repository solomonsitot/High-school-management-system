import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class IncrementalIdGenerator {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hms";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
  static String TABLE_NAME = "";
  static String ID_COLUMN_NAME = "";

    public IncrementalIdGenerator(String tableName,String columnName) {
        TABLE_NAME = tableName;
        ID_COLUMN_NAME = columnName;
    }



    public static String generateId() {
        String id = UUID.randomUUID().toString();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " = ?")) {

            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                // If ID already exists, generate a new one incrementally
                id = generateId();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your requirement
        }

        return id;
    }
}
