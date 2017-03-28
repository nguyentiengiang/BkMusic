package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.github.gfx.android.orma.widget.OrmaListAdapter;

import dev.ongteu.bkmusic.data.entity.Playlist;
import dev.ongteu.bkmusic.data.entity.Playlist_Relation;

/**
 * Created by TienGiang on 28/3/2017.
 */

public class PlaylistAdapter extends OrmaListAdapter<Playlist> {

    public PlaylistAdapter(final Context context, Playlist_Relation playlist_relation) {
        super(context, playlist_relation);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
