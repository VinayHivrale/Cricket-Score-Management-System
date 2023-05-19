import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChooseTeamPlayersForm extends JFrame {
    private JTextField matchIdField;
    private JTextField teamIdField;
    private List<JCheckBox> playerCheckboxes;

    public ChooseTeamPlayersForm() {
        super("Choose Team Players");

        // Create labels and text fields
        JLabel matchIdLabel = new JLabel("Match ID:");
        matchIdField = new JTextField(10);
        JLabel teamIdLabel = new JLabel("Team ID:");
        teamIdField = new JTextField(10);

        // Create player checkboxes
        playerCheckboxes = new ArrayList<>();

        // Create submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int matchId = Integer.parseInt(matchIdField.getText());
                int teamId = Integer.parseInt(teamIdField.getText());

                // Validate match ID and team ID
                if (!validateMatchAndTeam(matchId, teamId)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Match ID and Team ID");
                    return;
                }

                List<String> selectedPlayers = getSelectedPlayers();

                if (selectedPlayers.size() != 11) {
                    JOptionPane.showMessageDialog(null, "Please select exactly 11 players");
                } else {
                    // Save selected players to the database
                    if (saveSelectedPlayersToDatabase(matchId, teamId, selectedPlayers)) {
                        JOptionPane.showMessageDialog(null, "Team players saved successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error occurred while saving team players");
                    }
                }
            }
        });

        // Create panel and add components
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.add(matchIdLabel);
        panel.add(matchIdField);
        panel.add(teamIdLabel);
        panel.add(teamIdField);

        panel.add(new JLabel("Players:"));

        // Retrieve players from the database based on the match ID and team ID
        // using inner join
        int matchId = Integer.parseInt(matchIdField.getText());
        int teamId = Integer.parseInt(teamIdField.getText());
        retrievePlayersFromDatabase(matchId, teamId);

        for (JCheckBox checkbox : playerCheckboxes) {
            panel.add(checkbox);
        }

        panel.add(submitButton);

        // Set window properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(panel);
        pack();
        setVisible(true);
    }

    private boolean validateMatchAndTeam(int matchId, int teamId) {
        // Perform validation based on your requirements
        // For example, check if the match and team exist in the database
        // Adjust the code according to your database schema and validation logic
        // Return true if the match ID and team ID are valid; otherwise, return false

        // Sample validation code to be replaced with your own logic
        if (matchId == 0 || teamId == 0) {
            return false;
        }

        return true;
    }

    private void retrievePlayersFromDatabase(int matchId, int teamId) {
        try {
            // Establish database connection
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cricket";
            String username = "root";
            String password = "Root@24680";
            Connection conn = DriverManager.getConnection(url, username, password);

            // Create the SQL query with inner join to retrieve players
            // Create the SQL query with inner join to retrieve players
            String selectQuery = "SELECT p.player_name FROM player p " +
                    "INNER JOIN cricket_team ct ON p.team_id = ct.team_id " +
                    "INNER JOIN `match` m ON ct.team_id = m.team1_id OR ct.team_id = m.team2_id " +
                    "WHERE m.match_id = ? AND ct.team_id = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, matchId);
            selectStatement.setInt(2, teamId);

            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                String playerName = resultSet.getString("player_name");
                JCheckBox checkbox = new JCheckBox(playerName);
                playerCheckboxes.add(checkbox);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getSelectedPlayers() {
        List<String> selectedPlayers = new ArrayList<>();

        for (JCheckBox checkbox : playerCheckboxes) {
            if (checkbox.isSelected()) {
                selectedPlayers.add(checkbox.getText());
            }
        }

        return selectedPlayers;
    }

    private boolean saveSelectedPlayersToDatabase(int matchId, int teamId, List<String> selectedPlayers) {
        try {
            // Establish database connection
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cricket";
            String username = "root";
            String password = "Root@24680";
            Connection conn = DriverManager.getConnection(url, username, password);

            // Delete previous entries for the given match and team
            String deleteQuery = "DELETE FROM match_selected_players WHERE match_id = ? AND team_id = ?";
            PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, matchId);
            deleteStatement.setInt(2, teamId);
            deleteStatement.executeUpdate();

            // Insert new entries for the selected players
            String insertQuery = "INSERT INTO match_selected_players (match_id, team_id, player_id) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);

            for (String playerName : selectedPlayers) {
                // Retrieve the player ID based on the player name
                String selectPlayerIdQuery = "SELECT player_id FROM player WHERE player_name = ?";
                PreparedStatement selectPlayerIdStatement = conn.prepareStatement(selectPlayerIdQuery);
                selectPlayerIdStatement.setString(1, playerName);
                ResultSet resultSet = selectPlayerIdStatement.executeQuery();

                if (resultSet.next()) {
                    int playerId = resultSet.getInt("player_id");
                    insertStatement.setInt(1, matchId);
                    insertStatement.setInt(2, teamId);
                    insertStatement.setInt(3, playerId);
                    insertStatement.executeUpdate();
                }
            }

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ChooseTeamPlayersForm();
            }
        });
    }
}
