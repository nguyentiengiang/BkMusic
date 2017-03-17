package dev.ongteu.bkmusic.utils;

import android.content.Context;

import com.github.mohammad.songig.common.PlayerException;
import com.github.mohammad.songig.common.SongigPlayer;
import com.github.mohammad.songig.model.Song;

import java.util.List;

/**
 * Created by TienGiang on 2/10/2016.
 */

public class MySongigPlayer{

    public static void changeNowPlaying(Context context, List<Song> playList){
        if (SongigPlayer.getInstance(context).isPlaying()){
            SongigPlayer.getInstance(context).stop();
            SongigPlayer.getInstance(context).getPlayList().clear();
            SongigPlayer.getInstance(context).removeAll();
        }
        SongigPlayer.getInstance(context).getPlayList().addAll(playList);
    }

    public static void playSong(Context context, int position){
        try {
            SongigPlayer.getInstance(context).play(position);
        } catch (PlayerException e) {
            e.printStackTrace();
        }
    }

}
