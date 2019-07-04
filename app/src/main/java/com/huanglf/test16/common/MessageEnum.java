package com.huanglf.test16.common;

/**
 * Date: 2019/7/4
 * Author: JinYue
 * description:
 */
public enum MessageEnum {
    LOGIN_SUCCESS(200, "登录成功"),
    ERROR(401, "error");

    private int resultCode;
    private String desc;

    MessageEnum(int resultCode, String desc) {
        this.resultCode = resultCode;
        this.desc = desc;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "MessageEnum{" +
                "resultCode=" + resultCode +
                ", desc='" + desc + '\'' +
                '}';
    }}
