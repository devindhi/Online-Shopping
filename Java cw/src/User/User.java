package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

    private String username;
    private char[] password;
    private static String client;

    //constructor
    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public static String getClient() {
        return client;
    }


    public static class Login extends Database implements ActionListener {
        private JFrame frame;
        private JLabel topic, type_id, type_password, sign, result;
        private JTextField id;
        private JPasswordField passwordField;
        private JButton enter, signup;
        private Connection connection;

        public Login() {
            frame = new JFrame();
            frame.setLayout(null);

            Database d = new Database();
            connection = d.dbConnection();

            topic = new JLabel("Login");
            topic.setFont(new Font("Times New Roman", Font.BOLD, 26));
            topic.setForeground(new Color(103, 4, 4));
            topic.setBounds(190, 10, 650, 30);
            frame.add(topic);

            type_id = new JLabel("User ID");
            type_id.setBounds(90, 60, 90, 30);
            frame.add(type_id);

            id = new JTextField(20);
            id.setBounds(200, 60, 200, 30);
            frame.add(id);

            type_password = new JLabel("Password");
            type_password.setBounds(90, 100, 90, 30);
            frame.add(type_password);

            passwordField = new JPasswordField(10);
            passwordField.setBounds(200, 100, 200, 30);
            frame.add(passwordField);

            enter = new JButton("Login");
            enter.addActionListener(this);
            enter.setBounds(200, 150, 100, 30);
            frame.add(enter);

            sign = new JLabel("Don't have an account?");
            sign.setBounds(178, 190, 200, 30);
            frame.add(sign);

            signup = new JButton("Sign Up");
            signup.setBounds(200, 230, 100, 30);
            signup.addActionListener(this);
            frame.add(signup);

            result = new JLabel("");
            result.setBounds(200, 300, 150, 30);
            frame.add(result);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Shopping Cart");
            frame.setSize(500, 400);
            frame.setResizable(false);
            frame.setVisible(true);
        }

        public void checking_password(Connection connection, User user) {
            String passwordString = new String(user.getPassword());
            int userid = Integer.parseInt(user.getUsername());
            try {
                Statement statement = connection.createStatement(); // Create a statement
                String query = "SELECT Password_num FROM customers WHERE id =" + userid;
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    String password1 = resultSet.getString("Password_num"); // Retrieve Password_num column

                    if (passwordString.equals(password1)) {
                        frame.dispose();
                        Shop s1 = new Shop();
                        s1.refreshTableData();
                    } else {
                        result.setText("Incorrect Password");
                    }
                } else {
                    result.setText("Incorrect User ID");

                }

                // Close resources
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle or log the exception
                result.setText("Error occurred");
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == signup) {
                frame.dispose();
                SignUp s1 = new SignUp();
            }
            if (e.getSource() == enter) {
                User user = new User(id.getText(), passwordField.getPassword());
                checking_password(connection,user);
                client = user.getUsername();

            }
        }




    }
}
