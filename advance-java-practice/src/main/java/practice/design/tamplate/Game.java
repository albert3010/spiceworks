package practice.design.tamplate;

/**
 * Created by omprakash.yadav on 15/03/16.
 */
public abstract class Game {

    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    //template method
    public final void play(){
        initialize();
        startPlay();
        endPlay();
    }
}
