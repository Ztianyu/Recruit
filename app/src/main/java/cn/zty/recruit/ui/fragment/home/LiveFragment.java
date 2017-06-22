package cn.zty.recruit.ui.fragment.home;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.LiveAdapter;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.LiveModel;
import cn.zty.recruit.listener.LiveItemListener;
import cn.zty.recruit.ui.activity.live.SendLiveActivity;
import cn.zty.recruit.utils.SnackbarUtils;

/**
 * Created by zty on 2017/3/6.
 */

public class LiveFragment extends BaseFragment implements LiveItemListener {
    @BindView(R.id.titleLeft)
    TextView titleLeft;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.titleRight)
    TextView titleRight;
    @BindView(R.id.layoutStatus)
    LinearLayout layoutStatus;
    @BindView(R.id.fragmentContentLayout)
    XRecyclerContentLayout fragmentContentLayout;

    LiveAdapter liveAdapter;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initView() {
        layoutStatus.setPadding(0, RecruitApplication.getInstance().getStatusBarHeight(), 0, 0);
        title.setText("直\u3000播");
        titleRight.setText("发 布");

        fragmentContentLayout.getRecyclerView().setRefreshEnabled(false);    //设置是否可刷新

        liveAdapter = new LiveAdapter(context, this);
        fragmentContentLayout.getRecyclerView().verticalLayoutManager(context)
                .setAdapter(liveAdapter);
        fragmentContentLayout.getRecyclerView().horizontalDivider(R.color.transparent, R.dimen.diverHeight);
    }

    @Override
    protected void initData() {
        List<LiveModel> liveModels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            liveModels.add(new LiveModel());
        }
        liveAdapter.setData(liveModels);
    }

    @OnClick(R.id.titleRight)
    public void onViewClicked() {
        startActivity(new Intent(context, SendLiveActivity.class));
    }


    @Override
    public void onLiveClick(int position) {
        SnackbarUtils.showLong(title, "开发中，敬请期待");
    }
}
