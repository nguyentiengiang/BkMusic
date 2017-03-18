package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mohammad.songig.common.SongigPlayer;
import com.github.mohammad.songig.model.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.SongItem;
import dev.ongteu.bkmusic.data.parser.online.GetPlayOnline;
import dev.ongteu.bkmusic.ui.ShadowImageView;
import dev.ongteu.bkmusic.utils.MyPicasso;

/**
 * Updated by GiangNT on 17/03/2017.
 */
public class PlayerAdapter extends PagerAdapter {

    private static final String TAG = "PlayerAdapter";
    private List<Song> mSongIgItems;
    private List<SongItem> mSongItems;
    private Song mSongNow;
    private Context mContext;
    private int mPlayType;
    private String mUrlPlay;

    public PlayerAdapter(Context context, int playType, String urlPlay) {
        mContext = context;
        mPlayType = playType;
        mUrlPlay = urlPlay;
    }

    @Override
    public int getCount() {
        return 2;
    }

    /**
     * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
     * same object as the {@link View} added to the {@link android.support.v4.view.ViewPager}.
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    /**
     * Instantiate the {@link View} which should be displayed at {@code position}. Here we
     * inflate a layout from the apps resources and then change the text view to signify the position.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Inflate a new layout from our resources
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int resId = -1;

        //Getting my layout's in my adapter. Three layouts defined.
        switch (position) {
            case 1:
                resId = R.layout.fragment_nowlist;
                break;
            case 0:
            default:
                resId = R.layout.fragment_player;
                break;
        }
        View view = inflater.inflate(resId, container, false);
        new GetPlayOnline(mContext, mUrlPlay);

//        mSongNow = SongigPlayer.getInstance(mContext).getCurrentSong();

        switch (position) {
            case 1:
                if (view instanceof ListView){
                    NowListAdapter nowListAdapter = new NowListAdapter(mContext, GetPlayOnline.SONGIG_ITEMS);
                    ((ListView) view).setAdapter(nowListAdapter);
                }
                break;
            case 0:
            default:
                TextView txtNameSong = (TextView) view.findViewById(R.id.txtSongName);
                TextView txtSongArtist = (TextView) view.findViewById(R.id.txtSongArtist);
                ShadowImageView imgCover = (ShadowImageView) view.findViewById(R.id.imgAlbumArt);

                new MyPicasso(imgCover.getContext(), imgCover, "http://avatar.nct.nixcdn.com/singer/avatar/2017/02/15/b/5/a/7/1487153681589.jpg");
                imgCover.startRotateAnimation();

                break;
        }

        container.addView(view);
        Log.i(TAG, "instantiateItem() [position: " + position + "]" + " childCount:" + container.getChildCount());
        // Return the View
        return view;
    }

    /**
     * Destroy the item from the {@link android.support.v4.view.ViewPager}. In our case this is simply removing the
     * {@link View}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.i(TAG, "destroyItem() [position: " + position + "]" + " childCount:" + container.getChildCount());
    }

    /**
     * This method is only gets called when we invoke {@link #notifyDataSetChanged()} on this adapter.
     * Returns the index of the currently active fragments.
     * There could be minimum two and maximum three active fragments(suppose we have 3 or more  fragments to show).
     * If there is only one fragment to show that will be only active fragment.
     * If there are only two fragments to show, both will be in active state.
     * PagerAdapter keeps left and right fragments of the currently visible fragment in ready/active state so that it could be shown immediate on swiping.
     * Currently Active Fragments means one which is currently visible one is before it and one is after it.
     *
     * @param object Active Fragment reference
     * @return Returns the index of the currently active fragments.
     */
    @Override
    public int getItemPosition(Object object) {
//        Utils.DummyItem dummyItem = (Utils.DummyItem) ((View) object).getTag();
//        int position = mDummyItems.indexOf(dummyItem);
//        if (position >= 0) {
//            // The current data matches the data in this active fragment, so let it be as it is.
//            return position;
//        } else {
//            // Returning POSITION_NONE means the current data does not matches the data this fragment is showing right now.  Returning POSITION_NONE constant will force the fragment to redraw its view layout all over again and show new data.
//            return POSITION_NONE;
//        }
        return 1;
    }
}
