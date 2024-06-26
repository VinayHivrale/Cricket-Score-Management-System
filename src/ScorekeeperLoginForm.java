import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ScorekeeperLoginForm extends JFrame {
    private JTextField usernameField, passwordField;
    private JButton loginButton, backButton; // Declare backButton as an instance variable

    public ScorekeeperLoginForm() {
        super("Scorekeeper Login Form");

        // create labels and text fields
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        // create login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // get the values from the fields
                String username = usernameField.getText();
                String password = passwordField.getText();

                // check if all fields are filled
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                    return;
                }

                // check if the credentials are valid
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cricket", "root", "Root@24680");
                    String query = "SELECT * FROM scorekeeper_cred WHERE username = ? AND password = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login successful");
                        MatchSearchApp app = new MatchSearchApp();
                        app.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }

                    conn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // create back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(2, 10, 15));
        backButton.setBorder(new LineBorder(new Color(42, 41, 41), 2));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // close login window
                ButtonWindow buttonWindow = new ButtonWindow();
                buttonWindow.setVisible(true);
                buttonWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
                dispose();
            }
        });

        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(backButton); // Add backButton to the panel
        panel.add(loginButton);

        // add panel to frame
        add(panel);

        // set frame properties
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
