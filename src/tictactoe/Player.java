package tictactoe;

public class Player {
    static Player[] players = {new Player("Human", 'X'), new Player("Human", 'O')};
    char symbol;
    String name;

    Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    String switchName() {
        return name = "Human".equals(name) ? "Robot" : "Human";
    }
}
