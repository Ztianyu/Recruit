package cn.zty.recruit.ui.activity.school;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
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
import cn.zty.recruit.ui.fragment.AreaSelectFragment;
import cn.zty.recruit.ui.fragment.school.MajorSelectFragment;
import cn.zty.recruit.ui.fragment.school.SchoolSelectFragment;
import cn.zty.recruit.utils.DialogUtils;

/**
 * Created by zty on 2017/3/9.
 */

public class SchoolActivity extends BaseActivity implements AreaSelectListener {

    @BindView(R.id.textProvinceTip)
    TextView textProvinceTip;
    @BindView(R.id.textCityTip)
    TextView textCityTip;
    @BindView(R.id.textMajorTip)
    TextView textMajorTip;
    @BindView(R.id.contentLayoutSchool)
    XRecyclerContentLayout contentLayoutSchool;
    @BindView(R.id.layoutSchoolSelect)
    LinearLayout layoutSchoolSelect;
    @BindView(R.id.btnSearchBack)
    ImageView btnSearchBack;
    @BindView(R.id.textSearch)
    TextView textSearch;
    @BindView(R.id.textSelectSchool)
    TextView textSelectSchool;
    @BindView(R.id.layoutSearchSchool)
    LinearLayout layoutSearchSchool;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_school;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.textProvinceTip, R.id.textCityTip, R.id.textMajorTip, R.id.btnSearchBack, R.id.textSearch, R.id.textSelectSchool})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearchBack:
                finish();
                break;
            case R.id.textSearch:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.textSelectSchool:
                DialogUtils.showSchoolSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight());
                break;
            case R.id.textProvinceTip:
                DialogUtils.showAreaSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 0, this);
                break;
            case R.id.textCityTip:
                DialogUtils.showAreaSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 1, this);
                break;
            case R.id.textMajorTip:
                DialogUtils.showMajorSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight());
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
}
