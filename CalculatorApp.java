import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {

    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton addButton, subButton, mulButton, divButton, eqButton, clrButton, dotButton;
    private double num1, num2, result;
    private char operator;


    public CalculatorApp() {

        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].addActionListener(this);
        }

        operatorButtons = new JButton[4];
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");
        clrButton = new JButton("C");
        dotButton = new JButton(".");

        operatorButtons[0] = addButton;
        operatorButtons[1] = subButton;
        operatorButtons[2] = mulButton;
        operatorButtons[3] = divButton;

        for (JButton button : operatorButtons) {
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
        }

        eqButton.setFont(new Font("Arial", Font.PLAIN, 18));
        eqButton.addActionListener(this);

        clrButton.setFont(new Font("Arial", Font.PLAIN, 18));
        clrButton.addActionListener(this);

        dotButton.setFont(new Font("Arial", Font.PLAIN, 18));
        dotButton.addActionListener(this);

        // Panel for Number Buttons
        JPanel numPanel = new JPanel();
        numPanel.setLayout(new GridLayout(4, 3));
        for (int i = 1; i <= 9; i++) {
            numPanel.add(numberButtons[i]);
        }
        numPanel.add(operatorButtons[0]);
        numPanel.add(numberButtons[0]);
        numPanel.add(clrButton);

        // Panel for Operator Buttons
        JPanel opPanel = new JPanel();
        opPanel.setLayout(new GridLayout(5, 1));
        opPanel.add(addButton);
        opPanel.add(subButton);
        opPanel.add(mulButton);
        opPanel.add(divButton);
        opPanel.add(eqButton);

        // Panel for Dot Button
        JPanel dotPanel = new JPanel();
        dotPanel.add(dotButton);

        // Combine all panels
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(numPanel, BorderLayout.CENTER);
        buttonPanel.add(opPanel, BorderLayout.EAST);
        buttonPanel.add(dotPanel, BorderLayout.SOUTH);

        // Add button panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Set up the frame visibility
        setVisible(true);
    }

    // ActionListener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText() + i);
            }
        }

        if (e.getSource() == dotButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText() + ".");
            }
        }

        if (e.getSource() == clrButton) {
            textField.setText("");
            num1 = num2 = result = 0;
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if (e.getSource() == eqButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                    break;
            }

            textField.setText(String.valueOf(result));
            num1 = result;
        }
    }

    
    public static void main(String[] args) {
        new CalculatorApp();
    }
}
