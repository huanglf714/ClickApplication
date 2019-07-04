package com.huanglf.test16.util;


import androidx.lifecycle.MutableLiveData;

/**
 * 全局异常收集
 */
public class MessageUtil {
    private static final MutableLiveData<String> exceptionLiveData = new MutableLiveData<>();

    public static MutableLiveData<String> getExceptionLiveData() {
        return exceptionLiveData;
    }

    public static void error(String value) {
        exceptionLiveData.postValue(value);
    }
}
