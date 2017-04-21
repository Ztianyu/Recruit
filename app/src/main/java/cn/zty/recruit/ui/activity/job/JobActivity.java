package cn.zty.recruit.ui.activity.job;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

/**
 * Created by zty on 2017/3/18.
 */

public class JobActivity extends BaseActivity {
    @BindView(R.id.textProvinceTip)
    TextView textProvinceTip;
    @BindView(R.id.textCityTip)
    TextView textCityTip;
    @BindView(R.id.textMajorTip)
    TextView textMajorTip;
    @BindView(R.id.layoutSchoolSelect)
    LinearLayout layoutSchoolSelect;
    @BindView(R.id.contentLayoutJob)
    XRecyclerContentLayout contentLayoutJob;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_job;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("就业");
        initToolbar(toolbar);
        textMajorTip.setText("行业类别");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.textProvinceTip, R.id.textCityTip, R.id.textMajorTip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textProvinceTip:
//                DialogUtils.showAreaSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 0, this);
                break;
            case R.id.textCityTip:
//                DialogUtils.showAreaSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 1, this);
                break;
            case R.id.textMajorTip:
//                DialogUtils.showMajorSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), this);
                break;
        }
    }
}
