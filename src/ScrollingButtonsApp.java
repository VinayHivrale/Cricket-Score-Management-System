import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrollingButtonsApp extends JFrame {
    private JPanel buttonPanel;
    private JScrollPane scrollPane;

    public ScrollingButtonsApp() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Scrolling Buttons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 1));

        // Add buttons to the panel
        for (int i = 1; i <= 1; i++) {
            JButton button = new JButton("Button " + i);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle button click event
                    JOptionPane.showMessageDialog(ScrollingButtonsApp.this,
                            "Button clicked: " + button.getText());
                }
            });
            buttonPanel.add(button);
        }

        scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setPreferredSize(new Dimension(380, 200));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
    }

    public static void main(String[] args) {
        ScrollingButtonsApp app = new ScrollingButtonsApp();
        app.setVisible(true);
    }
}
