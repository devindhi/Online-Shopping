package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SignUp extends Database implements ActionListener {
    private JFrame frame;
    private JLabel topic, type_name, type_password, sign;
    private JTextField name;
    private JPasswordField passwordField;
    private JButton enter, signup;
    private Connection connection;
    private JLabel userid;
    private JLabel warning;


    SignUp(){

        Database d = new Database();
        connection = d.dbConnection();
        frame = new JFrame();
        frame.setLayout(null);

        topic = new JLabel("Sign Up");
        topic.setFont(new Font("Times New Roman",Font.BOLD, 26));
        topic.setForeground(new Color(103, 4, 4));
        topic.setBounds(190, 10, 650, 30);
        frame.add(topic);

        type_name = new JLabel("Name");
        type_name.setBounds(90, 60, 90, 30);
        frame.add(type_name);

        name = new JTextField(20);
        name.addActionListener(this);
        name.setBounds(200, 60, 200, 30);
        frame.add(name);

        type_password = new JLabel("Password");
        type_password.setBounds(90, 100, 90, 30);
        frame.add(type_password);

        warning = new JLabel("Password max length is 8 characters");
        warning.setBounds(90,130,300,30);
        frame.add(warning);

        passwordField = new JPasswordField(8);
        passwordField.addActionListener(this);
        passwordField.setBounds(200, 100, 200, 30);
        frame.add(passwordField);

        enter = new JButton("Sign up");
        enter.setBounds(200, 160, 100, 30);
        enter.addActionListener(this);
        frame.add(enter);

        sign = new JLabel("Already have an account?");
        sign.setBounds(178, 200, 200, 30);
        frame.add(sign);

        signup = new JButton("Login");
        signup.setBounds(200, 240, 100, 30);
        signup.addActionListener(this);
        frame.add(signup);

        userid = new JLabel("");
        userid.setBounds(200,300,100,30);
        frame.add(userid);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Shopping Cart");
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void insertData(Connection connection,String name , String password){
        Statement statement = null;
        ResultSet generatedKeys = null;
        try {
            statement = connection.createStatement();
            String query = "INSERT INTO customers (Name, Password_num) " +
                    "VALUES ('" + name + "', '" + password + "')";

            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS); //returns the auto incrementing values in the table too
            generatedKeys = statement.getGeneratedKeys();//stores the auto generated values

            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                userid.setText("User ID:   " + userId); // Set the userid with the generated ID
            }

            System.out.println("Data inserted into the table successfully.");
        } catch (SQLException e) {
            System.out.println("Data insertion error: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signup){
            frame.dispose();
            User.Login s2 = new User.Login();

        }  else if (e.getSource() == enter) {
            String name1 = name.getText(); // Get text from the JTextField
            char[] password1 = passwordField.getPassword();
            String passwordString = new String(password1);
            insertData(connection,name1, passwordString);

        }

    }


}
