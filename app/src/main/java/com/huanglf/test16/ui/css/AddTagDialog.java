package com.huanglf.test16.ui.css;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.huanglf.test16.R;

/**
 * @author chenshanshan
 * @time 2019/7/11 9:40
 */
public class AddTagDialog extends Dialog implements View.OnClickListener {
    //在构造方法里提前加载了样式
    private Context context;//上下文
    private int layoutResID;//布局文件id
    private int[] listenedItem;//监听的控件id
    private OnCenterItemClickListener listener;

    public AddTagDialog(Context context, int layoutResID, int[] listenedItem) {
        super(context, R.style.mydialog);//加载dialog的样式
        this.context = context;
        this.layoutResID = layoutResID;
        this.listenedItem = listenedItem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);//设置dialog显示居中
        //dialogWindow.setWindowAnimations();设置动画效果
        //系统构建的View
//        setContentView(layoutResID);
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() * 4 / 5;// 设置dialog宽度为屏幕的4/5
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);//点击外部Dialog消失
        //遍历控件id添加点击注册
        for (int id : listenedItem) {
            findViewById(id).setOnClickListener(this);
        }
    }

    public interface OnCenterItemClickListener {
        void OnCenterItemClick(AddTagDialog dialog, View view);
    }

    public void setOnCenterItemClickListener(OnCenterItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.OnCenterItemClick(this, v);
    }


}
