package dev.ongteu.bkmusic.data.dao;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.github.mohammad.songig.model.Song;

import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.adapter.PlayerAdapter;
import dev.ongteu.bkmusic.data.entity.Playlist;
import dev.ongteu.bkmusic.data.entity.PlaylistSong;
import dev.ongteu.bkmusic.data.entity.PlaylistSong_Schema;
import dev.ongteu.bkmusic.data.entity.Playlist_Schema;
import dev.ongteu.bkmusic.data.entity.Songs;
import dev.ongteu.bkmusic.data.entity.Songs_Schema;
import dev.ongteu.bkmusic.utils.MySongigPlayer;

/**
 * Created by TienGiang on 28/3/2017.
 */

public class PlaylistDAO extends BaseDAO {

    public PlaylistDAO(final Context context) {
        super(context, true);
    }

    public List<Playlist> getAll() {
        List<Playlist> allPlaylist = this.bkOrm.selectFromPlaylist().toList();
        List<Playlist> fixedPlaylist = new ArrayList<>();
        for (Playlist playlistItem : allPlaylist) {
            int countSong = this.bkOrm.selectFromPlaylistSong().where(PlaylistSong_Schema.INSTANCE.playListId, "=", playlistItem.getId()).count();
            playlistItem.setCount(countSong);
            fixedPlaylist.add(playlistItem);
        }
        return fixedPlaylist;
    }

    public boolean isDuplicateName(String playlistName) {
        return this.bkOrm.selectFromPlaylist().where(Playlist_Schema.INSTANCE.name, "=", playlistName).count() > 0;
    }

    public long createNewPlaylist(String playlistName) {
        return this.bkOrm.insertIntoPlaylist(new Playlist(playlistName));
    }

    public int editPlaylist(long playlistId, String playlistName) {
        return this.bkOrm.updatePlaylist().idEq(playlistId).name(playlistName).execute();
    }

    public int removePlaylist(long playlistId){
        removeAllSongOfPlaylist(playlistId);
        return this.bkOrm.deleteFromPlaylist().idEq(playlistId).execute();
    }

    public long addSongItemToPlaylist(long playlistId, String keyMp3) {
        PlaylistSong playlistSong = new PlaylistSong(playlistId, keyMp3);
        return this.bkOrm.insertIntoPlaylistSong(playlistSong);
    }

    public int removeSongItemFromPlaylist(long playlistId, String keyMp3) {
        return this.bkOrm.deleteFromPlaylistSong().where(PlaylistSong_Schema.INSTANCE.playListId, "=", playlistId)
                .where(PlaylistSong_Schema.INSTANCE.songId, "=", keyMp3).execute();
    }

    public List<Song> getSongIgByPlaylist(long playlistId) {
        List<PlaylistSong> songOnPlaylist = this.bkOrm.selectFromPlaylistSong().where(PlaylistSong_Schema.INSTANCE.playListId, "=", playlistId).toList();
        List<Song> songIgItems = new ArrayList<>();
        for (int i = 0; i < songOnPlaylist.size(); i++) {
            List<Songs> songListTmp = this.bkOrm.selectFromSongs().where(Songs_Schema.INSTANCE.keyMp3, "=", songOnPlaylist.get(i).getSongId()).toList();
            if (songListTmp.size() > 0) {
                songIgItems.add(SongDAO.convert2SongIgItem(songListTmp.get(0),i));
            }
        }
        return songIgItems;
    }

    public List<PlaylistSong> getSongEnityByPlaylist(long playlistId) {
        List<PlaylistSong> songOnPlaylist = this.bkOrm.selectFromPlaylistSong().where(PlaylistSong_Schema.INSTANCE.playListId, "=", playlistId).toList();
        return songOnPlaylist;
    }

    public void addSongs2PlayList(long playlistId, List<String> keyMp3List) {
        for (String key : keyMp3List) {
            this.addSongItemToPlaylist(playlistId, key);
        }
    }

    public int removeAllSongOfPlaylist(long playlistId){
        return this.bkOrm.deleteFromPlaylistSong().where(PlaylistSong_Schema.INSTANCE.playListId, "=", playlistId).execute();
    }

    public void playMusicByPlaylistId(long playlistId, final ViewPager viewPager) {
        List<Song> songIgList = getSongIgByPlaylist(playlistId);
        if (songIgList.size() > 0) {
            MySongigPlayer mySongigPlayer = new MySongigPlayer(context);
            mySongigPlayer.changeNowPlaying(songIgList);
            mySongigPlayer.playSong(0);

            PlayerAdapter playerAdapter = new PlayerAdapter(this.context, songIgList);
            viewPager.setAdapter(playerAdapter);
            playerAdapter.notifyDataSetChanged();
        }
    }
}
