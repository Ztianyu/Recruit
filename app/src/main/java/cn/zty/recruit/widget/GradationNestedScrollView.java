package cn.zty.recruit.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * Created by zty on 2017/4/5.
 */

public class GradationNestedScrollView extends NestedScrollView {

    public interface ScrollViewListener {
        void onScrollChanged(GradationNestedScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    private ScrollViewListener scrollViewListener = null;

    public GradationNestedScrollView(Context context) {
        super(context);
    }

    public GradationNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradationNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
