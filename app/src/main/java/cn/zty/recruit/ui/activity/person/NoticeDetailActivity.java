package cn.zty.recruit.ui.activity.person;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.NoticeModel;

/**
 * Created by zty on 2017/3/30.
 */

public class NoticeDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textNoticeTitle)
    TextView textNoticeTitle;
    @BindView(R.id.textNoticeTime)
    TextView textNoticeTime;
    @BindView(R.id.textNoticeContent)
    TextView textNoticeContent;

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
    }

    @Override
    protected void initData() {
        textNoticeTitle.setText(noticeModel.getTitle());
        textNoticeTime.setText(noticeModel.getUpdateDate());
        textNoticeContent.setText("\u3000\u3000" + noticeModel.getContent());
    }
}
