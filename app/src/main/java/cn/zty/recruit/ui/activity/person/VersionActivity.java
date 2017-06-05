package cn.zty.recruit.ui.activity.person;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.zty.baselib.utils.VersionUtils;
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
    TextView textVersionUpdate;
    @BindView(R.id.textVersionShare)
    TextView textVersionShare;

    String strVersion;
    int versionCode;

    VersionPresenter versionPresenter;

    private String url = "http://a.app.qq.com/o/simple.jsp?pkgname=cn.zty.recruit";

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
        ShareSDK.initSDK(this);
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

    @OnClick({R.id.textVersionUpdate, R.id.textVersionShare})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textVersionUpdate:
                versionPresenter.getVersion();
                break;
            case R.id.textVersionShare:
                showShare();
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        // 在自动授权时可以禁用SSO方式
        oks.disableSSOWhenAuthorize();
        //oks.setAddress("12345678901"); //分享短信的号码和邮件的地址
        oks.setTitle("选择好的学校就来这里");
        oks.setTitleUrl(url);
        oks.setText("学程通是一款提供职业学校、培训机构、进修学校来源的教育类软件，这里有全面的学校资源、详细的学校介绍、720度校园全景，丰富的专业设置。一直致力于各类职业院校的招生工作，我们与多家职业院校建立了长期合作关系，为学校的生源提供有力的帮助。同时为学生的择校、学习和就业提供一站式全方位服务。我们系统有海量的学府、人性化的生活消费服务和就业机会供你选择，为你的学程保驾护航。");
        //oks.setImagePath("/sdcard/test-pic.jpg");  //分享sdcard目录下的图片
        oks.setImageUrl("http://api.open.qq.com/tfs/show_img.php?appid=1106045903&uuid=512.png%7C1048576%7C1493086810.0222");
        oks.setUrl(url); //微信不绕过审核分享链接
//        oks.setFilePath(testVideo);  //filePath用于视频分享
//        oks.setComment(context.getString(R.string.app_share_comment)); //我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
        oks.setSite("学程通");  //QZone分享完之后返回应用时提示框上显示的名称
        oks.setSiteUrl(url);//QZone分享参数
        // 启动分享GUI
        oks.show(this);
    }
}
