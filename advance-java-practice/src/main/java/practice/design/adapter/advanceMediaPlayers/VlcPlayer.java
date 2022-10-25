package practice.design.adapter.advanceMediaPlayers;

/**
 * Created by omprakash.yadav on 10/03/16.
 */
public class VlcPlayer implements AdvanceMediaPlayer {

    @Override
    public void playVlc(String filename){
        System.out.println("Playing Vlc player");
    }
    @Override
    public void playMp4(String filename){
       // System.out.println("Play mp4");
    }
}
