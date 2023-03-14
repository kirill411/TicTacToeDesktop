package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.stream.IntStream;

public class TicTacToe extends JFrame {
    GameState state = GameState.STOPPED;
    JLabel labelStatus;
    JButton startResetButton;
    Player playersTurn = Player.players[0];

    public TicTacToe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setLocationRelativeTo(null);
        setLayout(null);
        setSize(400,490);
        setResizable(false);

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch(Exception e){
            e.printStackTrace();
        }

        GameMenuBar menuBar = new GameMenuBar();
        menuBar.getItems().forEach(item -> item.addActionListener(a -> setPlayers(item.getText())));
        setJMenuBar(menuBar);

        GameButtonPanel gameButtonPanel = new GameButtonPanel();
        GameButtonPanel.buttons.forEach(b -> b.addActionListener(a -> {
            makeMove(b);
            gameLoop();
        }));
        add(gameButtonPanel);

        startResetButton = getStartResetButton();
        startResetButton.addActionListener(a -> startReset());
        add(startResetButton);

        labelStatus = getLabelStatus();
        add(labelStatus);

        PlayerButton.buttons.forEach(this::add);
        setVisible(true);
    }

    void makeMove(JButton button) {
        if (state != GameState.IN_PROGRESS || !button.getText().isBlank()) {
            return;
        }

        button.setText(playersTurn.symbol + "");
        state = GameButtonPanel.evaluate();

        if (state == GameState.IN_PROGRESS) {
            playersTurn = playersTurn == Player.players[0] ? Player.players[1] : Player.players[0];
        }
        labelStatus.setText(getMsg());
    }

    void gameLoop() {
        if (state == GameState.IN_PROGRESS && playersTurn.name.equals("Robot")) {
            robotMove();
        }
    }

    void robotMove() {
        int n;

        do {
            n = new Random().nextInt(GameButtonPanel.buttons.size());
        } while (!GameButtonPanel.buttons.get(n).getText().isBlank());

        GameButtonPanel.buttons.get(n).doClick();
    }

    void startReset() {
        GameButtonPanel.buttons.forEach(button -> button.setText(" "));

        if ("Start".equals(startResetButton.getText())) {
            GameButtonPanel.buttons.forEach(button -> button.setEnabled(true));
            PlayerButton.buttons.forEach(button -> button.setEnabled(false));
            state = GameState.IN_PROGRESS;
            startResetButton.setText("Reset");
            gameLoop();
        } else {
            GameButtonPanel.buttons.forEach(button -> button.setEnabled(false));
            PlayerButton.buttons.forEach(button -> {
                button.changePlayerName("Human");
                button.setEnabled(true);
            });

            state = GameState.STOPPED;
            playersTurn = Player.players[0];
            startResetButton.setText("Start");
        }

        labelStatus.setText(getMsg());
    }

    void setPlayers(String s) {
        String[] arr = s.split(" vs ");
        IntStream.range(0, arr.length).forEach(i -> {
            PlayerButton.buttons.get(i).changePlayerName(arr[i]);
            PlayerButton.buttons.get(i).setEnabled(false);
        });

        GameButtonPanel.buttons.forEach(button -> {
            button.setText(" ");
            button.setEnabled(true);
        });

        state = GameState.IN_PROGRESS;
        startResetButton.setText("Reset");
        labelStatus.setText(getMsg());
        gameLoop();
    }

    String getMsg() {
        return switch (state) {
            case STOPPED -> "Game is not started";
            case IN_PROGRESS -> String.format("The turn of %s Player (%c)", playersTurn.name, playersTurn.symbol);
            case X_WINS, O_WINS -> String.format("The %s Player (%c) wins", playersTurn.name, playersTurn.symbol);
            case DRAW -> "Draw";
        };
    }

    static JButton getStartResetButton() {
        var button = new JButton("Start");
        button.setBounds(150, 5, 100,20);
        button.setFont(new Font(Font.MONOSPACED, Font.BOLD,  12));
        button.setName("ButtonStartReset");
        button.setFocusable(false);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        return button;
    }

    static JLabel getLabelStatus() {
        var label = new JLabel();
        label.setBounds(6, 420, 250,20);
        label.setName("LabelStatus");
        label.setText(GameState.STOPPED.msg);
        return label;
    }
}