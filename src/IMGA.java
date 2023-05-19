import javax.swing.*;
import java.awt.*;

public class IMGA extends JFrame
{  IMGA()
{     JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    setTitle("Image");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon i1 = new ImageIcon("data/john.jpg");
    Image i2 = i1.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel("john wick",i3,JLabel.LEFT);
    panel.add(image);
    add(panel);
    setVisible(true);
}


    public static void main(String[] args) {
       new IMGA().setVisible(true);

    }

}