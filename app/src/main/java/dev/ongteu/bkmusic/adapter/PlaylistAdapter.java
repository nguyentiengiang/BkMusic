package dev.ongteu.bkmusic.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.activities.MainActivity;
import dev.ongteu.bkmusic.data.dao.PlaylistDAO;
import dev.ongteu.bkmusic.data.entity.Playlist;
import dev.ongteu.bkmusic.fragment.MyPlayerFragment;
import dev.ongteu.bkmusic.fragment.PlaylistFragment;
import dev.ongteu.bkmusic.utils.Constant;

public class PlaylistAdapter extends BaseAdapter {

    private final List<Playlist> mValues;
    private final Context mContext;
    private final ListView mListView;

    public PlaylistAdapter(List<Playlist> items, final Context context, final ListView listView) {
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
        final LocalPlaylistViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_playlist_item, parent, false);
            viewHolder = new LocalPlaylistViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LocalPlaylistViewHolder) convertView.getTag();
        }

        viewHolder.pliPlaylistName.setText(String.valueOf(playlistItem.getName()));
        viewHolder.pliTotalSong.setText(String.valueOf(playlistItem.getCount()) + " bài hát");
        viewHolder.pliMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, viewHolder.pliMenu);
                popup.getMenuInflater().inflate(R.menu.menu_playlist_offline, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String title = item.getTitle().toString();
                        if (title.equalsIgnoreCase(mContext.getResources().getString(R.string.menu_playlist_edit_name))) {
                            showEditPlaylistName(mContext, mListView, "", playlistItem.getId(), playlistItem.getName());
                        } else if (title.equalsIgnoreCase(mContext.getResources().getString(R.string.menu_playlist_change))) {
                            PlaylistFragment.showEditSong(mContext, mListView, playlistItem.getId());
                        } else if (title.equalsIgnoreCase(mContext.getResources().getString(R.string.menu_playlist_delete))) {
                            new AlertDialog.Builder(mContext)
                                    .setTitle("Xóa playlist")
                                    .setMessage("Bạn muốn xóa danh sách phát " + playlistItem.getName() + " ?")
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            new PlaylistDAO(mContext).removePlaylist(playlistItem.getId());
                                            Toast.makeText(mContext, "Đã xóa", Toast.LENGTH_SHORT).show();
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
                if (playlistItem.getCount() > 0) {
                    Toast.makeText(mContext, "Click " + playlistItem.getId(), Toast.LENGTH_SHORT).show();
                    MyPlayerFragment myPlayerFragment = MyPlayerFragment.newInstance(Constant.PLAY_TYPE_OFFLINE, "", playlistItem.getId());
                    FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .remove(fragmentManager.findFragmentById(R.id.fragment_container)).commit();
                    fragmentManager.beginTransaction().addToBackStack("NOW_PLAYING")
                            .replace(R.id.fragment_container, myPlayerFragment).commit();
                    ((MainActivity) mContext).setTitle(R.string.NOW_PLAYING);
                }
            }
        });
        return convertView;
    }

    private void showEditPlaylistName(final Context context, final ListView listView, String vaildString, final long playlistId, CharSequence playlistName) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(mContext)
                .title("Đổi tên playlist")
                .content(vaildString)
                .positiveText("Cập nhật")
                .negativeText("Hủy bỏ")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(R.string.create_playlist_hint, R.string.create_playlist_suggest, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        final PlaylistDAO playlistDAO = new PlaylistDAO(context);
                        String inputPlaylistName = dialog.getInputEditText().getText().toString();
                        if (playlistDAO.isDuplicateName(String.valueOf(inputPlaylistName))) {
                            showEditPlaylistName(context, listView, "Tên playlist đã có", playlistId, inputPlaylistName);
                        } else {
                            playlistDAO.editPlaylist(playlistId, inputPlaylistName);
                            PlaylistFragment.refreshAdapter(context, listView);
                        }
                    }
                }).show();
        if (!playlistName.equals("")) {
            materialDialog.getInputEditText().setText(playlistName);
        }
    }

    private class LocalPlaylistViewHolder {
        public final TextView pliPlaylistName;
        public final TextView pliTotalSong;
        public final ImageView pliMenu;

        public LocalPlaylistViewHolder(View view) {
            pliPlaylistName = (TextView) view.findViewById(R.id.pliPlaylistName);
            pliTotalSong = (TextView) view.findViewById(R.id.pliTotalSong);
            pliMenu = (ImageView) view.findViewById(R.id.imgMenuPlaylist);
        }
    }

}
