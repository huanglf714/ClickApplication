package com.huanglf.test16.ui.css;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.huanglf.test16.R;
import com.huanglf.test16.repository.impl.UserRepositoryImpl;

import static com.huanglf.test16.ClickApplication.sharedPreferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChangePassword extends Fragment {
    private ChangePasswordViewModel changePasswordViewModel;
    Button button = null;
    EditText oldPasswordText = null;
    EditText newPasswordText = null;
    EditText newPasswordAgainText = null;
    ImageView back = null;


    public FragmentChangePassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        changePasswordViewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel.class);
        button = view.findViewById(R.id.changePasswordBtn);
        back = view.findViewById(R.id.back_img);
        oldPasswordText = view.findViewById(R.id.oldPasswordText);
        newPasswordText = view.findViewById(R.id.newPasswordText);
        newPasswordAgainText = view.findViewById(R.id.newPasswordAgainText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = oldPasswordText.getText().toString();
                String newPassword = newPasswordText.getText().toString();
                String newPasswordAgain = newPasswordAgainText.getText().toString();
                changePasswordViewModel.changePassword(oldPassword, newPassword, newPasswordAgain);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fragmentChangePassword_to_fragmentPersonInfo);
            }
        });

        ChangePasswordViewModel.getChangePasswordLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT);
                sharedPreferences.edit().remove("isLogin").commit();
//                sharedPreferences.edit().remove("account").commit();
                sharedPreferences.edit().remove("password").commit();
                UserRepositoryImpl.getInstance().setLoginUserData(new MutableLiveData<String>());
                Navigation.findNavController(getView()).navigate(R.id.action_fragmentChangePassword_to_fragmentLaunch);
            }
        });
    }


}
