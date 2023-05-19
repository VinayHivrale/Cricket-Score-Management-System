import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {

    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem, saveItem, exitItem;

    public Notepad() {
        super("Notepad");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the text area
        textArea = new JTextArea();
        textArea.setBackground(new Color(24, 20, 20, 255));
        textArea.setForeground(new Color(103, 242, 249));

        textArea.setFont(new Font("Comic Sans MS", Font.ITALIC+ Font.BOLD, 22));
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // Create the menu bar
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(242, 242, 2, 255));
        setJMenuBar(menuBar);

        // Create the file menu
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Create the file menu items
        openItem = new JMenuItem("Open");
        openItem.addActionListener(this);
        fileMenu.add(openItem);

        saveItem = new JMenuItem("Save");
        saveItem.addActionListener(this);
        fileMenu.add(saveItem);

        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == openItem) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    BufferedReader in = new BufferedReader(new FileReader(file));
                    String line;
                    textArea.setText("");
                    while ((line = in.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    in.close();
                } catch (IOException ex) {
                    System.err.println("Error opening file: " + ex);
                }
            }
        } else if (source == saveItem) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(file));
                    out.write(textArea.getText());
                    out.close();
                } catch (IOException ex) {
                    System.err.println("Error saving file: " + ex);
                }
            }
        } else if (source == exitItem) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }
}
