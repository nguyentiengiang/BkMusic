package dev.ongteu.bkmusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.adapter.CategoriesRecyclerViewAdapter;
import dev.ongteu.bkmusic.data.dao.CategoryDAO;
import dev.ongteu.bkmusic.data.model.CategoryItem;
import dev.ongteu.bkmusic.utils.Constant;
import io.reactivex.disposables.CompositeDisposable;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class CategoriesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_PARENT_ID = "parent-id";
    // TODO: Customize parameters
    private int mColumnCount = 3;
    private int mParentId = 1;
    private OnFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoriesFragment() {
    }

    // TODO: Customize parameter initialization
    public static CategoriesFragment newInstance(int columnCount, int parentId) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_PARENT_ID, parentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mParentId = getArguments().getInt(ARG_PARENT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            switch (mParentId){
                case Constant.PARENT_CHART:
                    mColumnCount = Constant.COLUMN_COUNT_3;
                    break;
                case Constant.PARENT_NHACHOT:
                case Constant.PARENT_POPALBUM:
                default:
                    mColumnCount = Constant.COLUMN_COUNT_2;
                    break;
            }
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            List<CategoryItem> categoryItems = new CategoryDAO(context).getCategoryByParentId(mParentId);
            CategoriesRecyclerViewAdapter adapter = new CategoriesRecyclerViewAdapter(categoryItems, mListener, context, mParentId);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(CategoryItem item);
    }
}
