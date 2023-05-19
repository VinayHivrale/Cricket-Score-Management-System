import javax.swing.*;
import java.awt.*;

public class CricketScorekeeperGUI extends JFrame {

    private JPanel mainPanel;
    private JLabel teamALabel, teamBLabel, player1Label, player2Label, scoreALabel, scoreBLabel, oversALabel, oversBLabel, wicketsALabel, wicketsBLabel;
    private JTextField player1NameField, player2NameField, player1ScoreField, player2ScoreField;
    private JTable bowlingStatsTable;
    private JButton addScoreButton, updateBowlingStatsButton, saveButton;

    public CricketScorekeeperGUI() {
        initComponents();
    }

    private void initComponents() {
        // Create main panel
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add team name labels
        teamALabel = new JLabel("Team A:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(teamALabel, gbc);

        teamBLabel = new JLabel("Team B:");
        gbc.gridx = 2;
        gbc.gridy = 0;
        mainPanel.add(teamBLabel, gbc);

        // Add player name labels and text fields
        player1Label = new JLabel("Player 1:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(player1Label, gbc);

        player1NameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(player1NameField, gbc);

        player2Label = new JLabel("Player 2:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        mainPanel.add(player2Label, gbc);

        player2NameField = new JTextField(20);
        gbc.gridx = 3;
        gbc.gridy = 1;
        mainPanel.add(player2NameField, gbc);

        // Add score labels and text fields
        scoreALabel = new JLabel("Score:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(scoreALabel, gbc);

        player1ScoreField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(player1ScoreField, gbc);

        scoreBLabel = new JLabel("Score:");
        gbc.gridx = 2;
        gbc.gridy = 2;
        mainPanel.add(scoreBLabel, gbc);

        player2ScoreField = new JTextField(5);
        gbc.gridx = 3;

        gbc.gridy = 2;
        mainPanel.add(player2ScoreField, gbc);

        // Add overs labels
        oversALabel = new JLabel("Overs:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(oversALabel, gbc);

        oversBLabel = new JLabel("Overs:");
        gbc.gridx = 2;
        gbc.gridy = 3;
        mainPanel.add(oversBLabel, gbc);

        // Add wickets labels
        wicketsALabel = new JLabel("Wickets:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(wicketsALabel, gbc);

        wicketsBLabel = new JLabel("Wickets:");
        gbc.gridx = 2;
        gbc.gridy = 4;
        mainPanel.add(wicketsBLabel, gbc);

        // Add bowling stats table
        String[] columnNames = {"Bowler", "Runs Conceded", "Wickets Taken"};
        Object[][] data = {{"Bowler 1", "0", "0"}, {"Bowler 2", "0", "0"}};
        bowlingStatsTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(bowlingStatsTable);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        mainPanel.add(scrollPane, gbc);

        // Add buttons
        addScoreButton = new JButton("Add Score");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        mainPanel.add(addScoreButton, gbc);

        updateBowlingStatsButton = new JButton("Update Bowling Stats");
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(updateBowlingStatsButton, gbc);

        saveButton = new JButton("Save");
        gbc.gridx = 2;
        gbc.gridy = 6;
        mainPanel.add(saveButton, gbc);

        // Set window properties
        setTitle("Cricket Scorekeeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CricketScorekeeperGUI();
    }
}

