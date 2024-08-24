package practice_lld.top25.lld.snakeAndLadder;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Board {
    private int boardSize;
    private Map<Integer, Integer> snake;
    private Map<Integer, Integer> ladder;

    Board(int size){
        this.boardSize =  size;
        snake = new HashMap<>();
        ladder = new HashMap<>();
        initialiseBoard();
    }

    private void initialiseBoard(){
        snake.put(50, 10);
        snake.put(40, 15);
        snake.put(30, 8);
        snake.put(20, 5);
        snake.put(10, 1);
        snake.put(99, 10);
        snake.put(80, 50);

        ladder.put(11, 33);
        ladder.put(22, 43);
        ladder.put(66, 83);
        ladder.put(76, 93);
        ladder.put(44, 73);
    }

    public int jump(int position){
        Integer jumpTo = snake.get(position);
        if(jumpTo!=null) return jumpTo;

        jumpTo = ladder.get(position);
        if(jumpTo!=null) return jumpTo;
        return position;
    }
}
