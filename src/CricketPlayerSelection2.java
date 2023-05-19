import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CricketPlayerSelection2 extends JFrame implements ActionListener {
    private JPanel panel;
    private JCheckBox[] checkboxes;
    private JLabel label;
    private int maxSelections = 3;
    private int numSelections = 0;

    public CricketPlayerSelection2() {
        setTitle("Cricket Player Selection");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        checkboxes = new JCheckBox[6];
        String[] players = {"Sachin Tendulkar", "Virat Kohli", "Rohit Sharma", "MS Dhoni", "Yuvraj Singh", "Jasprit Bumrah"};

        for (int i = 0; i < checkboxes.length; i++) {
            checkboxes[i] = new JCheckBox(players[i]);
            checkboxes[i].addActionListener(this);
            panel.add(checkboxes[i]);
        }

        label = new JLabel("Selected players: ");
        panel.add(label);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JCheckBox) {
            JCheckBox checkbox = (JCheckBox) source;
            if (checkbox.isSelected()) {
                if (numSelections >= maxSelections) {
                    checkbox.setSelected(false);
                } else {
                    numSelections++;
                    updateLabel();
                }
            } else {
                numSelections--;
                updateLabel();
            }
        }
    }

    private void updateLabel() {
        StringBuilder sb = new StringBuilder("Selected players: ");
        int count = 0;
        for (JCheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                sb.append(checkbox.getText()).append(", ");
                count++;
            }
        }
        if (count > 0) {
            sb.delete(sb.length() - 2, sb.length()); // remove the last ", "
        } else {
            sb.append("none");
        }
        label.setText(sb.toString());
    }

    public static void main(String[] args) {
        new CricketPlayerSelection2();
    }
}
