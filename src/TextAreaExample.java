import javax.swing.*;
import java.awt.*;

public class TextAreaExample {
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Text Area Example");
          frame.setLayout(new BorderLayout(40,40));
        // Create a new text area with 5 rows and 20 columns
        JTextArea textArea = new JTextArea(5, 20);
        frame.getContentPane().setBackground(Color.YELLOW);
        // Add the text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the scroll pane to the frame
        frame.getContentPane().add(scrollPane, BorderLayout.NORTH);

        // Set the size of the frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Make the frame visible
        frame.setVisible(true);

        // Set the default close operation for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

