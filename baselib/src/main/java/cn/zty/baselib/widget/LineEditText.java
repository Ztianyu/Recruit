package cn.zty.baselib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.EditText;

import cn.zty.baselib.R;

/**
 * Created by zty on 2017/3/13.
 */

public class LineEditText extends EditText {

    private Paint mPaint;
    private int mLineColor;

    public LineEditText(Context context) {
        super(context);
        init();
    }

    public LineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mLineColor = ContextCompat.getColor(getContext(), R.color.normalColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mLineColor);
        canvas.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1, mPaint);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            mLineColor = ContextCompat.getColor(getContext(), R.color.selectColor);
        } else {
            mLineColor = ContextCompat.getColor(getContext(), R.color.normalColor);
        }
        invalidate();
    }
}
