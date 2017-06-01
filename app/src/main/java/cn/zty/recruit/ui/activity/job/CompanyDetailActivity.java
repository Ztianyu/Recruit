package cn.zty.recruit.ui.activity.job;

import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.CompanyJobAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.CompanyJobModel;
import cn.zty.recruit.bean.CompanyModel;
import cn.zty.recruit.presenter.CompanyJobPresenter;
import cn.zty.recruit.utils.WebLoadHtmlUtils;
import cn.zty.recruit.view.CompanyJobView;

/**
 * Created by zty on 2017/6/1.
 */

public class CompanyDetailActivity extends BaseActivity implements CompanyJobView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textComName)
    TextView textComName;
    @BindView(R.id.textComNature)
    TextView textComNature;
    @BindView(R.id.textComScale)
    TextView textComScale;
    @BindView(R.id.textIndustry)
    TextView textIndustry;
    @BindView(R.id.webComRemarks)
    WebView webComRemarks;
    @BindView(R.id.recyclerViewComJob)
    XRecyclerView recyclerViewComJob;

    private CompanyModel companyModel;

    private CompanyJobPresenter presenter;

    private CompanyJobAdapter adapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_com_detail;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("公司信息");
        initToolbar(toolbar);

        webComRemarks.getSettings().setUseWideViewPort(true);
        webComRemarks.getSettings().setLoadWithOverviewMode(true);

        companyModel = getIntent().getExtras().getParcelable("model");

        presenter = new CompanyJobPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        adapter = new CompanyJobAdapter(this);
        recyclerViewComJob.verticalLayoutManager(this)
                .setAdapter(adapter);
        recyclerViewComJob.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
    }

    @Override
    protected void initData() {
        presenter.getCompanyJobList(1, Constants.MAX_PAGE_SIZE, companyModel.getId(), null, null, null, null, null, null, null);

        textComName.setText(companyModel.getName());
        textComNature.setText(companyModel.getCompanyNatureLabel());
        textComScale.setText(companyModel.getCompanyScaleLabel());
        textIndustry.setText(companyModel.getIndustryLabel());

        webComRemarks.loadDataWithBaseURL("file:///android_asset/",
                WebLoadHtmlUtils.loadHtml(companyModel.getName(),
                        companyModel.getContent() + "<br/><br/>公司网址：" + companyModel.getCompanyUrl()),
                "text/html", "utf-8", null);
    }

    @Override
    public void onCompanyJob(List<CompanyJobModel> models) {
        adapter.setData(models);
    }
}
