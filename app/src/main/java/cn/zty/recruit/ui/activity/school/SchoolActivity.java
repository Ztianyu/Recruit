package cn.zty.recruit.ui.activity.school;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.ui.fragment.school.MajorSelectFragment;
import cn.zty.recruit.ui.fragment.school.SchoolSelectFragment;

/**
 * Created by zty on 2017/3/9.
 */

public class SchoolActivity extends BaseActivity {

    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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

    @Override
    protected int initLayoutId() {
        return R.layout.activity_school;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("学校");
        toolbar.inflateMenu(R.menu.search);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        startActivity(new Intent(SchoolActivity.this, SearchActivity.class));
                        return true;
                    case R.id.choiceSchool:
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag("schoolSelectFragment");
                        if (fragment != null)
                            getSupportFragmentManager().beginTransaction().remove(fragment);
                        SchoolSelectFragment schoolSelectFragment = SchoolSelectFragment.newInstance(layoutSchoolSelect.getHeight() + toolbar.getHeight());
                        schoolSelectFragment.show(getSupportFragmentManager().beginTransaction(), "schoolSelectFragment");
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.textProvinceTip, R.id.textCityTip, R.id.textMajorTip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textProvinceTip:
                break;
            case R.id.textCityTip:
                break;
            case R.id.textMajorTip:
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("majorSelectFragment");
                if (fragment != null)
                    getSupportFragmentManager().beginTransaction().remove(fragment);
                MajorSelectFragment majorSelectFragment = MajorSelectFragment.newInstance(layoutSchoolSelect.getHeight() + toolbar.getHeight());
                majorSelectFragment.show(getSupportFragmentManager().beginTransaction(), "majorSelectFragment");
                break;
        }
    }

}
