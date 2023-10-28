import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverter {

    private JFrame frame;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JTextField amountTextField;
    private JLabel resultLabel;

    private final double USD_TO_EUR = 0.85;
    private final double USD_TO_GBP = 0.75;
    private final double USD_TO_JPY = 113.05;
    private final double USD_TO_INR = 75.36;

    public CurrencyConverter() {
        frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        String[] currencies = {"USD", "EUR", "GBP", "JPY", "INR"};

        JLabel fromCurrencyLabel = new JLabel("From Currency:");
        fromCurrencyComboBox = new JComboBox<>(currencies);
        JLabel toCurrencyLabel = new JLabel("To Currency:");
        toCurrencyComboBox = new JComboBox<>(currencies);
        JLabel amountLabel = new JLabel("Amount:");
        amountTextField = new JTextField();
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        panel.add(fromCurrencyLabel);
        panel.add(fromCurrencyComboBox);
        panel.add(toCurrencyLabel);
        panel.add(toCurrencyComboBox);
        panel.add(amountLabel);
        panel.add(amountTextField);
        panel.add(convertButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void convertCurrency() {
        try {
            String fromCurrency = fromCurrencyComboBox.getSelectedItem().toString();
            String toCurrency = toCurrencyComboBox.getSelectedItem().toString();
            double amount = Double.parseDouble(amountTextField.getText());

            double convertedAmount;
            switch (fromCurrency) {
                case "USD":
                    convertedAmount = amount * getConversionRate(toCurrency);
                    break;
                case "EUR":
                    convertedAmount = amount / getConversionRate("USD") * getConversionRate(toCurrency);
                    break;
                case "GBP":
                    convertedAmount = amount / getConversionRate("USD") * getConversionRate(toCurrency);
                    break;
                case "JPY":
                    convertedAmount = amount / getConversionRate("USD") * getConversionRate(toCurrency);
                    break;
                case "INR":
                    convertedAmount = amount / getConversionRate("USD") * getConversionRate(toCurrency);
                    break;
                default:
                    convertedAmount = 0;
            }

            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            resultLabel.setText(decimalFormat.format(convertedAmount) + " " + toCurrency);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input");
        }
    }

    private double getConversionRate(String currency) {
        switch (currency) {
            case "USD":
                return 1;
            case "EUR":
                return USD_TO_EUR;
            case "GBP":
                return USD_TO_GBP;
            case "JPY":
                return USD_TO_JPY;
            case "INR":
                return USD_TO_INR;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverter();
            }
        });
    }
}
