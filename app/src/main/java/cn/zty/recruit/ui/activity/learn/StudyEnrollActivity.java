package cn.zty.recruit.ui.activity.learn;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseData;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.DepositSystemModel;
import cn.zty.recruit.bean.StudyMajorModel;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.listener.EducationSelectListener;
import cn.zty.recruit.listener.EnrollTypeSelectListener;
import cn.zty.recruit.listener.SexSelectListener;
import cn.zty.recruit.presenter.SubmitOrderPresenter;
import cn.zty.recruit.ui.activity.PayActivity;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.StringView;

/**
 * Created by zty on 2017/3/18.
 */

public class StudyEnrollActivity extends BaseActivity implements
        SexSelectListener,
        EducationSelectListener,
        EnrollTypeSelectListener,
        StringView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.textMajorChildName)
    TextView textMajorChildName;
    @BindView(R.id.textStudyLength)
    TextView textStudyLength;
    @BindView(R.id.textStudyEducation)
    TextView textStudyEducation;
    @BindView(R.id.textStudyPrise)
    TextView textStudyPrise;

    private StudyMajorModel studyMajorModel;

    private int sexType = -1;
    private DepositSystemModel enrollTypeModel;
    private String educationName;
    private String educationCode;

    private SubmitOrderPresenter submitOrderPresenter;

    private UserModel userModel;

    private String office;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_study_major_enroll;
    }

    @Override
    protected void initView() {

        office = getIntent().getStringExtra("office");
        studyMajorModel = getIntent().getParcelableExtra("majorModel");

        toolbar.setTitle("在线报名");
        toolbar.inflateMenu(R.menu.phone);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.takePhone:
                        DialogUtils.showCall(getSupportFragmentManager(), BaseData.studySchoolPhone, BaseData.studySchoolTime);
                        return true;
                }
                return false;
            }
        });

        submitOrderPresenter = new SubmitOrderPresenter();
        submitOrderPresenter.attach(this);
        presenters.add(submitOrderPresenter);
    }

    @Override
    protected void initData() {
        textStudyMajorIntroduction.setText("专业介绍");
        if (studyMajorModel != null) {
            textMajorChildName.setText(studyMajorModel.getMajorNm());
            textStudyLength.setText("学制" + studyMajorModel.getSchoolLength() + "年");
            textStudyEducation.setText(studyMajorModel.getStudyTypeLabel());
            textStudyPrise.setText(studyMajorModel.getRegistrationFee() + "元/" +
                    studyMajorModel.getChargeStandardLabel().replace("按", "").replace("收", ""));

            expandText.setText(MyTextUtils.notNullStr(studyMajorModel.getRemarks()));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        userModel = RecruitApplication.getInstance().getUserModel();
        if (userModel != null) {
            editEnrollName.setText(MyTextUtils.notNullStr(userModel.getFullNm()));
            btnChoseSex.setText(MyTextUtils.notNullStr(userModel.getSex()));
            btnChoseAge.setText(MyTextUtils.notNullStr(userModel.getBirthDate()));
            editEnrollPhone.setText(MyTextUtils.notNullStr(userModel.getMobile()));
            editEnrollName.setText(MyTextUtils.notNullStr(userModel.getFullNm()));
            btnChoseEducation.setText(MyTextUtils.notNullStr(userModel.getEducationLabel()));

            educationName = MyTextUtils.notNullStr(userModel.getEducationLabel());
            educationCode = MyTextUtils.notNullStr(userModel.getEducation());
        }
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
                DialogUtils.showEnrollTypeSelect(getSupportFragmentManager(), this, office);
                break;
            case R.id.btnSubmit:
                submitOrder();
                break;
        }
    }

    private void submitOrder() {
        if (check())
            submitOrderPresenter.submitOrder(office,
                    studyMajorModel.getId(),
                    enrollTypeModel.getId(),
                    editEnrollName.getText().toString(),
                    btnChoseSex.getText().toString(),
                    btnChoseAge.getText().toString(),
                    editEnrollPhone.getText().toString(),
                    educationCode,
                    editEnrollPs.getText().toString());
    }

    public boolean check() {
        if (enrollTypeModel == null) {
            ToastUtils.show("请选择定金类型");
            return false;
        }
        if (TextUtils.isEmpty(editEnrollName.getText().toString())) {
            ToastUtils.show("请输入姓名");
            return false;
        }
        if (TextUtils.isEmpty(btnChoseSex.getText().toString())) {
            ToastUtils.show("请选择性别");
            return false;
        }
        if (TextUtils.isEmpty(btnChoseAge.getText().toString())) {
            ToastUtils.show("请选择出生日期");
            return false;
        }
        if (TextUtils.isEmpty(editEnrollPhone.getText().toString())) {
            ToastUtils.show("请填写手机号码");
            return false;
        }
        if (TextUtils.isEmpty(educationCode)) {
            ToastUtils.show("请选择学历");
            return false;
        }
        return true;
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

        textBillMoney.setText((int) enrollTypeModel.getAmount() + "");
        textBillTip.setText("(可抵" + (int) enrollTypeModel.getDeductibleAmount() + "元)");

        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(DialogUtils.ENROLL_TYPE_SELECT);
        dialogFragment.dismiss();
    }

    @Override
    public void onEducationListener(TipModel tipModel) {
        educationName = tipModel.getValue();
        educationCode = tipModel.getKey();

        btnChoseEducation.setText(tipModel.getValue());
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(DialogUtils.EDUCATION_SELECT);
        dialogFragment.dismiss();
    }

    @Override
    public void onSuccess(String msg) {
        startActivity(new Intent(this, PayActivity.class)
                .putExtra("orderCode", msg)
                .putExtra("money", enrollTypeModel.getAmount() + ""));
        finish();
    }
}
