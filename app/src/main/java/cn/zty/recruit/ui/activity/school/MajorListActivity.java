package cn.zty.recruit.ui.activity.school;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.List;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.MajorListAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.CollegeModel;
import cn.zty.recruit.bean.DepartmentMajorModel;
import cn.zty.recruit.presenter.DepartmentPresenter;
import cn.zty.recruit.presenter.MajorListPresenter;
import cn.zty.recruit.view.DepartmentListView;
import cn.zty.recruit.view.MajorListView;

/**
 * 专业介绍列表
 * Created by zty on 2017/3/13.
 */

public class MajorListActivity extends BaseActivity implements
        MajorListView,
        DepartmentListView,
        ExpandableListView.OnGroupClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.expandedMajor)
    ExpandableListView expandedMajor;

    MajorListAdapter adapter;

    DepartmentPresenter presenter;

    private String schoolId;

    MajorListPresenter majorListPresenter;

    private int handleGroupPosition;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_major_list;
    }

    @Override
    protected void initView() {

        schoolId = getIntent().getStringExtra("schoolId");

        toolbar.setTitle("专业报名");
        initToolbar(toolbar);
        adapter = new MajorListAdapter(this);
        expandedMajor.setAdapter(adapter);

        presenter = new DepartmentPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        majorListPresenter = new MajorListPresenter();
        majorListPresenter.attach(this);
        presenters.add(majorListPresenter);
    }

    @Override
    protected void initData() {
        presenter.getDepartmentList(schoolId);
    }

    @Override
    public void onMajorListSuccess(List<DepartmentMajorModel> majorModels) {
        adapter.setChildData(majorModels, handleGroupPosition);
        expandedMajor.expandGroup(handleGroupPosition);
    }

    @Override
    public void onDepartmentSuccess(List<CollegeModel> models) {
        adapter.setData(models);
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        handleGroupPosition = groupPosition;
        boolean expanded = parent.isGroupExpanded(groupPosition);
        if (!expanded) {
            if (adapter.getChildData().get(groupPosition) == null) {
                // 加载child 数据
                majorListPresenter.getSchoolMajorList(adapter.getData().get(groupPosition).getId());
            } else {
                expandedMajor.expandGroup(handleGroupPosition);
            }
            return true;
        }
        return false;
    }
}
