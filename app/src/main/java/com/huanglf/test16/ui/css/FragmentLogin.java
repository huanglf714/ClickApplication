package com.huanglf.test16.ui.css;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment{
    private ViewModel loginViewModel = null;
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
        btnLogin = view.findViewById(R.id.signUp);
        btnRegister = view.findViewById(R.id.signIn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.toMainFromLogin);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.toRegisterFromLogin);
            }
        });
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        //数据监听
        ((LoginViewModel) loginViewModel).data.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                //使用新数据更新页面
            }
        });



    }
}
