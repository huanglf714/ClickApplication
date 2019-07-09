package com.huanglf.test16.ui.jy;

import android.graphics.Canvas;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Date: 2019/7/8
 * Author: JinYue
 * description:实现recyclerview的滑动效果
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.START);
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 1.1f;
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return Float.MIN_VALUE;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        MyNoteRecyclerViewAdapter.ViewHolder myViewHolder = (MyNoteRecyclerViewAdapter.ViewHolder) viewHolder;
        dX = getCurrentPosX(dX, myViewHolder.getActionWidth());
        setViewTranslationX(dX, myViewHolder);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    private float getCurrentPosX(float dX, float width) {
        if (dX < -(width / 2)) {
            return -width;
        } else {
            return dX;
        }
    }

    private void setViewTranslationX(float dX, MyNoteRecyclerViewAdapter.ViewHolder myViewHolder) {
        myViewHolder.mContent.setTranslationX(dX);
        myViewHolder.mSeparator.setTranslationX(dX);
        myViewHolder.mMenuDelete.setTranslationX(dX);
    }
}
