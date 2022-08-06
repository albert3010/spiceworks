package practice.snakeGame;

import lombok.Getter;

@Getter
public class SnakeConfig {
    private int boardSize;
    private int initialSize;
    private int steps;
    private int grow;

    public SnakeConfig(int boardSize, int initialSize, int steps, int grow) {
        this.boardSize = boardSize;
        this.initialSize = initialSize;
        this.steps = steps;
        this.grow = grow;
    }
}
