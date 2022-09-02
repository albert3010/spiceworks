package SnakeLadderGame;

import lombok.Getter;

@Getter
public class Config {
    private int minPlayer;
    private int maxPlayer;
    private int firstRollVal;
    private int startPosition;
    private int winningPosition;
    private int diceMax;

    public Config(int minPlayer, int maxPlayer, int firstRollVal, int startPosition, int winningPosition, int diceMax) {
        this.minPlayer = minPlayer;
        this.maxPlayer = maxPlayer;
        this.firstRollVal = firstRollVal;
        this.startPosition = startPosition;
        this.winningPosition = winningPosition;
        this.diceMax = diceMax;
    }
}
