package com.huanglf.test16.ui.ty;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.editText.ExtendEditText;
import com.huanglf.test16.repository.editText.ExtendEditTextListener;
import com.huanglf.test16.repository.editText.Rule;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetailNote extends Fragment {
    private Button btnBack,btnSave = null;
    private ImageButton mIbBold;
    private ImageButton mIbItalic;
    private ImageButton mIbUnderline;
    private ImageButton mIbStrikethrough;
    private ImageButton mIbLink;
    private ImageButton mIbBullet;
    private ImageButton mIbClear;
    private ExtendEditText editText;

    public FragmentDetailNote() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setupExtendEditText();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.toMainFromDetail);
            }
        });
        mIbBold.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectBold();
            }
        });
        mIbItalic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectItalic();
            }
        });
        mIbUnderline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectUnderline();
            }
        });
        mIbStrikethrough.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectStrikethrough();
            }
        });
        mIbLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickLink();
            }
        });
        mIbBullet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickBullet();
            }
        });
        mIbClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickClear();
            }
        });
    }

    private void initView(View view){
        btnBack = view.findViewById(R.id.back);
        btnSave = view.findViewById(R.id.save);
        mIbBold = view.findViewById(R.id.ib_bold);
        mIbBullet = view.findViewById(R.id.ib_bullet);
        mIbClear = view.findViewById(R.id.ib_clear);
        mIbItalic = view.findViewById(R.id.ib_italic);
        mIbLink = view.findViewById(R.id.ib_link);
        mIbStrikethrough = view.findViewById(R.id.ib_strikethrough);
        mIbUnderline = view.findViewById(R.id.ib_underline);
        editText = view.findViewById(R.id.extend_edit_text);
    }

    private void setupExtendEditText() {
        editText.setExtendEditTextListener(new ExtendEditTextListener() {
            @Override
            public void onCursorChange(int selStart, int selEnd, List<Integer> formats) {
                if (formats != null) {
                    mIbBold.setSelected(formats.contains(ExtendEditText.STYLE_BOLD));
                    mIbItalic.setSelected(formats.contains(ExtendEditText.STYLE_ITALIC));
                    mIbUnderline.setSelected(formats.contains(ExtendEditText.STYLE_UNDERLINE));
                    mIbStrikethrough.setSelected(formats.contains(ExtendEditText.STYLE_STRIKETHROUGH));
                    mIbLink.setSelected(formats.contains(ExtendEditText.STYLE_LINK));
                }
            }
        });
    }
    public void selectBold() {
        if (mIbBold.isSelected()) {
            mIbBold.setSelected(false);
        } else {
            mIbBold.setSelected(true);
        }
        editText.bold();
    }

    public void selectItalic() {
        if (mIbItalic.isSelected()) {
            mIbItalic.setSelected(false);
        } else {
            mIbItalic.setSelected(true);
        }
        editText.italic();
    }

    public void selectUnderline() {
        if (mIbUnderline.isSelected()) {
            mIbUnderline.setSelected(false);
        } else {
            mIbUnderline.setSelected(true);
        }
        editText.underline();
    }

    public void selectStrikethrough() {
        if (mIbStrikethrough.isSelected()) {
            mIbStrikethrough.setSelected(false);
        } else {
            mIbStrikethrough.setSelected(true);
        }
        editText.strikethrough();
    }

    public void onClickLink() {
        if (mIbLink.isSelected()) {
            mIbLink.setSelected(false);
        } else {
            mIbLink.setSelected(true);
        }
        editText.link();
    }

    public void onClickBullet() {
        editText.setRule(Rule.EXCLUSIVE_EXCLUSIVE);
        editText.bullet();
        editText.setRule(Rule.EXCLUSIVE_INCLUSIVE);
    }


    public void onClickClear() {
        editText.clear();
    }
}
