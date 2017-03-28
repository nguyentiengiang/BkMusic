package dev.ongteu.bkmusic.ui;

import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mohammad.songig.common.PlayerException;
import com.github.mohammad.songig.common.RepeatMode;
import com.github.mohammad.songig.common.SongigPlayer;
import com.github.mohammad.songig.listener.OnCompleteListener;
import com.github.mohammad.songig.listener.OnPauseListener;
import com.github.mohammad.songig.listener.OnPlayListener;
import com.github.mohammad.songig.listener.OnStopListener;
import com.github.mohammad.songig.listener.OnUpdateListener;
import com.github.mohammad.songig.model.Song;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.MyPicasso;
import dev.ongteu.bkmusic.utils.MySongigPlayer;

/**
 * Created by TienGiang on 27/3/2017.
 */

public class PlayerProcess {

    public void initPlayerAction(final View viewRoot) {
        final SongigPlayer mySongigPlayer = new MySongigPlayer(viewRoot.getContext()).instance();

        Song currentSong = mySongigPlayer.getCurrentSong();
        this.changeUI(viewRoot, currentSong);
        final double[] currentSongDuration = {0};

        //Btn play
        final AppCompatImageView btnPlay = (AppCompatImageView) viewRoot.findViewById(R.id.btn_song_play_toggle);
        if (mySongigPlayer.isPlaying()){
            btnPlay.setImageResource(R.drawable.ic_pause);
        }
        mySongigPlayer.addPlayListener(new OnPlayListener() {
            @Override
            public void onMusicPlayed() {
                btnPlay.setImageResource(R.drawable.ic_pause);
            }
        });
        mySongigPlayer.addPauseListener(new OnPauseListener() {
            @Override
            public void onMusicPaused() {
                btnPlay.setImageResource(R.drawable.ic_play);
            }
        });
        mySongigPlayer.addStopListener(new OnStopListener() {
            @Override
            public void onMusicStopped() {
                btnPlay.setImageResource(R.drawable.ic_play);
            }
        });

        final TextView txtDurationFull = (TextView) viewRoot.findViewById(R.id.txt_song_duration);
        final TextView txtDurationProgress = (TextView) viewRoot.findViewById(R.id.txt_song_progress);
        final AppCompatSeekBar skbProgress = (AppCompatSeekBar) viewRoot.findViewById(R.id.song_seek_bar);
        skbProgress.setProgress(0);
        skbProgress.setMax(100);
        skbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mySongigPlayer.seekTo(Common.progressToTimer(seekBar.getProgress(), currentSongDuration[0]));
            }
        });

        mySongigPlayer.addUpdateListener(new OnUpdateListener() {
            @Override
            public void onUpdate(double finalTime, double timeElapsed, double remainingTime) {
                currentSongDuration[0] = finalTime;
                txtDurationProgress.setText(Common.milliSecondsToTimer(timeElapsed));
                txtDurationFull.setText(Common.milliSecondsToTimer(finalTime));
                skbProgress.setProgress(Common.getProgressPercentage(timeElapsed, finalTime));
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mySongigPlayer.isPlaying()) {
                    mySongigPlayer.pause();
                } else {
                    try {
                        mySongigPlayer.play(mySongigPlayer.getCurrentSongIndex());
                    } catch (PlayerException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //Btn previous
        final AppCompatImageView btnPrev = (AppCompatImageView) viewRoot.findViewById(R.id.btn_song_play_last);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mySongigPlayer.hasPrev()) {
                    mySongigPlayer.prev();
                    changeUI(viewRoot, mySongigPlayer.getCurrentSong());
                    if (mySongigPlayer.isPlaying()) {
                        try {
                            mySongigPlayer.play(mySongigPlayer.getCurrentSongIndex());
                        } catch (PlayerException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        //Btn next
        final AppCompatImageView btnNext = (AppCompatImageView) viewRoot.findViewById(R.id.btn_song_play_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mySongigPlayer.hasNext()) {
                    mySongigPlayer.next();
                    changeUI(viewRoot, mySongigPlayer.getCurrentSong());
                    if (mySongigPlayer.isPlaying()) {
                        try {
                            mySongigPlayer.play(mySongigPlayer.getCurrentSongIndex());
                        } catch (PlayerException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        //Btn shuffle
        final AppCompatImageView btnShuffle = (AppCompatImageView) viewRoot.findViewById(R.id.btn_shuffle_toggle);
        if (!mySongigPlayer.isShuffle()) {
            btnShuffle.setImageResource(R.drawable.ic_play_mode_list);
        } else {
            btnShuffle.setImageResource(R.drawable.ic_play_mode_shuffle);
        }
        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mySongigPlayer.isShuffle()) {
                    btnShuffle.setImageResource(R.drawable.ic_play_mode_list);
                    mySongigPlayer.unShuffle();
                } else {
                    btnShuffle.setImageResource(R.drawable.ic_play_mode_shuffle);
                    mySongigPlayer.shuffle();
                }
            }
        });
        //Btn repeat mode
        final AppCompatImageView btnRepeat = (AppCompatImageView) viewRoot.findViewById(R.id.btn_repeat_mode_toggle);
        if (mySongigPlayer.getRepeatMode() == RepeatMode.OFF) {
            btnRepeat.setImageResource(R.drawable.ic_play_mode_loop);
            btnRepeat.setColorFilter(Color.GRAY);
        } else if (mySongigPlayer.getRepeatMode() == RepeatMode.ONCE) {
            btnRepeat.setImageResource(R.drawable.ic_play_mode_single);
            btnRepeat.setColorFilter(Color.parseColor("#DDf9FF"));
        } else if (mySongigPlayer.getRepeatMode() == RepeatMode.ALL) {
            btnRepeat.setImageResource(R.drawable.ic_play_mode_loop);
            btnRepeat.setColorFilter(Color.parseColor("#DDf9FF"));
        }
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepeatMode repeatMode = mySongigPlayer.getRepeatMode();
                if (repeatMode == RepeatMode.OFF) {
                    mySongigPlayer.setRepeatMode(RepeatMode.ALL);
                    btnRepeat.setImageResource(R.drawable.ic_play_mode_loop);
                    btnRepeat.setColorFilter(Color.parseColor("#DDf9FF"));
                }
                if (repeatMode == RepeatMode.ALL) {
                    mySongigPlayer.setRepeatMode(RepeatMode.ONCE);
                    btnRepeat.setImageResource(R.drawable.ic_play_mode_single);
                    btnRepeat.setColorFilter(Color.parseColor("#DDf9FF"));
                }
                if (repeatMode == RepeatMode.ONCE) {
                    mySongigPlayer.setRepeatMode(RepeatMode.OFF);
                    btnRepeat.setImageResource(R.drawable.ic_play_mode_loop);
                    btnRepeat.setColorFilter(Color.GRAY);
                }
            }
        });

        mySongigPlayer.addCompleteListener(new OnCompleteListener() {
            @Override
            public void onMusicCompleted() {
                RepeatMode repeatMode = mySongigPlayer.getRepeatMode();
                if (repeatMode == RepeatMode.ONCE && mySongigPlayer.isOneTrackPlaying() || (repeatMode == RepeatMode.ALL && mySongigPlayer.isLastTrack())) {
                    try {
                        mySongigPlayer.play(0);
                    } catch (PlayerException e) {
                        e.printStackTrace();
                    }
                }
                if ((repeatMode == RepeatMode.ALL && mySongigPlayer.hasNext()) || (repeatMode == RepeatMode.OFF && mySongigPlayer.hasNext())) {
                    mySongigPlayer.next();
                }

            }
        });

    }

    private void changeUI(View viewRoot, Song song) {
        final TextView txtNameSong = (TextView) viewRoot.findViewById(R.id.txtSongName);
        final TextView txtSongArtist = (TextView) viewRoot.findViewById(R.id.txtSongArtist);
        final ShadowImageView imgCover = (ShadowImageView) viewRoot.findViewById(R.id.imgAlbumArt);
        txtNameSong.setText(song.getName());
        txtSongArtist.setText(song.getArtistName());

        new MyPicasso(imgCover.getContext(), imgCover, song.getImageUrl(), true);
        imgCover.startRotateAnimation();
    }

}
