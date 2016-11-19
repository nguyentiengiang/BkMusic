package dev.ongteu.bkmusic.utils;

import android.content.Context;

import com.github.mohammad.songig.common.SongigPlayer;

/**
 * Created by TienGiang on 2/10/2016.
 */

public class MySongigPlayer{

    private static SongigPlayer songigPlayer;

    public static void instance(final Context context){
        songigPlayer = SongigPlayer.getInstance(context);
    }

    public static SongigPlayer getPlayer() {
        return songigPlayer;
    }

}
