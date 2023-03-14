package tictactoe;

enum GameState {
    STOPPED("Game is not started"),
    IN_PROGRESS("Game in progress"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    DRAW("Draw");

    final String msg;

    GameState(String msg) {
        this.msg = msg;
    }
}
