package cn.zty.recruit.ui.fragment.learn;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
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
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.DictAdapter;
import cn.zty.recruit.adapter.MajorNameAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.listener.StudySchoolListener;
import cn.zty.recruit.presenter.DictPresenter;
import cn.zty.recruit.presenter.GetProvincePresenter;
import cn.zty.recruit.presenter.HotMajorPresenter;
import cn.zty.recruit.utils.SharedPrefUtils;
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
    @BindView(R.id.viewSelectBottom)
    View viewSelectBottom;
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

    private static List<TipModel> areaModels;
    private static List<TipModel> schoolTypeModels;
    private static List<TipModel> studyTypeModels;
    private static List<TipModel> disciplineModels;
    private static List<MajorModel> majorModels;
    private static List<TipModel> tuitionModels;

    private static String provinceId;
    private static String schoolType;
    private static String studyType;
    private static String discipline;
    private static String majorId;
    private static String tuition;

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

        provinceId = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.studySelectArea);
        schoolType = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.studySchoolType);
        studyType = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.studyType);
        discipline = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.studySelectDiscipline);
        majorId = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.studySelectMajor);
        tuition = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.studySelectTuition);
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
        windowParams.height = BaseActivity.screenHeight - height - RecruitApplication.getInstance().getStatusBarHeight();
        window.setAttributes(windowParams);
        window.setBackgroundDrawable(new ColorDrawable(ResourceUtil.resToColor(getActivity(), R.color.transparent60)));

        spinnerSpace.setOnItemSelectedListener(this);
        spinnerSchoolType.setOnItemSelectedListener(this);
        spinnerStudyEducation.setOnItemSelectedListener(this);
        spinnerSubjectType.setOnItemSelectedListener(this);
        spinnerIntentionMajor.setOnItemSelectedListener(this);
        spinnerTuitionRange.setOnItemSelectedListener(this);

        areaAdapter = new DictAdapter(getActivity(), -1);
        schoolTypeAdapter = new DictAdapter(getActivity(), -1);
        studyTypeAdapter = new DictAdapter(getActivity(), -1);
        disciplineAdapter = new DictAdapter(getActivity(), -1);
        majorAdapter = new MajorNameAdapter(getActivity());
        tuitionAdapter = new DictAdapter(getActivity(), -1);

        spinnerSpace.setAdapter(areaAdapter);
        spinnerSchoolType.setAdapter(schoolTypeAdapter);
        spinnerStudyEducation.setAdapter(studyTypeAdapter);
        spinnerSubjectType.setAdapter(disciplineAdapter);
        spinnerIntentionMajor.setAdapter(majorAdapter);
        spinnerTuitionRange.setAdapter(tuitionAdapter);

        viewSelectBottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return true;
            }
        });

        if (areaModels != null) {
            setAreaData();
        } else {
            getProvincePresenter.getProvince();
        }

        if (disciplineModels != null) {
            setDisciplineData();
        } else {
            dictPresenter.getDictList(Constants.DICT_TYPE1);
        }
        if (schoolTypeModels != null) {
            setSchoolTypeData();
        } else {
            dictPresenter.getDictList(Constants.DICT_TYPE8);
        }
        if (studyTypeModels != null) {
            setStudyTypeData();
        } else {
            dictPresenter.getDictList(Constants.DICT_TYPE6);
        }

        if (tuitionModels != null) {
            setTuitionData();
        } else {
            dictPresenter.getDictList(Constants.DICT_TYPE7);
        }
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
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.studySelectArea, provinceId);
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.studySchoolType, schoolType);
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.studyType, studyType);
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.studySelectDiscipline, discipline);
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.studySelectMajor, majorId);
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.studySelectTuition, tuition);

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
                majorAdapter.clearData();
                hotMajorPresenter.getHotMajorList(null, -1, discipline, 1, Constants.MAX_PAGE_SIZE);
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
        areaModels = models;
        setAreaData();
    }

    private void setAreaData() {
        areaAdapter.setData(areaModels);
        if (!TextUtils.isEmpty(provinceId)) {
            for (int i = 0; i < areaModels.size(); i++) {
                if (areaModels.get(i).getKey().equals(provinceId))
                    spinnerSpace.setSelection(i);
            }
        }
    }

    @Override
    public void onDictSuccess(String type, List<TipModel> models) {
        if (type.equals(Constants.DICT_TYPE1)) {
            disciplineModels = models;
            setDisciplineData();
        } else if (type.equals(Constants.DICT_TYPE8)) {
            schoolTypeModels = models;
            setSchoolTypeData();
        } else if (type.equals(Constants.DICT_TYPE6)) {
            studyTypeModels = models;
            setStudyTypeData();
        } else if (type.equals(Constants.DICT_TYPE7)) {
            tuitionModels = models;
            setTuitionData();
        }
    }

    private void setSchoolTypeData() {
        schoolTypeAdapter.setData(schoolTypeModels);
        if (!TextUtils.isEmpty(schoolType)) {
            for (int i = 0; i < schoolTypeModels.size(); i++) {
                if (schoolTypeModels.get(i).getKey().equals(schoolType))
                    spinnerSchoolType.setSelection(i);
            }
        }
    }

    private void setStudyTypeData() {
        studyTypeAdapter.setData(studyTypeModels);
        if (!TextUtils.isEmpty(studyType)) {
            for (int i = 0; i < studyTypeModels.size(); i++) {
                if (studyTypeModels.get(i).getKey().equals(studyType))
                    spinnerStudyEducation.setSelection(i);
            }
        }
    }

    private void setDisciplineData() {
        disciplineAdapter.setData(disciplineModels);
        if (!TextUtils.isEmpty(provinceId)) {
            for (int i = 0; i < disciplineModels.size(); i++) {
                if (disciplineModels.get(i).getKey().equals(discipline))
                    spinnerSubjectType.setSelection(i);
            }
        }
    }

    private void setTuitionData() {
        tuitionAdapter.setData(tuitionModels);
        if (!TextUtils.isEmpty(tuition)) {
            for (int i = 0; i < tuitionModels.size(); i++) {
                if (tuitionModels.get(i).getKey().equals(tuition))
                    spinnerTuitionRange.setSelection(i);
            }
        }
    }

    @Override
    public void onHotMajorSuccess(List<MajorModel> majorModels) {
        this.majorModels =majorModels;
        setMajorData();
    }

    private void setMajorData() {
        majorAdapter.setData(majorModels);
        if (!TextUtils.isEmpty(majorId)) {
            for (int i = 0; i < majorModels.size(); i++) {
                if (majorModels.get(i).getId().equals(majorId))
                    spinnerIntentionMajor.setSelection(i);
            }
        } else {
            majorId = majorModels.get(0).getId();
        }
    }

    public StudySchoolListener getListener() {
        return listener;
    }

    public void setListener(StudySchoolListener listener) {
        this.listener = listener;
    }
}
