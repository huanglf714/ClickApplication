package com.huanglf.test16.ui.jy;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.huanglf.test16.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2019/7/5
 * Author: JinYue
 * description: 主页面的样式
 */
public class MainTabAdapter extends FragmentPagerAdapter {
    private final int COUNT;
    private final int COLUMN_COUNT;
    private final String ARG_COLUMN_COUNT;
    private final String ARG_IS_FAVOR;
    private final boolean IS_FAVOR;
    private final List<NoteFragment> noteFragmentList;

    public MainTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        COUNT = 2;
        COLUMN_COUNT = 1;
        ARG_COLUMN_COUNT = "column-count";
        ARG_IS_FAVOR = "is-favor";
        IS_FAVOR = false;
        noteFragmentList = new ArrayList<>(4);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
//        if (noteFragmentList.size() <= position) {
        NoteFragment noteFragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, COLUMN_COUNT);
        if (position == 1) {
            args.putBoolean(ARG_IS_FAVOR, !IS_FAVOR);
        } else {
            args.putBoolean(ARG_IS_FAVOR, !IS_FAVOR);
        }
        noteFragment.setArguments(args);
        noteFragmentList.add(noteFragment);
//        }
        return noteFragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
