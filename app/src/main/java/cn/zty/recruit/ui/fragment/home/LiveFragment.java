package cn.zty.recruit.ui.fragment.home;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.base.RecruitApplication;

/**
 * Created by zty on 2017/3/6.
 */

public class LiveFragment extends BaseFragment {
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

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initView() {
        layoutStatus.setPadding(0, RecruitApplication.getInstance().getStatusBarHeight(), 0, 0);
        title.setText("直\u3000播");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.titleLeft, R.id.title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleLeft:
                break;
            case R.id.title:
                break;
        }
    }
}
