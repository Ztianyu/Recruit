package cn.zty.recruit.ui.activity.learn;

import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.DepositSystemModel;
import cn.zty.recruit.bean.InstitutionMajorModel;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.listener.EducationSelectListener;
import cn.zty.recruit.listener.EnrollTypeSelectListener;
import cn.zty.recruit.listener.SexSelectListener;
import cn.zty.recruit.utils.DialogUtils;

/**
 * Created by zty on 2017/3/17.
 */

public class EnrollActivity extends BaseActivity implements
        SexSelectListener,
        EducationSelectListener,
        EnrollTypeSelectListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textMajorDetailName)
    TextView textMajorDetailName;
    @BindView(R.id.textMajorDetailCount)
    TextView textMajorDetailCount;
    @BindView(R.id.textMajorPrise)
    TextView textMajorPrise;
    @BindView(R.id.textMajorUnit)
    TextView textMajorUnit;
    @BindView(R.id.editEnrollName)
    EditText editEnrollName;
    @BindView(R.id.btnChoseSex)
    TextView btnChoseSex;
    @BindView(R.id.btnChoseAge)
    TextView btnChoseAge;
    @BindView(R.id.editEnrollPhone)
    EditText editEnrollPhone;
    @BindView(R.id.btnChoseEducation)
    TextView btnChoseEducation;
    @BindView(R.id.btnChoseType)
    TextView btnChoseType;
    @BindView(R.id.editEnrollPs)
    EditText editEnrollPs;
    @BindView(R.id.textBillMoney)
    TextView textBillMoney;
    @BindView(R.id.textBillTip)
    TextView textBillTip;
    @BindView(R.id.btnSubmit)
    TextView btnSubmit;
    @BindView(R.id.expandText)
    ExpandableTextView expandText;
    @BindView(R.id.textStudyMajorIntroduction)
    TextView textStudyMajorIntroduction;

    private int sexType = -1;
    private DepositSystemModel enrollTypeModel;
    private TipModel educationModel;
    private String educationName;

    private InstitutionMajorModel majorModel;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_class_enroll;
    }

    @Override
    protected void initView() {
        majorModel = getIntent().getParcelableExtra("model");

        toolbar.setTitle("在线报名");
        initToolbar(toolbar);
    }

    @Override
    protected void initData() {
        expandText.setText(MyTextUtils.notNullStr(majorModel.getRemarks()));
        textMajorDetailName.setText(majorModel.getName());
        textMajorDetailCount.setText("总学时：" + majorModel.getHours());
        textMajorPrise.setText(majorModel.getMoney() + "");

        String unit = "";
        if (majorModel.getChargeStandard().equals("1")) {
            unit = Constants.chargeStandard1;
        }
        if (majorModel.getChargeStandard().equals("2")) {
            unit = Constants.chargeStandard2;
        }
        textMajorUnit.setText("元/" + unit);
    }

    @OnClick({R.id.btnChoseSex, R.id.btnChoseAge, R.id.btnChoseEducation, R.id.btnChoseType, R.id.btnSubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnChoseSex:
                DialogUtils.showSexSelect(getSupportFragmentManager(), sexType, this);
                break;
            case R.id.btnChoseAge:
                DialogUtils.showDataSelect(this, btnChoseAge);
                break;
            case R.id.btnChoseEducation:
                DialogUtils.showEducationSelect(getSupportFragmentManager(), educationName, this);
                break;
            case R.id.btnChoseType:
                DialogUtils.showEnrollTypeSelect(getSupportFragmentManager(), this, Constants.OFFICE_TYPE1);
                break;
            case R.id.btnSubmit:
                break;
        }
    }

    @Override
    public void onSexListener(String sex, int type) {
        sexType = type;
        btnChoseSex.setText(sex);
    }

    @Override
    public void onEnrollTypeSelect(DepositSystemModel enrollTypeModel) {
        this.enrollTypeModel = enrollTypeModel;
        btnChoseType.setText((int) enrollTypeModel.getAmount() + "(可抵" + (int) enrollTypeModel.getDeductibleAmount() + "元)");
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(DialogUtils.ENROLL_TYPE_SELECT);
        dialogFragment.dismiss();
    }

    @Override
    public void onEducationListener(TipModel tipModel) {
        this.educationModel = tipModel;
        educationName = tipModel.getValue();
        btnChoseEducation.setText(tipModel.getValue());
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(DialogUtils.EDUCATION_SELECT);
        dialogFragment.dismiss();
    }
}
