import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminRegistrationForm extends JFrame {
    private JTextField nameField, ageField, usernameField, passwordField;
    private JButton submitButton, backButton;

    public AdminRegistrationForm() {
        super("Admin Registration Form");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create labels and text fields with Comic Sans MS font
        Font comicSans = new Font("Comic Sans MS", Font.PLAIN, 16);
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(comicSans);
        nameField = new JTextField(20);
        nameField.setFont(comicSans);
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(comicSans);
        ageField = new JTextField(20);
        ageField.setFont(comicSans);
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(comicSans);
        usernameField = new JTextField(20);
        usernameField.setFont(comicSans);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(comicSans);
        passwordField = new JPasswordField(20);
        passwordField.setFont(comicSans);

        // create submit button with Comic Sans MS font
        submitButton = new JButton("Submit");
        submitButton.setFont(comicSans);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // get the values from the fields
                String name = nameField.getText();
                String age = ageField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();

                // check if all fields are filled
                if (name.isEmpty() || age.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                    return;
                }

                // insert data into admin_cred table
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cricket", "root", "Root@24680");
                    String query = "INSERT INTO admin_cred (name, age, username, password) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, name);
                    stmt.setString(2, age);
                    stmt.setString(3, username);
                    stmt.setString(4, password);
                    stmt.executeUpdate();
                    conn.close();
                    JOptionPane.showMessageDialog(null, "Registration successful");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // create back button with Comic Sans MS font
        backButton = new JButton("Back");
        backButton.setFont(comicSans);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ButtonWindow buttonWindow = new ButtonWindow();
                buttonWindow.setVisible(true);
                buttonWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
                dispose();

            }
        });

        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(backButton);
        panel.add(submitButton);

        // add panel to frame
        add(panel);

        // set frame properties
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}