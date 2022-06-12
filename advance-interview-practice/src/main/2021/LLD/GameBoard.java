import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameBoard {

    int[][] board;
    int gameId;
    HashMap<Integer, Position> playerPositions;
    HashMap<Integer, Integer> snakes;
    HashMap<Integer, Integer> ladders;

    public GameBoard(int n, List<Snake> snakes, List<Ladder> ladders) {
        this.board = new int[n][n];
        this.gameId = Constants.getGameID();
        updateBoardNumber(board, n);
        updateBoardSnake(snakes);
        updateBoardLadder(ladders);

        this.playerPositions = new HashMap<>();
    }

    private void updateBoardNumber(int[][] board, int n) {
        int counter = 1;
        int row = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (row % 2 == 0) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = counter;
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    board[i][j] = counter;
                }
            }
            row++;
            counter++;
        }
    }

    Position updatePlayerPosition(int playerId, Position position) {
        Position newPosition = checkNewPosition(position);
        playerPositions.put(playerId, newPosition);
        return newPosition;
    }

    private Position checkNewPosition(Position position) {
        int n = board.length;
        Integer pos = snakes.get(position.x * n + position.y);
      return null;
    }


    boolean checkForWinner(int playerId) {
        Position position = playerPositions.get(playerId);
        int n = board.length;
        if (position.x == n && position.y == n) {
            return true;
        }
        return false;
    }


    private void updateBoardSnake(List<Snake> paths) {
        for (Snake snake : paths) {
            snakes.put(snake.head, snake.tail);
        }
    }

    private void updateBoardLadder(List<Ladder> paths) {
        for (Ladder ladder : paths) {
            snakes.put(ladder.start, ladder.end);
        }
    }


}
