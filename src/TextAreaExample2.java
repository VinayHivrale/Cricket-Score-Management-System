import javax.swing.*;
import java.awt.*;

public class TextAreaExample2 extends JFrame {

    public TextAreaExample2() {
        setTitle("Text Area Example");
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setBackground(Color.YELLOW);
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));
        textArea.setLineWrap(true);
      //  textArea.setBorder(BorderFactory.createLineBorder(Color.RED));
        //textArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.RED, Color.GREEN));
        textArea.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 50, 400, 300);
        add(scrollPane);
        // textArea.setEditable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TextAreaExample2();
    }
}
