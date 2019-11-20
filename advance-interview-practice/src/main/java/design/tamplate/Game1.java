package design.tamplate;

/**
 * Created by omprakash.yadav on 15/03/16.
 */
public class Game1 extends Game {

    @Override
    public void initialize(){
        System.out.println("init game1");
    }
    @Override
    void endPlay() {
        System.out.println("game1 Game Finished!");
    }
    @Override
    void startPlay() {
        System.out.println("game1 Game Started. Enjoy the game!");
    }

}
