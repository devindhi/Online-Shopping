package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection;
    public Connection dbConnection() {
        String url = "jdbc:mysql://localhost:3306/ westminster_shopping"; // MySQL connection URL
        String username = "root"; // MySQL username
        String password = ""; // MySQL password
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL driver class
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC driver not found.");
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
        return connection;
    }
    public void createTable(){
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS customers ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "Name VARCHAR(255) NOT NULL, "
                    + "Password_num VARCHAR(8) NOT NULL, "
                    + "sales BOOLEAN DEFAULT TRUE"
                    + ")";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Database d = new Database();
        d.dbConnection();
    }

}

