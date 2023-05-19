import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ImageFromURLExample extends JFrame {

    public ImageFromURLExample() {
        // Create a new JLabel to display the image
        JLabel imageLabel = new JLabel();

        // Load the image from the URL
        try {


            URL imageUrl = new URL("https://ae01.alicdn.com/kf/HTB1lYT6SFXXXXbgXFXXq6xXFXXXZ/Diy-painting-by-numbers-hand-painted-Doraemon-Cartoon-oil-paintings-coloring-by-numbers-canvas-Children-bedroom.jpg");
            URLConnection connection = imageUrl.openConnection();
            connection.setConnectTimeout(5000); // 5 seconds

            Image image = ImageIO.read(imageUrl);

            ImageIcon imageIcon = new ImageIcon(image);
            imageLabel.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the image label to the frame
        add(imageLabel);

        // Set the frame properties
        setTitle("Image from URL Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ImageFromURLExample();
    }

}
