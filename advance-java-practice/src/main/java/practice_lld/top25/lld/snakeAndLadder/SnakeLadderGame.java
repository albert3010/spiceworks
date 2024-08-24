package practice_lld.top25.lld.snakeAndLadder;

import java.util.ArrayList;
import java.util.List;

public class SnakeLadderGame {
    Board board;
    Dice dice;
    List<Player> players;
    int currentPlayerIndex;

    public SnakeLadderGame(int boardSize, Dice dice, List<String> players) {
        this.board = new Board(boardSize);
        this.dice = dice;
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        for (String name : players){
            this.players.add(new Player(name));
        }
    }

    public void startGame() {
        while (!isGameOver()) {
//            System.out.println("running game .... ");
            int roll = dice.roll();

            Player player = players.get(currentPlayerIndex);
            int newPosition = player.getPosition() + roll;

            if (newPosition <= board.getBoardSize()) {
                int jumped = board.jump(newPosition);
                if(jumped!= newPosition){
                    System.out.println("jumped happen for player : " + player.getName());
                }
                player.setPosition(jumped);
                notifyAllPlayers(player.getName() + " rolled a dice " + roll + " and new position " + jumped);
            }
            nextPlayer();
        }

    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
//        System.out.println("Next player move : " + currentPlayerIndex);
    }

    private boolean isGameOver() {
        Player player = players.get(currentPlayerIndex);
        if(player.getPosition() == board.getBoardSize()) {
            System.out.println("player wins : " + player.getName());
            return true;
        }
        return false;
    }
    void notifyAllPlayers(String msg){
        for ( Player player : players){
            player.update(msg);
        }
    }
}
