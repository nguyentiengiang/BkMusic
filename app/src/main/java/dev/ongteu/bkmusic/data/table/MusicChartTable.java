package dev.ongteu.bkmusic.data.table;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.ongteu.bkmusic.data.model.MusicChartItem;
import dev.ongteu.bkmusic.data.parser.API;
import dev.ongteu.bkmusic.data.parser.ParserMusicChart;
import dev.ongteu.bkmusic.utils.Constant;

/**
 * Created by TienGiang on 27/9/2016.
 */

public class MusicChartTable {
    private static ParserMusicChart getChart;

    public static final List<MusicChartItem.SongChart> ITEMS = new ArrayList<MusicChartItem.SongChart>();
    public static final Map<Integer, MusicChartItem.SongChart> ITEM_MAP = new HashMap<Integer, MusicChartItem.SongChart>();
    public static String chartPlayURL = "";

    public MusicChartTable(final Context context, RecyclerView.Adapter adapter, int playlistId) {
        API.GetData getDataPopularAlbum = new API.GetData(context);
        getChart = new ParserMusicChart(){
            @Override
            public void success(String json) {
                super.success(json);
                int index = 1;
                ITEMS.clear();
                ITEM_MAP.clear();
                chartPlayURL = playAllUrl;
                for (MusicChartItem.SongChart songItem : listSongOnChart) {
                    ITEMS.add(songItem);
                    ITEM_MAP.put(index, songItem);
                    index++;
                }
            }
        };
        String url = Constant.URL_GET_CHART + String.valueOf(playlistId);
        getDataPopularAlbum.execute(API.getPackedParameters(url, null, getChart, adapter));
    }
}
