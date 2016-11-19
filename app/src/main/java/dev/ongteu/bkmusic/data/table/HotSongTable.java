package dev.ongteu.bkmusic.data.table;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.ongteu.bkmusic.data.model.HotSongItem;
import dev.ongteu.bkmusic.data.parser.API;
import dev.ongteu.bkmusic.data.parser.ParserHotMusic;
import dev.ongteu.bkmusic.utils.Constant;

/**
 * Created by TienGiang on 25/9/2016.
 */

public class HotSongTable {

    private static ParserHotMusic getHotMusic;

    public static final List<HotSongItem> ITEMS = new ArrayList<HotSongItem>();
    public static final Map<Integer, HotSongItem> ITEM_MAP = new HashMap<Integer, HotSongItem>();

    public HotSongTable(final Context context, RecyclerView.Adapter adapterView, int playlistId, int pageNumber) {
        API.GetData getDataHotMusic = new API.GetData(context);
        getHotMusic = new ParserHotMusic(){
            @Override
            public void success(String json) {
                super.success(json);
                int index = 1;
                ITEMS.clear();
                ITEM_MAP.clear();
                for (HotSongItem songItem : listHotSong) {
                    ITEMS.add(songItem);
                    ITEM_MAP.put(index, songItem);
                    index++;
                }
            }
        };
        String url = Constant.URL_GET_NHACHOT + String.valueOf(playlistId) + "/" + String.valueOf(pageNumber);
        getDataHotMusic.execute(API.getPackedParameters(url, null, getHotMusic, adapterView));
    }


}
