import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ScientificCalculator {
    private JFrame frame;
    private JTextField textField;

    public ScientificCalculator() {
        frame = new JFrame("Scientific Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        frame.add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "sin", "cos", "tan", "sqrt",
            "log", "ln", "x^2", "x^y"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JButton source = (JButton) event.getSource();
            String buttonText = source.getText();

            switch (buttonText) {
                case "=":
                    evaluateExpression();
                    break;
                case "sqrt":
                    calculateSquareRoot();
                    break;
                case "x^2":
                    calculateSquare();
                    break;
                case "sin":
                    calculateTrigFunction("sin");
                    break;
                case "cos":
                    calculateTrigFunction("cos");
                    break;
                case "tan":
                    calculateTrigFunction("tan");
                    break;
                case "log":
                    calculateLogarithm("log");
                    break;
                case "ln":
                    calculateLogarithm("ln");
                    break;
                case "x^y":
                    performExponentiation();
                    break;
                default:
                    textField.setText(textField.getText() + buttonText);
            }
        }
    }

    private void evaluateExpression() {
        try {
            String expression = textField.getText();
            double result = CalculatorEngine.evaluate(expression);
            textField.setText(String.valueOf(result));
        } catch (Exception e) {
            textField.setText("Error");
        }
    }

    private void calculateSquareRoot() {
        try {
            double number = Double.parseDouble(textField.getText());
            double result = Math.sqrt(number);
            textField.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            textField.setText("Error");
        }
    }

    private void calculateSquare() {
        try {
            double number = Double.parseDouble(textField.getText());
            double result = number * number;
            textField.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            textField.setText("Error");
        }
    }

    private void calculateTrigFunction(String functionName) {
        try {
            double number = Double.parseDouble(textField.getText());
            double result;
            switch (functionName) {
                case "sin":
                    result = Math.sin(Math.toRadians(number));
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(number));
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(number));
                    break;
                default:
                    result = 0; // Default case, should not occur
            }
            textField.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            textField.setText("Error");
        }
    }

    private void calculateLogarithm(String functionName) {
        try {
            double number = Double.parseDouble(textField.getText());
            double result;
            switch (functionName) {
                case "log":
                    result = Math.log10(number);
                    break;
                case "ln":
                    result = Math.log(number);
                    break;
                default:
                    result = 0; // Default case, should not occur
            }
            textField.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            textField.setText("Error");
        }
    }

    private void performExponentiation() {
        try {
            String[] parts = textField.getText().split("\\^");
            if (parts.length == 2) {
                double base = Double.parseDouble(parts[0]);
                double exponent = Double.parseDouble(parts[1]);
                double result = Math.pow(base, exponent);
                textField.setText(String.valueOf(result));
            } else {
                textField.setText("Error");
            }
        } catch (NumberFormatException e) {
            textField.setText("Error");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScientificCalculator();
            }
        });
    }
    
}
