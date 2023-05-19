import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonWindow extends JFrame {

    public ButtonWindow() {
        // Set up the JFrame
        setTitle("Button Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);

        // Set up the container
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(42, 41, 41, 255));
        Font font = new Font("Comic Sans MS", Font.BOLD, 30);
        setFont(font);

        // Create the label for the title
        JLabel titleLabel = new JLabel("Cricket Score Management System");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 80));
        titleLabel.setForeground(new Color(103, 242, 249));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the north of the BorderLayout
        container.add(titleLabel, BorderLayout.NORTH);

        // Create the buttons
        JButton createAdminButton = new JButton("Create Admin Account");
        JButton createScorekeeperButton = new JButton("Create Scorekeeper");
        JButton adminLoginButton = new JButton("Login for Admin");
        JButton scorekeeperLoginButton = new JButton("Login for Scorekeeper");

        // Change the button color
        createAdminButton.setBackground(new Color(25, 23, 23)); // light red
        createScorekeeperButton.setBackground(new Color(25, 23, 23)); // light green
        adminLoginButton.setBackground(new Color(25, 23, 23, 255)); // light blue
        scorekeeperLoginButton.setBackground(new Color(25, 23, 23, 255)); // light yellow

        createScorekeeperButton.setFont(font);
        createAdminButton.setFont(font);
        adminLoginButton.setFont(font);
        scorekeeperLoginButton.setFont(font);

        createScorekeeperButton.setForeground(new Color(103, 242, 249));
        createAdminButton.setForeground(new Color(103, 242, 249));
        adminLoginButton.setForeground(new Color(103, 242, 249));
        scorekeeperLoginButton.setForeground(new Color(103, 242, 249));

        // Add a border to the buttons
        Border buttonBorder = BorderFactory.createLineBorder(new Color(255, 255, 255, 255), 5);
        createAdminButton.setBorder(buttonBorder);
        createScorekeeperButton.setBorder(buttonBorder);
        adminLoginButton.setBorder(buttonBorder);
        scorekeeperLoginButton.setBorder(buttonBorder);

        // Remove the button focus ring
        createAdminButton.setFocusPainted(false);
        createScorekeeperButton.setFocusPainted(false);
        adminLoginButton.setFocusPainted(false);
        scorekeeperLoginButton.setFocusPainted(false);
        createAdminButton.setBackground(new Color(6, 27, 41));
        createScorekeeperButton.setBackground(new Color(6, 27, 41));
        adminLoginButton.setBackground(new Color(6, 27, 41));
        scorekeeperLoginButton.setBackground(new Color(6, 27, 41));

        // Add an ActionListener to the createAdminButton
        adminLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AdminLogin();
            }
        });

        createAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AdminRegistrationForm();
            }
        });

        createScorekeeperButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ScorekeeperRegistrationFormm();
            }
        });

        scorekeeperLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ScorekeeperLoginForm();
            }
        });

        // Add the buttons to the container
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.add(createAdminButton);
        buttonPanel.add(createScorekeeperButton);
        buttonPanel.add(adminLoginButton);
        buttonPanel.add(scorekeeperLoginButton);
        container.add(buttonPanel, BorderLayout.CENTER);

        // Add an image to the east side of the BorderLayout
        ImageIcon eastImageIcon = new ImageIcon("data/.jpeg");
        JLabel eastImageLabel = new JLabel(eastImageIcon);
        container.add(eastImageLabel, BorderLayout.WEST);

        // Add an image to the west side of the BorderLayout
        ImageIcon westImageIcon = new ImageIcon("data/Screenshot 2023-05-17 210147.png");
        JLabel westImageLabel = new JLabel(westImageIcon);
        container.add(westImageLabel, BorderLayout.EAST);

        // Center the JFrame
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ButtonWindow buttonWindow = new ButtonWindow();
                buttonWindow.setVisible(true);
                buttonWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }
}

