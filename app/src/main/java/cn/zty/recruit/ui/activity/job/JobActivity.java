package cn.zty.recruit.ui.activity.job;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.CompanyJobAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.CompanyJobModel;
import cn.zty.recruit.listener.AreaSelectListener;
import cn.zty.recruit.listener.IndustrySelectListener;
import cn.zty.recruit.presenter.CompanyJobPresenter;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.SnackbarUtils;
import cn.zty.recruit.view.CompanyJobView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/18.
 */

public class JobActivity extends BaseActivity implements
        CompanyJobView,
        AreaSelectListener,
        IndustrySelectListener,
        XRecyclerView.OnRefreshAndLoadMoreListener {
    @BindView(R.id.textProvinceTip)
    TextView textProvinceTip;
    @BindView(R.id.textCityTip)
    TextView textCityTip;
    @BindView(R.id.textMajorTip)
    TextView textMajorTip;
    @BindView(R.id.layoutSchoolSelect)
    LinearLayout layoutSchoolSelect;
    @BindView(R.id.contentLayoutJob)
    XRecyclerContentLayout contentLayoutJob;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    private String province;
    private String city;
    private String industry;

    private CompanyJobPresenter presenter;

    private CompanyJobAdapter adapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_job;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("就业");
        initToolbar(toolbar);
        textMajorTip.setText("行业类别");

        presenter = new CompanyJobPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        loadMoreFooter = new LoadMoreFooter(this);

        adapter = new CompanyJobAdapter(this);

        contentLayoutJob.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        contentLayoutJob.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(contentLayoutJob.getRecyclerView());
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(this)
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
        recyclerView.setOnRefreshAndLoadMoreListener(this);
        recyclerView.setLoadMoreView(loadMoreFooter);
        recyclerView.setLoadMoreUIHandler(loadMoreFooter);
    }

    @Override
    protected void initData() {
        if (currentPage == 1)
            contentLayoutJob.refreshState(true);
        presenter.getCompanyJobList(currentPage,pageSize, null, null, null, province, city, null, null, industry);
    }

    @OnClick({R.id.textProvinceTip, R.id.textCityTip, R.id.textMajorTip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textProvinceTip:
                DialogUtils.showAreaSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + toolbar.getHeight(), 0, this, province);
                break;
            case R.id.textCityTip:
                if (!TextUtils.isEmpty(province)) {
                    DialogUtils.showAreaSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + toolbar.getHeight(), 1, this, province);
                } else {
                    SnackbarUtils.showShort(toolbar, "请选择省份");
                }
                break;
            case R.id.textMajorTip:
                DialogUtils.showIndustrySelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + toolbar.getHeight(), this);
                break;
        }
    }

    @Override
    public void onCompanyJob(List<CompanyJobModel> models) {
        contentLayoutJob.refreshState(false);
        if (models != null && models.size() > 0) {
            if (currentPage == 1) {
                adapter.setData(models);
            } else {
                adapter.addData(models);
            }

            if (models.size() < pageSize) {
                maxPage = currentPage;
            } else {
                maxPage = currentPage + 1;
            }
            contentLayoutJob.getRecyclerView().setPage(currentPage, maxPage);
        } else {
            if (currentPage == 1)
                adapter.clearData();
            contentLayoutJob.getRecyclerView().setPage(currentPage, maxPage);
        }
    }

    @Override
    public void onAreaSelect(String code, String value, int type) {
        if (type == 0) {
            if (value.equals("全部")) {
                province = null;
                city = null;
                textProvinceTip.setText("省份");
                textCityTip.setText("城市");
            } else {
                province = code;
                textProvinceTip.setText(value);
            }
        } else {
            city = code;
            textCityTip.setText(value);
        }
        initData();
    }

    @Override
    public void onIndustrySelect(String code, String value) {
        if (value.equals("全部")) {
            industry = null;
            textMajorTip.setText("行业类别");
        } else {
            industry = code;
            textMajorTip.setText(value);
        }
        initData();
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        initData();
    }

    @Override
    public void onLoadMore(int page) {
        currentPage = page;
        initData();
    }
}
