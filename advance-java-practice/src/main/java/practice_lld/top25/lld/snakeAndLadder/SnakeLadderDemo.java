package practice_lld.top25.lld.snakeAndLadder;

import java.util.Arrays;
import java.util.List;


public class SnakeLadderDemo {
    public static void main(String[] args) {
        Dice dice = new DefaultDice();
        List<String> players = Arrays.asList("omp", "harry", "tom");
        SnakeLadderGame snakeLadderGame = new SnakeLadderGame(100, dice, players);

        snakeLadderGame.startGame();
    }

}
