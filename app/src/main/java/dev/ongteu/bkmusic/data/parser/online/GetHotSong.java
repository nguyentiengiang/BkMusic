package dev.ongteu.bkmusic.data.parser.online;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.mohammad.songig.common.PlayMode;
import com.github.mohammad.songig.model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.data.model.HotSongItem;
import dev.ongteu.bkmusic.data.model.SongItem;
import dev.ongteu.bkmusic.data.parser.api.BaseRetrofit;

import dev.ongteu.bkmusic.data.parser.api.IServices;
import dev.ongteu.bkmusic.utils.MySongigPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TienGiang on 16/3/2017.
 */

public class GetHotSong {

    public static List<HotSongItem> HOT_SONG_ITEMS = new ArrayList<HotSongItem>();

    public GetHotSong(final Context context, int playlistCode, int page){
//    public GetHotSong(final Context context, int playlistCode, int page, final RecyclerView.Adapter adapterView){
        IServices service = BaseRetrofit.instance().create(IServices.class);
        Call<List<HotSongItem>> call = service.listHotSongs(playlistCode, page);

//        try {
//            Response<List<HotSongItem>> response = call.execute();
//            HOT_SONG_ITEMS = response.body();
//            adapterView.notifyDataSetChanged();
//            Log.e("data changed", "DATA CHANGE");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        call.enqueue(new Callback<List<HotSongItem>>() {
            @Override
            public void onResponse(Call<List<HotSongItem>> call, Response<List<HotSongItem>> response) {
                HOT_SONG_ITEMS = response.body();
//                if (adapterView != null) {
//                    adapterView.notifyDataSetChanged();
//                }
                Log.e("data changed", "DATA CHANGE");
            }

            @Override
            public void onFailure(Call<List<HotSongItem>> call, Throwable t) {

            }
        });
    }

}
