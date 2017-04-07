package dev.ongteu.bkmusic.data.dao;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.github.mohammad.songig.common.PlayMode;
import com.github.mohammad.songig.model.Song;
import com.sromku.simple.storage.Storage;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.adapter.PlayerAdapter;
import dev.ongteu.bkmusic.data.entity.SearchItem;
import dev.ongteu.bkmusic.data.entity.Songs;
import dev.ongteu.bkmusic.data.entity.Songs_Schema;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.File.FileHelper;
import dev.ongteu.bkmusic.utils.MySongigPlayer;

/**
 * Created by TienGiang on 17/3/2017.
 */

public class SongDAO extends BaseDAO {

    public SongDAO(final Context context, boolean isOnMain) {
        super(context, isOnMain);
    }

    public List<Songs> getAll() {
        List<Songs> listSongChecked = new ArrayList<>();
        List<Songs> listSong = this.bkOrm.selectFromSongs().toList();
        for (Songs songItem : listSong) {
            if (new File(Common.getPathWithoutProtocol(songItem.getMp3Url())).exists()) {
                listSongChecked.add(songItem);
            } else {
                bkOrm.deleteFromSongs().idEq(songItem.getId()).execute();
            }
        }
        return listSongChecked;
    }

    public List<SearchItem> searchByName(String songName) {
        List<SearchItem> listSongChecked = new ArrayList<>();
        List<Songs> listSong = this.bkOrm.selectFromSongs().where(Songs_Schema.INSTANCE.songName, "LIKE", "%" + songName + "%").toList();
        for (Songs songItem : listSong) {
            if (new File(Common.getPathWithoutProtocol(songItem.getMp3Url())).exists()) {
                listSongChecked.add(new SearchItem(songItem.getSongName(), songItem.getSinger(), songItem.getKeyMp3(), R.drawable.ic_device));
            } else {
                bkOrm.deleteFromSongs().idEq(songItem.getId()).execute();
            }
        }
        return listSongChecked;
    }

    public boolean isSongNotOnDb(String fileName) {
        return this.bkOrm.selectFromSongs()
//                .where(Songs_Schema.INSTANCE.isUserLocal, "=", Constant.IS_USER_LOCAL)
                .where(Songs_Schema.INSTANCE.fileName, "=", fileName)
                .isEmpty();
    }

    public long addNewSong(String songName, String singer, String singerUrl, String bgImage, String avatar, String keyMp3, String mp3Url, String songUrl, String fileName, int isUserLocal) {
        Songs newSong = new Songs(songName, singer, singerUrl, bgImage, avatar, keyMp3, mp3Url, songUrl, fileName, isUserLocal);
        return this.bkOrm.insertIntoSongs(newSong);
    }

    public int deleteSong(long songId) {
        return this.bkOrm.deleteFromSongs().idEq(songId).execute();
    }

    public int deleteSong(String keyMp3) {
        return this.bkOrm.deleteFromSongs().where(Songs_Schema.INSTANCE.keyMp3, "=", keyMp3).execute();
    }

    public static Song convert2SongIgItem(Songs songItem, int songIndex) {
        int songId = songIndex;
        String songName = songItem.getSongName();
        String singer = songItem.getSinger();
        String albumName = "";
        String url = songItem.getMp3Url();
        String imageUrl = songItem.getAvatar();
        String albumId = songItem.getKeyMp3();
        Song s = new Song(songId, songName, singer, albumName, url, imageUrl, albumId, PlayMode.LOCAL);
        s.setPath(url);
        return s;
    }

    public void playOneSong(String keyMp3) {
        List<Songs> songItems = this.bkOrm.selectFromSongs().where(Songs_Schema.INSTANCE.keyMp3, "=", keyMp3).toList();
        List<Song> songIgList = new ArrayList<>();
        if (songItems.size() == 1) {
            Song s = this.convert2SongIgItem(songItems.get(0), 0);
            songIgList.add(s);
            MySongigPlayer mySongigPlayer = new MySongigPlayer(this.context);
            mySongigPlayer.changeNowPlaying(songIgList);
            mySongigPlayer.playSong(0);
            PlayerAdapter playerAdapter = new PlayerAdapter(this.context);
            playerAdapter.notifyDataSetChanged();
        }
    }

    public void playWithFirstSong(String keyMp3, final ViewPager viewPager) {
        List<Songs> allSongs = this.getAll();
        List<Song> songIgList = new ArrayList<>();
        int activeIdx = 0;
        for (int i = 0; i < allSongs.size(); i++) {
            if (allSongs.get(i).getKeyMp3().equalsIgnoreCase(keyMp3)) {
                activeIdx = i;
            }
            songIgList.add(this.convert2SongIgItem(allSongs.get(i), i));
        }
        MySongigPlayer mySongigPlayer = new MySongigPlayer(this.context);
        mySongigPlayer.changeNowPlaying(songIgList);
        mySongigPlayer.playSong(activeIdx);

        PlayerAdapter playerAdapter = new PlayerAdapter(this.context, songIgList);
        viewPager.setAdapter(playerAdapter);
        playerAdapter.notifyDataSetChanged();
    }

}
