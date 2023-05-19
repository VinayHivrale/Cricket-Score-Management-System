import javax.swing.*;
import java.awt.*;
public class LoginScreen1 {
    public static void main(String[] args) {
        JFrame frame= new JFrame();

        frame.setTitle("Image Frame");

        // Create an image icon from an image file
        ImageIcon imageIcon = new ImageIcon("data/sachin.jpg");

        // Create a label to hold the image icon
        JLabel imageLabel = new JLabel(imageIcon);

        // Set the layout of the frame
       frame.setLayout(new BorderLayout());

        // Add the image label to the center of the frame
        frame.add(imageLabel, BorderLayout.CENTER);

        // Set the size of the frame
        frame.setSize(800, 800);



        // Set the default close operation of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Show the frame
       frame.setVisible(true);


    }


}
