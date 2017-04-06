package cn.zty.recruit.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zty.recruit.R;
import cn.zty.recruit.listener.SexSelectListener;

/**
 * Created by zty on 2017/3/17.
 */

public class SelectSexFragment extends DialogFragment implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.radioGroupSex)
    RadioGroup radioGroupSex;
    @BindView(R.id.radioButtonMan)
    RadioButton radioButtonMan;
    @BindView(R.id.radioButtonWoman)
    RadioButton radioButtonWoman;

    private int type;//0：男；1：女

    private SexSelectListener listener;

    public static SelectSexFragment newInstance(int type, SexSelectListener listener) {
        SelectSexFragment fragment = new SelectSexFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        fragment.setListener(listener);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, 0);

        type = getArguments().getInt("type", -1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_sex_select, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.6f;
        windowParams.gravity = Gravity.CENTER;
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(windowParams);

        if (type == 0) {
            radioButtonMan.setChecked(true);
        } else if (type == 1) {
            radioButtonWoman.setChecked(true);
        }

        radioGroupSex.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.radioButtonMan:
                listener.onSexListener("男", 0);
                dismiss();
                break;
            case R.id.radioButtonWoman:
                listener.onSexListener("女", 1);
                dismiss();
                break;
        }
    }

    public SexSelectListener getListener() {
        return listener;
    }

    public void setListener(SexSelectListener listener) {
        this.listener = listener;
    }
}
