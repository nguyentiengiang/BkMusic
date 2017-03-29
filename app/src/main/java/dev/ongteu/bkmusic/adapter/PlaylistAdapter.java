package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.entity.Playlist;

public class PlaylistAdapter extends BaseAdapter {

    private final List<Playlist> mValues;
    private final Context mContext;

    public PlaylistAdapter(List<Playlist> items, final Context context) {
        super();
        mValues = items;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public Playlist getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Playlist playlistItem = getItem(position);
        LocalPlaylistViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_playlist_item, parent, false);
            viewHolder = new LocalPlaylistViewHolder();
            viewHolder.pliPlaylistName = (TextView) convertView.findViewById(R.id.pliPlaylistName);
            viewHolder.pliTotalSong = (TextView) convertView.findViewById(R.id.pliTotalSong);
            viewHolder.pliSpinner = (Spinner) convertView.findViewById(R.id.pliSpinner);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LocalPlaylistViewHolder) convertView.getTag();
        }

        viewHolder.pliPlaylistName.setText(String.valueOf(playlistItem.getName()));
        viewHolder.pliTotalSong.setText(String.valueOf(playlistItem.getCount()) + " bài hát");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Click " + playlistItem.getId(),Toast.LENGTH_SHORT ).show();
//                MyPlayerFragment myPlayerFragment = MyPlayerFragment.newInstance(Constant.PLAY_TYPE_OFFLINE, songItem.getKeyMp3(), 0);
//                FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .remove(fragmentManager.findFragmentById(R.id.fragment_container)).commit();
//                fragmentManager.beginTransaction().addToBackStack("NOW_PLAYING")
//                        .replace(R.id.fragment_container, myPlayerFragment).commit();
//                ((MainActivity) mContext).setTitle(R.string.NOW_PLAYING);
            }
        });
        return convertView;
    }

    private class LocalPlaylistViewHolder {
        TextView pliPlaylistName;
        TextView pliTotalSong;
        Spinner pliSpinner;
    }

}
