package com.huanglf.test16.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.impl.NoteRepository;
import com.huanglf.test16.ui.jy.NoteFragment;
import com.huanglf.test16.ui.jy.NoteListViewModel;
import com.huanglf.test16.util.MessageUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;


public class MainActivity extends AppCompatActivity implements NoteFragment.OnListFragmentInteractionListener {
    private NoteListViewModel noteListViewModel;
    NoteRepository noteRepository = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QMUIStatusBarHelper.translucent(this);
        noteListViewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        noteRepository = NoteRepository.getInstance();
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
    public void onNoteListListener(Note note) {
    }

    @Override
    public void onShareListener(Note note) {

    }

    @Override
    public void onFavorListener(Note note) {
        noteListViewModel.setFavor(note);
    }

    @Override
    public void onDeleteListener(Note note) {
        noteListViewModel.removeItem(note);
    }
}
