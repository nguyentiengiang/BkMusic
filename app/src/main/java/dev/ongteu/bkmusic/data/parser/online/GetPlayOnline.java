package dev.ongteu.bkmusic.data.parser.online;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mohammad.songig.common.PlayMode;
import com.github.mohammad.songig.model.Song;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.activities.MainActivity;
import dev.ongteu.bkmusic.adapter.PlayerAdapter;
import dev.ongteu.bkmusic.data.model.SongItem;
import dev.ongteu.bkmusic.data.parser.api.BaseRetrofit;
import dev.ongteu.bkmusic.data.parser.api.IServices;
import dev.ongteu.bkmusic.fragment.MyPlayerFragment;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.MyDownloader;
import dev.ongteu.bkmusic.utils.MySongigPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
                    String songName = songItem.getSongName();
                    String singerName = songItem.getSinger();
                    String albumName = "";
                    String playUrl = songItem.getMp3Url();
                    String imgUrl = songItem.getAvatar();
                    String albumId = songItem.getSongUrl();
                    Song song = new Song(index, songName, singerName, albumName, playUrl, imgUrl, albumId, PlayMode.STREAM);
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

    public GetPlayOnline(final Context mContext, String songUrl) {

        final MaterialDialog dialog = new MaterialDialog.Builder(mContext)
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
                    String songName = songItem.getSongName();
                    String singerName = songItem.getSinger();
                    String albumName = "";
                    String playUrl = songItem.getMp3Url();
                    String imgUrl = songItem.getAvatar();
                    String albumId = songItem.getSongUrl();
                    Song song = new Song(index, songName, singerName, albumName, playUrl, imgUrl, albumId, PlayMode.STREAM);
                    songIgList.add(song);
                    index++;
                }
                MySongigPlayer mySongigPlayer = new MySongigPlayer(mContext);
                mySongigPlayer.changeNowPlaying(songIgList);
                mySongigPlayer.playSong(0);
                MyPlayerFragment myPlayerFragment = MyPlayerFragment.newInstance(Constant.PLAY_TYPE_UNKNOW, "", 0);
                FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .remove(fragmentManager.findFragmentById(R.id.fragment_container)).commit();
                fragmentManager.beginTransaction().addToBackStack("NOW_PLAYING")
                        .replace(R.id.fragment_container, myPlayerFragment).commit();
                ((MainActivity) mContext).setTitle(R.string.NOW_PLAYING);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<SongItem>> call, Throwable t) {
                if (t instanceof UnknownHostException) {
                    dialog.setContent("Mất kết nối mạng");
                }
            }
        });
    }

    public static void downloadMusic(final Context context, String songUrl) {
        IServices service = BaseRetrofit.instanceService();
        Call<List<SongItem>> call = service.listSongs(songUrl);

        call.enqueue(new Callback<List<SongItem>>() {
            @Override
            public void onResponse(Call<List<SongItem>> call, Response<List<SongItem>> response) {
                List<SongItem> songItemList = response.body();
                new MyDownloader(context).download(songItemList);
            }

            @Override
            public void onFailure(Call<List<SongItem>> call, Throwable t) {

            }
        });

    }

}
