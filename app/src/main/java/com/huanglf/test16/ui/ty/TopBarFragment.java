package com.huanglf.test16.ui.ty;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.huanglf.test16.R;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

/**
 * Date: 2019/7/6
 * Author: JinYue
 * description:
 */
public class TopBarFragment extends Fragment {
    private QMUITopBarLayout mTopBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_note, container, false);
        mTopBar = view.findViewById(R.id.topBar);
        initTopBar();
        ImageButton back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.toMainFromDetail);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initTopBar() {
        mTopBar.setTitle("编辑备忘录");
        mTopBar.addLeftImageButton(R.drawable.back, R.id.back);
        mTopBar.addRightImageButton(R.drawable.save, R.id.save);
    }
}
