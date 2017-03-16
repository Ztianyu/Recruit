package cn.zty.recruit.ui.fragment.learn;

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
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.listener.AreaSelectListener;
import cn.zty.recruit.ui.activity.school.SearchActivity;
import cn.zty.recruit.utils.DialogUtils;

/**
 * Created by zty on 2017/3/16.
 */

public class StudyFragment extends BaseFragment implements AreaSelectListener {

    @BindView(R.id.btnSearchBack)
    ImageView btnSearchBack;
    @BindView(R.id.textSearch)
    TextView textSearch;
    @BindView(R.id.textSelectSchool)
    TextView textSelectSchool;
    @BindView(R.id.textProvinceTip)
    TextView textProvinceTip;
    @BindView(R.id.textCityTip)
    TextView textCityTip;
    @BindView(R.id.textMajorTip)
    TextView textMajorTip;
    @BindView(R.id.layoutSchoolSelect)
    LinearLayout layoutSchoolSelect;
    @BindView(R.id.contentLayoutSchool)
    XRecyclerContentLayout contentLayoutSchool;
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

    @OnClick({R.id.btnSearchBack, R.id.textSearch, R.id.textSelectSchool, R.id.textProvinceTip, R.id.textCityTip, R.id.textMajorTip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearchBack:
                ((BaseActivity) context).finish();
                break;
            case R.id.textSearch:
                startActivity(new Intent(context, SearchActivity.class));
                break;
            case R.id.textSelectSchool:
                DialogUtils.showSchoolSelect(getChildFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight());
                break;
            case R.id.textProvinceTip:
                DialogUtils.showAreaSelect(getFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 0, this);
                break;
            case R.id.textCityTip:
                DialogUtils.showAreaSelect(getFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 1, this);
                break;
            case R.id.textMajorTip:
                DialogUtils.showAreaSelect(getFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 2, this);
                break;
        }
    }

    @Override
    public void onAreaSelect(String code, String value, int type) {
        if (type == 0) {
            textProvinceTip.setText(value);
        } else if (type == 1) {
            textCityTip.setText(value);
        } else if (type == 2) {
            textMajorTip.setText(value);
        }
    }
}
