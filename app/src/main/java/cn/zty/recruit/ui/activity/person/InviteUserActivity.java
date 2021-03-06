package cn.zty.recruit.ui.activity.person;

import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.InviteUserAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.presenter.InviteUserPresenter;
import cn.zty.recruit.view.InviteUserView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/4/20.
 */

public class InviteUserActivity extends BaseActivity implements
        XRecyclerView.OnRefreshAndLoadMoreListener,
        InviteUserView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layoutContent)
    XRecyclerContentLayout layoutContent;
    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    private InviteUserAdapter adapter;

    private InviteUserPresenter presenter;

    @Override
    protected int initLayoutId() {
        return R.layout.view_content;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("邀请用户");
        initToolbar(toolbar);

        presenter = new InviteUserPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        adapter = new InviteUserAdapter(this);
        loadMoreFooter = new LoadMoreFooter(this);
        layoutContent.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        layoutContent.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(layoutContent.getRecyclerView());
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
            layoutContent.refreshState(true);
        presenter.getInviteUserList(currentPage);
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

    @Override
    public void onInviteUser(List<UserModel> models) {
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
