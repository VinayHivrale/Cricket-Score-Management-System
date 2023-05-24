import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchTeamWindow extends JFrame {
    private JTextField searchField;
    private JTextArea resultArea;
    private JButton backButton; // New back button

    public SearchTeamWindow() {
        super("Search Team");
        Font comicSans = new Font("Comic Sans MS", Font.PLAIN, 16);
        // create search labels and text fields
        JLabel searchLabel = new JLabel("Search Team by Team ID or Name:");
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
                    String query = "SELECT * FROM cricket_team WHERE team_id = ? OR team_name LIKE ?";

                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, searchTerm);
                    stmt.setString(2, "%" + searchTerm + "%");

                    ResultSet rs = stmt.executeQuery();

                    // display results in result area
                    resultArea.setText("");
                    while (rs.next()) {
                        String teamId = rs.getString("team_id");
                        String teamName = rs.getString("team_name");
                        String ownerName = rs.getString("owner_name");

                        String resultString = "Team ID: " + teamId + "\n" +
                                "Team Name: " + teamName + "\n" +
                                "Owner Name: " + ownerName + "\n\n";

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

        // create back button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                new AdminWindow(); // Create a new instance of AdminWindow
            }
        });

        // create panel and add components
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(clearButton);
        panel.add(new JScrollPane(resultArea));
        panel.add(backButton); // Add the back button to the panel
        panel.setBackground(new Color(242, 242, 2));

        // set window properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setBackground(new Color(103, 242, 249));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(panel);
        setVisible(true);
    }
}
