package dev.ongteu.bkmusic.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
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
import dev.ongteu.bkmusic.data.model.CategoryItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link CategoryItem} and makes a call to the
 * specified {@link SongFragment.OnFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesRecyclerViewAdapter.ViewHolder> {

    private final List<CategoryItem> mValues;
    private final CategoriesFragment.OnFragmentInteractionListener mListener;
    private final int mParentId;
    private final Context mContext;

    public CategoriesRecyclerViewAdapter(List<CategoryItem> items, CategoriesFragment.OnFragmentInteractionListener listener, final Context context, int parentId) {
        mValues = items;
        mListener = listener;
        mParentId = parentId;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resoureView = R.layout.fragment_category_item;
        if (mParentId == 3) {
            // set view MusicChart layout item
        }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(resoureView, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
//        Ion.with(holder.mCateImageView.getContext()).load(mValues.get(position).getImg()).intoImageView(holder.mCateImageView);
        Picasso.with(holder.mCateImageView.getContext()).load(mValues.get(position).getImg())
                .placeholder(R.drawable.lol_guy)
                .error(R.drawable.lol_guy)
                .networkPolicy(NetworkPolicy.OFFLINE).into(holder.mCateImageView, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {
                Picasso.with(holder.mCateImageView.getContext()).load(mValues.get(position).getImg())
                        .into(holder.mCateImageView, new Callback() {
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
        holder.mmCateNameView.setText(mValues.get(position).getName());

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
        public final ImageView mCateImageView;
        public final TextView mmCateNameView;
        public CategoryItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCateImageView = (ImageView) view.findViewById(R.id.category_item_image);
            mmCateNameView = (TextView) view.findViewById(R.id.category_item_name);
            mCateImageView.setOnClickListener(this);
            mmCateNameView.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mmCateNameView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            Fragment fragment = null;
            String FRM_NAME_BACK_STACK = "HOT_MUSIC";
            //NOTE: CUSTOM pageNumber LATER
            int pageNumber = 1;
            switch (mParentId){
                case 2:
//                    fragment = PopularAlbumFragment.newInstance(1, mItem.getId(), pageNumber);
                    break;
                case 3:
//                    fragment = MusicChartFragment.newInstance(1, mItem.getId());
                    break;
                case 1:
                default:
                    fragment = HotMusicFragment.newInstance(1, mItem.getCategoryCode(), pageNumber);
                    break;
            }
            FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentById(R.id.fragment_container)).commit();
            fragmentManager.beginTransaction().addToBackStack(FRM_NAME_BACK_STACK)
                    .replace(R.id.fragment_container, fragment).commit();
        }
    }
}
