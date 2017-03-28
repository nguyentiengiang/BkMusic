package dev.ongteu.bkmusic.data.parser.online;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mohammad.songig.common.PlayMode;
import com.github.mohammad.songig.model.Song;

import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.adapter.PlayerAdapter;
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

    public static List<Song> songIgList = new ArrayList<>();
    public static List<SongItem> songList = new ArrayList<>();

    public GetPlayOnline(final Context context, String songUrl, final ViewPager viewPager) {

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(R.string.player_online)
                .content(R.string.waitting_text)
                .show();

        IServices service = BaseRetrofit.instanceService();
        Call<List<SongItem>> call = service.listSongs(songUrl);

        call.enqueue(new Callback<List<SongItem>>() {
            @Override
            public void onResponse(Call<List<SongItem>> call, Response<List<SongItem>> response) {
                songList = response.body();
                songIgList.clear();
                int index = 1;
                for (SongItem songItem : songList) {
                    Song song = new Song(index, songItem.getSongName(),
                            songItem.getSinger(), "", songItem.getMp3Url(), songItem.getBgimage(), "", PlayMode.STREAM);
                    songIgList.add(song);
                    index++;
                }
                MySongigPlayer mySongigPlayer = new MySongigPlayer(context);
                mySongigPlayer.changeNowPlaying(songIgList);
                mySongigPlayer.playSong(0);
                PlayerAdapter playerAdapter = new PlayerAdapter(context, songIgList);
                viewPager.setAdapter(playerAdapter);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<SongItem>> call, Throwable t) {

            }
        });
    }

}
