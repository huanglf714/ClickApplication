package com.huanglf.test16.ui.css;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huanglf.test16.ClickApplication;
import com.huanglf.test16.R;
import com.huanglf.test16.common.MessageEnum;
import com.huanglf.test16.ui.jy.FragmentMain;

import cn.bmob.v3.BmobUser;

import static cn.bmob.v3.Bmob.getApplicationContext;
import static com.huanglf.test16.ClickApplication.sharedPreferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {
    private LoginViewModel loginViewModel;
    EditText account = null;
    EditText password = null;
    Button btnLogin = null;
    TextView registerView = null;
    TextView forgetPwdView = null;
    CheckBox rememberPwd = null;

    public FragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("myLog","login fragment");
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
        forgetPwdView = view.findViewById(R.id.forgetPwd);
        rememberPwd = view.findViewById(R.id.rememberPwd);
        boolean isRemember = sharedPreferences.getBoolean("remember_pwd",false);
        if (isRemember){
            String accountStr = sharedPreferences.getString("account","");
            String passwordStr = sharedPreferences.getString("password","");
            account.setText(accountStr);
            password.setText(passwordStr);
            rememberPwd.setChecked(true);
        }
        //手机账户+密码登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(rememberPwd.isChecked()){
                    sharedPreferences.edit().putBoolean("remember_pwd",true).commit();
                }
                sharedPreferences.edit().putString("account",account.getText().toString()).commit();
                sharedPreferences.edit().putString("password",password.getText().toString()).commit();
                loginViewModel.loginWithPassword(
                        account.getText().toString(), password.getText().toString());
            }
        });

        //第三方账户登录
        //跳转到注册页面
        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","注册");
                Navigation.findNavController(v).navigate(R.id.toRegisterFromLogin,bundle);
            }
        });

        //忘记密码
        forgetPwdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","修改密码");
                Navigation.findNavController(v).navigate(R.id.toRegisterFromLogin,bundle);
            }
        });


        //数据监听
        loginViewModel.getIsLogin().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                sharedPreferences.edit().putBoolean("isLogin",true).commit();
                Log.e("myLog","return the string is "+s);
                Toast.makeText(getContext(), MessageEnum.LOGIN_SUCCESS.getDesc(), Toast.LENGTH_LONG);
                Navigation.findNavController(getView()).navigate(R.id.toMainFromLogin);
            }
        });
    }


}
