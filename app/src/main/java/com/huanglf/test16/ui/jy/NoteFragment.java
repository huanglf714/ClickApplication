package com.huanglf.test16.ui.jy;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.database.Note;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NoteFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<Note> mNoteList;
    private RecyclerView recyclerView;
    private ItemTouchHelperCallback itemTouchHelperCallback;
    private ItemTouchHelper itemTouchHelper;
    private NoteListViewModel noteListViewModel;
    private boolean favorTag;
    private boolean isFirst;
    private final String ARG_IS_FAVOR;
    private MyNoteRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment() {
        super();
        itemTouchHelperCallback = new ItemTouchHelperCallback();
        itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        favorTag = false;
        isFirst = true;
        ARG_IS_FAVOR = "is-favor";
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NoteFragment newInstance(int columnCount) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            favorTag = getArguments().getBoolean(ARG_IS_FAVOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("JY", "onViewCreated: ----------------------");
        noteListViewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        noteListViewModel.getNotes(favorTag);
        noteListViewModel.getNoteList(favorTag).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> list) {
                Log.e("JY", "onChanged: ------------------------------" + list.size());
                mNoteList = list;
                if (isFirst) {
                    adapter = new MyNoteRecyclerViewAdapter(mNoteList, mListener);
                    recyclerView.setAdapter(adapter);
                    itemTouchHelper.attachToRecyclerView(recyclerView);
                    isFirst = false;
                } else {
                    adapter.setmValues(mNoteList);
                    adapter.notifyData();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
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
    public interface OnListFragmentInteractionListener {

        /**
         * 监听列表项内容点击事件
         *
         * @param v
         * @param note
         */
        void onNoteListListener(View v, Note note);

        /**
         * 监听分享点击按钮
         *
         * @param note
         */
        void onShareListener(Note note);

        /**
         * 监听收藏点击按钮
         *
         * @param note
         */
        void onFavorListener(Note note);

        /**
         * 监听删除点击按钮
         *
         * @param note
         */
        void onDeleteListener(Note note);
    }
}
