import javax.swing.*;

public class ComboBoxExample {

    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("JComboBox Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null); // Set layout to nullframe.
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Create a JComboBox and add items to it
        String[] items = {"Item 1", "Item 2", "Item 3"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBounds(100, 50, 150, 30); // Set bounds
        comboBox.setSelectedIndex(2);
        //comboBox.setEditable(true);
        // Create a JLabel
        JLabel label = new JLabel("Selected item will be displayed here");
        label.setBounds(50, 100, 300, 30); // Set bounds

        // Create a JButton
        JButton button = new JButton("Show Item");
        button.setBounds(150, 150, 100, 30); // Set bounds

        // Add action listener to the button
        button.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            label.setText(selected);
        });

        // Add components to the frame
        frame.add(comboBox);
        frame.add(label);
        frame.add(button);

        // Set the frame visible
        frame.setVisible(true);
    }
}
