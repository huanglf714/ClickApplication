package com.huanglf.test16;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.huanglf.test16.repository.database.AppDatabase;
import com.mob.MobSDK;

import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Date: 2019/7/3
 * Author: huanglf
 * description:
 */
public class ClickApplication extends Application {
    public static SharedPreferences sharedPreferences = null;

    @Override
    public void onCreate() {
        super.onCreate();
        //完成全局初始化
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Bmob.initialize(this, "8586480382e9cd031762ec9e654151d0");
        AppDatabase.createInstance(getApplicationContext());
        MobSDK.init(this);
    }
}
