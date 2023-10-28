import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicTacToeGame {
    private JFrame frame;
    private JButton[] buttons;
    private boolean isXNext = true;
    private boolean gameWon = false;

    public TicTacToeGame() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));
        frame.setResizable(false);

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = createButton();
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    private JButton createButton() {
        JButton button = new JButton("");
        button.setFont(new Font("Arial", Font.PLAIN, 48));
        button.setFocusPainted(false);
        button.setBackground(Color.LIGHT_GRAY);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (!gameWon && button.getText().isEmpty()) {
                    button.setBackground(new Color(200, 200, 200));
                }
            }

            public void mouseExited(MouseEvent e) {
                if (!gameWon && button.getText().isEmpty()) {
                    button.setBackground(Color.LIGHT_GRAY);
                }
            }
        });
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!gameWon && button.getText().isEmpty()) {
                    if (isXNext) {
                        button.setText("X");
                        button.setForeground(Color.BLUE);
                    } else {
                        button.setText("O");
                        button.setForeground(Color.RED);
                    }
                    isXNext = !isXNext;
                    button.setBackground(Color.LIGHT_GRAY);
                    if (checkForWin()) {
                        gameWon = true;
                        JOptionPane.showMessageDialog(frame, (isXNext ? "O" : "X") + " wins!");
                    }
                }
            }
        });
        return button;
    }

    private boolean checkForWin() {
        String[] positions = new String[9];
        for (int i = 0; i < 9; i++) {
            positions[i] = buttons[i].getText();
        }

        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (positions[i * 3].equals(positions[i * 3 + 1]) && positions[i * 3 + 1].equals(positions[i * 3 + 2]) && !positions[i * 3].isEmpty()) {
                return true;
            }
            if (positions[i].equals(positions[i + 3]) && positions[i + 3].equals(positions[i + 6]) && !positions[i].isEmpty()) {
                return true;
            }
        }
        if (positions[0].equals(positions[4]) && positions[4].equals(positions[8]) && !positions[0].isEmpty()) {
            return true;
        }
        return positions[2].equals(positions[4]) && positions[4].equals(positions[6]) && !positions[2].isEmpty();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGame());
    }
}
