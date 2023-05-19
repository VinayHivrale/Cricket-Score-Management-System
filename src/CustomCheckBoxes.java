import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomCheckBoxes extends JPanel implements MouseListener {

    private final String[] playerNames = {"Sachin Tendulkar", "Virat Kohli", "Jacques Kallis", "Kumar Sangakkara", "Ricky Ponting", "Shane Warne"};
    private boolean[] checkBoxStates = {false, false, false, false, false, false};
    private final Font font = new Font("Arial", Font.PLAIN, 14);
    private final Image checkedImage;
    private final Image uncheckedImage;
    private final Image hoveredImage;
    private final int checkboxWidth = 25;
    private final int checkboxHeight = 25;

    public CustomCheckBoxes() {
        super();
        setPreferredSize(new Dimension(500, 100));
        checkedImage = new ImageIcon("data/checked.png").getImage();
        uncheckedImage = new ImageIcon("data/unchecked.png").getImage();
        hoveredImage = new ImageIcon("data/rohit.png").getImage();
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(font);
        for (int i = 0; i < playerNames.length; i++) {
            int x = 10 + (i * 100);
            int y = 50;
            String playerName = playerNames[i];
            g.drawString(playerName, x + checkboxWidth + 10, y + 15);
            if (checkBoxStates[i]) {
                g.drawImage(checkedImage, x, y, checkboxWidth, checkboxHeight, this);
            } else if (isHovered(x, y)) {
                g.drawImage(hoveredImage, x, y, checkboxWidth, checkboxHeight, this);
            } else {
                g.drawImage(uncheckedImage, x, y, checkboxWidth, checkboxHeight, this);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for (int i = 0; i < playerNames.length; i++) {
            int checkboxX = 10 + (i * 100);
            int checkboxY = 50;
            if (isHovered(checkboxX, checkboxY) && e.getButton() == MouseEvent.BUTTON1) {
                checkBoxStates[i] = !checkBoxStates[i];
            }
        }
        repaint();
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    private boolean isHovered(int x, int y) {
        Point mousePos = getMousePosition();
        return mousePos != null && mousePos.x >= x && mousePos.x <= x + checkboxWidth && mousePos.y >= y && mousePos.y <= y + checkboxHeight;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Checkboxes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CustomCheckBoxes panel = new CustomCheckBoxes();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
