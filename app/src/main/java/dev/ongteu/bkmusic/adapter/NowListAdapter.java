package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.PlaylistItem;
import dev.ongteu.bkmusic.data.model.SongItem;
import dev.ongteu.bkmusic.fragment.NowListFragment;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaylistItem} and makes a call to the
 * specified {@link NowListFragment.OnFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class NowListAdapter extends RecyclerView.Adapter<NowListAdapter.ViewHolder> {

    private final List<SongItem> mValues;
    private final NowListFragment.OnFragmentInteractionListener mListener;
    private final Context mContext;

    public NowListAdapter(List<SongItem> items, NowListFragment.OnFragmentInteractionListener listener, final Context context) {
        mValues = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_hotmusic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.hotSongArtist.setText(mValues.get(position).getSinger().toString());
        holder.hotSongName.setText(mValues.get(position).getSongName().toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView hotSongName;
        public final TextView hotSongArtist;
        public SongItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            hotSongName = (TextView) view.findViewById(R.id.hotSongName);
            hotSongArtist = (TextView) view.findViewById(R.id.hotSongArtist);
            hotSongName.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + hotSongName.getText() + "'";
        }

        @Override
        public void onClick(View v) {

//            SongigPlayer.getInstance(mContext).removeAll();
//            List<Song> songList = new GetSongs(mContext, mItem.getSongUrl()).SONGIG_ITEMS;
//            for (Song songItem : songList) {
//                SongigPlayer.getInstance(mContext).addToFirst(songItem);
//            }
//            NowPlayingFragment nowPlayingFragment = NowPlayingFragment.newInstance(mItem.getSongUrl(), 1);
//            FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
//            fragmentManager.beginTransaction()
//                    .remove(fragmentManager.findFragmentById(R.id.fragment_container)).commit();
//            fragmentManager.beginTransaction().addToBackStack("NOW_PLAYING")
//                    .replace(R.id.fragment_container, nowPlayingFragment).commit();
        }
    }

}
