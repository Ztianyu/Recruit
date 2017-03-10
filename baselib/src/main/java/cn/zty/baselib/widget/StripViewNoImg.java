package cn.zty.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.zty.baselib.R;

/**
 * Created by zty on 2017/1/7.
 */

public class StripViewNoImg extends RelativeLayout {
    private TextView tvName;
    private TextView tvAddition;
    private View topLine;
    private View bottomLine;
    private String name;
    private String addition;
    private boolean isHasTop;
    private boolean isHasBottom;
    private int lineMargin;

    public StripViewNoImg(Context context) {
        this(context, null);
    }

    public StripViewNoImg(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 将自定义组合控件的布局渲染成View
        View view = View.inflate(context, R.layout.view_strip_no_img, this);
        tvName = (TextView) view.findViewById(R.id.textStrip);
        tvAddition = (TextView) view.findViewById(R.id.textStripAddition);
        // 加载自定义的属性并设置
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StripMenuView);
        name = a.getString(R.styleable.StripMenuView_textName);
        tvName.setText(name);
        tvName.setTextColor(a.getColor(R.styleable.StripMenuView_textNameColor, ContextCompat.getColor(context, R.color.textColor)));
        addition = a.getString(R.styleable.StripMenuView_textAddition);
        tvAddition.setText(addition);
        tvAddition.setTextColor(a.getColor(R.styleable.StripMenuView_textAdditionColor, ContextCompat.getColor(context, R.color.textColor)));

        topLine = view.findViewById(R.id.topLine);
        isHasTop = a.getBoolean(R.styleable.StripMenuView_isHasTopLine, true);
        bottomLine = view.findViewById(R.id.bottomLine);
        isHasBottom = a.getBoolean(R.styleable.StripMenuView_isHasBottomLine, true);

        lineMargin = a.getInteger(R.styleable.StripMenuView_lineMargin, 0);

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        lp.setMargins(lineMargin, 0, lineMargin, 0);
        topLine.setLayoutParams(lp);
        bottomLine.setLayoutParams(lp);

        if (isHasTop) {
            topLine.setVisibility(View.VISIBLE);
        } else {
            topLine.setVisibility(View.INVISIBLE);
        }
        if (isHasBottom) {
            bottomLine.setVisibility(View.VISIBLE);
        } else {
            bottomLine.setVisibility(View.INVISIBLE);
        }

    }

    // 设置附加的文字内容
    public void setAdditionText(String addition) {
        tvAddition.setText(addition);
    }
}
