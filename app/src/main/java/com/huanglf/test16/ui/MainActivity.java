package com.huanglf.test16.ui;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.database.Note;
import com.huanglf.test16.repository.database.Tag;
import com.huanglf.test16.ui.css.TagFragment;
import com.huanglf.test16.ui.jy.NoteFragment;
import com.huanglf.test16.ui.jy.NoteListViewModel;
import com.huanglf.test16.ui.ty.ChooseTagFragment;
import com.huanglf.test16.ui.ty.SaveViewModel;
import com.huanglf.test16.util.MessageUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;


public class MainActivity extends AppCompatActivity implements
        NoteFragment.OnListFragmentInteractionListener, TagFragment.OnListFragmentInteractionListener
        , ChooseTagFragment.OnListFragmentInteractionListener{
    private NoteListViewModel noteListViewModel;
    private SaveViewModel saveViewModel;
    public final String ARG_DATA = "note_data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QMUIStatusBarHelper.translucent(this);
        noteListViewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        saveViewModel = ViewModelProviders.of(this).get(SaveViewModel.class);

        //全局监听异常
        MessageUtil.getExceptionLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onNoteListListener(View v, Note note) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA,note);
        Navigation.findNavController(v).navigate(R.id.toDetailFromMain,args);
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

    @Override
    public void onListFragmentInteraction(Tag item) {

    }

}
