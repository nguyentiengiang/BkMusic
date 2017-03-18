package dev.ongteu.bkmusic.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mohammad.songig.model.Song;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.adapter.PlayerAdapter;
//import dev.ongteu.bkmusic.data.table.GetSongs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyPlayerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyPlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPlayerFragment extends Fragment {
    private static final String ARG_PLAY_TYPE = "playType";
    private static final String ARG_SONG_URL = "songUrl";

    private int mPlayType;
    private String mSongUrl;
    private List<Song> mPlaylist;

    private OnFragmentInteractionListener mListener;

    public MyPlayerFragment() {}

    public static MyPlayerFragment newInstance(){
        MyPlayerFragment fragment = new MyPlayerFragment();
        return fragment;
    }

    public static MyPlayerFragment newInstance(int playType, String url) {
        MyPlayerFragment fragment = new MyPlayerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PLAY_TYPE, playType);
        args.putString(ARG_SONG_URL, url);
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
        View viewRoot = inflater.inflate(R.layout.fragment_my_player, container, false);
        ViewPager viewPagerPlayer = (ViewPager) viewRoot.findViewById(R.id.viewPagerPlayer);
        Context context = viewRoot.getContext();
        PlayerAdapter playerAdapter = new PlayerAdapter(context, mPlayType, mSongUrl);
        viewPagerPlayer.getCurrentItem();
        viewPagerPlayer.setAdapter(playerAdapter);
//        viewPagerPlayer.setCurrentItem(0);
//        new GetSongs(context, mSongUrl);

        return viewRoot;
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
