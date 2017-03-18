package cn.zty.recruit.ui.activity.learn;

import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

/**
 * Created by zty on 2017/3/18.
 */

public class StudyVideoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_study_video;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("视频宣传");
        initToolbar(toolbar);
    }

    @Override
    protected void initData() {

    }

}
