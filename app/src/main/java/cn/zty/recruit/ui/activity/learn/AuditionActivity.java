package cn.zty.recruit.ui.activity.learn;

import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.RadioAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.RadioModel;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * 在线试听
 * Created by zty on 2017/3/17.
 */

public class AuditionActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layoutContent)
    XRecyclerContentLayout layoutContent;

    RadioAdapter adapter;

    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    @Override
    protected int initLayoutId() {
        return R.layout.view_content;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("在线试听");
        initToolbar(toolbar);

        loadMoreFooter = new LoadMoreFooter(this);

        adapter = new RadioAdapter(this);

        layoutContent.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        layoutContent.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(layoutContent.getRecyclerView());
        layoutContent.refreshState(true);

    }

    @Override
    protected void initData() {

        List<RadioModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new RadioModel());
        }
        adapter.setData(list);

        layoutContent.refreshState(false);
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(this)
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
