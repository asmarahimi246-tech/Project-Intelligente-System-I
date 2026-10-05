package UI;
import javax.swing.*;
import java.awt.*;

public class UI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null); // Opens the window in the center
        frame.setLayout(new GridLayout(3, 3));

        for(int i=0;i<9;i++) {
            JButton button = new JButton();
            button.setBackground(Color.darkGray);
            button.setForeground(Color.white); // Text color
            button.setFont(new Font("Arial", Font.BOLD, 120));
            button.addActionListener(event -> {
                button.setText("X");
                button.setEnabled(false);
            });

            frame.add(button);
        }

        frame.setVisible(true);
    }
}
