package com.huanglf.test16.ui.ty;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.editText.ExtendEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {
    private TestViewModel testViewModel = null;


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testViewModel.loadAllUsers().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                Log.e("myLog","visit the note list");
                for (Note note:notes
                     ) {
                    Log.e("myLog",note.toString());
                    Spanned spanned = Html.fromHtml(note.getContent());
                    ExtendEditText editText = view.findViewById(R.id.test_edit);
                    editText.setText(spanned);
                }
            }
        });
    }
}
