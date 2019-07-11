package com.huanglf.test16.ui.ty;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.editText.ExtendEditText;
import com.huanglf.test16.repository.editText.ExtendEditTextListener;
import com.huanglf.test16.repository.editText.Rule;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetailNote extends Fragment {
    private ImageView btnBack,btnSave = null;
    private ImageButton increase,decrease;
    private ImageButton mIbBold;
    private ImageButton mIbItalic;
    private ImageButton mIbUnderline;
    private ImageButton mIbStrikethrough;
    private ImageButton mIbLink;
    private ImageButton mIbBullet;
    private ImageButton mIbClear;
    private ExtendEditText editText;
    private SaveViewModel saveViewModel;

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
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setupExtendEditText();
        saveViewModel = ViewModelProviders.of(this).get(SaveViewModel.class);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = saveViewModel.getTitle((editText.getText()).toString());
                String content = Html.toHtml(editText.getText());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String date = df.format(new Date());
                //后面修改，通过接收数据来判断是否为新建笔记
                int id = 0;
                if (id == 0) {
                    saveViewModel.saveNote(title, content, date);
                } else {
                    saveViewModel.saveNote(id, title, content, date);
                }
                Log.e("myLog", "has save the note");
                Navigation.findNavController(view)
                        .navigate(R.id.action_fragmentDetailNote_to_testFragment);
            }
        });

        /**
         * set Click Listener for every font style change button
         */
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.increaseSize();
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.decreaseSize();
            }
        });

        /**
         * set Click Listener for every font style change button
         */
        mIbBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBold();
            }
        });
        mIbItalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItalic();
            }
        });
        mIbUnderline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectUnderline();
            }
        });
        mIbStrikethrough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStrikethrough();
            }
        });
        mIbLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLink();
            }
        });
        mIbBullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBullet();
            }
        });
        mIbClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickClear();
            }
        });
    }

    private void initView(View view){
        btnBack = view.findViewById(R.id.left_user);
        btnSave = view.findViewById(R.id.right_new);
        increase = view.findViewById(R.id.increase);
        decrease = view.findViewById(R.id.decrease);
        mIbBold = view.findViewById(R.id.ib_bold);
        mIbBullet = view.findViewById(R.id.ib_bullet);
        mIbClear = view.findViewById(R.id.ib_clear);
        mIbItalic = view.findViewById(R.id.ib_italic);
        mIbLink = view.findViewById(R.id.ib_link);
        mIbStrikethrough = view.findViewById(R.id.ib_strikethrough);
        mIbUnderline = view.findViewById(R.id.ib_underline);
        editText = view.findViewById(R.id.extend_edit_text);
        editText.requestFocus();
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
