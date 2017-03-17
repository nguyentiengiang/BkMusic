package dev.ongteu.bkmusic.data.parser.online;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.HotSongItem;
import dev.ongteu.bkmusic.data.parser.api.BaseRetrofit;

import dev.ongteu.bkmusic.data.parser.api.IServices;
import dev.ongteu.bkmusic.fragment.HotMusicFragment;
import dev.ongteu.bkmusic.adapter.HotMusicRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TienGiang on 16/3/2017.
 */

public class GetHotSong {

    public static List<HotSongItem> HOT_SONG_ITEMS = new ArrayList<HotSongItem>();

    public GetHotSong(final Context context, int playlistCode, int page, final RecyclerView recyclerView, final HotMusicFragment.OnFragmentInteractionListener mListener){
        IServices service = BaseRetrofit.instance().create(IServices.class);
        Call<List<HotSongItem>> call = service.listHotSongs(playlistCode, page);

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(R.string.menuHotMusic)
                .content(R.string.waitting_text)
                .cancelable(false)
                .show();

        call.enqueue(new Callback<List<HotSongItem>>() {
            @Override
            public void onResponse(Call<List<HotSongItem>> call, Response<List<HotSongItem>> response) {
                HOT_SONG_ITEMS = response.body();
                HotMusicRecyclerViewAdapter hotMusicAdapter = new HotMusicRecyclerViewAdapter(GetHotSong.HOT_SONG_ITEMS, mListener, context);
                recyclerView.setAdapter(hotMusicAdapter);
                dialog.dismiss();
                Log.e("data changed", "DATA CHANGE");
            }

            @Override
            public void onFailure(Call<List<HotSongItem>> call, Throwable t) {

            }
        });
    }

}
