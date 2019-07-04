package com.huanglf.test16.ui.css;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.IUserRepository;
import com.huanglf.test16.repository.impl.UserRepositoryImpl;
import com.huanglf.test16.util.MessageUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

/**
 * Date: 2019/7/3
 * Author: huanglf
 * description:
 */
public class LoginViewModel extends ViewModel {
    private MutableLiveData<BmobUser> isLogin = new MutableLiveData();
    private IUserRepository userRepository = UserRepositoryImpl.getInstance();

    /**
     * 手机账户+密码登录
     */
    public void loginWithPassword(String account, String password) {
        try {
            isLogin.postValue(userRepository.loginWithPassword(account, password));
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtil.error(e.getMessage());

        }
    }


    /**
     * 微信第三方平台登录
     */
    public void loginWithWeChat() {
        try {
            isLogin.postValue(userRepository.loginWithWeChat());
        } catch (BmobException e) {
            e.printStackTrace();
            MessageUtil.error(e.getMessage());
        }
    }

    public MutableLiveData<BmobUser> getIsLogin() {
        return isLogin;
    }
}
