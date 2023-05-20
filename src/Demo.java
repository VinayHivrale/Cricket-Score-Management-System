import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demo {
    private JFrame frame;
    private JLabel currentOverLabel;
    private int currentOver;
    private int currentBall;

    public Demo() {
        // Initialize the current over and ball number
        currentOver = 0;
        currentBall = 0;

        // Create and configure the frame
        frame = new JFrame("Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        // Create the current over label
        currentOverLabel = new JLabel("Current Over: " + currentOver + "." + currentBall);
        frame.add(currentOverLabel);

        // Create the next button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the current over and ball number
                currentBall++;
                if (currentBall > 5) {
                    currentOver++;
                    currentBall = 0;
                }

                // Update the current over label
                currentOverLabel.setText("Current Over: " + currentOver + "." + currentBall);
            }
        });
        frame.add(nextButton);

        // Display the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Demo();
            }
        });
    }
}
