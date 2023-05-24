import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class AdminWindow extends JFrame {
    public AdminWindow() {
        super("Admin Window");
        // create buttons
        JButton addPlayerButton = createStyledButton("Add Player");
        addPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AddPlayerWindow();
            }
        });

        JButton searchMatchButton = createStyledButton("Search Match");
     searchMatchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AdminSearchMatch();}
        });

        JButton createMatchButton = createStyledButton("Create Match");
        createMatchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateMatchForm();
            }
        });

        JButton addTeamButton = createStyledButton("Add Team");
        addTeamButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AddTeamWindow();
            }
        });

        JButton searchPlayerButton = createStyledButton("Search Player");
        searchPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchPlayerWindow();
                setVisible(false);
            }
        });

        JButton searchTeamButton = createStyledButton("Search Team");
        searchTeamButton.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                SearchTeamWindow searchTeamWindow=new SearchTeamWindow();
                setVisible(false);
            }
        });

        JButton backButton = createStyledButton("Back");
        backButton.setBackground(new Color(0, 1, 3));
        backButton.setForeground(new Color(255, 255, 255));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // setVisible(false);
                new AdminLogin(1);
                setVisible(false);
            }
        });


        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(37, 37, 5));
        panel.add(addPlayerButton);
        panel.add(createMatchButton);
        panel.add(addTeamButton);
        panel.add(searchPlayerButton);
        panel.add(searchTeamButton);
        panel.add(searchMatchButton);
        panel.add(backButton);



        add(panel);


        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(new Color(2, 10, 15));
        button.setBackground(new Color(203, 203, 47, 255));
        button.setFont(new Font("Comic Sans MS", Font.BOLD +Font.ITALIC, 20));
        button.setFocusPainted(false);
        Border buttonBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
        button.setBorder(buttonBorder);
        return button;
    }
}
