package com.huanglf.test16.ui.ty;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.editText.ExtendEditText;
import com.huanglf.test16.repository.editText.ExtendEditTextListener;
import com.huanglf.test16.repository.editText.Rule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetailNote extends Fragment {
    public final String ARG_DATA = "note_data";
    private ImageView btnSave = null;
    private ImageButton increase, decrease;
    private ImageButton mIbBold;
    private ImageButton mIbItalic;
    private ImageButton mIbUnderline;
    private ImageButton mIbStrikethrough;
    private ImageButton mIbLink;
    private ImageButton mIbBullet;
    private ImageButton mIbClear;
    private ImageView tag;
    private ExtendEditText editText;
    private SaveViewModel saveViewModel;
    private Note note;
    private Boolean isNew = true;

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
        init(view);
        setupExtendEditText();
        initEditText();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note = constructNote();
                saveViewModel.saveNote(note,isNew);
                Navigation.findNavController(view)
                        .navigate(R.id.action_fragmentDetailNote_to_testFragment);

            }
        });

        /**
         * set diaolog for select note tag
         */
        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
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
    }

    private void init(View view) {
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
        tag = view.findViewById(R.id.tag);
        editText = view.findViewById(R.id.extend_edit_text);
        saveViewModel = ViewModelProviders.of(this).get(SaveViewModel.class);
    }

    private void initEditText(){
        Bundle args = getArguments();
         if(args != null){
             note = (Note) args.getSerializable(ARG_DATA);
             if (note != null) {
                 Spanned spanned = Html.fromHtml(note.getContent());
                 editText.setText(spanned);
                 isNew = false;
             }
        }

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

    private Note constructNote(){
        String title = saveViewModel.getTitle((editText.getText()).toString());
        String content = Html.toHtml(editText.getText());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = df.format(new Date());
        if(isNew){
            note.setCreateDate(date);
        }
        note.setTitle(title);
        note.setContent(content);
        note.setUpdateDate(date);
        return  note;
    }

    private void setDialog() {
        Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View dialogView = inflater.inflate(R.layout.dialog_view, null);
        //放入自定义布局
        dialog.setContentView(dialogView);
        //设置dialog宽高
        WindowManager.LayoutParams layoutManger = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        layoutManger.copyFrom(window.getAttributes());
        layoutManger.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutManger.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        window.setAttributes(layoutManger);
    }
}
