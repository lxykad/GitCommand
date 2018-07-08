package com.lxy.git;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * @author liuxinyu
 * @date 2018/7/8  下午7:16
 */
public class TestPresenterImpl implements TestPresenter, LifecycleObserver {

    @Override
    public void showDialog() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        System.out.println("p======oncreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        System.out.println("p======onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        System.out.println("p======onDestory");
    }
}
