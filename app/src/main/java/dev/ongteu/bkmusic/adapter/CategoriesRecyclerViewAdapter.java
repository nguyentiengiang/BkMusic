package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.activities.MainActivity;
import dev.ongteu.bkmusic.data.model.CategoryItem;
import dev.ongteu.bkmusic.fragment.CategoriesFragment;
import dev.ongteu.bkmusic.fragment.HotMusicFragment;
import dev.ongteu.bkmusic.fragment.MusicChartFragment;
import dev.ongteu.bkmusic.fragment.PopularAlbumFragment;
import dev.ongteu.bkmusic.fragment.SongFragment;
import dev.ongteu.bkmusic.utils.MyPicasso;

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
        new MyPicasso(holder.mCateImageView.getContext(), holder.mCateImageView, mValues.get(position).getImg());
        holder.mmCateNameView.setText(mValues.get(position).getName());

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
            mView.setOnClickListener(this);
            mCateImageView = (ImageView) view.findViewById(R.id.category_item_image);
            mmCateNameView = (TextView) view.findViewById(R.id.category_item_name);
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
                    FRM_NAME_BACK_STACK = "POP_ALBUM";
                    fragment = PopularAlbumFragment.newInstance(1, mItem.getCategoryCode(), pageNumber);
                    break;
                case 3:
                    FRM_NAME_BACK_STACK = "MUSIC_CHART";
                    fragment = MusicChartFragment.newInstance(1, mItem.getCategoryCode());
                    break;
                case 1:
                default:
                    fragment = HotMusicFragment.newInstance(1, mItem.getCategoryCode(), pageNumber);
                    break;
            }
            FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
//            fragmentManager.beginTransaction()
//                    .hide(fragmentManager.findFragmentById(R.id.fragment_container)).commit();
            fragmentManager.beginTransaction().addToBackStack(FRM_NAME_BACK_STACK)
                    .add(R.id.fragment_container, fragment).commit();
        }
    }
}
