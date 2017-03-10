package cn.zty.recruit.ui.fragment.home;

import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.base.RecruitApplication;

/**
 * Created by zty on 2017/3/6.
 */

public class LifeFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.fragmentContentLayout)
    XRecyclerContentLayout fragmentContentLayout;
    @BindView(R.id.layoutStatus)
    LinearLayout layoutStatus;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initView() {
        layoutStatus.setPadding(0, RecruitApplication.getInstance().getStatusBarHeight(), 0, 0);
        title.setText("生\u3000活");
    }

    @Override
    protected void initData() {

    }
}
