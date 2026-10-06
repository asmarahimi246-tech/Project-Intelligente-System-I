package ui;

import framework.GameType;
import games.Game;
import network.Client;
import players.Player;
import players.localPlayer;

import java.security.PublicKey;

public class GameMenu {
    private UI ui = new UI();
    private MainMenu mainMenu = new MainMenu();\

    public void displayMenu() {
        System.out.println("== Game Menu ==\n");
        System.out.println("1. Choose a game");
        System.out.println("2. Choose a mode");
    }

    public void menuChoice() {
        int input = ui.getMenuChoice();

        switch (input) {
            case 1:
                displayGameList();
            case 2:
                displayModeList();
            default:
                throw new RuntimeException("Invalid input");
        }
    }

    public void displayGameList() {
        System.out.println("== Game List ==\n");
        System.out.println("1. Tic-tac-toe");
    }

    public GameType gameChoice() {
        int input = ui.getMenuChoice();

        switch (input) {
            case 1:
                return GameType.TIC_TAC_TOE;
            default:
                throw new RuntimeException("Invalid input");
        }
    }

    public void displayModeList() {
        System.out.println("== Mode List ==\n");
        System.out.println("1. Local");
        System.out.println("2. Remote");
        System.out.println("3. Vs. AI");
    }

    public Player getModeChoice() {
        int input = ui.getMenuChoice();

        switch (input) {
            case 1:
                return localPlayer();
            case 2:
                return
        }
    }
}

/*
> choose game type & mode
> tictactoe/... --> GameType: chooseGame()
> local/bot/online --> GameMode: chooseMode()
*/