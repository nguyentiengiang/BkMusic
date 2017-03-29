package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mohammad.songig.model.Song;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.MyPicasso;
import dev.ongteu.bkmusic.utils.MySongigPlayer;

public class NowListAdapter extends BaseAdapter {

    private final List<Song> mValues;
    private final Context mContext;

    public NowListAdapter(List<Song> items, final Context context) {
        super();
        mValues = items;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public Song getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Song songIgItem = getItem(position);
        final NowListViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_nowlist_item, parent, false);
            viewHolder = new NowListViewHolder();
            viewHolder.siSongName = (TextView) convertView.findViewById(R.id.siSongName);
            viewHolder.siSongArtist = (TextView) convertView.findViewById(R.id.siSongArtist);
            viewHolder.siAlbumArt = (ImageView) convertView.findViewById(R.id.siAlbumArt);
            viewHolder.siSpinner = (Spinner) convertView.findViewById(R.id.siSpinner);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (NowListViewHolder) convertView.getTag();
        }
        viewHolder.siSongName.setText(Common.cutterLongName(songIgItem.getName().toString(), Constant.MAX_LENGTH_NAME_TITLE_CATE));
        viewHolder.siSongArtist.setText(songIgItem.getArtistName().toString());
        new MyPicasso(viewHolder.siAlbumArt.getContext(), viewHolder.siAlbumArt, songIgItem.getImageUrl());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySongigPlayer mySongigPlayer = new MySongigPlayer(mContext);
                if (!(mySongigPlayer.instance().getCurrentSongIndex() == songIgItem.getId())) {
                    mySongigPlayer.playSong(songIgItem.getId());
                }
            }
        });
        return convertView;
    }

    private class NowListViewHolder {
        TextView siSongName;
        TextView siSongArtist;
        ImageView siAlbumArt;
        Spinner siSpinner;
    }
}
