package dev.ongteu.bkmusic.fragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.activities.MainActivity;
import dev.ongteu.bkmusic.data.model.HotSongItem;
import dev.ongteu.bkmusic.fragment.HotMusicFragment.OnFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link HotSongItem} and makes a call to the
 * specified {@link OnFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class HotMusicRecyclerViewAdapter extends RecyclerView.Adapter<HotMusicRecyclerViewAdapter.ViewHolder> {

    private final List<HotSongItem> mValues;
    private final OnFragmentInteractionListener mListener;
    private final Context mContext;

    public HotMusicRecyclerViewAdapter(List<HotSongItem> items, OnFragmentInteractionListener listener, final Context context) {
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
        public HotSongItem mItem;

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
            PlayerFragment playerFragment = PlayerFragment.newInstance(mItem.getSongUrl(), "");
            FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentById(R.id.fragment_container)).commit();
            fragmentManager.beginTransaction().addToBackStack("NOW_PLAYING")
                    .replace(R.id.fragment_container, playerFragment).commit();
        }
    }


}
