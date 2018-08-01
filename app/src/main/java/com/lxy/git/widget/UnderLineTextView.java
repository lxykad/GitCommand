package com.lxy.git.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author lxy
 */
public class UnderLineTextView extends AppCompatTextView {

    public static final int UNDERLINE_HEIGHT = 5;
    public static final int PADDING_BOTTOM = 25;
    public static final int PADDING_LEFT = 25;

    private Paint mPaint;

    public UnderLineTextView(Context context) {
        this(context, null);
    }

    public UnderLineTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderLineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(getCurrentTextColor());
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(UNDERLINE_HEIGHT);
        setPadding(getPaddingLeft() + PADDING_LEFT, getPaddingTop(), getPaddingRight(), getPaddingBottom() + PADDING_BOTTOM);
        this.animate().rotation(-13f).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(10, getHeight() - PADDING_BOTTOM, getWidth(), getHeight() - PADDING_BOTTOM, mPaint);
        canvas.drawLine(0, getHeight() - UNDERLINE_HEIGHT, getWidth() - 10, getHeight() - UNDERLINE_HEIGHT, mPaint);
    }
}
