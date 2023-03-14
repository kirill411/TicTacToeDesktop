package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerButton extends JButton {
    static List<PlayerButton> buttons =
            List.of(
                    new PlayerButton("ButtonPlayer1", Player.players[0], 20, 5),
                    new PlayerButton("ButtonPlayer2", Player.players[1], 280, 5));

    Player player;
    PlayerButton(String name, Player player, int x, int y) {
        super(player.name);
        setName(name);
        setBounds(x, y, 100, 20);
        setBackground(Color.white);
        setFocusable(false);
        this.player = player;
        this.addActionListener(a -> setText(player.switchName()));
    }

    void changePlayerName(String name) {
        setText(player.name = name);
    }

}
