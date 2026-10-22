package app.games.tictactoe.model;

// Potentially replace the following with import javax.swing.* and java.awt.*
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {

    private JButton[] buttons = new JButton[9];
    private JLabel messageLabel = new JLabel("", JLabel.CENTER);

    public Board() {
        setLayout(new BorderLayout());

        JPanel squares = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setBackground(Color.darkGray);
            buttons[i].setForeground(Color.white);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 120));

            squares.add(buttons[i]);
        }

        add(messageLabel, BorderLayout.NORTH);
        add(squares, BorderLayout.CENTER);
    }

}
