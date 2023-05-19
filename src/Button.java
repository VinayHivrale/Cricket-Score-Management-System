import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JFrame implements ActionListener {

    public Button() {
        // Set the title of the frame
        setTitle("Button Frame");

        // Create a grid layout with 2 rows and 3 columns
        setLayout(new GridLayout(2, 3));

        // Create 6 buttons and add them to the frame
        for (int i = 1; i <= 6; i++) {
            JButton button = new JButton("Button " + i);
            button.addActionListener(this); // Add action listener to the button
            button.addActionListener(this); // Add action listener to the button
            button.setFocusPainted(false); // Remove the border highlight on button click
            button.setBackground(Color.BLUE); // Set the background color of button
            button.setForeground(Color.WHITE); // Set the te
            add(button); // Add the button to the frame
        }

        // Set the size of the frame and make it visible
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the button that was clicked
        JButton button = (JButton) e.getSource();

        // Change the text of the button to "vinay"
        button.setText("vinay");

        // Change the name of the button to "Button"
        button.setName("Button");

        button.setBackground(Color.GREEN);
        try {
            Thread.sleep(30); // Wait for 5 seconds
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        button.setText("Button " + button.getName().substring(button.getName().length() - 1));
    }

    public static void main(String[] args) {
        new Button();
    }
}

