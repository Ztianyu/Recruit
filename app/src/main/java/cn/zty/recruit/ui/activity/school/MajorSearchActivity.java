package cn.zty.recruit.ui.activity.school;

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
import cn.zty.baselib.utils.AutoLineLayoutManager;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.MajorAdapter;
import cn.zty.recruit.adapter.SearchMajorAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.presenter.HotMajorPresenter;
import cn.zty.recruit.view.HotMajorView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/4/5.
 */

public class MajorSearchActivity extends BaseActivity implements
        XRecyclerView.OnRefreshAndLoadMoreListener,
        HotMajorView {

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

    SearchMajorAdapter searchMajorAdapter;

    MajorAdapter majorAdapter;

    private HotMajorPresenter hotMajorPresenter;

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
        editSearch.setHint("搜索专业名称");
        
        AutoLineLayoutManager layoutManager = new AutoLineLayoutManager();
        layoutManager.setAutoMeasureEnabled(true);
        autoLineRecycler.setLayoutManager(layoutManager);
        searchMajorAdapter = new SearchMajorAdapter(this);
        autoLineRecycler.setAdapter(searchMajorAdapter);

        loadMoreFooter = new LoadMoreFooter(this);

        majorAdapter = new MajorAdapter(this);

        searchContentLayout.getRecyclerView().setRefreshEnabled(false);    //设置是否可刷新
        initAdapter(searchContentLayout.getRecyclerView());

        hotMajorPresenter = new HotMajorPresenter();
        hotMajorPresenter.attach(this);
        presenters.add(hotMajorPresenter);

    }

    @Override
    protected void initData() {
        hotMajorPresenter.getHotMajorList(null, 1, null, currentPage, 20);
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(this)
                .setAdapter(majorAdapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
        recyclerView.setOnRefreshAndLoadMoreListener(this);
        recyclerView.setLoadMoreView(loadMoreFooter);
        recyclerView.setLoadMoreUIHandler(loadMoreFooter);
    }

    private void search() {
        String name = editSearch.getText().toString();
        hotMajorPresenter.getHotMajorList(name, -1, null, currentPage, Constants.DEFAULT_PAGE_SIZE);
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
    public void onHotMajorSuccess(List<MajorModel> majorModels) {
        if (!isShowHot) {
            if (majorModels != null && majorModels.size() > 0) {
                if (currentPage == 1) {
                    majorAdapter.setData(majorModels);
                } else {
                    majorAdapter.addData(majorModels);
                }
                if (majorModels.size() < pageSize) {
                    maxPage = currentPage;
                } else {
                    maxPage = currentPage + 1;
                }
                searchContentLayout.getRecyclerView().setPage(currentPage, maxPage);
            } else {
                searchContentLayout.getRecyclerView().setPage(currentPage, maxPage);
                majorAdapter.clearData();
            }
        } else {
            if (majorModels != null && majorModels.size() > 0)
                searchMajorAdapter.setData(majorModels);
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
}
