package dev.ongteu.bkmusic.data.dao;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.github.mohammad.songig.common.PlayMode;
import com.github.mohammad.songig.model.Song;
import com.sromku.simple.storage.Storage;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import dev.ongteu.bkmusic.adapter.LocalSongAdapter;
import dev.ongteu.bkmusic.adapter.PlayerAdapter;
import dev.ongteu.bkmusic.data.entity.OrmaDatabase;
import dev.ongteu.bkmusic.data.entity.Songs;
import dev.ongteu.bkmusic.data.entity.Songs_Schema;
import dev.ongteu.bkmusic.data.model.SongItem;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.File.FileHelper;
import dev.ongteu.bkmusic.utils.MySongigPlayer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableFromCallable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TienGiang on 17/3/2017.
 */

public class SongDAO extends BaseDAO {

    public SongDAO(final Context context){
        super(context, true);
    }

    public List<Song> getUserSong(){

        List<Song> listSongIg = new ArrayList<>();
        List<Songs> listSong = this.bkOrm.selectFromSongs()
                .where(Songs_Schema.INSTANCE.isUserLocal, "=", Constant.IS_USER_LOCAL)
                .toList();
        for (Songs songItem : listSong) {
                Storage storage = FileHelper.initStorge(context);
                if (storage.isFileExist(Constant.PATH_MUSIC_USER, songItem.getFileName()) || storage.isFileExist(Constant.PATH_MUSIC_APP, songItem.getFileName())) {
                    listSongIg.add(this.convert2SongIgItem(songItem));
                } else {
                    bkOrm.deleteFromSongs().idEq(songItem.getId()).execute();
                }
        }
        return listSongIg;
    }

    public boolean isSongNotOnDb(String keyMp3){
        return this.bkOrm.selectFromSongs()
                .where(Songs_Schema.INSTANCE.isUserLocal, "=", Constant.IS_USER_LOCAL)
                .where(Songs_Schema.INSTANCE.keyMp3, "=", keyMp3)
                .isEmpty();
    }

    public long addNewSong(String songName, String singer, String singerUrl, String bgImage, String avatar, String keyMp3, String mp3Url, String songUrl, String fileName, int isUserLocal){
        Songs newSong = new Songs(songName, singer, singerUrl, bgImage, avatar, keyMp3, mp3Url, songUrl, fileName, isUserLocal);
        return this.bkOrm.insertIntoSongs(newSong);
    }

    public static Song convert2SongIgItem(Songs songItem){
        int songId = Common.safeLongToInt(songItem.getId());
        String songName = songItem.getSongName();
        String singer = songItem.getSinger();
        String albumName = "";
        String url = songItem.getMp3Url();
        String imageUrl = Constant.URL_LOCAL_FILE + songItem.getAvatar();
        String albumId = songItem.getFileName();
        return new Song(songId, songName, singer, albumName, url, imageUrl, albumId, PlayMode.LOCAL);
    }

    public void playOneSong(ViewPager viewPager, String keyMp3){
        List<Songs> songItems = this.bkOrm.selectFromSongs().where(Songs_Schema.INSTANCE.keyMp3, "=", keyMp3).toList();
        if (songItems.size() == 1){
            this.convert2SongIgItem(songItems.get(0));
        }
        List<Song> songIgList = new ArrayList<>();
        MySongigPlayer mySongigPlayer = new MySongigPlayer(this.context);
        mySongigPlayer.changeNowPlaying(songIgList);
        mySongigPlayer.playSong(0);
        PlayerAdapter playerAdapter = new PlayerAdapter(this.context, songIgList);
        viewPager.setAdapter(playerAdapter);
    }

}
