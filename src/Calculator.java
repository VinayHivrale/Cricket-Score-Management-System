import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JTextField firstNumberTextField, secondNumberTextField;
    private JLabel resultLabel;
    private JButton addButton, subButton, mulButton, divButton;

    public Calculator() {
        // Set the title of the frame
        setTitle("Calculator using two panels and flowLYOUT");

        // Create the text fields for input numbers
        firstNumberTextField = new JTextField(10);
        secondNumberTextField = new JTextField(10);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(mulButton);
        buttonPanel.add(divButton);

        // Create a panel for the text fields and the result label
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("First Number: "));
        inputPanel.add(firstNumberTextField);
        inputPanel.add(new JLabel("Second Number: "));
        inputPanel.add(secondNumberTextField);
        inputPanel.add(new JLabel("Result: "));
        inputPanel.add(resultLabel);

        // Add the panels to the frame
        add(buttonPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);

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
        new Calculator();
    }
}
