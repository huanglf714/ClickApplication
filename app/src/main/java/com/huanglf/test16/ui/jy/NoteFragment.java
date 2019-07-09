package com.huanglf.test16.ui.jy;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.huanglf.test16.R;
import com.huanglf.test16.dummy.DummyContent;
import com.huanglf.test16.dummy.DummyContent.DummyItem;
import com.huanglf.test16.repository.Note;

import java.util.ArrayList;
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

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment() {
        itemTouchHelperCallback = new ItemTouchHelperCallback();
        itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
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
        }

        Log.e("JY", "onCreate: -------------------------oncreste");
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
            itemTouchHelper.attachToRecyclerView(recyclerView);
            mNoteList = new ArrayList<>(40);
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            mNoteList.add(new Note());
            recyclerView.setAdapter(new MyNoteRecyclerViewAdapter(mNoteList, mListener));
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            private float x1, x2, y1, y2;

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    x1 = e.getX();
                    y1 = e.getY();
                }
                if (e.getAction() == MotionEvent.ACTION_UP) {
                    x2 = e.getX();
                    y2 = e.getY();
                    if (Math.abs(x1 - x2) < 6) {
                        return false;// 距离较小，当作click事件来处理
                    }
                    if(Math.abs(x1 - x2) >60){  // 真正的onTouch事件
                        Log.e("JY", "onInterceptTouchEvent: "+x1);
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

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
        // TODO: Update argument type and name
        void onListFragmentInteraction(Note note);
    }
}
