package SnakeLadderGame;

import java.util.Random;

public class NormalDice extends Dice {
    int max;

    public NormalDice(int max) {
        this.max = max;
    }

    public int getRandom() {
        Random random = new Random();
        return random.nextInt(max) + 1;
    }
}
