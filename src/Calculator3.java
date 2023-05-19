import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator3 extends JFrame implements ActionListener {

    private JTextField firstNumberTextField, secondNumberTextField;
    private JLabel resultLabel;
    private JButton addButton, subButton, mulButton, divButton;

    public Calculator3() {
        // Set the title of the frame
        setTitle("Calculator");

        // Create the text fields for input numbers
        firstNumberTextField = new JTextField(10);
        secondNumberTextField = new JTextField(10);

        // Create the buttons for arithmetic operations
        addButton = new JButton("Add");
        subButton = new JButton("Subtract");
        mulButton = new JButton("Multiply");
        divButton = new JButton("Divide");

        // Add action listener to the buttons
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);

        // Create the label for displaying result
        resultLabel = new JLabel("Result");

        // Create a panel and add the components to it
        JPanel panel = new JPanel(new GridLayout(3, 2, 100, 200));
        panel.add(new JLabel("First Number: "));
        panel.add(firstNumberTextField);
        panel.add(new JLabel("Second Number: "));
        panel.add(secondNumberTextField);
        panel.add(addButton);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(divButton);
        panel.add(new JLabel("Result: "));
        panel.add(resultLabel);

        // Add the panel to the frame
        add(panel);

        // Set the size of the frame and make it visible
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the values from the text fields
        String firstNumberString = firstNumberTextField.getText();
        String secondNumberString = secondNumberTextField.getText();

        // Handle exceptions for invalid input
        try {
            // Parse the input strings into double values
            double firstNumber = Double.parseDouble(firstNumberString);
            double secondNumber = Double.parseDouble(secondNumberString);

            // Perform the arithmetic operation based on the button clicked
            double result = 0;
            if (e.getSource() == addButton) {
                result = firstNumber + secondNumber;
            } else if (e.getSource() == subButton) {
                result = firstNumber - secondNumber;
            } else if (e.getSource() == mulButton) {
                result = firstNumber * secondNumber;
            } else if (e.getSource() == divButton) {
                result = firstNumber / secondNumber;
            }

            // Set the result in the label
            resultLabel.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            // Handle invalid input
            resultLabel.setText("Invalid input");
        } catch (ArithmeticException ex) {
            // Handle division by zero
            resultLabel.setText("Cannot divide by zero");
        }
    }

    public static void main(String[] args) {
        new Calculator3();
    }
}
