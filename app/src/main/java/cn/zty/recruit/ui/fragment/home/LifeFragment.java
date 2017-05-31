package cn.zty.recruit.ui.fragment.home;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.utils.SnackbarUtils;
import cn.zty.recruit.widget.LabView;

/**
 * Created by zty on 2017/3/6.
 */

public class LifeFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layoutStatus)
    LinearLayout layoutStatus;
    @BindView(R.id.labLife1)
    LabView labLife1;
    @BindView(R.id.labLife2)
    LabView labLife2;
    @BindView(R.id.labLife3)
    LabView labLife3;
    @BindView(R.id.labLife4)
    LabView labLife4;
    @BindView(R.id.labLife5)
    LabView labLife5;
    @BindView(R.id.labLife6)
    LabView labLife6;
    Unbinder unbinder;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_life;
    }

    @Override
    protected void initView() {
        layoutStatus.setPadding(0, RecruitApplication.getInstance().getStatusBarHeight(), 0, 0);
        title.setText("生\u3000活");
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.labLife1, R.id.labLife2, R.id.labLife3, R.id.labLife4, R.id.labLife5, R.id.labLife6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.labLife1:
                SnackbarUtils.showLong(title, "开发中，敬请期待");
                break;
            case R.id.labLife2:
                SnackbarUtils.showLong(title, "开发中，敬请期待");
                break;
            case R.id.labLife3:
                SnackbarUtils.showLong(title, "开发中，敬请期待");
                break;
            case R.id.labLife4:
                SnackbarUtils.showLong(title, "开发中，敬请期待");
                break;
            case R.id.labLife5:
                SnackbarUtils.showLong(title, "开发中，敬请期待");
                break;
            case R.id.labLife6:
                SnackbarUtils.showLong(title, "开发中，敬请期待");
                break;
        }
    }
}
