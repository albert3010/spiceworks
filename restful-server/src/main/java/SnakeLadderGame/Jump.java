package SnakeLadderGame;

import lombok.Getter;

@Getter
public class Jump {
    private int start;
    private int end;

    public Jump(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
