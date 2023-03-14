package tictactoe;

import javax.swing.*;
import java.util.Arrays;
import java.util.stream.Stream;

class GameMenuBar extends JMenuBar {

    GameMenuBar() {
        JMenu menuGame = new JMenu("Game");
        menuGame.setName("MenuGame");
        JMenuItem menuHumanHuman = new JMenuItem("Human vs Human");
        JMenuItem menuHumanRobot = new JMenuItem("Human vs Robot");
        JMenuItem menuRobotHuman = new JMenuItem("Robot vs Human");
        JMenuItem menuRobotRobot = new JMenuItem("Robot vs Robot");
        JMenuItem menuExit = new JMenuItem("Exit");

        menuHumanHuman.setName("MenuHumanHuman");
        menuHumanRobot.setName("MenuHumanRobot");
        menuRobotHuman.setName("MenuRobotHuman");
        menuRobotRobot.setName("MenuRobotRobot");
        menuExit.setName("MenuExit");

        menuExit.addActionListener(a -> System.exit(0));

        menuGame.add(menuHumanHuman);
        menuGame.add(menuHumanRobot);
        menuGame.add(menuRobotHuman);
        menuGame.add(menuRobotRobot);
        menuGame.addSeparator();
        menuGame.add(menuExit);

        add(menuGame);
    }

    Stream<JMenuItem> getItems() {
        return Arrays.stream(this.getComponents())
                .filter(c -> c instanceof JMenu)
                .map(c -> (JMenu) c)
                .flatMap(c -> Arrays.stream(c.getMenuComponents())
                .filter(item -> item instanceof JMenuItem)
                .map(item -> (JMenuItem) item));
    }
}
