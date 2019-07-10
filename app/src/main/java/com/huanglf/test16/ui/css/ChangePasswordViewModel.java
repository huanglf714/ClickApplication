package com.huanglf.test16.ui.css;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.IUserRepository;
import com.huanglf.test16.repository.impl.UserRepositoryImpl;
import com.huanglf.test16.util.MessageUtil;

import static com.huanglf.test16.ClickApplication.sharedPreferences;

/**
 * @author chenshanshan
 * @time 2019/7/8 15:37
 */
public class ChangePasswordViewModel extends ViewModel {
    private IUserRepository userRepository = UserRepositoryImpl.getInstance();
    public static MutableLiveData<String> changePasswordLiveData = new MutableLiveData<>();

    public void changePassword(String oldPassword,
                               String newPassword, String newPasswordAgain) {
        Log.e("Mylog", sharedPreferences.getString("password", "") + "fffffffffffff");
        if (!oldPassword.equals(sharedPreferences.getString("password", ""))) {
            MessageUtil.error("原密码不正确");
        } else {
            userRepository.changePwd(oldPassword, newPassword, newPasswordAgain);
        }
    }

    public static MutableLiveData<String> getChangePasswordLiveData() {
        return changePasswordLiveData;
    }

    public static void setChangePasswordLiveData() {
        ChangePasswordViewModel.changePasswordLiveData = new MutableLiveData<>();
    }
}
