package com.huanglf.test16.repository.editText;

import java.util.List;


public interface ExtendEditTextListener {

    void onCursorChange(int selStart, int selEnd, List<Integer> format);
}
