package com.huanglf.test16.ui.css;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.Message;

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
    private MutableLiveData<Message> isRegister = new MutableLiveData();

    //发送验证码
    public static void sendConfirmCode(String account){
        BmobSMS.requestSMSCode(account,"template1",
            new QueryListener<Integer>() {
                @Override
                public void done(Integer smsId, BmobException ex) {
                    if(ex==null){//验证码发送成功
                        Log.i("myLog", "短信id："+smsId);//用于查询本次短信发送详情
                    }else {
                        Log.e("myLog","验证码发送失败"+ ex.toString());
                    }
                }
            });
    }

    //验证验证码是否正确，正确则注册用户
    public void register(final String account,final String password,String confirmCode){
        BmobSMS.verifySmsCode(account,confirmCode, new UpdateListener() {
            @Override
            public void done(BmobException ex) {
                //短信验证码已验证成功
                if(ex==null){
                    Log.i("myLog", "验证通过");
                    BmobUser newUser = new BmobUser();
                    newUser.setUsername("aa");
                    newUser.setMobilePhoneNumber(account);
                    newUser.setPassword(password);
                    newUser.signUp(new SaveListener<BmobUser>() {
                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if(e==null){
                                isRegister.postValue(new Message(000,"注册成功"));
                            }else {
                                isRegister.postValue(new Message(001,"该账户已注册,请选择找回密码"));
                                Log.e("myLog",e.toString());
                            }
                        }});
                }else {
                    //验证码输入错误
                    Log.e("myLog", "验证失败：code ="+ex.getErrorCode()+",msg = "+ex.getLocalizedMessage());
                    isRegister.postValue(new Message(002,"验证码输入错误"));
                }
            }
        });
    }

    public MutableLiveData<Message> getIsRegister() {
        return isRegister;
    }

}

