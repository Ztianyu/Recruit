package cn.zty.recruit.ui.activity.person;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

/**
 * Created by zty on 2017/1/14.
 */

public class SetSexActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.radioGroupSex)
    RadioGroup radioGroupSex;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String strSex;

    private String id;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_set_sex;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("性 别");
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
    }

    @Override
    protected void initData() {
        radioGroupSex.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radioButtonMan:
                strSex = "男";
                break;
            case R.id.radioButtonWoman:
                strSex = "女";
                break;
        }
    }
}
