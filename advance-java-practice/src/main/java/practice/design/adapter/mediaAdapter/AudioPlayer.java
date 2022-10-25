package practice.design.adapter.mediaAdapter;

import practice.design.adapter.audioPlayer.MediaPlayer;

/**
 * Created by omprakash.yadav on 10/03/16.
 */
public class AudioPlayer implements MediaPlayer{

    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileType){

        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("playing mp3");
        }
        if(audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")){
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileType);
        }
    }
}
