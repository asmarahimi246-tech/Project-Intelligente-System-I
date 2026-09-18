package Game;

import Network.Client;

        if (board[number] != 'X' && board[number] != 'O') {
            board[number] = 'X';
            System.out.println("Tussenstand");
            printBoard();
            client.sendCommand("move " + number);
        }
    }

    public void Move(int number) {
        if (board[number] != 'X') {
            board[number] = 'O';
            printBoard();
        }
    }

    public void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }
}
