package dev.ongteu.bkmusic.data.dao;

import android.content.Context;
import android.util.Log;
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
import dev.ongteu.bkmusic.data.entity.OrmaDatabase;
import dev.ongteu.bkmusic.data.entity.Songs;
import dev.ongteu.bkmusic.data.entity.Songs_Schema;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.File.FileHelper;
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
                    int songId = Common.safeLongToInt(songItem.getId());
                    String songName = songItem.getSongName();
                    String singer = songItem.getSinger();
                    String albumName = "";
                    String url = songItem.getMp3Url();
                    String imageUrl = Constant.URL_LOCAL_FILE + songItem.getAvatar();
                    String albumId = songItem.getFileName();

                    Song songIgItem = new Song(songId, songName, singer, albumName, url, imageUrl, albumId, PlayMode.LOCAL);
                    listSongIg.add(songIgItem);
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

}
