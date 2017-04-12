package cn.zty.recruit.ui.activity.person;

import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

/**
 * Created by zty on 2017/3/15.
 */

public class SetEducationActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.radioGroupEducation)
    RadioGroup radioGroupEducation;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_set_education;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("学 历");
        toolbar.inflateMenu(R.menu.save);
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
                switch (item.getItemId()) {
                    case R.id.save:
                        finish();
                        return true;
                }
                return false;
            }
        });
        radioGroupEducation.setOnCheckedChangeListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.radioPrimarySchool:
                break;
            case R.id.radioMiddleSchool:
                break;
            case R.id.radioHighSchool:
                break;
            case R.id.radioUndergraduate:
                break;
            case R.id.radioMaster:
                break;
            case R.id.radioDoctor:
                break;
        }
    }
}
