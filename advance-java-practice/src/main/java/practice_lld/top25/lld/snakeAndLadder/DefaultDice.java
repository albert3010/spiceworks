package practice_lld.top25.lld.snakeAndLadder;

import lombok.AllArgsConstructor;

import java.util.Random;

@AllArgsConstructor
public class DefaultDice implements Dice{
    private Random random;
    DefaultDice(){
        this.random = new Random();
    }

    @Override
    public int roll() {
        int roll = random.nextInt(6)+1;
//        System.out.println("roll dice : " + roll);
        return roll;
    }
}
