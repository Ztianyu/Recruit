package cn.zty.recruit.ui.activity.learn;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.TrainAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.TrainingModel;
import cn.zty.recruit.presenter.TrainOrgListPresenter;
import cn.zty.recruit.view.TrainOrgListView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/4/5.
 */

public class TrainSearchActivity extends BaseActivity implements
        XRecyclerView.OnRefreshAndLoadMoreListener,
        TrainOrgListView {

    @BindView(R.id.layoutAutoLine)
    LinearLayout layoutAutoLine;
    @BindView(R.id.autoLineRecycler)
    XRecyclerView autoLineRecycler;
    @BindView(R.id.searchContentLayout)
    XRecyclerContentLayout searchContentLayout;
    @BindView(R.id.btnSearchBack)
    ImageView btnSearchBack;
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;

    LoadMoreFooter loadMoreFooter;

    TrainAdapter trainAdapter;

    private TrainOrgListPresenter trainOrgListPresenter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    private boolean isShowHot = true;//是否展示热门学校

    @Override
    protected int initLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

        editSearch.setHint("搜索学校名称");

        loadMoreFooter = new LoadMoreFooter(this);

        trainAdapter = new TrainAdapter(this);

        searchContentLayout.getRecyclerView().setRefreshEnabled(false);    //设置是否可刷新
        initAdapter(searchContentLayout.getRecyclerView());

        trainOrgListPresenter = new TrainOrgListPresenter();
        trainOrgListPresenter.attach(this);
        presenters.add(trainOrgListPresenter);

    }

    @Override
    protected void initData() {
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(this)
                .setAdapter(trainAdapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
        recyclerView.setOnRefreshAndLoadMoreListener(this);
        recyclerView.setLoadMoreView(loadMoreFooter);
        recyclerView.setLoadMoreUIHandler(loadMoreFooter);
    }

    private void search() {
        String name = editSearch.getText().toString();
        trainOrgListPresenter.getTrainOrgList(name, null, null, null, currentPage);
    }

    @OnClick({R.id.btnSearchBack, R.id.imgSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSearchBack:
                finish();
                break;
            case R.id.imgSearch:
                isShowHot = false;
                layoutAutoLine.setVisibility(View.GONE);
                searchContentLayout.setVisibility(View.VISIBLE);
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                search();
                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore(int page) {
        currentPage = page;
        search();
    }

    @Override
    public void onTrainOrgListSuccess(List<TrainingModel> models) {
        if (!isShowHot) {
            if (models != null && models.size() > 0) {
                if (currentPage == 1) {
                    trainAdapter.setData(models);
                } else {
                    trainAdapter.addData(models);
                }
                if (models.size() < pageSize) {
                    maxPage = currentPage;
                } else {
                    maxPage = currentPage + 1;
                }
                searchContentLayout.getRecyclerView().setPage(currentPage, maxPage);
            } else {
                searchContentLayout.getRecyclerView().setPage(currentPage, maxPage);
                trainAdapter.clearData();
            }
        }
    }
}
