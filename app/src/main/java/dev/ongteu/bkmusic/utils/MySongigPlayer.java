package dev.ongteu.bkmusic.utils;

import android.content.Context;
import android.util.Log;

import com.github.mohammad.songig.common.PlayerException;
import com.github.mohammad.songig.common.SongigPlayer;
import com.github.mohammad.songig.model.Song;

import java.util.List;

/**
 * Created by TienGiang on 2/10/2016.
 */

public class MySongigPlayer{

    SongigPlayer songigPlayer;
    public MySongigPlayer(final Context context) {
        songigPlayer = SongigPlayer.getInstance(context);
    }

    public SongigPlayer instance(){
        return this.songigPlayer;
    }

    public void changeNowPlaying(List<Song> playList){
        if (songigPlayer.isPlaying()){
            songigPlayer.stop();
        }
        songigPlayer.removeAll();
        songigPlayer.getPlayList().clear();
        songigPlayer.getPlayList().addAll(playList);
    }

    public void playSong(int position){
        for (Song s : this.songigPlayer.getPlayList()) {
            Log.e("Song item", s.getName());
        }
        Log.e("Crr song nam", String.valueOf(this.songigPlayer.getCurrentSong().getName()));
        Log.e("Crr song idx", String.valueOf(this.songigPlayer.getCurrentSongIndex()));
        Log.e("Last song idx", String.valueOf(this.songigPlayer.getLastSongIndex()));
        try {
             this.songigPlayer.play(position);
        } catch (PlayerException e) {
            e.printStackTrace();
        }
    }

}
