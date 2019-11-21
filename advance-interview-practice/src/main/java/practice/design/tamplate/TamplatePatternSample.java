package practice.design.tamplate;

import org.junit.Test;

/**
 * Created by omprakash.yadav on 15/03/16.
 */
public class TamplatePatternSample {

    @Test
    public  void test() {

        Game game1 = new Game1();
        Game game2 = new Game2();

        game1.play();
        game2.play();
    }
}