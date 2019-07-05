package com.huanglf.test16.ui.css;

import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.IUserRepository;
import com.huanglf.test16.repository.impl.UserRepositoryImpl;

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
    public void register(final String account,final String password,
                         final String repeatPwd,String confirmCode){
        userRepository.register(account,confirmCode,password,repeatPwd);
    }

    //更改密码
    public void alterPwd(final String account,final String password,
                         final String repeatPwd,String confirmCode){
        userRepository.alterPwd(account,confirmCode,password,repeatPwd);
    }
}

