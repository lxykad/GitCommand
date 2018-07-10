package com.lxy.git;

import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author liuxinyu
 * @date 2018/7/8  下午10:18
 */
public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private RecyclerView mRecyclerView;
    private GestureDetectorCompat mGesture;

    public OnRecyclerItemClickListener(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        mGesture = new GestureDetectorCompat(recyclerView.getContext(), new ItemGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        mGesture.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        mGesture.onTouchEvent(motionEvent);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    class ItemGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View item = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (item != null) {
                RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(item);
                onItemClick(childViewHolder);
            }

            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            View item = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (item != null) {
                RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(item);
                onItemLongClick(childViewHolder);
            }

        }
    }

    /**
     * 条目点击回调接口
     */
    public abstract void onItemClick(RecyclerView.ViewHolder vh);

    /**
     * 条目长按事件
     */
    public abstract void onItemLongClick(RecyclerView.ViewHolder vh);
}
