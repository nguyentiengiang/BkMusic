package dev.ongteu.bkmusic.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import dev.ongteu.bkmusic.data.model.SongItem;

/**
 * Created by TienGiang on 2/10/2016.
 */

public class PlayerAdapter extends ArrayAdapter<SongItem> {

    private SongItem[] objSongs;
    LayoutInflater inflater ;

    public PlayerAdapter(Context context, int resource, SongItem[] objects) {
        super(context, resource, objects);
        this.objSongs = objects;
        this.inflater = LayoutInflater.from(context);
    }

//    public PlayerAdapter(final Context context, SongItem songItem) {
//        super(context, songItem);
//    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}

