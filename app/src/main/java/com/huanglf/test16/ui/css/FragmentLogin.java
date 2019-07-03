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
import android.widget.Toast;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.Message;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment{
    private LoginViewModel loginViewModel;
    EditText account = null;
    EditText password = null;
    Button btnLogin = null;
    Button btnRegister = null;

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
        btnLogin = view.findViewById(R.id.signUp);
        btnRegister = view.findViewById(R.id.signIn);
        //手机账户+密码登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                loginViewModel.loginWithPassword(
                        account.getText().toString(),password.getText().toString());
            }
        });

        //手机账户+验证码登录
        //第三方账户登录
        //跳转到注册页面
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.toRegisterFromLogin);
            }
        });

       //数据监听
        loginViewModel.getIsLogin().observe(this, new Observer<Message>() {
            @Override
            public void onChanged(Message message) {
                if (message.getResultCode()==000){
                    Toast.makeText(getContext(),"登录成功",Toast.LENGTH_SHORT);
                    Navigation.findNavController(getView()).navigate(R.id.toMainFromLogin);
                }else {
                    Toast.makeText(getContext(), "用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


}
