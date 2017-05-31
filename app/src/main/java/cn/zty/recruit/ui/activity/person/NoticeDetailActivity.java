package cn.zty.recruit.ui.activity.person;

import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.NoticeModel;
import cn.zty.recruit.utils.WebLoadHtmlUtils;

/**
 * Created by zty on 2017/3/30.
 */

public class NoticeDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webViewNotice)
    WebView webViewNotice;

    private NoticeModel noticeModel;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_notice_detail;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("通 知");
        initToolbar(toolbar);

        noticeModel = getIntent().getParcelableExtra("noticeModel");

        // 设置加载进来的页面自适应手机屏幕
        webViewNotice.getSettings().setUseWideViewPort(true);
        webViewNotice.getSettings().setLoadWithOverviewMode(true);
    }

    @Override
    protected void initData() {
        webViewNotice.loadDataWithBaseURL("file:///android_asset/",
                WebLoadHtmlUtils.loadNoticeHtml(noticeModel),
                "text/html", "utf-8", null);
    }
}
