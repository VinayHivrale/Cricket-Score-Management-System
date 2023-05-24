import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MatchSearchApp extends JFrame {
    private JLabel dateLabel;
    private JTextField dateField;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton; // New back button
    private JPanel buttonPanel;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cricket";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Root@24680";

    public MatchSearchApp() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Match Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new FlowLayout());

        dateLabel = new JLabel("Match Date (YYYY-MM-DD):");
        dateField = new JTextField(10);
        searchButton = new JButton("Search");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back"); // Initialize back button

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setPreferredSize(new Dimension(550, 350));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMatches();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearButtons();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScorekeeperLoginForm(); // Create a new instance of ScorekeeperLoginForm
                dispose(); // Close the current frame
            }
        });

        add(dateLabel);
        add(dateField);
        add(searchButton);
        add(clearButton);
        add(backButton); // Add back button
        add(scrollPane);
    }

    private void searchMatches() {
        clearButtons();

        String searchDate = dateField.getText();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "SELECT * FROM `match` WHERE match_date = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, searchDate);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int matchId = rs.getInt("match_id");
                int team1Id = rs.getInt("team1_id");
                int team2Id = rs.getInt("team2_id");

                String team1 = getTeamNameById(team1Id);
                String team2 = getTeamNameById(team2Id);
                String buttonText = team1 + " vs " + team2 + " (" + matchId + ")";
                String matchDate = rs.getString("match_date");
                String matchTime = rs.getString("match_time");
                int overs = rs.getInt("overs");

                JButton button = new JButton(buttonText);
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new MatchTossForm(matchId, team1Id, team2Id, team1, team2, matchDate, matchTime, overs);
                        dispose();
                    }
                });

                buttonPanel.add(button);
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving matches: " + ex.getMessage());
        }

        revalidate();
        repaint();
    }

    private void clearButtons() {
        buttonPanel.removeAll();
        revalidate();
        repaint();
    }

    private String getTeamNameById(int teamId) {
        String teamName = "";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "SELECT team_name FROM `cricket_team` WHERE team_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, teamId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                teamName = rs.getString("team_name");
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving team name: " + ex.getMessage());
        }

        return teamName;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MatchSearchApp app = new MatchSearchApp();
                app.setVisible(true);
            }
        });
    }
}
