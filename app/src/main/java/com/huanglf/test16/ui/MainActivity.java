package com.huanglf.test16.ui;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.ui.jy.NoteFragment;
import com.huanglf.test16.ui.jy.NoteListViewModel;
import com.huanglf.test16.util.MessageUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;


public class MainActivity extends AppCompatActivity implements NoteFragment.OnListFragmentInteractionListener {
    private NoteListViewModel noteListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QMUIStatusBarHelper.translucent(this);
        noteListViewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);

        //全局监听异常
        MessageUtil.getExceptionLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
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
