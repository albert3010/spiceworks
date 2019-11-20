package design.tamplate;

/**
 * Created by omprakash.yadav on 15/03/16.
 */
public class Game2 extends Game{
    @Override
    public void initialize(){
        System.out.println("init game2");
    }
    @Override
    void endPlay() {
        System.out.println("game2 Game Finished!");
    }
    @Override
    void startPlay() {
        System.out.println("game2 Game Started. Enjoy the game!");
    }

}
