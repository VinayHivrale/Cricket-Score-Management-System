import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginScreen extends JFrame implements ActionListener {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton submitButton;

    public LoginScreen() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create username label and field
        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(20);

        // Create password label and field
        JLabel passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField(20);

        // Create submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        // Create panel and add components
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(submitButton);

        // Add panel to frame
        getContentPane().add(panel);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        // Handle submit button click event
        if (ae.getSource() == submitButton) {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);


            try {
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata","root","Root@24680");
                Statement stmt= ((Connection) con).createStatement();
                String query = "select * from credentials where username= '"+username+"' and password = '"+password+"'";

                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login");

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    usernameField.setText("");
                    passwordField.setText("");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public static void main(String[] args) {
        LoginScreen loginScreen = new LoginScreen();
    }
}


