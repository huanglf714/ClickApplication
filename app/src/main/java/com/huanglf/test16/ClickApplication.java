package com.huanglf.test16;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Date: 2019/7/3
 * Author: huanglf
 * description:
 */
public class ClickApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //完成全局初始化
        Bmob.initialize(this, "8586480382e9cd031762ec9e654151d0");
    }
}
