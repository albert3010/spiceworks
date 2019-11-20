package design.adapter.audioPlayer;

/**
 * Created by omprakash.yadav on 10/03/16.
 */
public class AudioPlayer implements MediaPlayer {

    @Override
    public void play(String audiotype , String filename){
        System.out.println("audio type");
    }
}
