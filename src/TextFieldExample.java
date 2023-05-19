import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TextFieldExample {

    public static void main(String[] args) {
        // Create a new JFrame and set its properties
        JFrame frame = new JFrame();
        frame.setTitle("Text Field Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,40,20));
      //  frame.setLayout(new FlowLayout(FlowLayout.CENTER,600,20));//try applying this
        // Create a new JTextField object and set its properties
        JTextField textField = new JTextField(20); // 20 columns
        Font font = new Font("Arial", Font.BOLD, 16);
        textField.setBounds(200,200,300,200);
        textField.setFont(font);
        textField.setForeground(Color.BLUE);
        textField.setBackground(Color.YELLOW);
        textField.setText("Enter your name"); // default text

        textField.setToolTipText("Type your name here"); // tooltip text
        textField.setEditable(true);
        textField.setBorder(new LineBorder(Color.RED, 2));


        // make the text field editable
       // textField.setEditable(false);
        // Add the text field to the frame

        JPasswordField passwordField = new JPasswordField(20);//columns
        passwordField.setFont(font);
        passwordField.setForeground(Color.RED);

        passwordField.setEchoChar('*');
        passwordField.setText("RaAVAn");
        frame.add(textField);
        frame.add(passwordField);


        //frame.pack();

        // Make the frame visible
        frame.setVisible(true);

    }
}

