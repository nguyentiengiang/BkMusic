package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mohammad.songig.model.Song;

import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.ui.PlayerProcess;

/**
 * Updated by GiangNT on 17/03/2017.
 */
public class PlayerAdapter extends PagerAdapter {

    private static final String TAG = "PlayerAdapter";
    private List<Song> mSongIgItems = new ArrayList<>();
    private Context mContext;

    public PlayerAdapter(Context context, List<Song> SongIgItems) {
        this.mContext = context;
        if (!this.mSongIgItems.isEmpty()) {
            this.mSongIgItems.clear();
        }
        this.mSongIgItems = SongIgItems;
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

        switch (position) {
            case 1:
                if (view instanceof ListView){
                    NowListAdapter nowListAdapter = new NowListAdapter(mSongIgItems, mContext);
                    ((ListView) view).setAdapter(nowListAdapter);
                }
                break;
            case 0:
            default:
                new PlayerProcess().initPlayerAction(view);
                break;
        }
        container.addView(view);
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
