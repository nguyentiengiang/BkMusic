package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
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
import dev.ongteu.bkmusic.activities.MainActivity;
import dev.ongteu.bkmusic.data.model.AlbumItem;
import dev.ongteu.bkmusic.fragment.MyPlayerFragment;
import dev.ongteu.bkmusic.fragment.PopularAlbumFragment.OnListFragmentInteractionListener;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.MyPicasso;

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
        holder.mAlbumName.setText(Common.cutterLongName(mValues.get(position).getAlbumName(), Constant.MAX_LENGTH_NAME_TITLE_CATE));
        holder.mAlbumArtist.setText(mValues.get(position).getSinger());

        new MyPicasso(holder.mAlbumArt.getContext(), holder.mAlbumArt, mValues.get(position).getAlbumArt());

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
            mView.setOnClickListener(this);
            mAlbumName = (TextView) view.findViewById(R.id.popAlbumName);
            mAlbumArtist = (TextView) view.findViewById(R.id.popAlbumArtist);
            mAlbumArt = (ImageView) view.findViewById(R.id.popAlbumArt);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mAlbumItem.getAlbumName() + "'";
        }

        @Override
        public void onClick(View v) {
            MyPlayerFragment myPlayerFragment = MyPlayerFragment.newInstance(Constant.PLAY_TYPE_ONLINE, mAlbumItem.getAlbumUrl());
            FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentById(R.id.fragment_container)).commit();
            fragmentManager.beginTransaction().addToBackStack("NOW_PLAYING")
                    .replace(R.id.fragment_container, myPlayerFragment).commit();
            ((MainActivity) mContext).setTitle(R.string.NOW_PLAYING);
        }
    }
}
