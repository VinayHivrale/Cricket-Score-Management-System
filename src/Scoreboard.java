import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;

public class Scoreboard extends JFrame {
    private int matchId;

    public Scoreboard(int matchId, int team1Id, int team2Id, String team1Name, String team2Name, String date, String time, int overs, String selectedWinner, String selectedDecision) {
        // Constructor logic here
        // Initialize and assign values to the instance variables


        this.matchId = matchId;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Scoreboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Create labels for team name, 1st inning score, total runs-wickets, and current over
        JLabel firstInningScoreLabel = new JLabel("TEAMNAME,1st Inning:");
        JLabel currentOverLabel = new JLabel("Current Over: (over number)");
        JLabel teamNameLabel = new JLabel("( Total Runs )-( Wickets)");
        JLabel currentRunRateLabel = new JLabel("Current Run Rate: runrate");
        Font font = new Font("Comic Sans MS", Font.BOLD, 22);
        firstInningScoreLabel.setFont(font);
        currentOverLabel.setFont(font);
        teamNameLabel.setFont(font);
        currentRunRateLabel.setFont(font);
        // Create a panel for the labels
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 20, 10));
        infoPanel.add(teamNameLabel);
        infoPanel.add(firstInningScoreLabel);
        infoPanel.add(currentOverLabel);
        infoPanel.add(currentRunRateLabel);

        // Add the infoPanel to the north of the BorderLayout
      //  add(infoPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));

        // Create buttons
        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton buttonOther = new JButton("Others");

        // Add buttons to the buttonPanel
        buttonPanel.add(button0);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(buttonOther);

        add(buttonPanel, BorderLayout.EAST);

        // Create table model
        String[] batsmanColumnNames = {"Batsman", "Runs", "Balls", "4s", "6s", "Strike Rate"};
        Object[][] batsmanRowData = {
                {"Batsman 1", 50, 30, 5, 2, 166.67},
                {"Batsman 2", 30, 20, 3, 1, 150.00}
        };
        DefaultTableModel batsmanTableModel = new DefaultTableModel(batsmanRowData, batsmanColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable batsmanTable = new JTable(batsmanTableModel);
        JScrollPane batsmanScrollPane = new JScrollPane(batsmanTable);

        // Create table model for bowlers
        String[] bowlerColumnNames = {"Bowler", "Overs", "Balls", "Runs", "Wickets", "Economy Rate"};
        Object[][] bowlerRowData = {
                {"Bowler 1", 4, 24, 30, 2, 7.50}
        };
        DefaultTableModel bowlerTableModel = new DefaultTableModel(bowlerRowData, bowlerColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable bowlerTable = new JTable(bowlerTableModel);
        JScrollPane bowlerScrollPane = new JScrollPane(bowlerTable);

        // Create a panel for the tables
        JPanel tablePanel = new JPanel(new GridLayout(8, 1, 10, 10));
        tablePanel.add(infoPanel);
        tablePanel.add(batsmanScrollPane);
        tablePanel.add(bowlerScrollPane);

        add(tablePanel, BorderLayout.CENTER);

        // Create checkboxes
        JCheckBox wideBallCheckBox = new JCheckBox("Wide Ball");
        JCheckBox noBallCheckBox = new JCheckBox("No Ball");
        JCheckBox byesCheckBox = new JCheckBox("Byes");
        JCheckBox legByesCheckBox = new JCheckBox("Leg Byes");
        JCheckBox wicketCheckBox = new JCheckBox("Wicket");

        // Create a panel for the checkboxes
        JPanel checkBoxPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        checkBoxPanel1.add(wideBallCheckBox);
        checkBoxPanel1.add(noBallCheckBox);
        JPanel checkBoxPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        checkBoxPanel1.add(byesCheckBox);
        checkBoxPanel1.add(legByesCheckBox);
        JPanel checkBoxPanel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        checkBoxPanel1.add(wicketCheckBox);


        // Add event listeners to the checkboxes
        wideBallCheckBox.addActionListener(e -> {
            if (wideBallCheckBox.isSelected()) {

                // Perform actions when the wide ball checkbox is selected
            noBallCheckBox.setSelected(false);
            byesCheckBox.setSelected(false);
            legByesCheckBox.setSelected(false);
            } else {
                // Perform actions when the wide ball checkbox is deselected
            }
        });

        noBallCheckBox.addActionListener(e -> {
            if (noBallCheckBox.isSelected()) {
                // Perform actions when the no ball checkbox is selected
                wideBallCheckBox.setSelected(false);
            } else {
                // Perform actions when the no ball checkbox is deselected
            }
        });


        byesCheckBox.addActionListener(e -> {
            if (byesCheckBox.isSelected()) {
                // Perform actions when the byes checkbox is selected
              legByesCheckBox.setSelected(false);
              wideBallCheckBox.setSelected(false);
            } else {
                // Perform actions when the byes checkbox is deselected
            }
        });

        legByesCheckBox.addActionListener(e -> {
            if (legByesCheckBox.isSelected()) {
                // Perform actions when the leg byes checkbox is selected
                byesCheckBox.setSelected(false);
               wideBallCheckBox.setSelected(false);
            } else {
                // Perform actions when the leg byes checkbox is deselected
            }
        });

        wicketCheckBox.addActionListener(e -> {
            if (wicketCheckBox.isSelected()) {
                // Perform actions when the wicket checkbox is selected
            } else {
                // Perform actions when the wicket checkbox is deselected
            }
        });
        tablePanel.add(checkBoxPanel1);
        tablePanel.add(checkBoxPanel2);
        tablePanel.add(checkBoxPanel3);
        add(tablePanel, BorderLayout.CENTER);
      //  add(checkBoxPanel, BorderLayout.WEST);

        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Assuming you have a match ID, you can pass it to the Scoreboard constructor
        int matchId = 12345;

        // Create an instance of the Scoreboard class
      //  Scoreboard scoreboard = new Scoreboard(matchId);
    }

    // Add scoreboard methods and functionality here
}


 class Scorecard {
    private int matchId;
    private Date date;
    private String team1;
    private String team2;
    private String tossWin;
    private String decision;

    private HalfScorecard inn1;

     private HalfScorecard inn2;
    public Scorecard(int matchId, Date date, String team1, String team2, String tossWin, String decision) {
        this.matchId = matchId;
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.tossWin = tossWin;
        this.decision = decision;
       // inn1=new HalfScorecard();
       // inn2 =new HalfScorecard();
    }

    // Getters and Setters

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTossWin() {
        return tossWin;
    }

    public void setTossWin(String tossWin) {
        this.tossWin = tossWin;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    // Main method to demonstrate usage

}
 class PlayerBatting {
    private String playerName;
    private int playerId;
    private int runs;
    private int balls;
    private int fours;
    private int sixes;
    private double strikeRate;
    private String status;

    public void setPlayerBatting(String playerName, int playerId, int runs, int balls, int fours, int sixes, double strikeRate, String status) {
        this.playerName = playerName;
        this.playerId = playerId;
        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.strikeRate = strikeRate;
        this.status = status;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getRuns() {
        return runs;
    }

    public int getBalls() {
        return balls;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public String getStatus() {
        return status;
    }
}

class PlayerBowling {
    private String playerName;
    private int playerId;
    private int overs;
    private int runs;
    private int wickets;
    private double economyRate;

    public PlayerBowling(String playerName, int playerId, int overs, int runs, int wickets, double economyRate) {
        this.playerName = playerName;
        this.playerId = playerId;
        this.overs = overs;
        this.runs = runs;
        this.wickets = wickets;
        this.economyRate = economyRate;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getOvers() {
        return overs;
    }

    public int getRuns() {
        return runs;
    }

    public int getWickets() {
        return wickets;
    }

    public double getEconomyRate() {
        return economyRate;
    }
}




class HalfScorecard {
    private String inning;
    private String battingTeam;
    private String bowlingTeam;
    private PlayerBatting[] battingScorecard;
    private PlayerBowling[] bowlingScorecard;

    public HalfScorecard(String inning, String battingTeam, String bowlingTeam) {
        this.inning = inning;
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        battingScorecard=new PlayerBatting[11];
        bowlingScorecard=new PlayerBowling[11];
        for(int i=0;i<11;i++)
        {
           // battingScorecard[i].setPlayerBatting();
        }


        for(int i=0;i<11;i++)
        {
            // battingScorecard[i].setPlayerBatting();
        }


    }

    public String getInning() {
        return inning;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }

    public PlayerBatting[] getBattingScorecard() {
        return battingScorecard;
    }

    public PlayerBowling[] getBowlingScorecard() {
        return bowlingScorecard;
    }

}
