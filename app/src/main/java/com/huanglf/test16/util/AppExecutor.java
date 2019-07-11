package com.huanglf.test16.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Date: 2019/7/9
 * Author: huanglf
 * description:
 */
public class AppExecutor {
    private Executor mIOExecutor;
    private static AppExecutor instance = new AppExecutor();

    public static AppExecutor getInstance() {
        return instance;
    }

    private AppExecutor() {
        mIOExecutor = Executors.newSingleThreadExecutor();
    }

    public Executor getDiskIO() {
        return mIOExecutor;
    }
}
