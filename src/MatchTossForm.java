import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BorderFactory.*;

public class MatchTossForm extends JFrame {
    private JComboBox<String> tossWinnerComboBox;
    private JComboBox<String> tossDecisionComboBox;

    public MatchTossForm(int matchId, int team1Id, int team2Id, String team1Name, String team2Name, String date, String time, int overs) {
        setTitle("Match Toss Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 25);

        JPanel infoPanel = new JPanel(new GridLayout(3, 3,20,20));

        JPanel inputPanel = new JPanel(new GridLayout(2,1,20,20));

        JLabel matchIdLabel = new JLabel("Match ID: " + matchId);
        JLabel team1Label = new JLabel("Team 1: " + team1Name);
        JLabel team2Label = new JLabel("Team 2: " + team2Name);
        JLabel dateLabel = new JLabel("Date: " + date);
        JLabel timeLabel = new JLabel("Time: " + time);
        JLabel oversLabel = new JLabel("Overs: " + overs);
        Border border = createLineBorder(Color.BLUE, 2);

        matchIdLabel.setFont(comicSansFont);
        team1Label.setFont(comicSansFont);
        team2Label.setFont(comicSansFont);
        dateLabel.setFont(comicSansFont);
        timeLabel.setFont(comicSansFont);
        oversLabel.setFont(comicSansFont);

        infoPanel.add(matchIdLabel);
        infoPanel.add(team1Label);
        infoPanel.add(team2Label);
        infoPanel.add(dateLabel);
        infoPanel.add(timeLabel);
        infoPanel.add(oversLabel);

        JLabel tossWinnerLabel = new JLabel("Toss Winner:");
        tossWinnerLabel.setFont(comicSansFont);

        tossWinnerComboBox = new JComboBox<>();
        tossWinnerComboBox.addItem(team1Name);
        tossWinnerComboBox.addItem(team2Name);

        JLabel tossDecisionLabel = new JLabel("Toss Decision:");
        tossDecisionLabel.setFont(comicSansFont);

        tossDecisionComboBox = new JComboBox<>();
        tossDecisionComboBox.addItem("Bat");
        tossDecisionComboBox.addItem("Ball");
        tossWinnerComboBox.setBackground(Color.LIGHT_GRAY);  // Set background color
        tossWinnerComboBox.setForeground(Color.BLACK);  // Set text color
        tossWinnerComboBox.setFont(comicSansFont);  // Set font
        tossWinnerComboBox.setBorder(createLineBorder(Color.DARK_GRAY, 1));  // Add border

        tossDecisionComboBox.setBackground(Color.LIGHT_GRAY);  // Set background color
        tossDecisionComboBox.setForeground(Color.BLACK);  // Set text color
        tossDecisionComboBox.setFont(comicSansFont);  // Set font
        tossDecisionComboBox.setBorder(createLineBorder(Color.DARK_GRAY, 1));  // Add border


        inputPanel.add(tossWinnerLabel);
        inputPanel.add(tossWinnerComboBox);
        inputPanel.add(tossDecisionLabel);
        inputPanel.add(tossDecisionComboBox);

        JButton submitButton = new JButton("Submit");

        submitButton.setBackground(Color.GREEN);  // Set background color to green
        submitButton.setForeground(Color.WHITE);  // Set text color to white
        submitButton.setFont(comicSansFont);  // Set font to Comic Sans MS
        submitButton.setBorderPainted(false);  // Remove border
        submitButton.setFocusPainted(false);  // Remove focus ring
        submitButton.setOpaque(true);  // Enable background color to be visible
        submitButton.setBorder(border);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assuming you have a JComboBox named tossWinnerComboBox
                String selectedWinner = (String) tossWinnerComboBox.getSelectedItem();
// Assuming you have a JComboBox named tossDecisionComboBox
                String selectedDecision = (String) tossDecisionComboBox.getSelectedItem();

               // new Scoreboard(1);
                new Scoreboard(matchId,team1Id,team2Id,team1Name,  team2Name,date,time,overs,selectedWinner, selectedDecision);
                dispose();
            }
        });

        infoPanel.add(inputPanel);
        infoPanel.add(submitButton);

        add(infoPanel, BorderLayout.CENTER);



        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MatchTossForm(123, 1, 2, "Team 1", "Team 2", "2023-05-19", "10:00 AM", 20);
            }
        });
    }
}
