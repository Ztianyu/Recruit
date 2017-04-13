package cn.zty.recruit.ui.activity.person;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.VersionUtils;
import cn.zty.baselib.widget.StripViewNoImg;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.VersionModel;
import cn.zty.recruit.presenter.VersionPresenter;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.SnackbarUtils;
import cn.zty.recruit.view.VersionView;

/**
 * Created by zty on 2017/3/21.
 */

public class VersionActivity extends BaseActivity implements
        VersionView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textVersion)
    TextView textVersion;
    @BindView(R.id.textVersionUpdate)
    StripViewNoImg textVersionUpdate;

    String strVersion;
    int versionCode;

    VersionPresenter versionPresenter;

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

        versionPresenter = new VersionPresenter();
        versionPresenter.attach(this);
        presenters.add(versionPresenter);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.textVersionUpdate)
    public void onClick() {
        versionPresenter.getVersion();
    }

    @Override
    public void onSuccess(VersionModel versionModel) {
        if (versionModel != null) {
            int versionCode = VersionUtils.getVersionCode(this);
            if (versionModel.getAppVersion() > versionCode) {
                DialogUtils.showVersion(getSupportFragmentManager(), "有新版本啦", versionModel);
            } else {
                SnackbarUtils.showShort(toolbar, "已经是最新版本了");
            }
        }
    }
}
