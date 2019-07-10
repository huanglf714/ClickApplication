package com.huanglf.test16.ui.jy;

import android.os.Bundle;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


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

    public MainTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        COUNT = 2;
        COLUMN_COUNT = 1;
        ARG_COLUMN_COUNT = "column-count";
        ARG_IS_FAVOR = "is-favor";
        IS_FAVOR = false;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.e("JY", "getItem: ----------------------------" + position);
        NoteFragment noteFragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, COLUMN_COUNT);
        if (position == 1) {
            args.putBoolean(ARG_IS_FAVOR, !IS_FAVOR);
            noteFragment.setArguments(args);
            return noteFragment;
        } else if (position == 0) {
            args.putBoolean(ARG_IS_FAVOR, IS_FAVOR);
            noteFragment.setArguments(args);
            return noteFragment;
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
