package com.huanglf.test16.ui.css;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huanglf.test16.R;
import com.huanglf.test16.common.MessageEnum;
import com.huanglf.test16.repository.Message;

import cn.bmob.v3.BmobUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {
    private LoginViewModel loginViewModel;
    EditText account = null;
    EditText password = null;
    Button btnLogin = null;
    TextView registerView = null;

    public FragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        account = view.findViewById(R.id.account);
        password = view.findViewById(R.id.pwd);
        btnLogin = view.findViewById(R.id.login);
        registerView = view.findViewById(R.id.register);
        //手机账户+密码登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                loginViewModel.loginWithPassword(
                        account.getText().toString(), password.getText().toString());
            }
        });

        //第三方账户登录
        //跳转到注册页面
        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("myLog", "-------------------");
                Navigation.findNavController(v).navigate(R.id.toRegisterFromLogin);
            }
        });

        //数据监听
        loginViewModel.getIsLogin().observe(this, new Observer<BmobUser>() {
            @Override
            public void onChanged(BmobUser user) {
                Toast.makeText(getContext(), MessageEnum.LOGIN_SUCCESS.getDesc(), Toast.LENGTH_SHORT);
                Navigation.findNavController(getView()).navigate(R.id.toMainFromLogin);
            }
        });
    }


}
