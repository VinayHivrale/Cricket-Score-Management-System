import javax.swing.*;
import java.awt.*;

public class FlowLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FlowLayout Example");
       // frame.setLocationRelativeTo(null);
       // frame.setBounds(20,20,500,500);
        FlowLayout f=new FlowLayout(FlowLayout.CENTER,50,40);
        JPanel panel = new JPanel(f);

        // Create buttons
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        JButton button5 = new JButton("Button 5");
        JButton button6 = new JButton("Button 6");

        // Add buttons to panel using an index
        panel.add(button1, 0);
        panel.add(button2, 1);
        panel.add(button3, 2);
        panel.add(button4, 3);
        panel.add(button5, 4);
        panel.add(button6, 5);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(600,300);// 600 300
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
