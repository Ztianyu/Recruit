package cn.zty.recruit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import cn.droidlover.xrecyclerview.LoadMoreUIHandler;
import cn.zty.recruit.R;


/**
 * Created by zty on 2017/2/23.
 */

public class LoadMoreFooter extends RelativeLayout implements LoadMoreUIHandler {

    TextView tvMsg;
    ProgressBar progressBar;

    private Context context;

    public LoadMoreFooter(Context context) {
        super(context);
        this.context = context;
        setupView();
    }

    public LoadMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public LoadMoreFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView();
    }

    private void setupView() {
        inflate(context, R.layout.footer_loading, this);

        tvMsg = (TextView) findViewById(R.id.textLoadMore);
        progressBar = (ProgressBar) findViewById(R.id.progressBarLoadMore);
    }

    @Override
    public void onLoading() {
        setVisibility(VISIBLE);
        tvMsg.setText("加载中");
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onLoadFinish(boolean hasMore) {
        if (hasMore) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
            tvMsg.setText("没有更多数据了");
            progressBar.setVisibility(GONE);
        }
    }
}
