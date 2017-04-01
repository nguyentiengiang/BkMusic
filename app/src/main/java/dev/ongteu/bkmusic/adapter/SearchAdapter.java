package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.entity.SearchItem;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;

public class SearchAdapter extends BaseAdapter {

    private final List<SearchItem> mValues;
    private final Context mContext;

    public SearchAdapter(final Context context, List<SearchItem> searchItems) {
        super();
        mValues = searchItems;
        mContext = context;
    }

    @Override
    public int getCount() {
        return (mValues != null) ? mValues.size() : 0;
    }

    @Override
    public SearchItem getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SearchItem searchItem = getItem(position);
        final NowListViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.search_layout_item, parent, false);
            viewHolder = new NowListViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (NowListViewHolder) convertView.getTag();
        }
        viewHolder.searchSongName.setText(Common.cutterLongName(searchItem.getSongName().toString(), Constant.MAX_LENGTH_NAME_TITLE_CATE));
        viewHolder.searchSongArtist.setText(searchItem.getArtistName().toString());
        viewHolder.searchSongStatus.setImageResource(searchItem.getIconStatus());

        return convertView;
    }

    private class NowListViewHolder {
        TextView searchSongName;
        TextView searchSongArtist;
        ImageView searchSongStatus;

        public NowListViewHolder(View view) {
            searchSongName = (TextView) view.findViewById(R.id.searchSongName);
            searchSongArtist = (TextView) view.findViewById(R.id.searchSongArtist);
            searchSongStatus = (ImageView) view.findViewById(R.id.searchSongStatus);
        }
    }

}
