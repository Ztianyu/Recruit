package cn.zty.recruit.ui.fragment.person;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.IntegralRecordAdapter;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.bean.IntegralRecordModel;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/20.
 */

public class IntegralRecord extends BaseFragment {
    @BindView(R.id.layoutContent)
    XRecyclerContentLayout layoutContent;

    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    IntegralRecordAdapter adapter;

    @Override
    protected int initLayoutId() {
        return R.layout.x_content;
    }

    @Override
    protected void initView() {
        loadMoreFooter = new LoadMoreFooter(context);

        adapter = new IntegralRecordAdapter(context);

        layoutContent.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        layoutContent.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(layoutContent.getRecyclerView());
        layoutContent.refreshState(true);

        layoutContent.refreshState(false);
    }

    @Override
    protected void initData() {
        List<IntegralRecordModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new IntegralRecordModel());
        }
        adapter.setData(list);
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(context)
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
        recyclerView.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                layoutContent.refreshState(false);
            }

            @Override
            public void onLoadMore(int page) {
                currentPage = page;

                layoutContent.getRecyclerView().setPage(currentPage, maxPage);
            }
        });
        recyclerView.setLoadMoreView(loadMoreFooter);
        recyclerView.setLoadMoreUIHandler(loadMoreFooter);
    }

}
