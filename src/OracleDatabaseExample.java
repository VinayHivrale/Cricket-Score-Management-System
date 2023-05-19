import java.sql.*;

public class OracleDatabaseExample {

    public static void main(String[] args) {
        try {
            // Establish database connection
            String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // Replace with your Oracle connection URL
            String username = "c##example"; // Replace with your Oracle username
            String password = "example"; // Replace with your Oracle password
            Connection conn = DriverManager.getConnection(url, username, password);

            // Create and execute SQL query
            String query = "SELECT * FROM vinay";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Count the number of rows returned
            int rowCount = 0;

            // Process the result set
            while (rs.next()) {
                rowCount++;

                String name = rs.getString("name");
                int age = rs.getInt("age");

                // Print the fetched records
                System.out.println("Name: " + name + ", Age: " + age);
            }

            // Check if any rows were retrieved
            if (rowCount == 0) {
                System.out.println("No records found in the table.");
            } else {
                System.out.println("Total records retrieved: " + rowCount);
            }

            // Close the resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
