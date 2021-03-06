package dev.ongteu.bkmusic.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.parser.online.GetPlayOnline;
import dev.ongteu.bkmusic.data.runtime.GetPlayNow;
import dev.ongteu.bkmusic.data.runtime.GetPlayOffline;
import dev.ongteu.bkmusic.utils.Constant;

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
    private static final String ARG_PLAYLIST_OFF = "playlistId";

    private int mPlayType = 3;
    private String mSongUrl;
    private long mPlaylist;

    private OnFragmentInteractionListener mListener;

    public MyPlayerFragment() {}

    public static MyPlayerFragment newInstance(){
        MyPlayerFragment fragment = new MyPlayerFragment();
        return fragment;
    }

    public static MyPlayerFragment newInstance(int playType, String url, long mPlaylist) {
        MyPlayerFragment fragment = new MyPlayerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PLAY_TYPE, playType);
        args.putString(ARG_SONG_URL, url);
        args.putLong(ARG_PLAYLIST_OFF, mPlaylist);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlayType = getArguments().getInt(ARG_PLAY_TYPE);
            mSongUrl = getArguments().getString(ARG_SONG_URL);
            mPlaylist = getArguments().getLong(ARG_PLAYLIST_OFF);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_my_player, container, false);
        ViewPager viewPagerPlayer = (ViewPager) viewRoot.findViewById(R.id.viewPagerPlayer);
        Context context = viewRoot.getContext();

        switch (mPlayType){
            case Constant.PLAY_TYPE_ONLINE:
                new GetPlayOnline(context, mSongUrl, viewPagerPlayer);
                break;
            case Constant.PLAY_TYPE_OFFLINE:
                if (mPlaylist == 0) {
                    new GetPlayOffline(context, mSongUrl, viewPagerPlayer);
                } else if(mPlaylist == -1) {
                } else {
                    new GetPlayOffline(context, mPlaylist, viewPagerPlayer);
                }
                break;
            default:
                new GetPlayNow(context, viewPagerPlayer);
                break;
        }

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
