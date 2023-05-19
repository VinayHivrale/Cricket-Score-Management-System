import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddPlayerGUI extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel nameLabel, ageLabel, teamLabel;
    private JTextField nameField, ageField, teamField;
    private JButton submitButton;

    public AddPlayerGUI() {
        setTitle("Add Player");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 30, 80, 25);
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(130, 30, 200, 25);
        panel.add(nameField);

        ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 60, 80, 25);
        panel.add(ageLabel);

        ageField = new JTextField(20);
        ageField.setBounds(130, 60, 200, 25);
        panel.add(ageField);

        teamLabel = new JLabel("Team:");
        teamLabel.setBounds(50, 90, 80, 25);
        panel.add(teamLabel);

        teamField = new JTextField(20);
        teamField.setBounds(130, 90, 200, 25);
        panel.add(teamField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 130, 80, 25);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String team = teamField.getText();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket", "root", "Root@24680");
                Statement stmt = conn.createStatement();

                String sql = "INSERT INTO player (name, age, team) VALUES ('" + name + "', " + age + ", '" + team + "')";
                stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet rs = stmt.getGeneratedKeys();
                int playerId = -1;
                if (rs.next()) {
                    playerId = rs.getInt(1);
                }

                sql = "INSERT INTO player_stats (id) VALUES (" + playerId + ")";
                stmt.executeUpdate(sql);

                JOptionPane.showMessageDialog(panel, "Player added successfully!");

                conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error adding player: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new AddPlayerGUI();
    }
}

