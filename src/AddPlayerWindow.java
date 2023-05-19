import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddPlayerWindow extends JFrame {
    private JTextField playerIdField;
    private JTextField teamIdField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField emailField;
    private JComboBox<String> playerTypeComboBox;

    public AddPlayerWindow() {
        super("Add Player");

        // create labels and text fields
        JLabel playerIdLabel = new JLabel("Player ID:");
        playerIdField = new JTextField(20);
        JLabel teamIdLabel = new JLabel("Team ID:");
        teamIdField = new JTextField(20);
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        JLabel playerTypeLabel = new JLabel("Type of Player:");
        String[] playerTypes = {"Batsman", "Bowler", "All-Rounder", "Wicket-Keeper"};
        playerTypeComboBox = new JComboBox<>(playerTypes);

        // create submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // get the values from the fields
                String playerId = playerIdField.getText();
                String teamId = teamIdField.getText();
                String name = nameField.getText();
                String age = ageField.getText();
                String email = emailField.getText();
                String playerType = (String) playerTypeComboBox.getSelectedItem();

                // check if all fields are filled
                if (playerId.isEmpty() || teamId.isEmpty() || name.isEmpty() || age.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                    return;
                }

                // check if player already exists in database
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cricket", "root", "Root@24680");
                    String query = "SELECT * FROM player WHERE player_id=?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, playerId);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Player already exists with this player ID");
                    } else {
                        // add player to database


                        // dispose(); // close add player window


                        // add player to database
                        String insertQuery = "INSERT INTO player (player_id, team_id, name, age, email, type_of_player) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                        insertStmt.setString(1, playerId);
                        insertStmt.setString(2, teamId);
                        insertStmt.setString(3, name);
                        insertStmt.setString(4, age);
                        insertStmt.setString(5, email);
                        insertStmt.setString(6, playerType);
                        insertStmt.executeUpdate();


                        String countQuery = "SELECT COUNT(*) AS player_count FROM player WHERE team_id = ?";
                        PreparedStatement countStmt = conn.prepareStatement(countQuery);
                        countStmt.setString(1, teamId);
                        ResultSet countRs = countStmt.executeQuery();

                        if (countRs.next()) {
                            int playerCount = countRs.getInt("player_count");
                            if (playerCount > 15) {
                                JOptionPane.showMessageDialog(null, "Team already has 15 players. Cannot add more players.");
                                // Optionally, you can also delete the player record that was inserted earlier
                                // to maintain data integrity.
                                String deleteQuery = "DELETE FROM player WHERE player_id = ?";
                                PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
                                deleteStmt.setString(1, playerId);
                                deleteStmt.executeUpdate();
                                return;
                            }
                        }



// add player stats to database
                        String insertStatsQuery = "INSERT INTO player_stats (player_id) VALUES (?)";
                        PreparedStatement insertStatsStmt = conn.prepareStatement(insertStatsQuery);
                        insertStatsStmt.setString(1, playerId);
                        insertStatsStmt.executeUpdate();


                        JOptionPane.showMessageDialog(null, "Player added successfully");
// dispose(); // close add player window

                    }

                    conn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // create panel and add components
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(playerIdLabel);
        panel.add(playerIdField);
        panel.add(teamIdLabel);
        panel.add(teamIdField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(playerTypeLabel);
        panel.add(playerTypeComboBox);
        panel.add(submitButton);
        // set window properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dispose on close to close only current window
        setResizable(false);
        setLocationRelativeTo(null); // center window
        setContentPane(panel);
        pack(); // pack components tightly together
        setVisible(true); // make window visible
    }
}

