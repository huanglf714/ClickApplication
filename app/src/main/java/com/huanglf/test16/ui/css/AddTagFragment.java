package com.huanglf.test16.ui.css;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTagFragment extends Fragment {
    private TagListViewModel tagListViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tagListViewModel = ViewModelProviders.of(this).get(TagListViewModel.class);
    }
}
