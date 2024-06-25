import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame {
    private JTextField displayField;
    private JButton[] buttons;

    private double firstNumber;
    private String operator;

    public CalculatorApp() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        buttons = new JButton[buttonLabels.length];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            buttonPanel.add(buttons[i]);

            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    handleButtonClick(command);
                }
            });
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void handleButtonClick(String command) {
        if (command.matches("[0-9]+")) {
            displayField.setText(displayField.getText() + command);
        } else if (command.equals(".")) {
            if (!displayField.getText().contains(".")) {
                displayField.setText(displayField.getText() + ".");
            }
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            firstNumber = Double.parseDouble(displayField.getText());
            operator = command;
            displayField.setText("");
        } else if (command.equals("=")) {
            double secondNumber = Double.parseDouble(displayField.getText());
            double result = calculateResult(firstNumber, secondNumber, operator);
            displayField.setText(String.valueOf(result));
        }
    }

    private double calculateResult(double num1, double num2, String op) {
        switch (op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Division by zero", "Error", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculatorApp();
            }
        });
    }
}

