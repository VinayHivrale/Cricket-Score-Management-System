import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CreateMatchForm {

    private JFrame frame;
    private JPanel panel;
    private JLabel team1Label, team2Label, venueLabel, dateLabel, timeLabel, oversLabel, matchIdLabel;
    private JTextField team1Field, team2Field, venueField, dateField, timeField, oversField, matchIdField;
    private JButton submitButton, backButton;
    private final Font font;

    private boolean insertMatch() {
        String matchId = matchIdField.getText();
        String team1 = team1Field.getText();
        String team2 = team2Field.getText();
        String venue = venueField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        String overs = oversField.getText();

        try {
            // Establish database connection
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cricket";
            String username = "root";
            String password = "Root@24680";
            Connection conn = DriverManager.getConnection(url, username, password);

            // Check if both teams have 11 or more players
            String query = "SELECT COUNT(*) AS player_count FROM player WHERE team_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(team1));
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int team1PlayerCount = rs.getInt("player_count");

            pstmt.setInt(1, Integer.parseInt(team2));
            rs = pstmt.executeQuery();
            rs.next();
            int team2PlayerCount = rs.getInt("player_count");

            // If any team has less than 11 players, show a dialog box indicating the team with fewer players
            if (team1PlayerCount < 11) {
                JOptionPane.showMessageDialog(frame, "Team 1 has less than 11 players. Please add more players to Team 1.");
                conn.close();
                return false;
            } else if (team2PlayerCount < 11) {
                JOptionPane.showMessageDialog(frame, "Team 2 has less than 11 players. Please add more players to Team 2.");
                conn.close();
                return false;
            }

            // Prepare statement for inserting match
            query = "INSERT INTO `match` (match_id, team1_id, team2_id, venue, match_date, match_time, overs) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(matchId));
            pstmt.setInt(2, Integer.parseInt(team1));
            pstmt.setInt(3, Integer.parseInt(team2));
            pstmt.setString(4, venue);
            pstmt.setDate(5, Date.valueOf(date));
            pstmt.setTime(6, Time.valueOf(time));
            pstmt.setInt(7, Integer.parseInt(overs));

            // Execute query
            pstmt.executeUpdate();

            // Set match status to upcoming
            query = "UPDATE `match` SET status='upcoming' WHERE match_id=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(matchId));
            pstmt.executeUpdate();

            // Close database connection
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error inserting match into database: " + e.getMessage());
            return false;
        }
        return true;
    }

    private void clearForm() {
        matchIdField.setText("");
        team1Field.setText("");
        team2Field.setText("");
        venueField.setText("");
        dateField.setText("");
        timeField.setText("");
        oversField.setText("");

    }

    private boolean validateForm() {
        String matchId = matchIdField.getText();
        String team1 = team1Field.getText();
        String team2 = team2Field.getText();
        String venue = venueField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        String overs = oversField.getText();

        if (matchId.isBlank() || team1.isBlank() || team2.isBlank() || venue.isBlank() || date.isBlank() || time.isBlank() || overs.isBlank()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields!");
            return false;
        }

        return true;
    }

    public CreateMatchForm() {
        font = new Font("Comic Sans MS", Font.PLAIN, 14);
        createForm();
    }

    private void createForm() {
        // Create frame and panel
        frame = new JFrame("Create Match");
        panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels
        matchIdLabel = new JLabel("Match ID:");
        team1Label = new JLabel("Team 1:");
        team2Label = new JLabel("Team 2:");
        venueLabel = new JLabel("Venue:");
        dateLabel = new JLabel("Date (yyyy-mm-dd):");
        timeLabel = new JLabel("Time (hh:mm:ss):");
        oversLabel = new JLabel("Overs:");
        matchIdLabel.setFont(font);
        team1Label.setFont(font);
        team2Label.setFont(font);
        venueLabel.setFont(font);
        dateLabel.setFont(font);
        timeLabel.setFont(font);
        oversLabel.setFont(font);

        // Create text fields
        matchIdField = new JTextField();
        team1Field = new JTextField();
        team2Field = new JTextField();
        venueField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();
        oversField = new JTextField();
        matchIdField.setFont(font);
        team1Field.setFont(font);
        team2Field.setFont(font);
        venueField.setFont(font);
        dateField.setFont(font);
        timeField.setFont(font);
        oversField.setFont(font);

        // Create buttons
        submitButton = new JButton("Submit");
        submitButton.setFont(font);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    if (insertMatch()) {
                        JOptionPane.showMessageDialog(frame, "Match created successfully!");
                    }
                    clearForm();
                }
            }
        });
        backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Add components to panel
        panel.add(matchIdLabel);
        panel.add(matchIdField);
        panel.add(team1Label);
        panel.add(team1Field);
        panel.add(team2Label);
        panel.add(team2Field);
        panel.add(venueLabel);
        panel.add(venueField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(timeLabel);
        panel.add(timeField);
        panel.add(oversLabel);
        panel.add(oversField);
        panel.add(submitButton);
        panel.add(backButton);

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CreateMatchForm();
            }
        });
    }
}

