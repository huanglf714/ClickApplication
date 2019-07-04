package com.huanglf.test16.ui.css;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.Message;



/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegister extends Fragment {
    RegisterViewModel registerViewModel;
    private Button btnSendCode;
    private Button btnRegister;
    EditText account = null;
    EditText password = null;
    EditText confirmCode = null;

    public FragmentRegister() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        account = view.findViewById(R.id.account);
        password = view.findViewById(R.id.pwd);
        btnSendCode = view.findViewById(R.id.sendConfirmCode);
        //点击发送验证码
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterViewModel.sendConfirmCode(account.getText().toString());
            }
        });
        confirmCode = view.findViewById(R.id.confirmCode);
        btnRegister = view.findViewById(R.id.login);
        //点击注册
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                registerViewModel.register(account.getText().toString(),
                        password.getText().toString(),
                        confirmCode.getText().toString());

            }
        });

        //数据监听
        registerViewModel.getIsRegister().observe(this, new Observer<Message>() {
            @Override
            public void onChanged(Message message) {
                if(message.getResultCode()==000){
                    Toast.makeText(getContext(),"注册成功",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getView()).navigate(R.id.toLoginFromRegister);
                }else {
                    Toast.makeText(getContext(),"注册失败，"+message.getDesc(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
