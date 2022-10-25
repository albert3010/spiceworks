package practice.design.adapter;

import practice.design.adapter.mediaAdapter.AudioPlayer;

/**
 * Created by omprakash.yadav on 10/03/16.
 */
public class AdapterPatternPlayer {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
