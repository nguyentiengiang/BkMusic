package dev.ongteu.bkmusic.data.table;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.ongteu.bkmusic.data.model.AlbumItem;
import dev.ongteu.bkmusic.data.parser.API;
import dev.ongteu.bkmusic.data.parser.ParserPopularAlbum;
import dev.ongteu.bkmusic.utils.Constant;

/**
 * Created by TienGiang on 27/9/2016.
 */

public class PopularAlbumTable {
    private static ParserPopularAlbum getPopularAlbum;

    public static final List<AlbumItem> ITEMS = new ArrayList<AlbumItem>();
    public static final Map<Integer, AlbumItem> ITEM_MAP = new HashMap<Integer, AlbumItem>();

    public PopularAlbumTable(final Context context, RecyclerView.Adapter adapter, int playlistId, int pageNumber) {
        API.GetData getDataPopularAlbum = new API.GetData(context);
        getPopularAlbum = new ParserPopularAlbum(){
            @Override
            public void success(String json) {
                super.success(json);
                int index = 1;
                ITEMS.clear();
                ITEM_MAP.clear();
                for (AlbumItem albumItem : listPopularAlbum) {
                    ITEMS.add(albumItem);
                    ITEM_MAP.put(index, albumItem);
                    index++;
                }
            }
        };
        String url = Constant.URL_GET_POPULAR_ALBUM + String.valueOf(playlistId) + "/" + pageNumber;
        Log.e("STRING POP AL", url);
        getDataPopularAlbum.execute(API.getPackedParameters(url, null, getPopularAlbum, adapter));
    }
}
