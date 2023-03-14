package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameButtonPanel extends JPanel {
    static List<JButton> buttons = new ArrayList<>();

    private static final int[][] winCombinations =
            {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    static {
        for (int i = 3; i > 0; i--) {
            for (char c = 'A'; c <= 'C' ; c++) {
                var button = new JButton(" ");
                button.setName("Button" + c + i);
                button.setFocusable(false);
                button.setEnabled(false);
                button.setFont(new Font(Font.MONOSPACED, Font.BOLD,  50));
                button.setBackground(Color.orange);
                buttons.add(button);
            }
        }
    }

    GameButtonPanel() {
        super(new GridLayout(3, 3, 5, 5));
        setBounds(5, 30, 390, 390);
        buttons.forEach(this::add);
    }

    static GameState evaluate() {
        for (int[] i : winCombinations) {
            if (buttons.get(i[0]).getText().isBlank()) {
                continue;
            }
            char one = buttons.get(i[0]).getText().charAt(0);
            char two = buttons.get(i[1]).getText().charAt(0);
            char three = buttons.get(i[2]).getText().charAt(0);
            if (one == two && two == three) {
                return one == 'X' ? GameState.X_WINS : GameState.O_WINS;

            }
        }

        if (buttons.stream().noneMatch(b -> b.getText().isBlank())) {
            return GameState.DRAW;
        }
        return GameState.IN_PROGRESS;
    }
}
