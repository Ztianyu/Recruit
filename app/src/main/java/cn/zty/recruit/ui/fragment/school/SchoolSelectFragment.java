package cn.zty.recruit.ui.fragment.school;

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
import android.widget.EditText;
import android.widget.TextView;

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
import cn.zty.recruit.listener.SchoolSelectListener;
import cn.zty.recruit.presenter.DictPresenter;
import cn.zty.recruit.presenter.GetProvincePresenter;
import cn.zty.recruit.presenter.HotMajorPresenter;
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
    @BindView(R.id.spinnerMajorType)
    AppCompatSpinner spinnerMajorType;
    @BindView(R.id.spinnerTestType)
    AppCompatSpinner spinnerTestType;
    @BindView(R.id.editScore)
    EditText editScore;
    @BindView(R.id.spinnerDiscipline)
    AppCompatSpinner spinnerDiscipline;
    @BindView(R.id.btnSure)
    TextView btnSure;

    Unbinder unbinder;

    int height;

    private GetProvincePresenter getProvincePresenter;
    private DictPresenter dictPresenter;
    private HotMajorPresenter hotMajorPresenter;

    private DictAdapter areaAdapter;
    private DictAdapter disciplineAdapter;
    private MajorNameAdapter majorAdapter;
    private DictAdapter examinationAdapter;

    SchoolSelectListener listener;

    private String provinceId;
    private String discipline;
    private String majorId;
    private String examinationType;

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
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(windowParams);

        spinnerSpace.setOnItemSelectedListener(this);
        spinnerDiscipline.setOnItemSelectedListener(this);
        spinnerMajorType.setOnItemSelectedListener(this);
        spinnerTestType.setOnItemSelectedListener(this);

        areaAdapter = new DictAdapter(getActivity(), 0);
        disciplineAdapter = new DictAdapter(getActivity(), 3);
        majorAdapter = new MajorNameAdapter(getActivity());
        examinationAdapter = new DictAdapter(getActivity(), 5);

        spinnerSpace.setAdapter(areaAdapter);
        spinnerDiscipline.setAdapter(disciplineAdapter);
        spinnerMajorType.setAdapter(majorAdapter);
        spinnerTestType.setAdapter(examinationAdapter);

        getProvincePresenter.getProvince();
        dictPresenter.getDictList(Constants.DICT_TYPE1);
        dictPresenter.getDictList(Constants.DICT_TYPE4);
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
        areaAdapter.setData(models);
    }

    @Override
    public void onDictSuccess(String type, List<TipModel> models) {
        if (type.equals(Constants.DICT_TYPE1)) {
            disciplineAdapter.setData(models);
        } else if (type.equals(Constants.DICT_TYPE4)) {
            examinationAdapter.setData(models);
        }
    }

    @Override
    public void onHotMajorSuccess(List<MajorModel> majorModels) {
        majorAdapter.setData(majorModels);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerSpace:
                provinceId = areaAdapter.getData().get(position).getKey();
                break;
            case R.id.spinnerDiscipline:
                discipline = disciplineAdapter.getData().get(position).getKey();
                hotMajorPresenter.getHotMajorList(null,-1, discipline, 1, Constants.MAX_PAGE_SIZE);
                break;
            case R.id.spinnerMajorType:
                majorId = majorAdapter.getData().get(position).getId();
                break;
            case R.id.spinnerTestType:
                examinationType = examinationAdapter.getData().get(position).getKey();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick(R.id.btnSure)
    public void onClick() {
        dismiss();
        listener.onSchoolSelect(provinceId, discipline, majorId, editScore.getText().toString(), examinationType);
    }

    public SchoolSelectListener getListener() {
        return listener;
    }

    public void setListener(SchoolSelectListener listener) {
        this.listener = listener;
    }
}
