package com.huanglf.test16.ui.jy;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.huanglf.test16.R;

/**
 * Date: 2019/7/5
 * Author: JinYue
 * description: 主页面的样式
 */
public class MainTabAdapter extends PagerAdapter {

    private final int COUNT = 2;
    private final int COLUMN_COUNT = 1;
    private final LayoutInflater layoutInflater;
    private final Context context;

    public MainTabAdapter(LayoutInflater layoutInflater, Context context) {
        this.layoutInflater = layoutInflater;
        this.context = context;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = container.getChildAt(position);
        if (view == null) {
            NoteFragment noteFragment = NoteFragment.newInstance(COLUMN_COUNT);
            noteFragment.onAttach(context);
            view = noteFragment.onCreateView(layoutInflater, container, null);
            container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}
