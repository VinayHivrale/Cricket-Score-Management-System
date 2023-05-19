import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonExample implements ActionListener {

    static JButton button = new JButton("Click me!");
    static JFrame frame = new JFrame("Button Example");
    static JPanel panel = (JPanel) frame.getContentPane();

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);


        ImageIcon i1 = new ImageIcon("data/john.jpg");
        Image i2 = i1.getImage().getScaledInstance(600, 200, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Create a JPanel to hold the button

        panel.setLayout(null);
        // panel.setLayout(new FlowLayout(FlowLayout.CENTER,40,20));
        Font font = new Font("Courier New", Font.ITALIC + Font.BOLD, 25);
        // Create a JButton and add it to the JPanel
        // JButton button = new JButton("Click me!",i3);

        //    button.setSize(300,100);
        button.setIcon(i3);
        button.setSize(i3.getIconWidth(), i3.getIconHeight());
        button.setLocation(650, 250);

        button.setFont(font);
        button.setText("PLEASE CLICK ME !");
        button.setBackground(Color.BLACK);
        button.setBorder(new LineBorder(Color.RED, 2));
        button.setForeground(Color.GREEN);
        button.setCursor(cur);
        //  button.setVisible(false);
        //  button.setEnabled(false);


        panel.add(button);

        // Add the JPanel to the JFrame and make it visible
        // frame.pack();
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Code to be executed when the component is interacted with
                if (e.getSource() == button) {
                    frame.setVisible(true);
                    frame.setBackground(Color.MAGENTA);
                }
            }
        });

   /* public void actionPerformed(ActionEvent e) {


        if (e.getSource() == button) {
            frame.setVisible(true);
            frame.setBackground(Color.MAGENTA);
        }
    }*/
}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

