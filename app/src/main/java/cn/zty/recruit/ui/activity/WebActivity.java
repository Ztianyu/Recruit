package cn.zty.recruit.ui.activity;

import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

/**
 * Created by zty on 2017/3/14.
 */

public class WebActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("院校简介");
        initToolbar(toolbar);

        // 启用javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 从assets目录下面的加载html
        webView.loadUrl("file:///android_asset/web.html");
        webView.addJavascriptInterface(this, "android");
    }

    @Override
    protected void initData() {

        // 传递参数调用JS的方法
        webView.loadUrl("javascript:javacalljswith(" + "'http://blog.csdn.net/Leejizhou'" + ")");

    }
}
