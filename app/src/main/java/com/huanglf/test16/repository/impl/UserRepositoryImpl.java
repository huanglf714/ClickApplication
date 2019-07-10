package com.huanglf.test16.repository.impl;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huanglf.test16.repository.IUserRepository;
import com.huanglf.test16.util.MessageUtil;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.huanglf.test16.ui.css.ChangePasswordViewModel.changePasswordLiveData;

/**
 * Date: 2019/7/4
 * Author: JinYue
 * description: 用户登录注册和个人信息业务
 */
public class UserRepositoryImpl implements IUserRepository {
    private static UserRepositoryImpl userRepositoryImpl;
    private MutableLiveData<String> loginUserData = new MutableLiveData<>();
    private MutableLiveData<BmobUser> registerUserData = new MutableLiveData<>();


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
        BmobUser.loginByAccount(account, password, new LogInListener<BmobUser>() {
            @Override
            public void done(BmobUser s, BmobException e) {
                if (e != null) {
                    Log.e("myLog", e.toString());
                    MessageUtil.error("用户名或密码错误");
                } else {
                    Log.e("myLog", "*******************************");
                    loginUserData.postValue("登录成功");
                }
            }
        });
    }

    @Override
    public void loginWithWeChat() {
    }

    @Override
    public void sendConfirmCode(String account) {
        BmobSMS.requestSMSCode(account, "template1",
                new QueryListener<Integer>() {
                    @Override
                    public void done(Integer smsId, BmobException ex) {
                        if (ex == null) {//验证码发送成功
                        } else {
                            MessageUtil.error("验证码发送失败");
                        }
                    }
                });
    }

    @Override
    public void register(final String account, final String confirmCode,
                         final String password, final String repeatPwd) {
        if (verifyPwd(password, repeatPwd) && verifyConfirmCode(account, confirmCode)) {
            final BmobUser newUser = new BmobUser();
            newUser.setUsername(account);
            newUser.setMobilePhoneNumber(account);
            newUser.setPassword(password);
            newUser.signUp(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                    if (e != null) {
                        MessageUtil.error("该手机号已被注册");
                    } else {
                        registerUserData.postValue(newUser);
                    }
                }
            });
        }
    }

    @Override
    public void alterPwd(final String account, String confirmCode,
                         final String password, String repeatPwd) {
        if (verifyPwd(password, repeatPwd)) {
            BmobUser.resetPasswordBySMSCode(confirmCode, password, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        BmobUser user = new BmobUser();
                        user.setUsername(account);
                        user.setMobilePhoneNumber(account);
                        user.setPassword(password);
                        registerUserData.postValue(user);
                    } else {
                        MessageUtil.error("验证码输入错误");
                    }
                }
            });
        }
    }

    private boolean verifyPwd(String password, String repeatPwd) {
        if (password == null || password.trim() == "") {
            MessageUtil.error("密码无效");
            return false;
        } else {
            if (!password.equals(repeatPwd)) {
                MessageUtil.error("两次输入的密码不一致");
                return false;
            }
        }
        return true;
    }

    private boolean verifyConfirmCode(final String account, final String confirmCode) {
        final Boolean[] result = {true};
        BmobSMS.verifySmsCode(account, confirmCode, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                //短信验证码已验证成功
                if (e == null) {
                    result[0] = true;
                } else {
                    result[0] = false;
                    MessageUtil.error("验证码输入错误");
                }
            }
        });
        return result[0];
    }


    @Override
    public MutableLiveData<String> getLoginUserData() {
        return loginUserData;
    }

    @Override
    public MutableLiveData<BmobUser> getRegisterUserData() {
        return registerUserData;
    }

    @Override
    public void setLoginUserData(MutableLiveData<String> loginUserData) {
        this.loginUserData = loginUserData;
    }

    @Override
    public void setRegisterUserData(MutableLiveData<BmobUser> registerUserData) {
        this.registerUserData = registerUserData;
    }

    @Override
    public void changePwd(String oldPassword, String newPassword, String newPasswordAgain) {
        if (!verifyPwd(newPassword, newPasswordAgain)) {
            MessageUtil.error("两次输入密码不一致");
        } else {
            BmobUser.updateCurrentUserPassword(oldPassword, newPassword, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e != null) {
                        MessageUtil.error("修改密码失败");
                    } else {
                        changePasswordLiveData.postValue("修改密码成功");
                    }
                }
            });
        }
    }
}
