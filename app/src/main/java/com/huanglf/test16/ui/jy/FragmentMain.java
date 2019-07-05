package com.huanglf.test16.ui.jy;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huanglf.test16.R;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment {
    private QMUITabSegment mMainTabSegment;
    private QMUITabSegment.Tab mMainTab, mFavorTab;
    private QMUIViewPager mViewPager;

    public FragmentMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainTabSegment = view.findViewById(R.id.mainTabSegment);
        initTab();
    }

    private void initTab() {
        mMainTab = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.drawable.list),
                ContextCompat.getDrawable(getContext(), R.drawable.list_selected),
                "我的列表", true);
        mFavorTab = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.drawable.favor),
                ContextCompat.getDrawable(getContext(), R.drawable.favor_selected),
                "我的收藏", true);
        mMainTabSegment.addTab(mMainTab);
        mMainTabSegment.addTab(mFavorTab);
        mMainTabSegment.setupWithViewPager(mViewPager, false);
    }
}
