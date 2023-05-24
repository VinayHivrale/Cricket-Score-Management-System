import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminSearchMatch extends JFrame {
    private JTextField searchField;
    private JTextArea resultArea;

    public AdminSearchMatch() {
        super("Search Match");
        Font comicSans = new Font("Comic Sans MS", Font.PLAIN, 16);
        // create search labels and text fields
        JLabel searchLabel = new JLabel("Search Match by Date:");
        searchField = new JTextField(20);
        searchLabel.setFont(comicSans);
        searchField.setFont(comicSans);

        // create search button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // get the search term
                String searchTerm = searchField.getText().trim();

                if (searchTerm.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a search term");
                    return;
                }

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cricket", "root", "Root@24680");
                    String query = "SELECT * FROM `match` WHERE match_date = ?";

                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setDate(1, Date.valueOf(searchTerm));

                    ResultSet rs = stmt.executeQuery();

                    // display results in result area
                    resultArea.setText("");
                    while (rs.next()) {
                        String matchId = rs.getString("match_id");
                        String team1Id = rs.getString("team1_id");
                        String team2Id = rs.getString("team2_id");
                        String venue = rs.getString("venue");
                        String matchDate = rs.getString("match_date");
                        String matchTime = rs.getString("match_time");
                        String overs = rs.getString("overs");
                        String status = rs.getString("status");

                        String resultString = "Match ID: " + matchId + "\n" +
                                "Team 1 ID: " + team1Id + "\n" +
                                "Team 2 ID: " + team2Id + "\n" +
                                "Venue: " + venue + "\n" +
                                "Match Date: " + matchDate + "\n" +
                                "Match Time: " + matchTime + "\n" +
                                "Overs: " + overs + "\n" +
                                "Status: " + status + "\n\n";

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

