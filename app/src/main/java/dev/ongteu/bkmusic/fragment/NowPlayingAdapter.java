package dev.ongteu.bkmusic.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.github.mohammad.songig.common.PlayerException;
import com.github.mohammad.songig.common.SongigPlayer;
import com.github.mohammad.songig.model.Song;

import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.data.table.GetSongs;

/**
 * Created by GiangNT on 03/15/2017.
 */

class NowPlayingAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private final Context mContext;

    public NowPlayingAdapter(FragmentManager manager, Context context, int playType, String songUrl) {
        super(manager);
        this.mContext = context;
//        this.initSong(context, playType, songUrl);
    }

//    private void initSong(Context context, int playType, String songUrl){
//        SongigPlayer.getInstance(context).removeAll();
//        List<Song> songList = new GetSongs(context, songUrl, ).SONGIG_ITEMS;
//        for (Song songItem : songList) {
//            SongigPlayer.getInstance(context).addToFirst(songItem);
//        }
//        try {
//            SongigPlayer.getInstance(mContext).play(SongigPlayer.getInstance(mContext).getLastSongIndex());
//        } catch (PlayerException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
