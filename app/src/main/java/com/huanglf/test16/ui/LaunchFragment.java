package com.huanglf.test16.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huanglf.test16.R;

import static com.huanglf.test16.ClickApplication.sharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaunchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("myLog","**********************");
        return inflater.inflate(R.layout.fragment_launch, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(sharedPreferences.getBoolean("isLogin",false)){
            Log.e("myLog","已登陆过");
            Navigation.findNavController(getView()).navigate(R.id.actionLaunchMain);
        }else{
            Log.e("myLog","第一次登陆");
            Navigation.findNavController(getView()).navigate(R.id.actionLaunchLogin);
        }
    }
}
