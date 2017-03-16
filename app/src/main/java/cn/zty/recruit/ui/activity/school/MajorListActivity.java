package cn.zty.recruit.ui.activity.school;

import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.MajorListAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.MajorModel;

/**
 * 专业介绍列表
 * Created by zty on 2017/3/13.
 */

public class MajorListActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.expandedMajor)
    ExpandableListView expandedMajor;

    MajorListAdapter adapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_major_list;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("开设专业");
        initToolbar(toolbar);
        adapter = new MajorListAdapter(this);
        expandedMajor.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        List<MajorModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MajorModel());
        }
        adapter.setData(list);
    }

}
