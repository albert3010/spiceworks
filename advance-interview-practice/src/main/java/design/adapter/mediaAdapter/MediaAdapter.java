package design.adapter.mediaAdapter;

import design.adapter.advanceMediaPlayers.AdvanceMediaPlayer;
import design.adapter.advanceMediaPlayers.Mp4Player;
import design.adapter.advanceMediaPlayers.VlcPlayer;
import design.adapter.audioPlayer.MediaPlayer;

/**
 * Created by omprakash.yadav on 10/03/16.
 */
public class MediaAdapter implements MediaPlayer {

    AdvanceMediaPlayer advanceMediaPlayer;

    public MediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc")){
            advanceMediaPlayer = new VlcPlayer();
        }
        if(audioType.equalsIgnoreCase("mp4")){
            advanceMediaPlayer = new Mp4Player();

        }
    }

    @Override
    public void play(String audioType , String fileType){
       if(audioType.equalsIgnoreCase("vlc")){
           advanceMediaPlayer.playVlc(fileType);
       }
        if(audioType.equalsIgnoreCase("mp4")){
            advanceMediaPlayer.playMp4(fileType);
        }
    }

}
