package com.huanglf.test16.repository.editText;


public class TypeBean {

    private int mStart;
    private int mEnd;

    public TypeBean(int start, int end) {
        this.mStart = start;
        this.mEnd = end;
    }

    public int getStart() {
        return mStart;
    }

    public int getEnd() {
        return mEnd;
    }

    public boolean isValid() {
        return mStart < mEnd;
    }
}
