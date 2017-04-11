package cn.zty.recruit.ui.activity.school;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

/**
 * Created by zty on 2017/4/10.
 */

public class PanoramaWebActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.progressWeb)
    ProgressBar progressWeb;

    private String url;

    private String defaultUrl = "http://720yun.com/t/85427wb8ucr?from=singlemessage&isappinstalled=0&pano_id=108447";

    @Override
    protected int initLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        url = getIntent().getStringExtra("url");

//        toolbar.setTitle("720度机构全景");
//        initToolbar(toolbar);

        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);// 设置js可以直接打开窗口，如window.open()，默认为false
        settings.setJavaScriptEnabled(true);// 是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        settings.setSupportZoom(false);// 是否可以缩放，默认true
        settings.setBuiltInZoomControls(true);// 是否显示缩放按钮，默认false
        settings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);// 是否使用缓存
        settings.setDomStorageEnabled(true);// DOM Storage

        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                toolbar.setTitle(title);
                initToolbar(toolbar);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 100) {
                    progressWeb.setVisibility(View.INVISIBLE);
                } else {
                    progressWeb.setVisibility(View.VISIBLE);
                }
            }
        };
        // 设置setWebChromeClient对象
        webView.setWebChromeClient(webChromeClient);
    }

    @Override
    protected void initData() {
        webView.loadUrl(defaultUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null)
            webView.destroy();
    }

}
