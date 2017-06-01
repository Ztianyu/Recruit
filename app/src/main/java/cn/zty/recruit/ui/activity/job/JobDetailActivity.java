package cn.zty.recruit.ui.activity.job;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.baselib.utils.AutoLineLayoutManager;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.JobLabAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.CompanyJobModel;
import cn.zty.recruit.bean.CompanyModel;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.presenter.CompanyPresenter;
import cn.zty.recruit.utils.StringUtils;
import cn.zty.recruit.view.CompanyView;

/**
 * Created by zty on 2017/6/1.
 */

public class JobDetailActivity extends BaseActivity implements CompanyView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textJobName)
    TextView textJobName;
    @BindView(R.id.textJobTime)
    TextView textJobTime;
    @BindView(R.id.textJobSalary)
    TextView textJobSalary;
    @BindView(R.id.textJobArea)
    TextView textJobArea;
    @BindView(R.id.textJobCount)
    TextView textJobCount;
    @BindView(R.id.textJobExp)
    TextView textJobExp;
    @BindView(R.id.textJobEdu)
    TextView textJobEdu;
    @BindView(R.id.textJobAddress)
    TextView textJobAddress;
    @BindView(R.id.textComName)
    TextView textComName;
    @BindView(R.id.textComNature)
    TextView textComNature;
    @BindView(R.id.textComScale)
    TextView textComScale;
    @BindView(R.id.textIndustry)
    TextView textIndustry;
    @BindView(R.id.textJobRemarks)
    TextView textJobRemarks;
    @BindView(R.id.layoutJobHeader)
    LinearLayout layoutJobHeader;
    @BindView(R.id.recyclerViewJobLabel)
    XRecyclerView recyclerViewJobLabel;

    private CompanyJobModel model;
    private CompanyModel companyModel;

    private CompanyPresenter presenter;

    private JobLabAdapter jobLabAdapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_job_detail;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("职位信息");
        initToolbar(toolbar);

        model = getIntent().getExtras().getParcelable("model");

        presenter = new CompanyPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        jobLabAdapter = new JobLabAdapter(this);
        AutoLineLayoutManager layoutManager = new AutoLineLayoutManager();
        layoutManager.setAutoMeasureEnabled(true);
        recyclerViewJobLabel.setLayoutManager(layoutManager);
        recyclerViewJobLabel.setAdapter(jobLabAdapter);
    }

    @Override
    protected void initData() {
        presenter.getCompany(model.getCompanyId());
        textJobName.setText(model.getName());
        textJobTime.setText(model.getCreateDate().substring(0, 10));
        textJobSalary.setText(model.getSalaryLabel() + "元/月");
        textJobArea.setText(model.getAreaNm());
        textJobCount.setText("招" + model.getNumber() + "人");
        textJobExp.setText(model.getExperienceLabel());
        textJobEdu.setText(model.getEducationLabel());
        textComName.setText(model.getCompanyNm());
        textJobRemarks.setText(model.getRemarks());

        if (!TextUtils.isEmpty(model.getLighten())) {
            List<TipModel> list = new ArrayList<>();
            String[] lighten = model.getLighten().split(",");
            for (int i = 0; i < lighten.length; i++) {
                TipModel model = new TipModel();
                model.setValue(StringUtils.replace(lighten[i]));
                list.add(model);
            }
            jobLabAdapter.setData(list);
        }
    }

    @OnClick(R.id.layoutJobHeader)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layoutJobHeader:
                if (companyModel != null)
                    startActivity(new Intent(this, CompanyDetailActivity.class).putExtra("model", companyModel));
                break;
        }

    }

    @Override
    public void onCompany(CompanyModel companyModel) {
        this.companyModel = companyModel;
        textComNature.setText(companyModel.getCompanyNatureLabel());
        textComScale.setText(companyModel.getCompanyScaleLabel());
        textIndustry.setText(companyModel.getIndustryLabel());
        textJobAddress.setText(companyModel.getAddress());
    }
}
