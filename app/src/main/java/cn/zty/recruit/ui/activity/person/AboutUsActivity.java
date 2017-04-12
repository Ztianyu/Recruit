package cn.zty.recruit.ui.activity.person;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

/**
 * Created by zty on 2017/4/12.
 */

public class AboutUsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textAboutUs)
    TextView textAboutUs;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_us;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("关于我们");
        initToolbar(toolbar);
        textAboutUs.setText("\u3000\u3000" + ResourceUtil.resToStr(this, R.string.aboutUs));
    }

    @Override
    protected void initData() {
    }
}
