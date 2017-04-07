package cn.zty.recruit.ui.activity;

import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.IntroductionModel;
import cn.zty.recruit.presenter.SchoolFacultyPresenter;
import cn.zty.recruit.presenter.SchoolIntroductionPresenter;
import cn.zty.recruit.utils.WebLoadHtmlUtils;
import cn.zty.recruit.view.IntroductionView;

/**
 * Created by zty on 2017/3/14.
 */

public class WebActivity extends BaseActivity implements
        IntroductionView {

    public static final int TYPE1 = 0;//院校概述
    public static final int TYPE2 = 1;//师资力量

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;

    private String schoolId;
    private String title;
    private int type;

    private SchoolIntroductionPresenter schoolIntroductionPresenter;

    private SchoolFacultyPresenter schoolFacultyPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        title = getIntent().getStringExtra("title");
        schoolId = getIntent().getStringExtra("schoolId");
        type = getIntent().getIntExtra("type", -1);

        toolbar.setTitle(title);
        initToolbar(toolbar);

        // 设置加载进来的页面自适应手机屏幕
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
    }

    @Override
    protected void initData() {
        switch (type) {
            case TYPE1:
                schoolIntroductionPresenter = new SchoolIntroductionPresenter();
                schoolIntroductionPresenter.attach(this);
                presenters.add(schoolIntroductionPresenter);

                schoolIntroductionPresenter.getSchoolIntroduction(schoolId);
                break;
            case TYPE2:
                schoolFacultyPresenter = new SchoolFacultyPresenter();
                schoolFacultyPresenter.attach(this);
                presenters.add(schoolFacultyPresenter);

                schoolFacultyPresenter.getSchoolFaculty(schoolId);
                break;
        }
    }

    @Override
    public void onIntroductionSuccess(IntroductionModel model) {
        webView.loadDataWithBaseURL("file:///android_asset/",
                WebLoadHtmlUtils.loadHtml(model.getTitle(),
                        model.getContent()),
                "text/html", "utf-8", null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null)
            webView.destroy();
    }
}
