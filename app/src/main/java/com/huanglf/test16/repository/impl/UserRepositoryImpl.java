package com.huanglf.test16.repository.impl;


import android.util.Log;

import com.huanglf.test16.repository.IUserRepository;

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
    /**
     * 1.装exception
     * 2.装数据bean
     */
    private final Object[] values = new Object[2];

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if (userRepositoryImpl == null) {
            userRepositoryImpl = new UserRepositoryImpl();
        }
        return userRepositoryImpl;
    }

    @Override
    public BmobUser loginWithPassword(String account, String password) throws BmobException {
        BmobUser.loginByAccount(account, password, this);
        judgeException();
        return (BmobUser)values[1];
    }


    @Override
    public BmobUser loginWithWeChat() throws BmobException {
        judgeException();
        return (BmobUser)values[1];
    }

    @Override
    public void done(BmobUser bmobUser, BmobException e) {
        if (bmobUser == null && e != null) {
            Log.e("UserRepositoryImpl", "done: ", e);
            values[0] = e;
        }else{
            Log.d("user", "done: --------------------------------------------");
            values[1] = bmobUser;
        }

    }

    /**
     * 判断当前是否存在异常
     *
     * @throws BmobException
     */
    private void judgeException() throws BmobException {
        if (values[0] != null) {
            BmobException exception = (BmobException) values[0];
            values[0] = null;
            throw exception;
        }
    }
}
