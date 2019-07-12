package com.huanglf.test16.ui.ty;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.database.Tag;
import com.huanglf.test16.ui.css.MyTagRecyclerViewAdapter;
import com.huanglf.test16.ui.css.TagFragment;
import com.huanglf.test16.ui.css.TagListViewModel;
import com.huanglf.test16.ui.ty.dummy.DummyContent;
import com.huanglf.test16.ui.ty.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ChooseTagFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private ChooseTagFragment.OnListFragmentInteractionListener mListener;
    private TagListViewModel tagListViewModel;
    private List<Tag> tagList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemTouchHelper itemTouchHelper;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChooseTagFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ChooseTagFragment newInstance(int columnCount) {
        ChooseTagFragment fragment = new ChooseTagFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
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
        tagListViewModel = ViewModelProviders.of(this).get(TagListViewModel.class);
        tagListViewModel.getTagList().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> list) {
                tagList = list;
                tagList.add(new Tag(1, "生活", 0, R.drawable.tag));
                tagList.add(new Tag(2, "工作", 0, R.drawable.tag1));
                tagList.add(new Tag(3, "学习", 0, R.drawable.tag2));
                tagList.add(new Tag(4, "娱乐", 0, R.drawable.tag3));
                tagList.add(new Tag(5, "购物", 0, R.drawable.tag4));
                tagList.add(new Tag(6, "吃喝", 0, R.drawable.tag5));
                tagList.add(new Tag(1, "生活", 0, R.drawable.tag));
                tagList.add(new Tag(2, "工作", 0, R.drawable.tag1));
                tagList.add(new Tag(3, "学习", 0, R.drawable.tag2));
                tagList.add(new Tag(4, "娱乐", 0, R.drawable.tag3));
                tagList.add(new Tag(5, "购物", 0, R.drawable.tag4));
                tagList.add(new Tag(6, "吃喝", 0, R.drawable.tag5));
                recyclerView.setAdapter(new TagRecyclerViewAdapter(tagList, mListener));
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
        void onListFragmentInteraction(Tag item);
    }
}
