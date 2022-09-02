package SnakeLadderGame;


import lombok.Getter;

@Getter
public class Player {
    private String name;
    private Integer position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    void updatePosition(int newPosition){
        this.position = newPosition;
    }
}
