package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.activities.MainActivity;
import dev.ongteu.bkmusic.data.dao.PlaylistDAO;
import dev.ongteu.bkmusic.data.dao.SongDAO;
import dev.ongteu.bkmusic.data.entity.Playlist;
import dev.ongteu.bkmusic.data.entity.Songs;
import dev.ongteu.bkmusic.fragment.MyPlayerFragment;
import dev.ongteu.bkmusic.fragment.SongFragment;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.File.FileHelper;
import dev.ongteu.bkmusic.utils.MyPicasso;

public class LocalSongAdapter extends BaseAdapter {

    private final List<Songs> mValues;
    private final Context mContext;
    private final ListView mListView;

    public LocalSongAdapter(List<Songs> items, final Context context, final ListView listView) {
        super();
        mValues = items;
        mContext = context;
        mListView = listView;
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
        final LocalSongViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_songlocal_item, parent, false);
            viewHolder = new LocalSongViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LocalSongViewHolder) convertView.getTag();
        }
        viewHolder.siSongName.setText(Common.cutterLongName(songItem.getSongName().toString(), Constant.MAX_LENGTH_NAME_TITLE_CATE));
        viewHolder.siSongArtist.setText(songItem.getSinger().toString());
        new MyPicasso(viewHolder.siAlbumArt.getContext(), viewHolder.siAlbumArt, songItem.getAvatar());

        viewHolder.siImgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, viewHolder.siImgMenu);
                popup.getMenuInflater().inflate(R.menu.menu_song_offline, popup.getMenu());
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
                                                new PlaylistDAO(mContext).addSongItemToPlaylist(pChoice.getId(), songItem.getKeyMp3());
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
                        } else if (title.equalsIgnoreCase(mContext.getResources().getString(R.string.menu_song_delete_soft))) {
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Xóa bài hát")
                                    .setMessage("Bạn muốn xóa bài hát " + songItem.getSongName() + " khỏi danh sách?")
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            new SongDAO(mContext, true).deleteSong(songItem.getId());
                                            new PlaylistDAO(mContext).removeFromPlaylistByKey(songItem.getKeyMp3());
                                            Toast.makeText(mContext, "Đã xóa khỏi danh sách", Toast.LENGTH_SHORT).show();
                                            SongFragment.refreshAdapter(mContext, mListView);
                                        }
                                    })
                                    .setNegativeButton(android.R.string.no, null).show();
                        } else if (title.equalsIgnoreCase(mContext.getResources().getString(R.string.menu_song_delete_hard))) {
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Xóa bài hát")
                                    .setMessage("Bạn muốn xóa bài hát " + songItem.getSongName() + " khỏi thiết bị?")
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            new SongDAO(mContext, true).deleteSong(songItem.getId());
                                            new PlaylistDAO(mContext).removeFromPlaylistByKey(songItem.getKeyMp3());
                                            FileHelper.deleteMusicFile(mContext, songItem.getIsUserLocal(), songItem.getFileName(), songItem.getKeyMp3());
                                            Toast.makeText(mContext, "Đã xóa khỏi thiết bị", Toast.LENGTH_SHORT).show();
                                            SongFragment.refreshAdapter(mContext, mListView);
                                        }
                                    })
                                    .setNegativeButton(android.R.string.no, null).show();
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPlayerFragment myPlayerFragment = MyPlayerFragment.newInstance(Constant.PLAY_TYPE_OFFLINE, songItem.getKeyMp3(), 0);
                FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .hide(fragmentManager.findFragmentById(R.id.fragment_container)).commit();
                fragmentManager.beginTransaction().addToBackStack("NOW_PLAYING")
                        .add(R.id.fragment_container, myPlayerFragment).commit();
                ((MainActivity) mContext).setTitle(R.string.NOW_PLAYING);
            }
        });

        return convertView;
    }

    private class LocalSongViewHolder {
        TextView siSongName;
        TextView siSongArtist;
        ImageView siAlbumArt;
        ImageView siImgMenu;

        public LocalSongViewHolder(View view) {
            siSongName = (TextView) view.findViewById(R.id.siSongName);
            siSongArtist = (TextView) view.findViewById(R.id.siSongArtist);
            siAlbumArt = (ImageView) view.findViewById(R.id.siAlbumArt);
            siImgMenu = (ImageView) view.findViewById(R.id.siImgMenu);
        }
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
