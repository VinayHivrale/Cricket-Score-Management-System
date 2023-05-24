import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ScorekeeperRegistrationFormm extends JFrame {
    private JTextField nameField, ageField, usernameField, passwordField;
    private JButton submitButton;

    public ScorekeeperRegistrationFormm() {
        super("Scorekeeper Registration Form");

        // create labels and text fields
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(20);
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        // create submit button
        submitButton = new JButton("Submit");
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

                // insert data into scorekeeper_cred table
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cricket", "root", "Root@24680");
                    String query = "INSERT INTO scorekeeper_cred (name, age, username, password) VALUES (?, ?, ?, ?)";
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

        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // empty label for spacing
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

