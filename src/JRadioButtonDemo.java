import javax.swing.*;
import java.awt.*;

public class JRadioButtonDemo extends JFrame {

    public JRadioButtonDemo() {
        setTitle("JRadioButton Demo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create two button groups
        ButtonGroup genderGroup = new ButtonGroup();
        ButtonGroup casteGroup = new ButtonGroup();

        // Create radio buttons for gender
        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setSelected(true);
        JRadioButton femaleButton = new JRadioButton("Female");

        // Add gender radio buttons to button group
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        // Create radio buttons for caste
        JRadioButton openButton = new JRadioButton("Open");
        JRadioButton scButton = new JRadioButton("SC");
        JRadioButton stButton = new JRadioButton("ST");
        JRadioButton obcButton = new JRadioButton("OBC");
        obcButton.setEnabled(false);

        // Add caste radio buttons to button group
        casteGroup.add(openButton);
        casteGroup.add(scButton);
        casteGroup.add(stButton);
        casteGroup.add(obcButton);

        // Create a panel and add the radio buttons to it
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 300, 300));
        panel.add(maleButton);
        panel.add(femaleButton);
        panel.add(openButton);
        panel.add(scButton);
        panel.add(stButton);
        panel.add(obcButton);

        // Set font and font size for radio buttons
        Font font = new Font("Arial", Font.PLAIN, 20);
        maleButton.setFont(font);
        femaleButton.setFont(font);
        openButton.setFont(font);
        scButton.setFont(font);
        stButton.setFont(font);
        obcButton.setFont(font);


        maleButton.setForeground(Color.RED);
        femaleButton.setForeground(Color.BLUE);
        openButton.setForeground(Color.GREEN);
        scButton.setForeground(Color.ORANGE);
        stButton.setForeground(Color.MAGENTA);
        obcButton.setForeground(Color.CYAN);

         femaleButton.setSelected(true);
        // Set border for the panel
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Set background color for the frame and text color for the radio buttons
        getContentPane().setBackground(Color.BLACK);
        panel.setBackground(Color.GRAY);


        // Add the panel to the frame and make it visible
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JRadioButtonDemo();
    }
}
