import javax.swing.*;
import java.awt.*;
/*
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

 */
public class FRAMEEX {

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setTitle("rohit");
        ImageIcon img;
        // Set the extended state of the frame to maximized
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        img = new ImageIcon("data/rohit.png");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setIconImage(img.getImage());
        frame.setLocation(200,200);
        frame.setBounds(100,100,700,700);
        frame.setVisible(true);

        Container c=frame.getContentPane();
        c.setBackground(new Color(0, 255, 47));
        //c.setBackground(Color.BLACK);
        frame.setResizable(true);
        JLabel lab=new JLabel("Great");
        lab.setBounds(200,300,300,400);
        //setText(String text): Sets the text of the label to the specified string.
       // getText(): Returns the text of the label.
        lab.setText("vinay is great");
        lab.setForeground(Color.BLUE);
        //setForeground(Color color): Sets the foreground color of the label's text.
        //getForeground(): Returns the foreground color of the label's text.
        Font font = new Font("Arial", Font.BOLD + Font.ITALIC, 20);
        lab.setFont(font);
        //setFont(Font font): Sets the font used to display the label's text.
        //getFont(): Returns the font used to display the label's text.
        c.add(lab);
        //c.setLayout(null);
        JLabel label = new JLabel();
       // ImageIcon icon = new ImageIcon("data/dhoni.jpg");
       // label.setIcon(icon);
       // label.setSize(icon.getIconWidth(), icon.getIconHeight());
       // label.setHorizontalAlignment(SwingConstants.CENTER);
        //label.setVerticalAlignment(SwingConstants.CENTER);
        // label.setBounds(50, 50, 300, 300);
       // ImageIcon icon = new ImageIcon("data/dhoni.jpg"); // Use an absolute path for the image file
       // Image imga= icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
       // icon = new ImageIcon(imga);
      //  label.setIcon(icon);
      //  c.add(label, BorderLayout.CENTER);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("data/sachin.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        frame.add(image);



    }
}
