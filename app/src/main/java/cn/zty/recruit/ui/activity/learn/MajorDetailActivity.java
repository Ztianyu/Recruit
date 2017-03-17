package cn.zty.recruit.ui.activity.learn;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.widget.LabView;

/**
 * Created by zty on 2017/3/17.
 */

public class MajorDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textMajorDetailName)
    TextView textMajorDetailName;
    @BindView(R.id.textMajorDetailCount)
    TextView textMajorDetailCount;
    @BindView(R.id.textMajorPrise)
    TextView textMajorPrise;
    @BindView(R.id.textMajorUnit)
    TextView textMajorUnit;
    @BindView(R.id.labMajorFun1)
    LabView labMajorFun1;
    @BindView(R.id.labMajorFun2)
    LabView labMajorFun2;
    @BindView(R.id.labMajorFun3)
    LabView labMajorFun3;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_major_detail;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("课程详情");
        initToolbar(toolbar);
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.labMajorFun1, R.id.labMajorFun2, R.id.labMajorFun3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.labMajorFun1:
                startActivity(new Intent(this, MajorPlanActivity.class));
                break;
            case R.id.labMajorFun2:
                startActivity(new Intent(this, AuditionActivity.class));
                break;
            case R.id.labMajorFun3:
                startActivity(new Intent(this, EnrollActivity.class));
                break;
        }
    }
}
