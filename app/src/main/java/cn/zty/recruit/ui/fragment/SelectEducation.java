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
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.listener.EducationSelectListener;

/**
 * Created by zty on 2017/3/17.
 */

public class SelectEducation extends DialogFragment implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.radioPrimarySchool)
    RadioButton radioPrimarySchool;
    @BindView(R.id.radioMiddleSchool)
    RadioButton radioMiddleSchool;
    @BindView(R.id.radioHighSchool)
    RadioButton radioHighSchool;
    @BindView(R.id.radioUndergraduate)
    RadioButton radioUndergraduate;
    @BindView(R.id.radioMaster)
    RadioButton radioMaster;
    @BindView(R.id.radioDoctor)
    RadioButton radioDoctor;
    @BindView(R.id.radioGroupEducation)
    RadioGroup radioGroupEducation;

    private int type;//0：小学；1：初中；2：高中；3：本科；4：硕士；5：博士

    private EducationSelectListener listener;

    public static SelectEducation newInstance(int type, EducationSelectListener listener) {
        SelectEducation fragment = new SelectEducation();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        fragment.setListener(listener);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_education_select, null);
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

        type = getArguments().getInt("type");

        switch (type) {
            case 0:
                radioPrimarySchool.setChecked(true);
                break;
            case 1:
                radioMiddleSchool.setChecked(true);
                break;
            case 2:
                radioHighSchool.setChecked(true);
                break;
            case 3:
                radioUndergraduate.setChecked(true);
                break;
            case 4:
                radioMaster.setChecked(true);
                break;
            case 5:
                radioDoctor.setChecked(true);
                break;
            default:
                break;
        }

        radioGroupEducation.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.radioPrimarySchool:
                listener.onEducationListener(ResourceUtil.resToStr(getContext(), R.string.primarySchool), 0);
                break;
            case R.id.radioMiddleSchool:
                listener.onEducationListener(ResourceUtil.resToStr(getContext(), R.string.middleSchool), 1);
                break;
            case R.id.radioHighSchool:
                listener.onEducationListener(ResourceUtil.resToStr(getContext(), R.string.highSchool), 2);
                break;
            case R.id.radioUndergraduate:
                listener.onEducationListener(ResourceUtil.resToStr(getContext(), R.string.undergraduate), 3);
                break;
            case R.id.radioMaster:
                listener.onEducationListener(ResourceUtil.resToStr(getContext(), R.string.master), 4);
                break;
            case R.id.radioDoctor:
                listener.onEducationListener(ResourceUtil.resToStr(getContext(), R.string.doctor), 5);
                break;
        }
        dismiss();
    }

    public EducationSelectListener getListener() {
        return listener;
    }

    public void setListener(EducationSelectListener listener) {
        this.listener = listener;
    }
}
