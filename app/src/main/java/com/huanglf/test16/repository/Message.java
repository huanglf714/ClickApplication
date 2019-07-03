package com.huanglf.test16.repository;

/**
 * Date: 2019/7/3
 * Author: huanglf
 * description:
 */
public class Message {
    private int resultCode;
    private String desc;

    public Message() { }

    public Message(int resultCode, String desc) {
        this.resultCode = resultCode;
        this.desc = desc;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Message{" +
                "resultCode=" + resultCode +
                ", desc='" + desc + '\'' +
                '}';
    }
}
