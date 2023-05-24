import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SearchPlayerWindow extends JFrame {
    private JTextField searchField;
    private JTextField searchTeamField;
    private JTextArea resultArea;

    public SearchPlayerWindow() {
        super("Search Player");
        Font comicSans = new Font("Comic Sans MS", Font.PLAIN, 16);
        // create search labels and text fields
        JLabel searchLabel = new JLabel("Search Player by Player ID or Name:");
        searchField = new JTextField(20);
        searchLabel.setFont(comicSans);
        searchField.setFont(comicSans);

        JLabel searchTeamLabel = new JLabel("Search Player by Team ID:");
        searchTeamField = new JTextField(20);
        searchTeamLabel.setFont(comicSans);
        searchTeamField.setFont(comicSans);
        // create search button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // get the search terms
                String playerSearchTerm = searchField.getText().trim();
                String teamSearchTerm = searchTeamField.getText().trim();

                if (playerSearchTerm.isEmpty() && teamSearchTerm.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a search term");
                    return;
                }

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cricket", "root", "Root@24680");
                    String query = "SELECT player.*, player_stats.matches_played, player_stats.total_runs, player_stats.total_wickets FROM player LEFT JOIN player_stats ON player.player_id = player_stats.player_id";

                    // construct the query based on the search terms
                    if (!playerSearchTerm.isEmpty()) {
                        try {
                            int playerId = Integer.parseInt(playerSearchTerm);
                            query += " WHERE player.player_id = " + playerId;
                        } catch (NumberFormatException ex) {
                            query += " WHERE player.name LIKE '%" + playerSearchTerm + "%'";
                        }
                    }

                    if (!teamSearchTerm.isEmpty()) {
                        if (query.contains("WHERE")) {
                            query += " AND (player.team_id = " + teamSearchTerm + " OR player.team_id IN (SELECT team_id FROM cricket_team WHERE team_name LIKE '%" + teamSearchTerm + "%'))";
                        } else {
                            query += " WHERE player.team_id = " + teamSearchTerm + " OR player.team_id IN (SELECT team_id FROM cricket_team WHERE team_name LIKE '%" + teamSearchTerm + "%')";
                        }
                    }

                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    // display results in result area
                    resultArea.setText("");
                    while (rs.next()) {
                        String playerId = rs.getString("player_id");
                        String teamId = rs.getString("team_id");
                        String name = rs.getString("name");
                        String age = rs.getString("age");
                        String email = rs.getString("email");
                        String typeOfPlayer = rs.getString("type_of_player");
                        String matchesPlayed = rs.getString("matches_played");
                        String totalRuns = rs.getString("total_runs");
                        String totalWickets = rs.getString("total_wickets");

                        String resultString = "Player ID: " + playerId + "\n" +
                                "Team ID: " + teamId + "\n" +
                                "Name: " + name + "\n" +
                                "Age: " + age + "\n" +
                                "Email: " + email + "\n" +
                                "Type of Player: " + typeOfPlayer + "\n" +
                                "Matches Played: " + matchesPlayed + "\n" +
                                "Total Runs: " + totalRuns + "\n" +
                                "Total Wickets: " + totalWickets + "\n\n";

                        resultArea.append(resultString);
                        resultArea.setForeground(new Color(13, 0, 248, 255));
                        resultArea.append("------------------------------------------------------\n\n");
                        resultArea.setForeground(new Color(242, 242, 2, 255));
                    }

                    conn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // create clear button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // clear the result area
                resultArea.setText("");
            }
        });

        // create result area
        resultArea = new JTextArea(20, 50);
        resultArea.setEditable(false);
        resultArea.setForeground(new Color(242, 242, 2, 255));
        resultArea.setBackground(new Color(42, 41, 41));

        resultArea.setFont(comicSans);

        // create panel and add components
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchTeamLabel);
        panel.add(searchTeamField);
        panel.add(searchButton);
        panel.add(clearButton);
        panel.add(new JScrollPane(resultArea));
        panel.setBackground(new Color(242, 242, 2));

        // set window properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setBackground(new Color(103, 242, 249));
        //setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(panel);
        //pack();
        setVisible(true);
    }
}
