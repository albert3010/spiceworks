package tic_tac_toe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class Game {

    Cell[][] board = new Cell[3][3];
    Player[] players;

    public Game(int playersCount) {
        initialiseBoard();
        Player[] players = new Player[playersCount];
        players[0] = new Player(1, "O");
        players[1] = new Player(2, "X");
    }

    public void initialiseBoard() {
        int counter = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Cell(counter++);
            }
        }
    }

    void startGame() throws IOException {
        String turnId = "O";

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean gameEnd = false;
        while (!gameEnd) {
            boolean allFull = displayBoard();
            if (allFull) {
                System.out.println("Game Draw");
                return;
            }
            System.out.println("Player turn " + turnId);
            int val = Integer.parseInt(in.readLine());
            int row = val / 3;
            int col = val % 3;
            board[row][col].setTurnId(turnId);
            if (checkPlayerWins(board, turnId)) {
                System.out.println("Player wins " + turnId);
                gameEnd = true;
            }
            if (turnId == "O")
                turnId = "X";
            else if (turnId == "X")
                turnId = "O";
        }

    }

    public boolean checkPlayerWins(Cell[][] board, String id) {
        for (int i = 0; i < 3; i++) {
            boolean win = true;
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getTurnId() != id) {
                    win = false;
                }
            }
            if (win) return win;
        }
        for (int i = 0; i < 3; i++) {
            boolean win = true;
            for (int j = 0; j < 3; j++) {
                if (board[j][i].getTurnId() != id) {
                    win = false;
                }
            }
            if (win) return win;
        }
        boolean win = true;
        for (int i = 0; i < 3; i++) {
            if (board[i][i].getTurnId() != id) {
                win = false;
            }
        }
        if (win) return win;
        win = true;
        int j = 0;
        for (int i = 2; i >= 0; i--) {
            if (board[i][j++].getTurnId() != id) {
                win = false;
            }
        }
        return win;
    }

    boolean displayBoard() {
        boolean allFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getTurnId() == "_") {
                    allFull = false;
                }
                System.out.print(board[i][j].getTurnId() + "  ");
            }
            System.out.println();
        }
        return allFull;
    }
}
