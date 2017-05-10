package cn.zty.recruit.ui.activity.learn;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.DepositSystemModel;
import cn.zty.recruit.bean.InstitutionMajorModel;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.listener.EducationSelectListener;
import cn.zty.recruit.listener.EnrollTypeSelectListener;
import cn.zty.recruit.listener.SexSelectListener;
import cn.zty.recruit.presenter.GetUserPresenter;
import cn.zty.recruit.presenter.SubmitOrderPresenter;
import cn.zty.recruit.ui.activity.PayActivity;
import cn.zty.recruit.ui.activity.person.LoginActivity;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.StringView;
import cn.zty.recruit.view.UserView;

/**
 * Created by zty on 2017/3/17.
 */

public class EnrollActivity extends BaseActivity implements
        SexSelectListener,
        EducationSelectListener,
        EnrollTypeSelectListener,
        StringView,
        UserView {

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
    private String educationName;
    private String educationCode;

    private InstitutionMajorModel majorModel;

    private SubmitOrderPresenter submitOrderPresenter;

    private GetUserPresenter getUserPresenter;

    private UserModel userModel;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_class_enroll;
    }

    @Override
    protected void initView() {
        majorModel = getIntent().getParcelableExtra("model");

        toolbar.setTitle("在线报名");
        initToolbar(toolbar);

        submitOrderPresenter = new SubmitOrderPresenter();
        submitOrderPresenter.attach(this);
        presenters.add(submitOrderPresenter);

        getUserPresenter = new GetUserPresenter();
        getUserPresenter.attach(this);
        presenters.add(getUserPresenter);
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
        } else {
            if (!TextUtils.isEmpty(RecruitApplication.getInstance().getUserId()))
                getUserPresenter.getUser(RecruitApplication.getInstance().getTokenId(),
                        RecruitApplication.getInstance().getUserId());
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
                DialogUtils.showEnrollTypeSelect(getSupportFragmentManager(), this, majorModel.getSchoolId());
                break;
            case R.id.btnSubmit:
                submitOrder();
                break;
        }
    }

    private void submitOrder() {
        if (check())
            submitOrderPresenter.submitOrder(Constants.OFFICE_TYPE1, majorModel.getId(), enrollTypeModel.getId(),
                    editEnrollName.getText().toString(),
                    btnChoseSex.getText().toString(),
                    btnChoseAge.getText().toString(),
                    editEnrollPhone.getText().toString(),
                    educationCode,
                    editEnrollPs.getText().toString());
    }

    public boolean check() {
        if (TextUtils.isEmpty(RecruitApplication.getInstance().getUserId())) {
            ToastUtils.show("请先登录");
            startActivity(new Intent(this, LoginActivity.class));
            return false;
        }
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


        if (enrollTypeModel != null && enrollTypeModel.getAmount() != 0) {
            if (enrollTypeModel.getAmount() == enrollTypeModel.getDeductibleAmount()) {
                btnChoseType.setText((int) enrollTypeModel.getAmount() + "元)");
            } else {
                btnChoseType.setText((int) enrollTypeModel.getAmount() + "(可抵" + (int) enrollTypeModel.getDeductibleAmount() + "元)");
                textBillTip.setText("(可抵" + (int) enrollTypeModel.getDeductibleAmount() + "元)");
            }
            textBillMoney.setText((int) enrollTypeModel.getAmount() + "");
        } else {
            btnChoseType.setText("无折扣");
            textBillMoney.setText("无定金");
        }

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

    @Override
    public void onUserSuccess(UserModel userModel) {
        if (userModel != null) {
            RecruitApplication.getInstance().setUserModel(userModel);
            this.userModel = userModel;
            onResume();
        }
    }
}
