package cn.zty.recruit.ui.activity.learn;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.baselib.utils.MyImageLoader;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.InstitutionMajorAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.InstitutionMajorModel;
import cn.zty.recruit.bean.TrainingModel;
import cn.zty.recruit.presenter.CourseSetListPresenter;
import cn.zty.recruit.presenter.TrainOrgPresenter;
import cn.zty.recruit.ui.activity.WebActivity;
import cn.zty.recruit.view.CourseSetListView;
import cn.zty.recruit.view.TrainOrgView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/16.
 */

public class InstitutionDetailActivity extends BaseActivity implements
        View.OnClickListener,
        XRecyclerView.OnRefreshAndLoadMoreListener,
        TrainOrgView,
        CourseSetListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layoutContent)
    XRecyclerContentLayout layoutContent;

    private View header1;
    private View header2;

    private ImageView imgInstitution;
    private TextView textInstitutionName;
    private TextView textInstitutionSpace;
    private TextView textInstitutionAddress;
    private TextView textInstitutionAccount;
    private TextView textInstitutionStartTime;
    private TextView textInstitutionEndTime;
    private TextView textInstitutionIntroduction;

    InstitutionMajorAdapter adapter;

    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    private String orgId;

    private TrainOrgPresenter trainOrgPresenter;

    private CourseSetListPresenter courseSetListPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.view_content;
    }

    @Override
    protected void initView() {
        orgId = getIntent().getStringExtra("orgId");

        toolbar.setTitle("机构详情");
        initToolbar(toolbar);

        header1 = View.inflate(this, R.layout.view_institution_header, null);
        imgInstitution = (ImageView) header1.findViewById(R.id.imgInstitution);
        textInstitutionName = (TextView) header1.findViewById(R.id.textInstitutionName);
        textInstitutionSpace = (TextView) header1.findViewById(R.id.textInstitutionSpace);
        textInstitutionAddress = (TextView) header1.findViewById(R.id.textInstitutionAddress);
        textInstitutionAccount = (TextView) header1.findViewById(R.id.textInstitutionAccount);
        textInstitutionStartTime = (TextView) header1.findViewById(R.id.textInstitutionStartTime);
        textInstitutionEndTime = (TextView) header1.findViewById(R.id.textInstitutionEndTime);

        header2 = View.inflate(this, R.layout.view_institution_header2, null);
        textInstitutionIntroduction = (TextView) header2.findViewById(R.id.textInstitutionIntroduction);
        textInstitutionIntroduction.setOnClickListener(this);

        loadMoreFooter = new LoadMoreFooter(this);
        layoutContent.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        layoutContent.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(layoutContent.getRecyclerView());

        trainOrgPresenter = new TrainOrgPresenter();
        trainOrgPresenter.attach(this);
        presenters.add(trainOrgPresenter);

        courseSetListPresenter = new CourseSetListPresenter();
        courseSetListPresenter.attach(this);
        presenters.add(courseSetListPresenter);
    }

    @Override
    protected void initData() {
        trainOrgPresenter.getTrainOrg(orgId);
        courseSetListPresenter.getCourseSetList(orgId, currentPage);
        layoutContent.refreshState(true);
    }

    private void initAdapter(XRecyclerView recyclerView) {
        if (adapter == null) adapter = new InstitutionMajorAdapter(this);
        recyclerView.verticalLayoutManager(this)
                .setAdapter(adapter);
        recyclerView.setOnRefreshAndLoadMoreListener(this);

        recyclerView.setLoadMoreView(loadMoreFooter);
        recyclerView.setLoadMoreUIHandler(loadMoreFooter);
        recyclerView.addHeaderView(header1);
        recyclerView.addHeaderView(header2);
    }

    @Override
    public void onTrainOrgSuccess(TrainingModel trainingModel) {
        MyImageLoader.load(this, trainingModel.getImgUrl(), imgInstitution);
        textInstitutionName.setText(trainingModel.getName());
        textInstitutionSpace.setText(trainingModel.getAreaNm());
        textInstitutionAddress.setText(trainingModel.getOrgAddress());
        textInstitutionAccount.setText(trainingModel.getNumber() + "人");
        textInstitutionStartTime.setText(trainingModel.getStartDate());
        textInstitutionEndTime.setText(trainingModel.getEndDate());
    }

    @Override
    public void onCourseSetSuccess(List<InstitutionMajorModel> majorModels) {
        layoutContent.refreshState(false);
        if (majorModels != null && majorModels.size() > 0) {
            if (currentPage == 1) {
                adapter.setData(majorModels);
            } else {
                adapter.addData(majorModels);
            }

            if (majorModels.size() < pageSize) {
                maxPage = currentPage;
            } else {
                maxPage = currentPage + 1;
            }
            layoutContent.getRecyclerView().setPage(currentPage, maxPage);
        } else {
            if (currentPage == 1) {
                adapter.clearData();
            }
            layoutContent.getRecyclerView().setPage(currentPage, currentPage);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textInstitutionIntroduction)
            startActivity(new Intent(this, WebActivity.class));
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        courseSetListPresenter.getCourseSetList(orgId, currentPage);
    }

    @Override
    public void onLoadMore(int page) {
        currentPage = page;
        courseSetListPresenter.getCourseSetList(orgId, currentPage);
    }
}
