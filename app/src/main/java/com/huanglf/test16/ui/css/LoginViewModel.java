package com.huanglf.test16.ui.css;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.Message;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Date: 2019/7/3
 * Author: huanglf
 * description:
 */
public class LoginViewModel extends ViewModel {
     private MutableLiveData<Message> isLogin = new MutableLiveData();

     //手机账户+密码登录
     public void loginWithPassword(String account,String password){
          Log.e("myLog",account+"------------------"+password);
          BmobUser.loginByAccount(account, password,
               new LogInListener<BmobUser>() {
                  @Override
                  public void done(BmobUser bmobUser, BmobException e) {
                       if(bmobUser!=null){
                            isLogin.postValue(new Message(000,"登录成功"));
                       }else{
                            isLogin.postValue(new Message(001,"登录失败"));
                            Log.e("myLog",e.toString());
                       }
                  }
               }
          );
     }

     //手机账户+验证码登录
     public void loginWithConfirmCode(String account,String confirmCode){
          BmobUser.loginBySMSCode(account, confirmCode, new LogInListener<BmobUser>() {
               @Override
               public void done(BmobUser user, BmobException e) {
                    if(user!=null){
                         Log.i("myLog","用户登陆成功");
                         isLogin.postValue(new Message(000,"登录成功"));
                    }else {
                         isLogin.postValue(new Message(001,"登录失败"));
                         Log.e("myLog",e.toString());
                    }
               }
          });
     }

     //微信第三方平台登录
     public void loginWithWeChat(){

     }

     public MutableLiveData<Message> getIsLogin() {
          return isLogin;
     }
}
