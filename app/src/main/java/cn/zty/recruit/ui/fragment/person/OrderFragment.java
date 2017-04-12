package cn.zty.recruit.ui.fragment.person;

import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.OrderAdapter;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.bean.OrderModel;
import cn.zty.recruit.listener.DeleteOrderListener;
import cn.zty.recruit.presenter.DeleteOrderPresenter;
import cn.zty.recruit.presenter.OrderPresenter;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.OrderView;
import cn.zty.recruit.view.StringView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/20.
 */

public class OrderFragment extends BaseFragment implements
        XRecyclerView.OnRefreshAndLoadMoreListener,
        OrderView,
        StringView,
        DeleteOrderListener {

    @BindView(R.id.layoutContent)
    XRecyclerContentLayout layoutContent;

    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    private int state;

    private OrderPresenter orderPresenter;

    private DeleteOrderPresenter deleteOrderPresenter;

    private OrderAdapter adapter;

    public static OrderFragment newInstance(int state) {
        OrderFragment fragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("state", state);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.x_content;
    }

    @Override
    protected void initView() {

        state = getArguments().getInt("state", 0);

        orderPresenter = new OrderPresenter();
        orderPresenter.attach(this);
        presenters.add(orderPresenter);

        deleteOrderPresenter = new DeleteOrderPresenter();
        deleteOrderPresenter.attach(this);
        presenters.add(deleteOrderPresenter);

        loadMoreFooter = new LoadMoreFooter(context);

        adapter = new OrderAdapter(context, getFragmentManager(), this);

        layoutContent.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        layoutContent.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(layoutContent.getRecyclerView());
    }

    @Override
    protected void initData() {
        if (currentPage == 1)
            layoutContent.refreshState(true);

        orderPresenter.getOrderList(state, currentPage);
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(context)
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.colorBackground, R.dimen.diverHeight2);
        recyclerView.setOnRefreshAndLoadMoreListener(this);
        recyclerView.setLoadMoreView(loadMoreFooter);
        recyclerView.setLoadMoreUIHandler(loadMoreFooter);
    }

    @Override
    public void onOrderListSuccess(List<OrderModel> models) {
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

    @Override
    public void onOrderDetail(OrderModel model) {

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

    private int currentPosition;

    @Override
    public void onDelete(int position) {
        currentPosition = position;

        String orderId = adapter.getData().get(position).getId();

        deleteOrderPresenter.delete(orderId);
    }

    @Override
    public void onSuccess(String msg) {
        ToastUtils.show("删除成功");
        orderPresenter.getOrderList(state, 1);
    }
}
