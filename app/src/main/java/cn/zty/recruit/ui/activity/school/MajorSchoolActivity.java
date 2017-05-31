package cn.zty.recruit.ui.activity.school;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.UniversityAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.VocationalModel;
import cn.zty.recruit.presenter.VocationalListPresenter;
import cn.zty.recruit.view.VocationalListView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/21.
 */

public class MajorSchoolActivity extends BaseActivity implements VocationalListView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layoutContent)
    XRecyclerContentLayout layoutContent;

    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    UniversityAdapter adapter;

    private String discipline;
    private String name;
    private String majorId;

    private VocationalListPresenter vocationalListPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.view_content;
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        discipline = bundle.getString("discipline");
        name = bundle.getString("name");
        majorId = bundle.getString("majorId");

        toolbar.setTitle(name);
        initToolbar(toolbar);

        vocationalListPresenter = new VocationalListPresenter();
        vocationalListPresenter.attach(this);
        presenters.add(vocationalListPresenter);

        adapter = new UniversityAdapter(this, false);

        loadMoreFooter = new LoadMoreFooter(this);

        layoutContent.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        layoutContent.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(layoutContent.getRecyclerView());
    }

    @Override
    protected void initData() {
        if (currentPage == 1)
            layoutContent.refreshState(true);
        vocationalListPresenter.getVocationList(null, null,null,null, discipline, majorId, null, null, -1, currentPage, Constants.DEFAULT_PAGE_SIZE);
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(this)
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
        recyclerView.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
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
        });
        recyclerView.setLoadMoreView(loadMoreFooter);
        recyclerView.setLoadMoreUIHandler(loadMoreFooter);
    }

    @Override
    public void onVocationalListSuccess(List<VocationalModel> models) {
        layoutContent.refreshState(false);
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
            layoutContent.getRecyclerView().setPage(currentPage, maxPage);
        } else {
            layoutContent.getRecyclerView().setPage(currentPage, currentPage);
        }

    }
}
