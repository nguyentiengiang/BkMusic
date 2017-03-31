package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mohammad.songig.common.PlayMode;
import com.github.mohammad.songig.common.SongigPlayer;
import com.github.mohammad.songig.model.Song;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.dao.PlaylistDAO;
import dev.ongteu.bkmusic.data.entity.Playlist;
import dev.ongteu.bkmusic.data.parser.online.GetPlayOnline;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.MyPicasso;
import dev.ongteu.bkmusic.utils.MySongigPlayer;

public class NowListAdapter extends BaseAdapter {

    private final List<Song> mValues;
    private final Context mContext;

    public NowListAdapter(Context mContext) {
        this.mContext = mContext;
        this.mValues = new MySongigPlayer(mContext).instance().getPlayList();
    }

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
            viewHolder = new NowListViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (NowListViewHolder) convertView.getTag();
        }
        viewHolder.siSongName.setText(Common.cutterLongName(songIgItem.getName().toString(), Constant.MAX_LENGTH_NAME_TITLE_CATE));
        viewHolder.siSongArtist.setText(songIgItem.getArtistName().toString());
        new MyPicasso(viewHolder.siAlbumArt.getContext(), viewHolder.siAlbumArt, songIgItem.getImageUrl());

        if (songIgItem.getPlayMode().equals(PlayMode.STREAM)){
            viewHolder.siImgMenu.setImageResource(R.drawable.ic_file_download);
        }

        viewHolder.siImgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songIgItem.getPlayMode().equals(PlayMode.STREAM)){
                    Toast.makeText(mContext, "Đang tải " + songIgItem.getName(), Toast.LENGTH_SHORT).show();
                    GetPlayOnline.downloadMusic(mContext, songIgItem.getAlbumID());
                } else if (songIgItem.getPlayMode().equals(PlayMode.LOCAL)){
                    getMenuOffline(viewHolder.siImgMenu, songIgItem);
                }
            }
        });

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
        ImageView siImgMenu;

        public NowListViewHolder(View view) {
            siSongName = (TextView) view.findViewById(R.id.siSongName);
            siSongArtist = (TextView) view.findViewById(R.id.siSongArtist);
            siAlbumArt = (ImageView) view.findViewById(R.id.siAlbumArt);
            siImgMenu = (ImageView) view.findViewById(R.id.siImgMenu);
        }
    }

    private void getMenuOffline(final ImageView siImgMenu, final Song songItem){
        PopupMenu popup = new PopupMenu(mContext, siImgMenu);
        popup.getMenuInflater().inflate(R.menu.menu_song_nowplay, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = item.getTitle().toString();
                if (title.equalsIgnoreCase(mContext.getResources().getString(R.string.menu_song_add_to_playlist))) {
                    final List<Playlist> allPlaylist = new PlaylistDAO(mContext).getAll();
                    if (!allPlaylist.isEmpty()) {
                        new MaterialDialog.Builder(mContext)
                                .title("Thêm vào playlist")
                                .items(allPlaylist)
                                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                        Playlist pChoice = getPlaylistIdByIndex(allPlaylist, which);
                                        new PlaylistDAO(mContext).addSongItemToPlaylist(pChoice.getId(), songItem.getAlbumID());
                                        Toast.makeText(mContext, "Đã thêm vào danh sách " + pChoice.getName(), Toast.LENGTH_SHORT).show();
                                        return true;
                                    }
                                })
                                .positiveText("Chấp nhận")
                                .negativeText("Hủy bỏ")
                                .show();
                    } else {
                        Toast.makeText(mContext, "Hãy tạo danh sách phát trước", Toast.LENGTH_LONG).show();
                    }
                } else if (title.equalsIgnoreCase(mContext.getResources().getString(R.string.menu_song_delete_from_nowplay))) {
                    SongigPlayer player = new MySongigPlayer(mContext).instance();
                    player.remove(songItem);
                    NowListAdapter nowListAdapter = new NowListAdapter(mContext);
                    nowListAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        popup.show();
    }

    private static Playlist getPlaylistIdByIndex(List<Playlist> allPlaylist, int idx) {
        Playlist playlistItem = null;
        for (int i = 0; i < allPlaylist.size(); i++) {
            if (i == idx) {
                playlistItem = allPlaylist.get(i);
                break;
            }
        }
        return playlistItem;
    }

}
