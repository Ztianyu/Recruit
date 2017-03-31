package cn.zty.recruit.ui.fragment.person;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.IntegralRecordAdapter;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.bean.IntegralRecordModel;
import cn.zty.recruit.presenter.IntegralRecordPresenter;
import cn.zty.recruit.view.IntegralRecordView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/20.
 */

public class IntegralRecord extends BaseFragment implements IntegralRecordView {
    @BindView(R.id.layoutContent)
    XRecyclerContentLayout layoutContent;

    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    IntegralRecordAdapter adapter;

    private IntegralRecordPresenter presenter;

    @Override
    protected int initLayoutId() {
        return R.layout.x_content_white;
    }

    @Override
    protected void initView() {

        presenter = new IntegralRecordPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        loadMoreFooter = new LoadMoreFooter(context);

        adapter = new IntegralRecordAdapter(context);

        layoutContent.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        layoutContent.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(layoutContent.getRecyclerView());
    }

    @Override
    protected void initData() {
        if (currentPage == 1)
            layoutContent.refreshState(true);

        presenter.getIntegralRecord(currentPage);
//        List<IntegralRecordModel> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(new IntegralRecordModel());
//        }
//        adapter.setData(list);
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(context)
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
    public void onIntegralRecordSuccess(List<IntegralRecordModel> models) {
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
