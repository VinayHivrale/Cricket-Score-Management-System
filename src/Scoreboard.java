import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Scoreboard extends JFrame {
    JCheckBox wicketCheckBox = new JCheckBox("Wicket");
    JCheckBox wideBallCheckBox = new JCheckBox("Wide Ball");
    JCheckBox noBallCheckBox = new JCheckBox("No Ball");
    JCheckBox byesCheckBox = new JCheckBox("Byes");
    JCheckBox legByesCheckBox = new JCheckBox("Leg Byes");
    JLabel currentOverLabel = new JLabel();

    JLabel firstInningScoreLabel = new JLabel();
    JLabel teamNameLabel=new JLabel();
    JLabel currentRunRateLabel = new JLabel("Current Run Rate: runrate");
    double totalRuns;
     int currentwickets;
    int totalBalls;
    int currentOver;

    Scorecard scorecard;

    private int matchId;
    int team1Id;
    int team2Id;
    String team1Name;
    String team2Name;
    String date;
    String time;
    int overs;
    String selectedWinner;
    String selectedDecision;

    public Scoreboard(int matchId, int team1Id, int team2Id, String team1Name, String team2Name, String date, String time, int overs, String selectedWinner, String selectedDecision) {
        // Constructor logic here
        // Initialize and assign values to the instance variables
        this.matchId = matchId;
        this.overs=overs;
        this.date=date;
        this.team2Id=team2Id;
        this.team1Id=team1Id;
        this.time=time;
        this.selectedDecision=selectedDecision;
        this.selectedWinner=selectedWinner;
        this.team1Name=team1Name;
        this.team2Name=team2Name;

        scorecard=new Scorecard(matchId,date,team1Name,team2Name,selectedWinner,selectedDecision,team1Id,team2Id);

        initializeUI1( );
    }

    private void initializeUI1() {

        resetScoreboard();
        currentOverLabel.setText("Current Over :"+currentOver+"."+totalBalls);
        teamNameLabel.setText("Total Runs: " + totalRuns+" / "+ currentwickets);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("SCOREBOARD : "+team1Name+ " vs "+team2Name+ "              (toss win by "+selectedWinner+" )             " + selectedWinner + " Chooses "+ selectedDecision + " fist");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new GridLayout(2,1));

        // Create labels for team name, 1st inning score, total runs-wickets, and current over

        if((selectedWinner == team1Name) && selectedDecision == "bat")
        {
             firstInningScoreLabel.setText(team1Name+",1st Inning:");
        }
        else
        {
            firstInningScoreLabel.setText(team2Name+", 1st Inning");
        }

     //   JLabel firstInningScoreLabel = new JLabel(,1st Inning:");
    //    JLabel teamNameLabel=new JLabel();
        JLabel currentRunRateLabel = new JLabel("Current Run Rate: 0");
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

        JPanel buttonPanel = new JPanel(new GridLayout(1, 7, 10, 1));

        JButton[] buttons = new JButton[7];
        for (int i = 0; i <= 6; i++) {
            buttons[i] = new JButton(String.valueOf(i));
            buttons[i].addActionListener(new ButtonListener());
            buttons[i].setFont(new Font("Cosmic Sans", Font.BOLD, 24));
            buttons[i].setPreferredSize(new Dimension(80, 80)); // Increase button size
            buttonPanel.add(buttons[i]);
        }

        String[] batsmanColumnNames = {"Batsman","onSTrike" ,"Runs", "Balls", "4s", "6s", "Strike Rate"};
        Object[][] batsmanRowData = {
                {"Batsman 1","***" ,50, 30, 5, 2, 166.67},
                {"Batsman 2","" ,30, 20, 3, 1, 150.00},
                {"Batsman 3","" ,30, 20, 3, 1, 150.00},
                {"Batsman 4","" ,30, 20, 3, 1, 150.00},
                {"Batsman 5","" ,30, 20, 3, 1, 150.00},
                {"Batsman 6","" ,30, 20, 3, 1, 150.00},
                {"Batsman 7","" ,30, 20, 3, 1, 150.00},
                {"Batsman 8","" ,30, 20, 3, 1, 150.00},
                {"Batsman 9","" ,30, 20, 3, 1, 150.00},
                {"Batsman 10","" ,30, 20, 3, 1, 150.00},
                {"Batsman 11","" ,30, 20, 3, 1, 150.00},
        };
        DefaultTableModel batsmanTableModel = new DefaultTableModel( batsmanColumnNames,0);

        JTable batsmanTable = new JTable(batsmanTableModel);
        JScrollPane batsmanScrollPane = new JScrollPane(batsmanTable);

        String[] bowlerColumnNames = {"Bowler", "Overs", "Balls", "Runs", "Wickets", "Economy Rate"};
        Object[][] bowlerRowData = {
                {"Bowler 1", 4, 24, 30, 2, 7.50},
                {"Bowler 1", 4, 24, 30, 2, 7.50},
                {"Bowler 1", 4, 24, 30, 2, 7.50},
                {"Bowler 1", 4, 24, 30, 2, 7.50},
                {"Bowler 1", 4, 24, 30, 2, 7.50},
                {"Bowler 1", 4, 24, 30, 2, 7.50},
                {"Bowler 1", 4, 24, 30, 2, 7.50},
                {"Bowler 1", 4, 24, 30, 2, 7.50},
                {"Bowler 1", 4, 24, 30, 2, 7.50},
                {"Bowler 1", 4, 24, 30, 2, 7.50},
                {"Bowler 1", 4, 24, 30, 2, 7.50},
        };

        DefaultTableModel bowlerTableModel = new DefaultTableModel( bowlerColumnNames,0);

        JTable bowlerTable = new JTable(bowlerTableModel);
        JScrollPane bowlerScrollPane = new JScrollPane(bowlerTable);
        JPanel tablePanel = new JPanel(new GridLayout(5, 1, 10, 10));
        tablePanel.add(infoPanel);
        tablePanel.add(batsmanScrollPane);
        tablePanel.add(bowlerScrollPane);

        // Create a panel for the checkboxes
        JPanel checkBoxPanel1 = new JPanel(new GridLayout(1,5,10,1));
        checkBoxPanel1.add(wideBallCheckBox);
        checkBoxPanel1.add(noBallCheckBox);
        checkBoxPanel1.add(byesCheckBox);
        checkBoxPanel1.add(legByesCheckBox);
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
        tablePanel.add(buttonPanel);
        add(tablePanel);

        JPanel bottom=new JPanel((new GridLayout(1,3)));

        JPanel options1 = new JPanel(new GridLayout(12, 1));
        JPanel options2 = new JPanel(new GridLayout(12, 1));

        JCheckBox[] cb1 = new JCheckBox[11];
        for (int i = 0; i < 11; i++) {
           cb1[i] = new JCheckBox(scorecard.inn1.battingScorecard[i].playerName);
            //cb1[i].setEnabled(false);   scorecard.inn1
            //scorecard.inn1.battingScorecard[i].playerName
            options1.add(cb1[i]);
        }

        JCheckBox[] cb2 = new JCheckBox[11];
        for (int i = 0; i < 11; i++) {
            cb2[i] = new JCheckBox(scorecard.inn1.bowlingScorecard[i].playerName);
            //scorecard.inn1.bowlingScorecard[i].playerName
            //cb2[i].setEnabled(false);
            options2.add(cb2[i]);
        }
        // HalfScorecard h= new HalfScorecard(2,1,1,2);
        //System.out.println(h.battingScorecard[0].playerName);
        JButton submitButton1 = new JButton("Submit");
        JButton submitButton2 = new JButton("Submit");
        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             if(currentwickets==0)
             {
                 int selectedCount=0;
                 for (JCheckBox checkBox : cb1) {
                     if (checkBox.isSelected()) {
                         selectedCount++;
                     }
                 }
                 if(selectedCount==2)
                 {
                     int i=0;
                     int j=0;
                     for (i=0;i<11;i++) {


                         if (cb1[i].isSelected()) {
                             j++;
                             cb1[i].setSelected(false);
                             cb1[i].setEnabled(false);
                             if(j==1)
                             {   batsmanRowData[i][0] = scorecard.inn1.battingScorecard[i].playerName;
                                 batsmanRowData[i][1] = "***";
                                 batsmanRowData[i][2] = scorecard.inn1.battingScorecard[i].runs;
                                 batsmanRowData[i][3] =scorecard.inn1.battingScorecard[i].balls;
                                 batsmanRowData[i][4] = scorecard.inn1.battingScorecard[i].fours;
                                 batsmanRowData[i][5] = scorecard.inn1.battingScorecard[i].sixes;
                                 batsmanRowData[i][6] = scorecard.inn1.battingScorecard[i].strikeRate;

                             batsmanTableModel.addRow(batsmanRowData[i]);
                             }
                             if(j==2)
                             {
                                 batsmanRowData[i][0] = scorecard.inn1.battingScorecard[i].playerName;
                                 batsmanRowData[i][1] = "";
                                 batsmanRowData[i][2] = scorecard.inn1.battingScorecard[i].runs;
                                 batsmanRowData[i][3] =scorecard.inn1.battingScorecard[i].balls;
                                 batsmanRowData[i][4] = scorecard.inn1.battingScorecard[i].fours;
                                 batsmanRowData[i][5] = scorecard.inn1.battingScorecard[i].sixes;
                                 batsmanRowData[i][6] = scorecard.inn1.battingScorecard[i].strikeRate;

                                 batsmanTableModel.addRow(batsmanRowData[i]);

                             }





                         }
                     }


                 }
                 else
                 {
                  //  JOptionPane.showMessageDialog(initializeUI1(), "choose only two batsmans");
                 }


             }

                // Handle submit button action here
                // You can access the selected checkboxes using cb1.isSelected(), cb2.isSelected(), etc.

            }
        });
        submitButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(totalBalls==0)
                {
                    int selectedCount=0;
                    for (JCheckBox checkBox : cb2) {
                        if (checkBox.isSelected()) {
                            selectedCount++;
                        }
                    }

                    if(selectedCount==1)
                    {
                        bowlerTableModel.setNumRows(0);

                        int i;
                        for (i=0;i<11;i++) {
                            if (cb2[i].isSelected()) {
                                bowlerRowData[i][0] =scorecard.inn1.bowlingScorecard[i].playerName;
                                bowlerRowData[i][1] =scorecard.inn1.bowlingScorecard[i].overs;
                                bowlerRowData[i][2] =scorecard.inn1.bowlingScorecard[i].overs;
                                bowlerRowData[i][3] =scorecard.inn1.bowlingScorecard[i].runs;
                                bowlerRowData[i][4] =scorecard.inn1.bowlingScorecard[i].wickets;
                                bowlerRowData[i][5] =scorecard.inn1.bowlingScorecard[i].economyRate;

                                bowlerTableModel.addRow(bowlerRowData[i]);
                               break;
                            }

                        }
                    }
                    else
                    {
                       // JOptionPane select 1 bowler at time
                    }


                }
                else
                {
                    //select bowler at the start of the over
                }


                // Handle submit button action here
                // You can access the selected checkboxes using cb1.isSelected(), cb2.isSelected(), etc.

            }
        });


        options1.add(submitButton1);
        options2.add(submitButton2);
        bottom.add(options1);
        bottom.add(options2);
        add(bottom);
        setVisible(true);



    }


    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {




            JButton clickedButton = (JButton) e.getSource();


            double run = Double.parseDouble(clickedButton.getText());
            totalRuns += run;
            totalBalls++;

            if (totalBalls % 6 == 0) {
                currentOver++;
                totalBalls = 0;
            }
            if(wicketCheckBox.isSelected())
            {
                currentwickets++;
                if(currentwickets==10)
                {
                  //  JOptionPane.showMessageDialog();
                    initializeUI2();
                }

            }


            currentOverLabel.setText("Current Over :"+currentOver+"."+totalBalls);
            teamNameLabel.setText("Total Runs: " + totalRuns+" / "+ currentwickets);


            // Color change effect
            /*Color originalColor = clickedButton.getBackground();
            clickedButton.setBackground(new Color(106, 106, 15));

            Timer timer = new Timer(150, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clickedButton.setBackground(originalColor);
                }
            });
            timer.setRepeats(false);
            timer.start();*/
            wicketCheckBox.setSelected(false);
            if (currentOver == overs) {
               // JOptionPane.showMessageDialog(CricketScoreboard.this, "5 overs completed!");
                resetScoreboard();
            }
        }
    }

    private void resetScoreboard() {
        totalRuns = 0;
        totalBalls = 0;
        currentOver = 0;
        currentwickets=0;
    }





    private void initializeUI2() {
        resetScoreboard();


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
    private int s_matchId;
    private String  s_date;
    private String s_team1;
    private String s_team2;
    private String s_tossWin;
    private String s_decision;


    private int s_team1Id;
    private int s_team2Id;
    public HalfScorecard inn1;
   public HalfScorecard inn2;
    public Scorecard(int matchId, String date, String team1, String team2, String tossWin, String decision, int s_team1Id, int s_team2Id) {
        this.s_matchId = matchId;
        this.s_date = date;
        this.s_team1 = team1;
        this.s_team2 = team2;
        this.s_tossWin = tossWin;
        this.s_decision = decision;
        this.s_team2Id=s_team2Id;
        this.s_team1Id=s_team1Id;

        if((tossWin == s_team1) && decision == "bat")
        {
            inn1=new HalfScorecard(s_matchId,1,s_team1Id,s_team2Id);
            inn2=new HalfScorecard(s_matchId,2,s_team2Id,s_team1Id);
        }
        else
        {
            inn1=new HalfScorecard(s_matchId,1,s_team2Id,s_team1Id);
            inn2=new HalfScorecard(s_matchId,2,s_team1Id,s_team2Id);

        }


         //  inn2 =new HalfScorecard();
    }

}

class HalfScorecard {
    private int inning;
    private int battingTeam_id;
    private int  bowlingTeam_id;
    public PlayerBatting[] battingScorecard;
    public PlayerBowling[] bowlingScorecard;

    public HalfScorecard(int matchid,int inning, int battingTeam_id, int bowlingTeam_id) {
        this.inning = inning;
        this.battingTeam_id = battingTeam_id;
        this.bowlingTeam_id = bowlingTeam_id;
        battingScorecard=new PlayerBatting[11];
        bowlingScorecard=new PlayerBowling[11];

try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cricket", "root", "Root@24680");
        String query = "SELECT * FROM match_selected_players WHERE match_id = ? AND team_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, matchid);
             stmt.setInt(2, battingTeam_id);
             ResultSet resultSet = stmt.executeQuery();

            int index = 0;
            while (resultSet.next() && index < 11) {
                String playerName = resultSet.getString("player_name");
                int playerId = resultSet.getInt("player_id");
                int runs = resultSet.getInt("runs");
                int balls = resultSet.getInt("balls");
                int  status = resultSet.getInt("battingstatus");
                battingScorecard[index] = new PlayerBatting();
                battingScorecard[index].setPlayerBatting(playerName, playerId, runs, balls,0, 0, 0, status);
                index++;
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }




        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cricket", "root", "Root@24680");
            String query = "SELECT * FROM match_selected_players WHERE match_id = ? AND team_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, matchid);
            stmt.setInt(2, bowlingTeam_id);
            ResultSet resultSet = stmt.executeQuery();

            int index = 0;
            while (resultSet.next() && index < 11) {
                String playerName = resultSet.getString("player_name");
                int playerId = resultSet.getInt("player_id");
                int overs = resultSet.getInt("overs");
                int wickets=resultSet.getInt("wickets");
                //int balls = resultSet.getInt("balls");
              //  int fours = resultSet.getInt("fours");
              //  int sixes = resultSet.getInt("sixes");
               // double strikeRate = resultSet.getDouble("strike_rate");
                int status = resultSet.getInt("bowlingstatus");

                bowlingScorecard[index] = new PlayerBowling();
                bowlingScorecard[index].setPlayerBowling(playerName, playerId, overs, 0,wickets,0, status);

                index++;
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
class PlayerBatting {
    public String playerName;
    public int playerId;
   public   int runs;
   public int balls;
   public   int fours;
    public int sixes;
   public double strikeRate;
   public int status;

    public void setPlayerBatting(String playerName, int playerId, int runs, int balls, int fours, int sixes, double strikeRate, int status) {
        this.playerName = playerName;
        this.playerId = playerId;
        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.strikeRate = strikeRate;
        this.status = status;
    }
}

class PlayerBowling {
    public String playerName;
    public int playerId;
    public int overs;
    public int runs;
    public int wickets;
    public double economyRate;
    public   int status;
    public void setPlayerBowling(String playerName, int playerId, int overs, int runs, int wickets, double economyRate,int status) {
        this.playerName = playerName;
        this.playerId = playerId;
        this.overs = overs;
        this.runs = runs;
        this.wickets = wickets;
        this.economyRate = economyRate;
    }
}