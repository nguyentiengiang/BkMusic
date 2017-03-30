package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.activities.MainActivity;
import dev.ongteu.bkmusic.data.entity.Songs;
import dev.ongteu.bkmusic.fragment.MyPlayerFragment;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.MyPicasso;

public class LocalSongAdapter extends BaseAdapter {

    private final List<Songs> mValues;
    private final Context mContext;

    public LocalSongAdapter(List<Songs> items, final Context context) {
        super();
        mValues = items;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public Songs getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Songs songItem = getItem(position);
        LocalSongViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_nowlist_item2, parent, false);
            viewHolder = new LocalSongViewHolder();
            viewHolder.siSongName = (TextView) convertView.findViewById(R.id.siSongName);
            viewHolder.siSongArtist = (TextView) convertView.findViewById(R.id.siSongArtist);
            viewHolder.siAlbumArt = (ImageView) convertView.findViewById(R.id.siAlbumArt);
            viewHolder.siSpinner = (Spinner) convertView.findViewById(R.id.siSpinner);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LocalSongViewHolder) convertView.getTag();
        }
        viewHolder.siSongName.setText(Common.cutterLongName(songItem.getSongName().toString(), Constant.MAX_LENGTH_NAME_TITLE_CATE));
        viewHolder.siSongArtist.setText(songItem.getSinger().toString());
        new MyPicasso(viewHolder.siAlbumArt.getContext(), viewHolder.siAlbumArt, songItem.getAvatar());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPlayerFragment myPlayerFragment = MyPlayerFragment.newInstance(Constant.PLAY_TYPE_OFFLINE, songItem.getKeyMp3(), 0);
                FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .remove(fragmentManager.findFragmentById(R.id.fragment_container)).commit();
                fragmentManager.beginTransaction().addToBackStack("NOW_PLAYING")
                        .replace(R.id.fragment_container, myPlayerFragment).commit();
                ((MainActivity) mContext).setTitle(R.string.NOW_PLAYING);
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        return convertView;
    }

    private class LocalSongViewHolder {
        TextView siSongName;
        TextView siSongArtist;
        ImageView siAlbumArt;
        Spinner siSpinner;
    }

}
