package com.huanglf.test16.ui.css;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.IUserRepository;
import com.huanglf.test16.repository.Message;
import com.huanglf.test16.repository.impl.UserRepositoryImpl;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Date: 2019/7/3
 * Author: huanglf
 * description:
 */
public class RegisterViewModel extends ViewModel {
    private IUserRepository userRepository = UserRepositoryImpl.getInstance();

    //发送验证码
    public void sendConfirmCode(String account){
        userRepository.sendConfirmCode(account);
    }

    //注册用户
    public void register(final String account,final String password,final String repeatPwd,String confirmCode){
        userRepository.register(account,confirmCode,password,repeatPwd);
    }
}

