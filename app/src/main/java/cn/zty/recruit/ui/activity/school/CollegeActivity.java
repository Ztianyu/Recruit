package cn.zty.recruit.ui.activity.school;

import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.CollegeAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.CollegeModel;

/**
 * Created by zty on 2017/3/13.
 */

public class CollegeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.expandedMajor)
    ExpandableListView expandedMajor;

    CollegeAdapter adapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_major_list;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("院系介绍");
        initToolbar(toolbar);
    }

    @Override
    protected void initData() {
        adapter = new CollegeAdapter(this);
        expandedMajor.setAdapter(adapter);

        List<CollegeModel> list = new ArrayList<>();
        list.add(new CollegeModel());
        list.add(new CollegeModel());
        list.add(new CollegeModel());
        list.add(new CollegeModel());

        adapter.setData(list);

    }

}
