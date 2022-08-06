package practice.snakeGame;

import java.util.*;

public class SnakeGameImp implements SnakeGame {
    private int boardSize;
    private Set<String> visited;
    private Queue<Node> queue;
    SnakeConfig snakeConfig;
    Node currentPos;
    int stepsCount;
    boolean isGameOver;

    public SnakeGameImp(SnakeConfig snakeConfig) {
        this.boardSize = snakeConfig.getBoardSize();
        this.visited = new HashSet<>();
        this.queue = new LinkedList<>();
        this.snakeConfig = snakeConfig;
        initz();
    }

    public Queue<Node> getSnakePath(){
        return queue;
    }

    private void initz() {
        int size = snakeConfig.getInitialSize();
        stepsCount = 0;
        isGameOver = false;
        for (int i = 0; i < size; i++) {
            int row = 0;
            Node current = new Node(row, i);
            queue.add(current);
            visited.add(current.toString());
            if (i == size - 1) {
                currentPos = current;
            }
        }
    }

    public void moveSnake(Direction direction) {
        int xPos = currentPos.x;
        int yPos = currentPos.y;
        int newXPos = xPos;
        int newYPos = yPos;
        stepsCount++;
        switch (direction) {
            case UP:
                newXPos = (xPos - 1 + boardSize) % boardSize;
                break;
            case DOWN:
                newXPos = (xPos + 1) % boardSize;
                break;
            case LEFT:
                newYPos = (yPos - 1 + boardSize) % boardSize;
                break;
            case RIGHT:
                newYPos = (yPos + 1) % boardSize;
                break;
        }
        String key = newXPos + "#" + newYPos;
        if (stepsCount == snakeConfig.getSteps()){
            if(visited.contains(key)){
                isGameOver = true;
                return;
            }
            stepsCount =0;
        }else {
            Node node = queue.poll();
            visited.remove(node.toString());
            if(visited.contains(key)){
                isGameOver = true;
                return;
            }
        }
        Node node = new Node(newXPos, newYPos);
        queue.add(node);
        visited.add(node.toString());
        currentPos = node;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
