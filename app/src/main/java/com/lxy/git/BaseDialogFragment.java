package com.lxy.git;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * DialogFragment base
 * Created by chris on 2018/3/13.
 */

public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {

    private boolean clickSpaceDismiss = true;
    protected View rootView;
    private @StyleRes
    int styleResId = R.style.Translucent_NoTitle;
    private @StyleRes
    int windowAnimationResId = 0;

    public void setStyleResId(@StyleRes int styleResId) {
        this.styleResId = styleResId;
    }

    public void setWindowAnimationResId(@StyleRes int animationResId) {
        this.windowAnimationResId = animationResId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, styleResId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int contentViewResId = getContentView();
        rootView = inflater.inflate(contentViewResId, container);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (clickSpaceDismiss) {
            rootView.setOnClickListener(this);
        }
        init(rootView);
    }

    public void setClickSpaceDismiss(boolean clickSpaceDismiss) {
        this.clickSpaceDismiss = clickSpaceDismiss;
        if (rootView != null) {
            rootView.setOnClickListener(clickSpaceDismiss ? this : null);
        }
    }

    @Override
    public void onStart() {
        Window window = getDialog().getWindow();
        if (window != null) {
            if (windowAnimationResId != 0) {
                window.setWindowAnimations(windowAnimationResId);
            }
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        if (v == rootView && clickSpaceDismiss) {
            dismiss();
        }
    }

    protected abstract @LayoutRes
    int getContentView();

    protected abstract void init(View view);
}
