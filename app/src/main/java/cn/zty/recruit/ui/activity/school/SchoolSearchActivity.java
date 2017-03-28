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
import cn.zty.recruit.adapter.SearchSchoolAdapter;
import cn.zty.recruit.adapter.UniversityAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.VocationalModel;
import cn.zty.recruit.presenter.VocationalListPresenter;
import cn.zty.recruit.view.VocationalListView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/28.
 */

public class SchoolSearchActivity extends BaseActivity implements
        XRecyclerView.OnRefreshAndLoadMoreListener,
        VocationalListView {

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

    SearchSchoolAdapter searchSchoolAdapter;

    UniversityAdapter universityAdapter;

    private VocationalListPresenter vocationalListPresenter;

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
        AutoLineLayoutManager layoutManager = new AutoLineLayoutManager();
        layoutManager.setAutoMeasureEnabled(true);
        autoLineRecycler.setLayoutManager(layoutManager);
        searchSchoolAdapter = new SearchSchoolAdapter(this);
        autoLineRecycler.setAdapter(searchSchoolAdapter);

        loadMoreFooter = new LoadMoreFooter(this);

        universityAdapter = new UniversityAdapter(this, false);

        searchContentLayout.getRecyclerView().setRefreshEnabled(false);    //设置是否可刷新
        initAdapter(searchContentLayout.getRecyclerView());

        vocationalListPresenter = new VocationalListPresenter();
        vocationalListPresenter.attach(this);
        presenters.add(vocationalListPresenter);
    }

    @Override
    protected void initData() {
        vocationalListPresenter.getVocationList(null, null, null, null, null, null, 1, 1, 20);
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(this)
                .setAdapter(universityAdapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
        recyclerView.setOnRefreshAndLoadMoreListener(this);
        recyclerView.setLoadMoreView(loadMoreFooter);
        recyclerView.setLoadMoreUIHandler(loadMoreFooter);
    }

    private void search() {
        String name = editSearch.getText().toString();
        vocationalListPresenter.getVocationList(name, null, null, null, null, null, -1, currentPage, Constants.DEFAULT_PAGE_SIZE);
    }

    @OnClick({R.id.btnSearchBack, R.id.imgSearch})
    public void onClick(View view) {
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
    public void onVocationalListSuccess(List<VocationalModel> models) {
        if (!isShowHot) {
            if (models != null && models.size() > 0) {
                if (currentPage == 1) {
                    universityAdapter.setData(models);
                } else {
                    universityAdapter.addData(models);
                }
                if (models.size() < pageSize) {
                    maxPage = currentPage;
                } else {
                    maxPage = currentPage + 1;
                }
                searchContentLayout.getRecyclerView().setPage(currentPage, maxPage);
            } else {
                searchContentLayout.getRecyclerView().setPage(currentPage, maxPage);
                universityAdapter.clearData();
            }
        } else {
            if (models != null && models.size() > 0)
                searchSchoolAdapter.setData(models);
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
