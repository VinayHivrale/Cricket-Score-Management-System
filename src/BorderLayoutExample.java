import javax.swing.*;
import java.awt.*;

public class BorderLayoutExample extends JFrame {

    public BorderLayoutExample() {
        setTitle("BorderLayout Example");

        // Create a panel and set its layout to BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components to the panel
        panel.add(new JButton("North"), BorderLayout.NORTH);
        panel.add(new JButton("South"), BorderLayout.SOUTH);
        panel.add(new JButton("East"), BorderLayout.EAST);
        panel.add(new JButton("West"), BorderLayout.WEST);
        panel.add(new JButton("Center"), BorderLayout.CENTER);
        panel.add(new JButton("Center222"), BorderLayout.CENTER);
        // Add the panel to the frame
        getContentPane().add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new BorderLayoutExample();
    }
}
