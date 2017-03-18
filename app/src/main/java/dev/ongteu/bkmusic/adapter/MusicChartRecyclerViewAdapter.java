package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.MusicChartItem;
import dev.ongteu.bkmusic.fragment.MusicChartFragment.OnListFragmentInteractionListener;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.MyPicasso;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MusicChartItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MusicChartRecyclerViewAdapter extends RecyclerView.Adapter<MusicChartRecyclerViewAdapter.ViewHolder> {

    private final List<MusicChartItem.SongChart> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context mContext;

    public MusicChartRecyclerViewAdapter(List<MusicChartItem.SongChart> items, OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_musicchart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mChartSongName.setText(Common.cutterLongName(mValues.get(position).getSongName()));
        holder.mChartSongArtist.setText(mValues.get(position).getSinger());
        new MyPicasso(holder.mChartAlbumArt.getContext(), holder.mChartAlbumArt, mValues.get(position).getAlbumArt());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mChartSongName;
        public final TextView mChartSongArtist;
        public final ImageView mChartAlbumArt;
        public MusicChartItem.SongChart mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mChartSongName = (TextView) view.findViewById(R.id.chartSongName);
            mChartSongArtist = (TextView) view.findViewById(R.id.chartSongArtist);
            mChartAlbumArt = (ImageView) view.findViewById(R.id.chartAlbumArt);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mChartSongName.getText() + "'";
        }
    }
}
