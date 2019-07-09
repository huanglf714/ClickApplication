package com.huanglf.test16.ui.css;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.impl.UserRepositoryImpl;

import cn.bmob.v3.BmobUser;

import static com.huanglf.test16.ClickApplication.sharedPreferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPersonInfo extends Fragment {
    ImageView btnBack = null;
    TextView accountTv = null;
    TextView changePasswordTv = null;
    Button logout = null;

    public FragmentPersonInfo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String accountStr = sharedPreferences.getString("account", "");
        accountTv = view.findViewById(R.id.accountTv);
        accountTv.setText(accountStr);
        logout = view.findViewById(R.id.logout);
        btnBack = view.findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.toMainFromPerson);
            }
        });
        changePasswordTv = view.findViewById(R.id.changePasswordTv);
        changePasswordTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ChangePasswordViewModel.setChangePasswordLiveData();
                Navigation.findNavController(getView()).navigate(R.id.action_fragmentPersonInfo_to_fragmentChangePassword2);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().remove("isLogin").commit();
                UserRepositoryImpl.getInstance().setLoginUserData(new MutableLiveData<String>());
                Navigation.findNavController(v).navigate(R.id.action_fragmentPersonInfo_to_fragmentLaunch);
            }
        });
    }
}
