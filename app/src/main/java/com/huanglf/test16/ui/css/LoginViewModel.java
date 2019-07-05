package com.huanglf.test16.ui.css;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.IUserRepository;
import com.huanglf.test16.repository.impl.UserRepositoryImpl;

import cn.bmob.v3.BmobUser;

/**
 * Date: 2019/7/3
 * Author: huanglf
 * description:
 */
public class LoginViewModel extends ViewModel {
    private IUserRepository userRepository = UserRepositoryImpl.getInstance();

    /**
     * 手机账户+密码登录
     */
    public void loginWithPassword(String account, String password) {
        userRepository.loginWithPassword(account, password);
    }


    /**
     * 微信第三方平台登录
     */
    public void loginWithWeChat() {
        userRepository.loginWithWeChat();
    }

    public MutableLiveData<BmobUser> getIsLogin() {
        return userRepository.getUserLiveData();
    }
}
