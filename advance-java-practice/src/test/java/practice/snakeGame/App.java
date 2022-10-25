package practice.snakeGame;

import java.util.Queue;

public class App {

    public static void main(String[] args) {
        SnakeConfig snakeConfig = new SnakeConfig(20, 3, 5, 1);
        SnakeGameImp snakeGame = new SnakeGameImp(snakeConfig);
        System.out.println(snakeGame.isGameOver());
        snakeGame.moveSnake(Direction.RIGHT);
        snakeGame.moveSnake(Direction.RIGHT);
        print(snakeGame.getSnakePath());
//        practice.snakeGame.moveSnake(Direction.RIGHT);
//        print(practice.snakeGame.getSnakePath());
//        practice.snakeGame.moveSnake(Direction.RIGHT);
//        print(practice.snakeGame.getSnakePath());
//        practice.snakeGame.moveSnake(Direction.RIGHT);
//        print(practice.snakeGame.getSnakePath());
//        practice.snakeGame.moveSnake(Direction.RIGHT);
//        print(practice.snakeGame.getSnakePath());
//        practice.snakeGame.moveSnake(Direction.RIGHT);
//        print(practice.snakeGame.getSnakePath());
//        practice.snakeGame.moveSnake(Direction.RIGHT);
//        print(practice.snakeGame.getSnakePath());
//        practice.snakeGame.moveSnake(Direction.RIGHT);
//        print(practice.snakeGame.getSnakePath());
//        practice.snakeGame.moveSnake(Direction.RIGHT);
//        print(practice.snakeGame.getSnakePath());

    }
    public static void print(Queue<Node> queue){
        int a =0;
        queue.stream().forEach(e -> System.out.print(e.toString() + " ,  "));
    }
}
