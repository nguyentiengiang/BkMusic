package dev.ongteu.bkmusic.data.runtime;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.activities.MainActivity;
import dev.ongteu.bkmusic.data.dao.SongDAO;
import dev.ongteu.bkmusic.data.entity.SearchItem;
import dev.ongteu.bkmusic.data.parser.online.GetPlayOnline;
import dev.ongteu.bkmusic.fragment.MyPlayerFragment;
import dev.ongteu.bkmusic.utils.Constant;

/**
 * Created by TienGiang on 01/04/2017.
 */

public class GetPlayFromSearch {

    public GetPlayFromSearch(final Context mContext, SearchItem searchItem){
        switch (searchItem.getIconStatus()){
            case R.drawable.ic_device:
                new SongDAO(mContext, true).playOneSong(searchItem.getSongUrl());
                MyPlayerFragment myPlayerFragment = MyPlayerFragment.newInstance(Constant.PLAY_TYPE_UNKNOW, "", 0);
                FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
                fragmentManager.beginTransaction().addToBackStack("NOW_PLAYING")
                        .add(R.id.fragment_container, myPlayerFragment).commit();
                ((MainActivity) mContext).setTitle(R.string.NOW_PLAYING);
                break;
            case R.drawable.ic_internet:
                new GetPlayOnline(mContext, searchItem.getSongUrl());
                break;
        }
    }

}
