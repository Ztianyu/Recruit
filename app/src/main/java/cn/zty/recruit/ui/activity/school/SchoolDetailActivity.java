package cn.zty.recruit.ui.activity.school;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.baselib.utils.MyImageLoader;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.SchoolLabAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseData;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.bean.VocationalModel;
import cn.zty.recruit.listener.VisitListener;
import cn.zty.recruit.presenter.VisitPresenter;
import cn.zty.recruit.presenter.VocationPresenter;
import cn.zty.recruit.ui.activity.WebActivity;
import cn.zty.recruit.ui.activity.learn.StudyMajorActivity;
import cn.zty.recruit.ui.activity.person.ArchivesActivity;
import cn.zty.recruit.ui.activity.person.LoginActivity;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.SnackbarUtils;
import cn.zty.recruit.utils.StringUtils;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.StringView;
import cn.zty.recruit.view.VocationView;
import cn.zty.recruit.widget.LabView;

/**
 * Created by zty on 2017/3/13.
 */

public class SchoolDetailActivity extends BaseActivity implements
        VocationView,
        StringView,
        VisitListener {

    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgSchool)
    ImageView imgSchool;
    @BindView(R.id.textSchoolName)
    TextView textSchoolName;
    @BindView(R.id.autoLineLayout)
    XRecyclerView autoLineLayout;
    @BindView(R.id.textSchoolSpace)
    TextView textSchoolSpace;
    @BindView(R.id.textSchoolNature)
    TextView textSchoolNature;
    @BindView(R.id.textSchoolCreateData)
    TextView textSchoolCreateData;
    @BindView(R.id.textSchoolAddress)
    TextView textSchoolAddress;
    @BindView(R.id.labSchool1)
    LabView labSchool1;
    @BindView(R.id.labSchool2)
    LabView labSchool2;
    @BindView(R.id.labSchool3)
    LabView labSchool3;
    @BindView(R.id.labSchool4)
    LabView labSchool4;
    @BindView(R.id.labSchool5)
    LabView labSchool5;
    @BindView(R.id.btnSchoolOrder)
    RelativeLayout btnSchoolOrder;
    @BindView(R.id.btnSchoolCall)
    RelativeLayout btnSchoolCall;

    SchoolLabAdapter labAdapter;

    SchoolLabAdapter adapter;

    VocationPresenter presenter;


    private VisitPresenter visitPresenter;

    private String schoolId;

    private VocationalModel vocationalModel;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_school_detail;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("院校概况");
        initToolbar(toolbar);

        schoolId = getIntent().getStringExtra("schoolId");

        presenter = new VocationPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        visitPresenter = new VisitPresenter();
        visitPresenter.attach(this);
        presenters.add(visitPresenter);

        labAdapter = new SchoolLabAdapter(this, false);
        autoLineLayout.horizontalLayoutManager(this)
                .setAdapter(labAdapter);

        adapter = new SchoolLabAdapter(this, true);
    }

    @Override
    protected void initData() {
        presenter.getVocationalSchool(schoolId);
    }

    @OnClick({R.id.labSchool1, R.id.labSchool2, R.id.labSchool3, R.id.labSchool4, R.id.labSchool5, R.id.btnSchoolOrder, R.id.btnSchoolCall})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.labSchool1:
                startActivity(new Intent(this, WebActivity.class)
                        .putExtra("schoolId", schoolId)
                        .putExtra("title", vocationalModel.getName())
                        .putExtra("type", WebActivity.TYPE1));
                break;
            case R.id.labSchool2:
                startActivity(new Intent(this, PanoramaActivity.class)
                        .putExtra("schoolId", schoolId)
                        .putExtra("schoolGate", vocationalModel.getSchoolGateImgUrl()));
                break;
            case R.id.labSchool3:
                startActivity(new Intent(this, CollegeActivity.class)
                        .putExtra("schoolId", schoolId));
                break;
            case R.id.labSchool4:
                startActivity(new Intent(this, WebActivity.class)
                        .putExtra("schoolId", schoolId)
                        .putExtra("title", "师资力量")
                        .putExtra("type", WebActivity.TYPE2));
                break;
            case R.id.labSchool5:
                startActivity(new Intent(this, StudyMajorActivity.class)
                        .putExtra("schoolId", schoolId)
                        .putExtra("office", Constants.OFFICE_TYPE0));
                break;
            case R.id.btnSchoolOrder:
                if (TextUtils.isEmpty(RecruitApplication.getInstance().getUserId())) {
                    ToastUtils.show("请先登录");
                    startActivity(new Intent(this, LoginActivity.class));
                    break;
                } else if (RecruitApplication.getInstance().getUserModel() != null &&
                        TextUtils.isEmpty(RecruitApplication.getInstance().getUserModel().getFullNm())) {
                    ToastUtils.show("请先完善个人信息");
                    startActivity(new Intent(this, ArchivesActivity.class));
                    break;
                }
                DialogUtils.showVisit(getSupportFragmentManager(), this);
                break;
            case R.id.btnSchoolCall:
                if (TextUtils.isEmpty(vocationalModel.getContactTel())) {
                    SnackbarUtils.showShort(toolbar, "暂无咨询电话");
                    break;
                } else if (TextUtils.isEmpty(RecruitApplication.getInstance().getUserId())) {
                    ToastUtils.show("请先登录");
                    startActivity(new Intent(this, LoginActivity.class));
                    break;
                } else if (RecruitApplication.getInstance().getUserModel() != null &&
                        TextUtils.isEmpty(RecruitApplication.getInstance().getUserModel().getFullNm())) {
                    ToastUtils.show("请先完善个人信息");
                    startActivity(new Intent(this, ArchivesActivity.class));
                    break;
                }
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + vocationalModel.getCustomerTel()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onVocationSuccess(VocationalModel model) {
        if (model != null) {
            vocationalModel = model;

            BaseData.studySchoolPhone = model.getContactTel();

            autoLineLayout.horizontalLayoutManager(this)
                    .setAdapter(adapter);
            List<TipModel> list = new ArrayList<>();
            String[] educationTypes = model.getEducationTypeLabel().split(",");
            for (int i = 0; i < educationTypes.length; i++) {
                TipModel tipModel = new TipModel();
                tipModel.setValue(StringUtils.replace(educationTypes[i]));
                list.add(tipModel);
            }
            adapter.setData(list);

            MyImageLoader.load(this, model.getImgUrl(), imgSchool);
            textSchoolName.setText(model.getName());
            textSchoolSpace.setText(model.getAreaNm());
            textSchoolNature.setText(model.getSchoolNatureLabel());
            textSchoolCreateData.setText(model.getEstablishDate());
            textSchoolAddress.setText(model.getSchoolAddress());
        }
    }

    @Override
    public void onVisit(String fullNm, String mobile) {
        visitPresenter.visit(fullNm, mobile, schoolId);
    }

    @Override
    public void onSuccess(String msg) {
        SnackbarUtils.showShort(toolbar, "提交成功");
    }

}
