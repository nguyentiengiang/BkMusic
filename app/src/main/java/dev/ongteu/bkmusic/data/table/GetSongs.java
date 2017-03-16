package dev.ongteu.bkmusic.data.table;

import android.content.Context;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.mohammad.songig.common.PlayMode;
import com.github.mohammad.songig.common.PlayerException;
import com.github.mohammad.songig.common.SongigPlayer;
import com.github.mohammad.songig.model.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.ongteu.bkmusic.data.model.SongItem;
import dev.ongteu.bkmusic.data.parser.API;
import dev.ongteu.bkmusic.data.parser.ParserSongs;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.MySongigPlayer;

/**
 * Created by TienGiang on 2/10/2016.
 */

public class GetSongs {
    private static ParserSongs getSongs;

    public static final List<Song> SONGIG_ITEMS = new ArrayList<Song>();
    public static final List<SongItem> ITEMS = new ArrayList<SongItem>();
    public static final Map<Integer, SongItem> ITEM_MAP = new HashMap<Integer, SongItem>();

//    public GetSongs(final Context context, String urlSong) {
    public GetSongs(final Context context, String urlSong, RecyclerView.Adapter adapterView) {
        API.GetSongData getDataSongs = new API.GetSongData(context);
        getSongs = new ParserSongs(){
            @Override
            public void success(String json) {
                super.success(json);
                int index = 1;
                SONGIG_ITEMS.clear();
                ITEMS.clear();ITEM_MAP.clear();
                for (SongItem songItem : listSongs) {
                    Song song = new Song(index, songItem.getSongName(),
                            songItem.getSinger(), "", songItem.getMp3Url(), songItem.getBgimage(), "", PlayMode.STREAM);
                    SONGIG_ITEMS.add(song);
                    ITEMS.add(songItem);
                    ITEM_MAP.put(index, songItem);
                    index++;
                }
                MySongigPlayer.changeNowPlaying(context, SONGIG_ITEMS);
            }
        };
        String urlParseSong = Constant.URL_GET_SONGS + urlSong;
        Log.e("URL SONG =>>>>", urlParseSong);
        getDataSongs.execute(API.getSongPackedParameters(urlParseSong, null, getSongs, adapterView));
    }
}
