package cn.zty.recruit.ui.activity.school;

import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import java.util.List;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.MajorListAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.DepartmentMajorModel;
import cn.zty.recruit.presenter.MajorListPresenter;
import cn.zty.recruit.view.MajorListView;

/**
 * 专业介绍列表
 * Created by zty on 2017/3/13.
 */

public class MajorListActivity extends BaseActivity implements MajorListView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.expandedMajor)
    ExpandableListView expandedMajor;

    MajorListAdapter adapter;

    private String departmentId;
    private String title;

    MajorListPresenter majorListPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_major_list;
    }

    @Override
    protected void initView() {
        departmentId = getIntent().getStringExtra("departmentId");
        title = getIntent().getStringExtra("title");

        toolbar.setTitle(title);
        initToolbar(toolbar);
        adapter = new MajorListAdapter(this);
        expandedMajor.setAdapter(adapter);

        majorListPresenter = new MajorListPresenter();
        majorListPresenter.attach(this);
        presenters.add(majorListPresenter);
    }

    @Override
    protected void initData() {
        majorListPresenter.getSchoolMajorList(departmentId);
    }

    @Override
    public void onMajorListSuccess(List<DepartmentMajorModel> majorModels) {
        adapter.setData(majorModels);
    }
}
