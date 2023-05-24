import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminLogin {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame frame;

    public AdminLogin(int p) {
        // Setting Nimbus look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Nimbus look and feel not available");
        }

        // create labels and text fields with comic sans font
        Font comicSans = new Font("Comic Sans MS", Font.PLAIN, 18);
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(comicSans);
        usernameField = new JTextField(20);
        usernameField.setFont(comicSans);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(comicSans);
        passwordField = new JPasswordField(20);
        passwordField.setFont(comicSans);

        // create login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // get the values from the fields
                String username = usernameField.getText();
                String password = passwordField.getText();

                // check if both fields are filled
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both username and password");
                    return;
                }

                // check if login credentials are correct
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cricket", "root", "Root@24680");
                    String query = "SELECT * FROM admin_cred WHERE username=? AND password=?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        //JOptionPane.showMessageDialog(null, "Login successful");
                        if(p==0)
                        {  new AdminWindow();
                        frame.dispose(); // close login window
                             }
                        else if(p==1)
                        {
                            new ScorekeeperRegistrationFormm();
                            frame.dispose();

                        }
                        else
                        {
                            new AdminRegistrationForm();
                            frame.dispose();
;                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }

                    conn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        loginButton.setFont(comicSans);
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setBackground(new Color(2, 10, 15));
        loginButton.setBorder(new LineBorder(new Color(42, 41, 41), 2));
        JButton backButton = new JButton("Back");
        backButton.setFont(comicSans);
        backButton.setForeground(new Color(255, 255, 255));
        backButton.setBackground(new Color(2, 10, 15));
        backButton.setBorder(new LineBorder(new Color(42, 41, 41), 2));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 // close login window
                ButtonWindow buttonWindow = new ButtonWindow();
                buttonWindow.setVisible(true);
                buttonWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.dispose();
            }
        });

        // create panel and add components with custom background color for text field
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
                 // empty label for spacing
        panel.add(backButton);
        panel.add(loginButton);

        panel.setBackground(new Color(242, 242, 2)); // custom background color

        // create frame and add panel to it
        frame = new JFrame("Admin Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        // set the size of the frame
        int width = 600;
        int height = 200;
        frame.setSize(width, height);

        // center the frame on the screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        frame.setLocation(x, y);

        frame.setVisible(true);
    }
}