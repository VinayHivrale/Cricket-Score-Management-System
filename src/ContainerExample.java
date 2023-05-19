import javax.swing.*;

public class ContainerExample {
    public static void main(String[] args) {
        // Create a panel
        JPanel panel = new JPanel();
        JLabel lab1=new JLabel("This is a panel");
        panel.add(lab1);
        JLabel lab2=new JLabel("This is a panel");
        panel.add(lab2);

        // Create a window
        JFrame window = new JFrame("This is a window");
        window.setSize(400, 500);
        window.add(panel);
        window.setVisible(true);

        // Create a frame
        JFrame frame = new JFrame("This is a frame");
        frame.setSize(1000, 800);
        frame.add(new JLabel("This is a frame"));
        frame.setVisible(true);
    }
}

