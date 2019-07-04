package com.huanglf.test16.repository.impl;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huanglf.test16.repository.IUserRepository;
import com.huanglf.test16.util.MessageUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Date: 2019/7/4
 * Author: JinYue
 * description: 用户登录注册和个人信息业务
 */
public class UserRepositoryImpl extends LogInListener<BmobUser> implements IUserRepository {
    private static UserRepositoryImpl userRepositoryImpl;
    private final MutableLiveData<BmobUser> userLiveData = new MutableLiveData<>();

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if (userRepositoryImpl == null) {
            userRepositoryImpl = new UserRepositoryImpl();
        }
        return userRepositoryImpl;
    }

    @Override
    public void loginWithPassword(String account, String password) {
        BmobUser.loginByAccount(account, password, this);
    }


    @Override
    public void loginWithWeChat() {
    }

    @Override
    public void done(BmobUser bmobUser, BmobException e) {
        if (bmobUser == null && e != null) {
            Log.e("myLog", e.toString() + "=================");
            MessageUtil.error(e.getMessage());
        } else {
            Log.d("myLog", "done: --------------------------------------------");
            userLiveData.postValue(bmobUser);
        }

    }

    @Override
    public MutableLiveData<BmobUser> getUserLiveData() {
        return this.userLiveData;
    }
}
