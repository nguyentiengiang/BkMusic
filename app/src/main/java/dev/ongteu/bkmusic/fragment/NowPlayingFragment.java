package dev.ongteu.bkmusic.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jean.jcplayer.JcPlayerView;
import com.github.mohammad.songig.common.PlayerException;
import com.github.mohammad.songig.common.SongigPlayer;
import com.github.mohammad.songig.model.Song;

import java.util.List;

import co.mobiwise.library.OnActionClickedListener;
import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.table.GetSongs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NowPlayingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NowPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayingFragment extends Fragment{

    private static final String ARG_PLAY_TYPE = "playType";
    private static final String ARG_SONG_URL = "urlNow";

    private int mPlayType;
    private String mSongUrl;

    private OnFragmentInteractionListener mListener;
    private ViewPager viewPager;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NowPlayingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowPlayingFragment newInstance() {
        NowPlayingFragment fragment = new NowPlayingFragment();
        return fragment;
    }

    public static NowPlayingFragment newInstance(String urlNow, int playType) {
        NowPlayingFragment fragment = new NowPlayingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PLAY_TYPE, playType);
        args.putString(ARG_SONG_URL, urlNow);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlayType = getArguments().getInt(ARG_PLAY_TYPE);
            mSongUrl = getArguments().getString(ARG_SONG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_nowplaying, container, false);
        Context context = viewRoot.getContext();
        viewPager = (ViewPager) viewRoot.findViewById(R.id.viewPager);

        NowPlayingAdapter adapter = new NowPlayingAdapter(getChildFragmentManager(), context, mPlayType, mSongUrl);
        initSong(viewRoot.getContext(), mSongUrl, adapter);
        adapter.addFragment(NowPlayerFragment.newInstance(), "Now playing");
        adapter.addFragment(NowListFragment.newInstance(), "Current playing");
        viewPager.setAdapter(adapter);
        return viewRoot;
    }

    private void initSong(Context context, String songUrl, FragmentStatePagerAdapter adapter){
        SongigPlayer.getInstance(context).getPlayList().clear();
        List<Song> songList = new GetSongs(context, adapter, songUrl).SONGIG_ITEMS;
        for (Song songItem : songList) {
            SongigPlayer.getInstance(context).addToFirst(songItem);
        }
        try {
            SongigPlayer.getInstance(context).play(0);
        } catch (PlayerException e) {
            e.printStackTrace();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
