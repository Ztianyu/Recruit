package cn.zty.recruit.ui.activity.person;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.VersionUtils;
import cn.zty.baselib.widget.StripViewNoImg;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.utils.ToastUtils;

/**
 * Created by zty on 2017/3/21.
 */

public class VersionActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textVersion)
    TextView textVersion;
    @BindView(R.id.textVersionUpdate)
    StripViewNoImg textVersionUpdate;

    String strVersion;
    int versionCode;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_version;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("版本信息");
        initToolbar(toolbar);
        strVersion = VersionUtils.getVersion(this);
        versionCode = VersionUtils.getVersionCode(this);
        textVersion.setText("版本号：" + strVersion);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.textVersionUpdate)
    public void onClick() {
        ToastUtils.show("已经是最新版本了");
    }
}
