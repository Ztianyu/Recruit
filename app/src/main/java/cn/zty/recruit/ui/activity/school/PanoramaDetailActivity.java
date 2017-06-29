package cn.zty.recruit.ui.activity.school;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.PanoramaModel;
import cn.zty.recruit.ui.activity.learn.VideoActivity;
import cn.zty.recruit.utils.SnackbarUtils;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.utils.WebLoadHtmlUtils;

/**
 * Created by zty on 2017/3/28.
 */

public class PanoramaDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;

    private PanoramaModel panoramaModel;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_panorama_detail;
    }

    @Override
    protected void initView() {

        panoramaModel = getIntent().getParcelableExtra("panoramaModel");

        if (!TextUtils.isEmpty(panoramaModel.getPlace())) {
            toolbar.setTitle(panoramaModel.getPlace());
        }

        toolbar.inflateMenu(R.menu.video);
        toolbar.setNavigationIcon(R.drawable.ic_main_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.video) {
                    if (!TextUtils.isEmpty(panoramaModel.getVideoUrl())) {
                        startActivity(new Intent(PanoramaDetailActivity.this, VideoActivity.class)
                                .putExtra("videoUrl", panoramaModel.getVideoUrl())
                                .putExtra("videoName", panoramaModel.getPlace()));
                    } else {
                        SnackbarUtils.showShort(webView, "暂无视频资源");
//                        ToastUtils.show("暂无视频资源");
                    }
                    return true;
                }
                return false;
            }
        });

        // 设置加载进来的页面自适应手机屏幕
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(panoramaModel.getContent())) {
            webView.loadDataWithBaseURL("file:///android_asset/",
                    WebLoadHtmlUtils.loadHtml("",
                            panoramaModel.getContent()),
                    "text/html", "utf-8", null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null)
            webView.destroy();
    }
}
