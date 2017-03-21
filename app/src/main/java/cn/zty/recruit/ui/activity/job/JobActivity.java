package cn.zty.recruit.ui.activity.job;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.listener.AreaSelectListener;
import cn.zty.recruit.listener.MajorSelectListener;
import cn.zty.recruit.ui.activity.school.SearchActivity;
import cn.zty.recruit.utils.DialogUtils;

/**
 * Created by zty on 2017/3/18.
 */

public class JobActivity extends BaseActivity implements
        AreaSelectListener,
        MajorSelectListener {
    @BindView(R.id.btnSearchBack)
    ImageView btnSearchBack;
    @BindView(R.id.textSearch)
    TextView textSearch;
    @BindView(R.id.textSelectSchool)
    TextView textSelectSchool;
    @BindView(R.id.layoutSearchSchool)
    LinearLayout layoutSearchSchool;
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

    @Override
    protected int initLayoutId() {
        return R.layout.activity_job;
    }

    @Override
    protected void initView() {
        textMajorTip.setText("行业类别");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btnSearchBack, R.id.textSearch, R.id.textSelectSchool, R.id.textProvinceTip, R.id.textCityTip, R.id.textMajorTip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearchBack:
                finish();
                break;
            case R.id.textSearch:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.textSelectSchool:
                break;
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

    @Override
    public void onAreaSelect(String code, String value, int type) {
        if (type == 0) {
            textProvinceTip.setText(value);
        } else {
            textCityTip.setText(value);
        }
    }

    @Override
    public void onMajorSelect(String code, String value) {

    }
}
