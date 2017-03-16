package cn.zty.recruit.ui.activity.school;

import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.CollegeListAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.CollegeModel;

/**
 * Created by zty on 2017/3/15.
 */

public class CollegeListActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    CollegeListAdapter adapter;

    @Override
    protected int initLayoutId() {
        return R.layout.view_recycler;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("所有院系");
        initToolbar(toolbar);

        adapter = new CollegeListAdapter(this);
        recyclerView.verticalLayoutManager(this)
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
    }

    @Override
    protected void initData() {
        List<CollegeModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new CollegeModel());
        }
        adapter.setData(list);
    }

}
