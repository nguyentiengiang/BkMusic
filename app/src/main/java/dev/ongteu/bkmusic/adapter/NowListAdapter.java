package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.github.mohammad.songig.model.Song;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.utils.MyPicasso;

public class NowListAdapter extends BaseSwipeAdapter {

    private Context mContext;
    private List<Song> mSongList;

    public NowListAdapter(Context mContext, List<Song> songList) {
        this.mContext = mContext;
        this.mSongList = songList;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeNowList;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.fragment_nowlist_item, null);
        SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.setOnClickListener(new SwipeLayout.OnClickListener(){
            @Override
            public void onClick(View v) {
                // call fragment player
            }
        });
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {

            }
        });
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        v.findViewById(R.id.btnSiNowDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "click delete", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void fillValues(int position, View convertView) {
        ImageView imgAlbumArt = (ImageView) convertView.findViewById(R.id.siAlbumArt);
        TextView txtSISongName = (TextView) convertView.findViewById(R.id.siSongName);
        TextView txtSISongArtist = (TextView) convertView.findViewById(R.id.siSongArtist);

        txtSISongName.setText(this.getItem(position).getName());
        txtSISongArtist.setText(this.getItem(position).getArtistName());
        new MyPicasso(convertView.getContext(), imgAlbumArt, this.getItem(position).getImageUrl());
        Log.e("NOW LIST ADAP ===>>>>", this.getItem(position).getName());
    }

    @Override
    public int getCount() {
        return mSongList.size();
    }

    @Override
    public Song getItem(int position) {
        return mSongList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
