package com.huanglf.test16.ui.css;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huanglf.test16.repository.User;

/**
 * Date: 2019/7/3
 * Author: huanglf
 * description:
 */
public class LoginViewModel extends ViewModel {
     /*private User = new User();*/
     public final LiveData<User> data = new MutableLiveData<>();
     public LoginViewModel() { }

     //业务方法
    /*User getUser(){

    }*/
}
