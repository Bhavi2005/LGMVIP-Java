import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String keyword = "java";
        String MYSQL_URL = "jdbc:mysql://localhost:3306/database_name";
        String USER = "root";
        String PASSWORD = "my_password";
        List<String> results = new ArrayList<>();

        try {
            // Register JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            Connection connection = DriverManager.getConnection(MYSQL_URL, USER, PASSWORD);

            // Execute SQL query
            Statement statement = connection.createStatement();
            String sql = "SELECT url FROM search_engine WHERE content LIKE '%" + keyword + "%'";
            ResultSet resultSet = statement.executeQuery(sql);

            // Process result set
            while (resultSet.next()) {
                results.add(resultSet.getString("url"));
            }

            // Close connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Print results
        for (String result : results) {
            System.out.println(result);
        }
    }
}
