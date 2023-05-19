import javax.swing.*;
import java.awt.*;

public class CricketCheckboxDemo extends JFrame {

    public CricketCheckboxDemo() {
        super("Cricket Checkbox Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1));

        JCheckBox checkbox1 = new JCheckBox("Virat Kohli");
        checkbox1.setBackground(Color.WHITE);
        checkbox1.setForeground(Color.BLUE);
        checkbox1.setFont(new Font("Arial", Font.BOLD, 16));
        checkbox1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        checkbox1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(checkbox1);

        JCheckBox checkbox2 = new JCheckBox("Rohit Sharma");
        checkbox2.setBackground(Color.WHITE);
        checkbox2.setForeground(Color.RED);
        checkbox2.setFont(new Font("Calibri", Font.PLAIN, 14));
        checkbox2.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        checkbox2.setHorizontalTextPosition(SwingConstants.LEFT);
        panel.add(checkbox2);

        JCheckBox checkbox3 = new JCheckBox("Jasprit Bumrah");
        checkbox3.setBackground(Color.WHITE);
        checkbox3.setForeground(Color.GREEN);
        checkbox3.setFont(new Font("Verdana", Font.BOLD, 12));
        checkbox3.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        checkbox3.setVerticalAlignment(SwingConstants.TOP);
        panel.add(checkbox3);

        JCheckBox checkbox4 = new JCheckBox("MS Dhoni");
        checkbox4.setBackground(Color.WHITE);
        checkbox4.setForeground(Color.ORANGE);
        checkbox4.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        checkbox4.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 5, Color.YELLOW));
        checkbox4.setVerticalTextPosition(SwingConstants.BOTTOM);
        panel.add(checkbox4);

        JCheckBox checkbox5 = new JCheckBox("Hardik Pandya");
        checkbox5.setBackground(Color.WHITE);
        checkbox5.setForeground(Color.MAGENTA);
        checkbox5.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        checkbox5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        checkbox5.setVerticalAlignment(SwingConstants.BOTTOM);
        panel.add(checkbox5);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CricketCheckboxDemo());
    }
}
