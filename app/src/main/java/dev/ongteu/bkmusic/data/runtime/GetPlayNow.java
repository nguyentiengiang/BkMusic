package dev.ongteu.bkmusic.data.runtime;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mohammad.songig.common.PlayMode;
import com.github.mohammad.songig.common.SongigPlayer;
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

public class GetPlayNow {

    public static List<Song> SONGIG_ITEMS = new ArrayList<Song>();

    public GetPlayNow(final Context context, final ViewPager viewPager){
        SONGIG_ITEMS = SongigPlayer.getInstance(context).getPlayList();
        PlayerOnlineAdapter playerAdapter = new PlayerOnlineAdapter(context, SONGIG_ITEMS);
        viewPager.setAdapter(playerAdapter);
    }

}
