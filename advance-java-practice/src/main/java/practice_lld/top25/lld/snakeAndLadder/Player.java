package practice_lld.top25.lld.snakeAndLadder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player implements Observer{
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position =0;
    }

    @Override
    public void update(String message) {
        System.out.println(name +" notified : "+ message);
    }
}
