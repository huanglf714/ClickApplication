package com.huanglf.test16.repository.editText;

import android.content.Context;
import android.media.effect.Effect;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;

/**
 * date：2019/7/9
 * desc：
 *
 * @author TY
 */
public class FontSize {
    protected int mRule = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
    private final int LARGEST_FONT_SIZE = 28;
    private final int SMALLEST_FONT_SIZE = 12;
    private final int DEFAULT_FONT_SIZE = 18;

    /**
     * 设置字体字号加大
     */
    public void increaseSize(Editable text, int start, int end) {
        if (start >= end) {
            return;
        }
        text.setSpan(new RelativeSizeSpan(1.5f),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public void decreaseSize(Editable text, int start, int end) {
        text.setSpan(new RelativeSizeSpan(0.5f),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
