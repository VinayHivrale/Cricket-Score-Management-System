import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddTeamWindow extends JFrame {
    private JTextField teamIdField;
    private JTextField teamNameField;
    private JTextField ownerNameField;

    public AddTeamWindow() {
        super("Add Team");

        // create labels and text fields
        JLabel teamIdLabel = new JLabel("Team ID:");
        teamIdField = new JTextField(20);
        JLabel teamNameLabel = new JLabel("Team Name:");
        teamNameField = new JTextField(20);
        JLabel ownerNameLabel = new JLabel("Owner Name:");
        ownerNameField = new JTextField(20);

        // create submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // get the values from the fields
                String teamId = teamIdField.getText();
                String teamName = teamNameField.getText();
                String ownerName = ownerNameField.getText();

                // check if all fields are filled
                if (teamId.isEmpty() || teamName.isEmpty() || ownerName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                    return;
                }

                // check if team already exists in database
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cricket", "root", "Root@24680");
                    String query = "SELECT * FROM cricket_team WHERE team_id=? OR team_name=?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, teamId);
                    stmt.setString(2, teamName);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Team already exists");
                    } else {
                        // add team to database
                        String insertQuery = "INSERT INTO cricket_team (team_id, team_name, owner_name) VALUES (?, ?, ?)";
                        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                        insertStmt.setString(1, teamId);
                        insertStmt.setString(2, teamName);
                        insertStmt.setString(3, ownerName);
                        insertStmt.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Team added successfully");
                       // dispose(); // close add team window
                    }

                    conn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(teamIdLabel);
        panel.add(teamIdField);
        panel.add(teamNameLabel);
        panel.add(teamNameField);
        panel.add(ownerNameLabel);
        panel.add(ownerNameField);
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

