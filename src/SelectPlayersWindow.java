import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;



class TeamPlayers {
    public int id;
    public String name;



    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Example method
    public void displayDetails() {
        System.out.println("Player ID: " + id);
        System.out.println("Player Name: " + name);
    }
}



public class SelectPlayersWindow extends JFrame {
    private JTextField matchIdTextField;
    boolean matchIdExists=false;
    private int t1id;
    private int t2id;
    private JButton enterButton;

    private JButton clearButton;
    private JRadioButton team1RadioButton;
    private JRadioButton team2RadioButton;
    private JCheckBox[] playerCheckBoxes; // Array to store player checkboxes
    private int[]  arr;
    private TeamPlayers[] teamPlayers;
    public SelectPlayersWindow() {
        teamPlayers= new TeamPlayers[15];
        setTitle("Select Players");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        JButton submitButton = new JButton("Submit");
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        arr=new int[15];
        JPanel inputPanel = new JPanel(new FlowLayout());
        matchIdTextField = new JTextField(10);
        enterButton = new JButton("Enter");
        clearButton = new JButton("Clear");
        inputPanel.add(new JLabel("Match ID:"));
        inputPanel.add(matchIdTextField);
        inputPanel.add(enterButton);
        inputPanel.add(clearButton);
      //  inputPanel.add(submitButton);
        JPanel teamPanel = new JPanel(new FlowLayout());

        team1RadioButton = new JRadioButton("Team 1");
        team2RadioButton = new JRadioButton("Team 2");
        ButtonGroup teamButtonGroup = new ButtonGroup();
        teamButtonGroup.add(team1RadioButton);
        teamButtonGroup.add(team2RadioButton);
        teamPanel.add(team1RadioButton);
        teamPanel.add(team2RadioButton);
          team1RadioButton.setSelected(true);
        JPanel playerPanel = new JPanel(new GridLayout(0, 1));
        playerCheckBoxes = new JCheckBox[15]; // Assuming 11 players per team
        for (int i = 0; i < playerCheckBoxes.length; i++) {
            playerCheckBoxes[i] = new JCheckBox();
            playerCheckBoxes[i].setEnabled(false);
            playerPanel.add(playerCheckBoxes[i]);
        }
        for (int i = 0; i < playerCheckBoxes.length; i++) {
            teamPlayers[i] = new TeamPlayers();
            playerPanel.add(playerCheckBoxes[i]);
        }

        container.add(inputPanel, BorderLayout.NORTH);
        container.add(teamPanel, BorderLayout.CENTER);
        container.add(playerPanel, BorderLayout.WEST);
        container.add(submitButton, BorderLayout.SOUTH);
        clearButton.setEnabled(false);



        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle the submit button action
                handleSubmitButtonClick();
            }
        });





        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matchIdText = matchIdTextField.getText();
                if (!matchIdText.isEmpty()) {
                    int matchId = Integer.parseInt(matchIdText);
                    checkMatchIdExists(matchId);
                    team1RadioButton.setSelected(true);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearMatchId();
            }
        });

        team1RadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePlayerCheckBoxes(t1id);
            }
        });

        team2RadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePlayerCheckBoxes(t2id);
            }
        });

        setVisible(true);
    }

    private void checkMatchIdExists(int matchId) {
        try {
            String url = "jdbc:mysql://localhost:3306/cricket";
            String username = "root";
            String password = "Root@24680";

            Connection conn = DriverManager.getConnection(url, username, password);

            String query = "SELECT m.team1_id, m.team2_id, t1.team_name, t2.team_name " +
                    "FROM `match` m " +
                    "INNER JOIN `cricket_team` t1 ON m.team1_id = t1.team_id " +
                    "INNER JOIN `cricket_team` t2 ON m.team2_id = t2.team_id " +
                    "WHERE m.match_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, matchId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                matchIdExists = true;
                int team1Id = rs.getInt("team1_id");
                int team2Id = rs.getInt("team2_id");
                String team1Name = rs.getString("t1.team_name");
                String team2Name = rs.getString("t2.team_name");
                t1id = team1Id;
                t2id = team2Id;

                team1RadioButton.setText(team1Name + " (" + team1Id + ")");
                team2RadioButton.setText(team2Name + " (" + team2Id + ")");
                matchIdTextField.setEnabled(false);
                team1RadioButton.setEnabled(true);
                team2RadioButton.setEnabled(true);
                clearButton.setEnabled(true);

                // Update player checkboxes based on the selected team
                updatePlayerCheckBoxes(team1Id);
            } else {
                JOptionPane.showMessageDialog(this, "Match ID does not exist.");
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while connecting to the database.");
        }
    }

    private void updatePlayerCheckBoxes(int teamId) {
        try {
            String url = "jdbc:mysql://localhost:3306/cricket";
            String username = "root";
            String password = "Root@24680";

            Connection conn = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM `player` WHERE team_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, teamId);
            ResultSet rs = stmt.executeQuery();

            int playerCount = 0;
            int ip=0;
            while (rs.next() && playerCount < playerCheckBoxes.length) {
                int playerId = rs.getInt("player_id");
                String playerName = rs.getString("name");
                teamPlayers[ip].id=playerId;
                teamPlayers[ip].name=playerName;
                arr[ip]=playerId;
                playerCheckBoxes[playerCount].setText(playerName + " (" + playerId + ")");
                playerCheckBoxes[playerCount].setEnabled(true);

                playerCount++;
                ip++;
            }




            // Disable remaining checkboxes
            for (int i = playerCount; i < playerCheckBoxes.length; i++) {
                playerCheckBoxes[i].setText("");
                playerCheckBoxes[i].setEnabled(false);
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while connecting to the database.");
        }
    }

    private void clearMatchId() {
        matchIdTextField.setText("");
        matchIdTextField.setEnabled(true);
        team1RadioButton.setText("Team 1");
        team2RadioButton.setText("Team 2");
        team1RadioButton.setEnabled(true);
        team2RadioButton.setEnabled(true);
        clearButton.setEnabled(false);
        matchIdExists = false;

        // Reset player checkboxes
        for (JCheckBox checkBox : playerCheckBoxes) {
            checkBox.setText("");
            checkBox.setEnabled(false);
        }
    }


    private void handleSubmitButtonClick() {
        int selectedCount = 0; // Counter for selected checkboxes

        // Count the number of selected checkboxes
        for (JCheckBox checkBox : playerCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedCount++;
            }
        }
        // ...

        if (selectedCount == 11) {
            String matchIdText = matchIdTextField.getText();
            int matchId = Integer.parseInt(matchIdText);
            int teamId = 0;
            if (team1RadioButton.isSelected()) {
                teamId = t1id;
            } else if (team2RadioButton.isSelected()) {
                teamId = t2id;
            }

            try {
                String url = "jdbc:mysql://localhost:3306/cricket";
                String username = "root";
                String password = "Root@24680";

                Connection conn = DriverManager.getConnection(url, username, password);

                // Delete all entries from the match_selected_players table
                String deleteQuery = "DELETE FROM match_selected_players WHERE team_id = ? AND match_id = ?";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
                deleteStmt.setInt(1, teamId);
                deleteStmt.setInt(2, matchId);
                deleteStmt.executeUpdate();

                // Insert the selected players into the match_selected_players table
                String insertQuery = "INSERT INTO match_selected_players (match_id, team_id, player_id,player_name) VALUES (?, ?, ?,?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);

                for (int i = 0; i < playerCheckBoxes.length; i++) {
                    if (playerCheckBoxes[i].isSelected()) {
                        int playerId = teamPlayers[i].getId();
                        insertStmt.setInt(1, matchId);
                        insertStmt.setInt(2, teamId);
                        insertStmt.setInt(3, playerId);
                        insertStmt.setString(4,teamPlayers[i].name);
                        insertStmt.executeUpdate();
                    }
                }

                conn.close();

                // Display a success message or perform any other actions
                JOptionPane.showMessageDialog(this, "team is selected successfully for "+ teamId);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while connecting to the database.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select exactly 11 players.");
        }


    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SelectPlayersWindow();
            }
        });
    }
}

