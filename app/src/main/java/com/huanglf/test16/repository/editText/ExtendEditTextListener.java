package com.huanglf.test16.repository.editText;

import java.util.List;

/**
 * Created by Leo on 2017/3/24.
 */

public interface ExtendEditTextListener {

    void onCursorChange(int selStart, int selEnd, List<Integer> format);
}
