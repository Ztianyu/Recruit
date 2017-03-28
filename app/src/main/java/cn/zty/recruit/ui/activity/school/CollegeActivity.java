package cn.zty.recruit.ui.activity.school;

import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import java.util.List;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.CollegeAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.CollegeModel;
import cn.zty.recruit.presenter.DepartmentPresenter;
import cn.zty.recruit.view.DepartmentListView;

/**
 * Created by zty on 2017/3/13.
 */

public class CollegeActivity extends BaseActivity implements DepartmentListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.expandedMajor)
    ExpandableListView expandedMajor;

    CollegeAdapter adapter;

    DepartmentPresenter presenter;

    private String schoolId;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_major_list;
    }

    @Override
    protected void initView() {
        schoolId = getIntent().getStringExtra("schoolId");

        toolbar.setTitle("院系介绍");
        initToolbar(toolbar);

        presenter = new DepartmentPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        adapter = new CollegeAdapter(this);
        expandedMajor.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.getDepartmentList(schoolId);
    }

    @Override
    public void onDepartmentSuccess(List<CollegeModel> models) {
        if (models != null && models.size() > 0) {
            adapter.setData(models);
        }
    }
}
