package dev.ongteu.bkmusic.data.parser.online;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mohammad.songig.common.PlayMode;
import com.github.mohammad.songig.model.Song;

import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.adapter.PlayerOnlineAdapter;
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

public class GetPlayOnline {

    public static List<Song> SONGIG_ITEMS = new ArrayList<Song>();
    public static List<SongItem> ITEMS = new ArrayList<SongItem>();

    public GetPlayOnline(final Context context, String songUrl, final ViewPager viewPager){
        IServices service = BaseRetrofit.instance().create(IServices.class);
        Call<List<SongItem>> call = service.listSongs(songUrl);

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(R.string.player_online)
                .content(R.string.waitting_text)
                .cancelable(false)
                .show();

        call.enqueue(new Callback<List<SongItem>>() {
            @Override
            public void onResponse(Call<List<SongItem>> call, Response<List<SongItem>> response) {
                ITEMS = response.body();
                int index = 1;
                for (SongItem songItem : ITEMS) {
                    Song song = new Song(index, songItem.getSongName(),
                            songItem.getSinger(), "", songItem.getMp3Url(), songItem.getBgimage(), "", PlayMode.STREAM);
                    SONGIG_ITEMS.add(song);
                    ITEMS.add(songItem);
                    index++;
                }
                MySongigPlayer.changeNowPlaying(context, SONGIG_ITEMS);
                MySongigPlayer.playSong(context, 0);
                PlayerOnlineAdapter playerAdapter = new PlayerOnlineAdapter(context, SONGIG_ITEMS);
                viewPager.setAdapter(playerAdapter);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<SongItem>> call, Throwable t) {

            }
        });
    }

}
