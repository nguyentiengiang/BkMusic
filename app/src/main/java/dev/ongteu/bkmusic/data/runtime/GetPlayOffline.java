package dev.ongteu.bkmusic.data.runtime;

import android.content.Context;
import android.support.v4.view.ViewPager;

import dev.ongteu.bkmusic.data.dao.PlaylistDAO;
import dev.ongteu.bkmusic.data.dao.SongDAO;

/**
 * Created by TienGiang on 16/3/2017.
 */

public class GetPlayOffline {

    public GetPlayOffline(final Context context, String keyMp3, final ViewPager viewPager) {
        SongDAO songDAO = new SongDAO(context);
        songDAO.playWithFirstSong(keyMp3, viewPager);
    }

    public GetPlayOffline(final Context context, long playlistId, final ViewPager viewPager) {
        PlaylistDAO playlistDAO = new PlaylistDAO(context);
        playlistDAO.playMusicByPlaylistId(playlistId, viewPager);
    }

}
