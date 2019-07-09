package com.huanglf.test16.ui;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.huanglf.test16.ClickApplication;
import com.huanglf.test16.R;
import com.huanglf.test16.repository.Note;
import com.huanglf.test16.ui.css.FragmentLogin;
import com.huanglf.test16.ui.css.FragmentRegister;
import com.huanglf.test16.ui.jy.FragmentMain;
import com.huanglf.test16.ui.jy.NoteFragment;
import com.huanglf.test16.util.MessageUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import static com.huanglf.test16.ClickApplication.sharedPreferences;

public class MainActivity extends AppCompatActivity implements NoteFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QMUIStatusBarHelper.translucent(this);


        //全局监听异常
        MessageUtil.getExceptionLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onListFragmentInteraction(Note note) {
        Log.d("JY", "onListFragmentInteraction: ___________________________");
    }
}
