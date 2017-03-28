package dev.ongteu.bkmusic.data.runtime;

import android.content.Context;
import android.support.v4.view.ViewPager;

import dev.ongteu.bkmusic.adapter.PlayerAdapter;
import dev.ongteu.bkmusic.utils.MySongigPlayer;

/**
 * Created by TienGiang on 16/3/2017.
 */

public class GetPlayNow {
    public GetPlayNow(final Context context, final ViewPager viewPager){
        MySongigPlayer mySongigPlayer = new MySongigPlayer(context);
        PlayerAdapter playerAdapter = new PlayerAdapter(context, mySongigPlayer.instance().getPlayList());
        viewPager.setAdapter(playerAdapter);
    }

}
