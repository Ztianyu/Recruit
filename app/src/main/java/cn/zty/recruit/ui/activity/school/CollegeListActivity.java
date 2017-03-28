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
import cn.zty.recruit.presenter.DepartmentPresenter;
import cn.zty.recruit.view.DepartmentListView;

/**
 * Created by zty on 2017/3/15.
 */

public class CollegeListActivity extends BaseActivity implements DepartmentListView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    CollegeListAdapter adapter;

    DepartmentPresenter presenter;

    private String schoolId;

    @Override
    protected int initLayoutId() {
        return R.layout.view_recycler;
    }

    @Override
    protected void initView() {
        schoolId = getIntent().getStringExtra("schoolId");

        toolbar.setTitle("所有院系");
        initToolbar(toolbar);

        presenter = new DepartmentPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        adapter = new CollegeListAdapter(this);
        recyclerView.verticalLayoutManager(this)
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
    }

    @Override
    protected void initData() {
        presenter.getDepartmentList(schoolId);
    }

    @Override
    public void onDepartmentSuccess(List<CollegeModel> models) {
        adapter.setData(models);
    }
}
