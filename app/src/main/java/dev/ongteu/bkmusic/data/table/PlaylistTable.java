package dev.ongteu.bkmusic.data.table;

import android.content.Context;
import android.util.Log;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.ongteu.bkmusic.data.model.SongItem;
import dev.ongteu.bkmusic.data.parser.API;
import dev.ongteu.bkmusic.data.parser.ParserSongs;
import dev.ongteu.bkmusic.utils.Constant;

/**
 * Created by TienGiang on 26/9/2016.
 */

public class PlaylistTable {

    private static ParserSongs getSongs;

    public static final List<SongItem> ITEMS = new ArrayList<SongItem>();
    public static final Map<Integer, SongItem> ITEM_MAP = new HashMap<Integer, SongItem>();

    public PlaylistTable(final Context context, BaseAdapter adapterView, String urlSong) {
        API.GetSongData getDataSongs = new API.GetSongData(context);
        getSongs = new ParserSongs(){
            @Override
            public void success(String json) {
                super.success(json);
                int index = 1;
                ITEMS.clear();
                ITEM_MAP.clear();
                for (SongItem songItem : listSongs) {
                    ITEMS.add(songItem);
                    ITEM_MAP.put(index, songItem);
                    Log.e("SONG URL >>>>>", songItem.getMp3Url());
                    index++;
                }
            }
        };
        String urlParseSong = Constant.URL_GET_SONGS + urlSong;
        getDataSongs.execute(API.getSongPackedParameters(urlParseSong, null, getSongs));
    }
}
