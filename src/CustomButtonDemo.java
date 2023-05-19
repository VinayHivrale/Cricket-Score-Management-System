import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class CustomButton extends JButton {
    private Color bgColor;
    private Color hoverColor;
    private Color clickedColor;

    public CustomButton(String text) {
        super(text);
        setOpaque(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
      // Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
     // Border margin = new EmptyBorder(10, 10, 10, 10);
     // setBorder(BorderFactory.createCompoundBorder(border, margin)); // black line border with 2 pixel width and 10 pixel empty margin
       // setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        //setBorderPainted(false);
       // setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
       setBorder(BorderFactory.createLineBorder(new Color(24, 10, 10), 6)); // black border with 2 pixel width
       setForeground(Color.BLACK);
        Font font = new Font("Comic Sans MS", Font.BOLD, 18);
        setFont(font);
        setPreferredSize(new Dimension(150, 40));
        setBgColor(new Color(78, 49, 170, 255)); // default gray color
        setHoverColor(new Color(255, 191, 0, 255)); // default hover color
        setClickedColor(new Color(255, 149, 81)); // default clicked color

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(bgColor);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(clickedColor);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(hoverColor);
            }
        });
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
        setBackground(bgColor);
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public void setClickedColor(Color clickedColor) {
        this.clickedColor = clickedColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isPressed()) {
            g2.setColor(clickedColor);
        } else if (getModel().isRollover()) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(bgColor);
        }
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        super.paintComponent(g);
    }
}

public class CustomButtonDemo extends JPanel {
    public CustomButtonDemo() {
        setLayout(new GridLayout(2, 3, 35, 20));
        setBackground(new Color(46, 79, 79, 255));
        CustomButton button1 = new CustomButton("Virat");
        CustomButton button2 = new CustomButton("Sachin");
        CustomButton button3 = new CustomButton("Rohit");
        CustomButton button4 = new CustomButton("Sehwag");
        CustomButton button5 = new CustomButton("Dhawan");
        CustomButton button6 = new CustomButton("Rahul");
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Button Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CustomButtonDemo());
      //  frame.setBackground(Color.BLUE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
