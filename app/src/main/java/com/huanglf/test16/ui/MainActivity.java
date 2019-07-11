package com.huanglf.test16.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.database.Note;
import android.view.View;

import com.huanglf.test16.repository.database.Tag;
import com.huanglf.test16.repository.impl.NoteRepositoryImpl;
import com.huanglf.test16.ui.css.TagFragment;
import com.huanglf.test16.ui.jy.NoteFragment;
import com.huanglf.test16.ui.jy.NoteListViewModel;
import com.huanglf.test16.util.MessageUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import cn.sharesdk.onekeyshare.OnekeyShare;


public class MainActivity extends AppCompatActivity implements
        NoteFragment.OnListFragmentInteractionListener, TagFragment.OnListFragmentInteractionListener {
    private NoteListViewModel noteListViewModel;
    NoteRepositoryImpl noteRepository = null;

    private final String ARG_DATA = "note_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QMUIStatusBarHelper.translucent(this);
        noteListViewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        noteRepository = NoteRepositoryImpl.getInstance();
        //全局监听异常
        MessageUtil.getExceptionLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
        //全局监听剪切板
        final ClipboardManager clipboard = (ClipboardManager)getApplicationContext().getSystemService(getApplicationContext().CLIPBOARD_SERVICE);
        clipboard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {
                    ClipData data = clipboard.getPrimaryClip();
                    ClipData.Item item = data.getItemAt(0);
                    String content = item.getText().toString();
                    Log.e("myLog",content+"--------------------");
                    Note note = new Note();
                    note.setTitle(content.substring(0,(content.length()<8?content.length()-1:7)));
                    note.setContent(content);
                    noteRepository.insertNote(note);
                }
        });
    }

    @Override
    public void onNoteListListener(View v, Note note) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, note);
        Navigation.findNavController(v).navigate(R.id.toDetailFromMain, args);
    }

    @Override
    public void onShareListener(Note note) {
        showShare(note);
    }

    @Override
    public void onFavorListener(Note note) {
        noteListViewModel.setFavor(note);
    }

    @Override
    public void onDeleteListener(Note note) {
        noteListViewModel.removeItem(note);
    }

    private void showShare(Note note) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(getString(R.string.app_name));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我的分享");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onListFragmentInteraction(Tag item) {

    }
}
