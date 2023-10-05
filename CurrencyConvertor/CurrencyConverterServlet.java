import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverterServlet extends JFrame implements ActionListener {

    private static final String[] CURRENCIES = {"USD", "EUR", "GBP", "JPY", "CNY"};
    private static final double[] CONVERSION_RATES = {1.0, 0.91, 0.78, 110.11, 6.46};

    private JPanel mainPanel;
    private JLabel amountLabel;
    private JTextField amountTextField;
    private JLabel sCL;
    private JComboBox<String> sCCB;
    private JLabel tCL;
    private JComboBox<String> tCCB;
    private JButton convertButton;
    private JLabel resultLabel;

    public CurrencyConverterServlet() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 2));

        amountLabel = new JLabel("Amount:");
        amountTextField = new JTextField();

        sCL = new JLabel("From:");
        sCCB = new JComboBox<>(CURRENCIES);
        sCCB.setFont(new Font("Arial", Font.PLAIN, 12));
        sCCB.setPreferredSize(new Dimension(150, 20));

        tCL = new JLabel("To:");
        tCCB = new JComboBox<>(CURRENCIES);
        tCCB.setFont(new Font("Arial", Font.PLAIN, 12));
        tCCB.setPreferredSize(new Dimension(150, 20));

        convertButton = new JButton("Convert");
        convertButton.setSize(10, 10);
        convertButton.addActionListener(this);

        resultLabel = new JLabel("");

        mainPanel.add(amountLabel);
        mainPanel.add(amountTextField);
        mainPanel.add(sCL);
        mainPanel.add(sCCB);
        mainPanel.add(tCL);
        mainPanel.add(tCCB);
        mainPanel.add(convertButton);
        mainPanel.add(resultLabel);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            int sIndex = sCCB.getSelectedIndex();
            int tIndex = tCCB.getSelectedIndex();
            
            double sRate = CONVERSION_RATES[sIndex];
            double tRate = CONVERSION_RATES[tIndex];

            double convertedAmount = amount * (tRate / sRate);
            resultLabel.setText(String.format("%.2f %s = %.2f %s",
                    amount, CURRENCIES[sIndex], convertedAmount, CURRENCIES[tIndex]));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverterServlet converter = new CurrencyConverterServlet();
            converter.setVisible(true);
        });
    }
}