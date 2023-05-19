import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScorekeeperGUI extends JFrame implements ActionListener {

    // Declare GUI components
    private JButton registerScorekeeperButton, loginScorekeeperButton, registerAdminButton, loginAdminButton;

    public ScorekeeperGUI() {

        // Set up the frame
        super("Scorekeeper Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Create the buttons
        registerScorekeeperButton = new JButton("Register as Scorekeeper");
        registerScorekeeperButton.addActionListener(this);
        loginScorekeeperButton = new JButton("Login as Scorekeeper");
        loginScorekeeperButton.addActionListener(this);
        registerAdminButton = new JButton("Register as Admin");
        registerAdminButton.addActionListener(this);
        loginAdminButton = new JButton("Login as Admin");
        loginAdminButton.addActionListener(this);

        // Add the buttons to the frame
        JPanel panel = new JPanel();
        panel.add(registerScorekeeperButton);
        panel.add(loginScorekeeperButton);
        panel.add(registerAdminButton);
        panel.add(loginAdminButton);
        add(panel);

        setVisible(true);
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerScorekeeperButton) {
            new ScorekeeperRegistrationFormm();
        } else if (e.getSource() == loginScorekeeperButton) {
            // Handle login as scorekeeper
        } else if (e.getSource() == registerAdminButton) {
            // Handle register as admin
        } else if (e.getSource() == loginAdminButton) {
            // Handle login as admin
        }
    }

    public static void main(String[] args) {
        new ScorekeeperGUI();
    }
}

class ScorekeeperRegistrationForm extends JFrame {

    // Declare GUI components
    private JLabel nameLabel, emailLabel, passwordLabel;
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton submitButton;

    public ScorekeeperRegistrationForm() {

        // Set up the frame
        super("Scorekeeper Registration Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Create the form components
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle form submission
            }
        });

        // Add the form components to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(new JLabel(""));
        panel.add(submitButton);
        add(panel);

        setVisible(true);
    }
}


