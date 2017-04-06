package cn.zty.recruit.ui.fragment.learn;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.DictAdapter;
import cn.zty.recruit.adapter.MajorNameAdapter;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.listener.StudySchoolListener;
import cn.zty.recruit.presenter.DictPresenter;
import cn.zty.recruit.presenter.GetProvincePresenter;
import cn.zty.recruit.presenter.HotMajorPresenter;
import cn.zty.recruit.view.AreaView;
import cn.zty.recruit.view.DictListView;
import cn.zty.recruit.view.HotMajorView;

/**
 * Created by zty on 2017/3/18.
 */

public class StudySchoolSelect extends DialogFragment implements
        AreaView,
        DictListView,
        HotMajorView,
        AdapterView.OnItemSelectedListener {

    @BindView(R.id.spinnerSpace)
    AppCompatSpinner spinnerSpace;
    @BindView(R.id.spinnerSchoolType)
    AppCompatSpinner spinnerSchoolType;
    @BindView(R.id.spinnerStudyEducation)
    AppCompatSpinner spinnerStudyEducation;
    @BindView(R.id.spinnerSubjectType)
    AppCompatSpinner spinnerSubjectType;
    @BindView(R.id.spinnerIntentionMajor)
    AppCompatSpinner spinnerIntentionMajor;
    @BindView(R.id.spinnerTuitionRange)
    AppCompatSpinner spinnerTuitionRange;
    @BindView(R.id.btnSure)
    Button btnSure;
    Unbinder unbinder;

    int height;

    private GetProvincePresenter getProvincePresenter;
    private DictPresenter dictPresenter;
    private HotMajorPresenter hotMajorPresenter;

    private DictAdapter areaAdapter;
    private DictAdapter schoolTypeAdapter;
    private DictAdapter studyTypeAdapter;
    private DictAdapter disciplineAdapter;
    private MajorNameAdapter majorAdapter;
    private DictAdapter tuitionAdapter;

    private String provinceId;
    private String schoolType;
    private String studyType;
    private String discipline;
    private String majorId;
    private String tuition;

    private StudySchoolListener listener;

    public static StudySchoolSelect newInstance(int height, StudySchoolListener listener) {
        StudySchoolSelect fragment = new StudySchoolSelect();
        Bundle bundle = new Bundle();
        bundle.putInt("height", height);
        fragment.setArguments(bundle);
        fragment.setListener(listener);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        height = getArguments().getInt("height");

        getProvincePresenter = new GetProvincePresenter();
        getProvincePresenter.attach(this);

        dictPresenter = new DictPresenter();
        dictPresenter.attach(this);

        hotMajorPresenter = new HotMajorPresenter();
        hotMajorPresenter.attach(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.dialog_study_select);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        unbinder = ButterKnife.bind(this, dialog);

        return dialog;
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.y = height;
        windowParams.dimAmount = 0;
        windowParams.gravity = Gravity.TOP;
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(windowParams);

        spinnerSpace.setOnItemSelectedListener(this);
        spinnerSchoolType.setOnItemSelectedListener(this);
        spinnerStudyEducation.setOnItemSelectedListener(this);
        spinnerSubjectType.setOnItemSelectedListener(this);
        spinnerIntentionMajor.setOnItemSelectedListener(this);
        spinnerTuitionRange.setOnItemSelectedListener(this);

        areaAdapter = new DictAdapter(getActivity(), 0);
        schoolTypeAdapter = new DictAdapter(getActivity(), 1);
        studyTypeAdapter = new DictAdapter(getActivity(), 2);
        disciplineAdapter = new DictAdapter(getActivity(), 3);
        majorAdapter = new MajorNameAdapter(getActivity());
        tuitionAdapter = new DictAdapter(getActivity(), 4);

        spinnerSpace.setAdapter(areaAdapter);
        spinnerSchoolType.setAdapter(schoolTypeAdapter);
        spinnerStudyEducation.setAdapter(studyTypeAdapter);
        spinnerSubjectType.setAdapter(disciplineAdapter);
        spinnerIntentionMajor.setAdapter(majorAdapter);
        spinnerTuitionRange.setAdapter(tuitionAdapter);

        getProvincePresenter.getProvince();
        dictPresenter.getDictList(Constants.DICT_TYPE1);
        dictPresenter.getDictList(Constants.DICT_TYPE8);
        dictPresenter.getDictList(Constants.DICT_TYPE6);
        dictPresenter.getDictList(Constants.DICT_TYPE7);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

        getProvincePresenter.detach();
        dictPresenter.detach();
        hotMajorPresenter.detach();
    }

    @OnClick(R.id.btnSure)
    public void onViewClicked() {
        dismiss();
        listener.onStudySchoolSure(provinceId, schoolType, studyType, discipline, majorId, tuition);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerSpace:
                provinceId = areaAdapter.getData().get(position).getKey();
                break;
            case R.id.spinnerSchoolType:
                schoolType = schoolTypeAdapter.getData().get(position).getKey();
                break;
            case R.id.spinnerStudyEducation:
                studyType = studyTypeAdapter.getData().get(position).getKey();
                break;
            case R.id.spinnerSubjectType:
                discipline = disciplineAdapter.getData().get(position).getKey();
                hotMajorPresenter.getHotMajorList(null,-1, discipline, 1, Constants.MAX_PAGE_SIZE);
                break;
            case R.id.spinnerIntentionMajor:
                majorId = majorAdapter.getData().get(position).getId();
                break;
            case R.id.spinnerTuitionRange:
                tuition = tuitionAdapter.getData().get(position).getKey();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onAreaSuccess(int type, List<TipModel> models) {
        areaAdapter.setData(models);
    }

    @Override
    public void onDictSuccess(String type, List<TipModel> models) {
        if (type.equals(Constants.DICT_TYPE1)) {
            disciplineAdapter.setData(models);
        } else if (type.equals(Constants.DICT_TYPE8)) {
            schoolTypeAdapter.setData(models);
        } else if (type.equals(Constants.DICT_TYPE6)) {
            studyTypeAdapter.setData(models);
        }else if (type.equals(Constants.DICT_TYPE7)) {
            tuitionAdapter.setData(models);
        }
    }

    @Override
    public void onHotMajorSuccess(List<MajorModel> majorModels) {
        majorAdapter.setData(majorModels);
    }

    public StudySchoolListener getListener() {
        return listener;
    }

    public void setListener(StudySchoolListener listener) {
        this.listener = listener;
    }
}
