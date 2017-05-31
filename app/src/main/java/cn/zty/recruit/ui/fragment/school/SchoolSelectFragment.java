package cn.zty.recruit.ui.fragment.school;

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
import android.widget.EditText;
import android.widget.TextView;

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
import cn.zty.recruit.listener.SchoolSelectListener;
import cn.zty.recruit.presenter.DictPresenter;
import cn.zty.recruit.presenter.GetProvincePresenter;
import cn.zty.recruit.presenter.HotMajorPresenter;
import cn.zty.recruit.utils.SharedPrefUtils;
import cn.zty.recruit.view.AreaView;
import cn.zty.recruit.view.DictListView;
import cn.zty.recruit.view.HotMajorView;

/**
 * Created by zty on 2017/3/13.
 */

public class SchoolSelectFragment extends DialogFragment implements
        AreaView,
        DictListView,
        HotMajorView,
        AdapterView.OnItemSelectedListener {

    @BindView(R.id.spinnerSpace)
    AppCompatSpinner spinnerSpace;
    @BindView(R.id.spinnerSchoolNature)
    AppCompatSpinner spinnerSchoolNature;
    @BindView(R.id.spinnerSchoolType)
    AppCompatSpinner spinnerSchoolType;
    @BindView(R.id.spinnerMajorType)
    AppCompatSpinner spinnerMajorType;
    @BindView(R.id.spinnerTestType)
    AppCompatSpinner spinnerTestType;
    @BindView(R.id.spinnerDiscipline)
    AppCompatSpinner spinnerDiscipline;
    @BindView(R.id.btnSure)
    TextView btnSure;
    @BindView(R.id.viewSelectBottom)
    View viewSelectBottom;

    Unbinder unbinder;

    int height;

    private GetProvincePresenter getProvincePresenter;
    private DictPresenter dictPresenter;
    private HotMajorPresenter hotMajorPresenter;

    private DictAdapter areaAdapter;
    private DictAdapter natureAdapter;
    private DictAdapter typeAdapter;
    private DictAdapter disciplineAdapter;
    private MajorNameAdapter majorAdapter;
    private DictAdapter tuitionAdapter;

    SchoolSelectListener listener;

    private static List<TipModel> areaModels;
    private static List<TipModel> natureModels;
    private static List<TipModel> typeModels;
    private static List<TipModel> disciplineModels;
    private static List<MajorModel> majorModels;
    private static List<TipModel> tuitionModels;

    private static String provinceId;
    private static String schoolNature;
    private static String schoolType;
    private static String discipline;
    private static String majorId;
    private static String tuitionType;

    public static SchoolSelectFragment newInstance(int height, SchoolSelectListener listener) {
        SchoolSelectFragment fragment = new SchoolSelectFragment();
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

        provinceId = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.schoolSelectArea);
        schoolNature = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.schoolNature);
        schoolType = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.schoolType);
        discipline = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.schoolSelectDiscipline);
        majorId = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.schoolSelectMajor);
        tuitionType = SharedPrefUtils.getString(getActivity(), SharedPrefUtils.schoolSelectTuition);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.dialog_school_select);
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
        spinnerSchoolNature.setOnItemSelectedListener(this);
        spinnerSchoolType.setOnItemSelectedListener(this);
        spinnerDiscipline.setOnItemSelectedListener(this);
        spinnerMajorType.setOnItemSelectedListener(this);
        spinnerTestType.setOnItemSelectedListener(this);

        areaAdapter = new DictAdapter(getActivity(), -1);
        disciplineAdapter = new DictAdapter(getActivity(), -1);
        majorAdapter = new MajorNameAdapter(getActivity());
        tuitionAdapter = new DictAdapter(getActivity(), -1);
        natureAdapter = new DictAdapter(getActivity(), -1);
        typeAdapter = new DictAdapter(getActivity(), -1);

        spinnerSpace.setAdapter(areaAdapter);
        spinnerSchoolNature.setAdapter(natureAdapter);
        spinnerSchoolType.setAdapter(typeAdapter);
        spinnerDiscipline.setAdapter(disciplineAdapter);
        spinnerMajorType.setAdapter(majorAdapter);
        spinnerTestType.setAdapter(tuitionAdapter);

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
        if (natureModels != null) {
            setNatureData();
        } else {
            dictPresenter.getDictList(Constants.DICT_TYPE2);
        }

        if (typeModels != null) {
            setTypeData();
        } else {
            dictPresenter.getDictList(Constants.DICT_TYPE3);
        }
        if (disciplineModels != null) {
            setDisciplineData();
        } else {
            dictPresenter.getDictList(Constants.DICT_TYPE1);
        }

        if (tuitionModels != null) {
            setTuitionData();
        } else {
            dictPresenter.getDictList(Constants.DICT_TYPE7);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        getProvincePresenter.detach();
        dictPresenter.detach();
        hotMajorPresenter.detach();
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
        } else if (type.equals(Constants.DICT_TYPE7)) {
            tuitionModels = models;
            setTuitionData();
        } else if (type.equals(Constants.DICT_TYPE2)) {
            natureModels = models;
            setNatureData();
        } else if (type.equals(Constants.DICT_TYPE3)) {
            typeModels = models;
            setTypeData();
        }
    }

    private void setNatureData() {
        natureAdapter.setData(natureModels);
        if (!TextUtils.isEmpty(schoolNature)) {
            for (int i = 0; i < natureModels.size(); i++) {
                if (natureModels.get(i).getKey().equals(schoolNature))
                    spinnerSchoolNature.setSelection(i);
            }
        }
    }

    private void setTypeData() {
        typeAdapter.setData(typeModels);
        if (!TextUtils.isEmpty(schoolType)) {
            for (int i = 0; i < typeModels.size(); i++) {
                if (typeModels.get(i).getKey().equals(schoolType))
                    spinnerSchoolType.setSelection(i);
            }
        }
    }

    private void setDisciplineData() {
        disciplineAdapter.setData(disciplineModels);
        if (!TextUtils.isEmpty(provinceId)) {
            for (int i = 0; i < disciplineModels.size(); i++) {
                if (disciplineModels.get(i).getKey().equals(discipline))
                    spinnerDiscipline.setSelection(i);
            }
        }
    }

    private void setTuitionData() {
        tuitionAdapter.setData(tuitionModels);
        if (!TextUtils.isEmpty(tuitionType)) {
            for (int i = 0; i < tuitionModels.size(); i++) {
                if (tuitionModels.get(i).getKey().equals(tuitionType))
                    spinnerTestType.setSelection(i);
            }
        }
    }

    private void setMajorData() {
        majorAdapter.setData(majorModels);
        if (!TextUtils.isEmpty(majorId)) {
            for (int i = 0; i < majorModels.size(); i++) {
                if (majorModels.get(i).getId().equals(majorId))
                    spinnerMajorType.setSelection(i);
            }
        } else {
            majorId = majorModels.get(0).getId();
        }
    }

    @Override
    public void onHotMajorSuccess(List<MajorModel> majorModels) {
        this.majorModels = majorModels;
        setMajorData();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerSpace:
                provinceId = areaAdapter.getData().get(position).getKey();
                break;
            case R.id.spinnerSchoolNature:
                schoolNature = natureAdapter.getData().get(position).getKey();
                break;
            case R.id.spinnerSchoolType:
                schoolType = typeAdapter.getData().get(position).getKey();
                break;
            case R.id.spinnerDiscipline:
                discipline = disciplineAdapter.getData().get(position).getKey();
                majorAdapter.clearData();
                hotMajorPresenter.getHotMajorList(null, -1, discipline, 1, Constants.MAX_PAGE_SIZE);
                break;
            case R.id.spinnerMajorType:
                majorId = majorAdapter.getData().get(position).getId();
                break;
            case R.id.spinnerTestType:
                tuitionType = tuitionAdapter.getData().get(position).getKey();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick(R.id.btnSure)
    public void onClick() {
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.schoolSelectArea, provinceId);
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.schoolNature, schoolNature);
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.schoolType, schoolType);
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.schoolSelectDiscipline, discipline);
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.schoolSelectMajor, majorId);
        SharedPrefUtils.setString(getActivity(), SharedPrefUtils.schoolSelectTuition, tuitionType);

        dismiss();
        listener.onSchoolSelect(provinceId, schoolNature, schoolType, discipline, majorId, "", tuitionType);
    }

    public SchoolSelectListener getListener() {
        return listener;
    }

    public void setListener(SchoolSelectListener listener) {
        this.listener = listener;
    }
}
