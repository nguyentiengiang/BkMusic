package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.AlbumItem;
import dev.ongteu.bkmusic.fragment.PopularAlbumFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link AlbumItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PopularAlbumRecyclerViewAdapter extends RecyclerView.Adapter<PopularAlbumRecyclerViewAdapter.ViewHolder> {

    private final List<AlbumItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context mContext;

    public PopularAlbumRecyclerViewAdapter(List<AlbumItem> items, OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_popularalbum_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mAlbumItem = mValues.get(position);
        holder.mAlbumName.setText(mValues.get(position).getAlbumName());
        holder.mAlbumArtist.setText(mValues.get(position).getSinger());

        Picasso.with(holder.mAlbumArt.getContext()).load(mValues.get(position).getAlbumArt())
                .placeholder(R.drawable.lol_guy)
                .error(R.drawable.lol_guy)
                .networkPolicy(NetworkPolicy.OFFLINE).into(holder.mAlbumArt, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {
                Picasso.with(holder.mAlbumArt.getContext()).load(mValues.get(position).getAlbumArt())
                        .into(holder.mAlbumArt, new Callback() {
                            @Override
                            public void onSuccess() {
                            }

                            @Override
                            public void onError() {
                                Log.v("Picasso", "Couldn't load image");
                            }
                        });
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mAlbumItem);
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
        public final TextView mAlbumName;
        public final TextView mAlbumArtist;
        public final ImageView mAlbumArt;
        public AlbumItem mAlbumItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAlbumName = (TextView) view.findViewById(R.id.albumName);
            mAlbumArtist = (TextView) view.findViewById(R.id.albumArtist);
            mAlbumArt = (ImageView) view.findViewById(R.id.albumArt);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mAlbumItem.getAlbumName() + "'";
        }

        @Override
        public void onClick(View v) {

        }
    }
}
