package cn.zty.recruit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.zty.recruit.R;

/**
 * Created by zty on 2017/6/22.
 */

public class UnderLineEditText extends RelativeLayout implements View.OnFocusChangeListener {

    EditText editText;
    TextView underLine;
    int colorNormal;
    int colorSelect;

    public UnderLineEditText(Context context) {
        super(context);
    }

    public UnderLineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = View.inflate(context, R.layout.view_edit, this);

        editText = (EditText) view.findViewById(R.id.editText);
        underLine = (TextView) view.findViewById(R.id.underLine);

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.UnderLineEditText);
        editText.setHint(arr.getString(R.styleable.UnderLineEditText_edt_hint));

        Drawable drawableLeft = arr.getDrawable(R.styleable.UnderLineEditText_edt_left);
        if (drawableLeft != null)
            editText.setCompoundDrawables(drawableLeft, null, null, null);

        colorNormal = arr.getColor(R.styleable.UnderLineEditText_edt_color_normal, ContextCompat.getColor(context, R.color.colorDiver));
        colorSelect = arr.getColor(R.styleable.UnderLineEditText_edt_color_select, ContextCompat.getColor(context, R.color.colorAccent));

        editText.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            underLine.setBackgroundColor(colorSelect);
        } else {
            underLine.setBackgroundColor(colorNormal);
        }
    }

    public String getText() {
        if (editText != null)
            return editText.getText().toString();
        return "";
    }
}
