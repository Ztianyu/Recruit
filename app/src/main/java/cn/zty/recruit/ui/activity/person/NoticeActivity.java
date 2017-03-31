package cn.zty.recruit.ui.activity.person;

import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.NoticeAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.NoticeModel;
import cn.zty.recruit.presenter.NoticePresenter;
import cn.zty.recruit.view.NoticeView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/21.
 */

public class NoticeActivity extends BaseActivity implements NoticeView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layoutContent)
    XRecyclerContentLayout layoutContent;

    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    NoticeAdapter adapter;

    private NoticePresenter presenter;

    @Override
    protected int initLayoutId() {
        return R.layout.view_content;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("系统通知");
        initToolbar(toolbar);

        presenter = new NoticePresenter();
        presenter.attach(this);
        presenters.add(presenter);

        loadMoreFooter = new LoadMoreFooter(this);

        adapter = new NoticeAdapter(this);

        layoutContent.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        layoutContent.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(layoutContent.getRecyclerView());
    }

    @Override
    protected void initData() {
        if (currentPage == 1)
            layoutContent.refreshState(true);

        presenter.getNotice(currentPage);
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
    public void onNoticeSuccess(List<NoticeModel> models) {
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
            if (currentPage == 1)
                adapter.clearData();
            layoutContent.getRecyclerView().setPage(currentPage, maxPage);
        }
    }
}
