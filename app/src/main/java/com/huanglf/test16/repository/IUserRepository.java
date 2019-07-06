package com.huanglf.test16.repository;

import androidx.lifecycle.MutableLiveData;

import cn.bmob.v3.BmobUser;

/**
 * Date: 2019/7/4
 * Author: JinYue
 * description:
 */
public interface IUserRepository {
    /**
     * 用户通过账号密码登录
     *
     * @param account
     * @param password
     * @return
     */
    void loginWithPassword(String account, String password);


    /**
     * 用户通过微信登录
     */
    void loginWithWeChat();

    /**
     * 发送注册验证码
     */
    void sendConfirmCode(String account);

    /**
     * 用户注册
     */
    void register(String account,String confirmCode,String password,String repeatPwd);

    void alterPwd(String account,String confirmCode,String password,String repeatPwd);

    MutableLiveData<BmobUser> getUserLiveData();

    MutableLiveData<BmobUser> getRegisterUserData();
}
