package com.huanglf.test16.repository.impl;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huanglf.test16.repository.IUserRepository;
import com.huanglf.test16.repository.Message;
import com.huanglf.test16.util.MessageUtil;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Date: 2019/7/4
 * Author: JinYue
 * description: 用户登录注册和个人信息业务
 */
public class UserRepositoryImpl extends LogInListener<BmobUser> implements IUserRepository {
    private static UserRepositoryImpl userRepositoryImpl;
    private final MutableLiveData<BmobUser> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<BmobUser> registerUserData = new MutableLiveData<>();


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
    public void sendConfirmCode(String account) {
        BmobSMS.requestSMSCode(account,"template1",
                new QueryListener<Integer>() {
                    @Override
                    public void done(Integer smsId, BmobException ex) {
                        if(ex==null){//验证码发送成功
                        }else {
                            MessageUtil.error("验证码发送失败");
                        }
                    }
                });
    }

    @Override
    public void register(final String account,final String confirmCode,
                         final String password,final String repeatPwd) {
        if(verifyPwd(password,repeatPwd)&&verifyConfirmCode(account,confirmCode)){
            final BmobUser newUser = new BmobUser();
            newUser.setUsername(account);
            newUser.setMobilePhoneNumber(account);
            newUser.setPassword(password);
            newUser.signUp(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                    if(e!=null){
                        MessageUtil.error("该手机号已被注册");
                    }else{
                        registerUserData.postValue(newUser);
                    }
                }});
        }
    }

    private boolean verifyPwd(String password,String repeatPwd){
        if(password==null||password.trim()==""){
            MessageUtil.error("密码无效");
            return false;
        }else {
            if(!password.equals(repeatPwd)){
                MessageUtil.error("两次输入的密码不一致");
                return false;
            }
        }
        return true;
    }

    private boolean verifyConfirmCode(final String account,final String confirmCode){
        final Boolean[] result = {true};
        BmobSMS.verifySmsCode(account, confirmCode, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                //短信验证码已验证成功
                if(e==null){
                    result[0] = true;
                }else{
                    result[0] = false;
                    MessageUtil.error("验证码输入错误");
                }
            }
        });
        return result[0];
    }

    @Override
    public void done(BmobUser bmobUser, BmobException e) {
        if (bmobUser == null && e != null) {
            MessageUtil.error(e.getMessage());
        } else {
            userLiveData.postValue(bmobUser);
        }

    }

    @Override
    public MutableLiveData<BmobUser> getUserLiveData() {
        return this.userLiveData;
    }

    public MutableLiveData<BmobUser> getRegisterUserData() {
        return registerUserData;
    }
}
