package com.huanglf.test16.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.view.Window;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.Message;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class MainActivity extends AppCompatActivity {
    private MutableLiveData<Message> isLogin = new MutableLiveData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
