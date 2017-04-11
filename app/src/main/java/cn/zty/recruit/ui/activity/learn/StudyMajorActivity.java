package cn.zty.recruit.ui.activity.learn;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.List;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.StudyEnrollAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.CollegeModel;
import cn.zty.recruit.bean.StudyMajorModel;
import cn.zty.recruit.presenter.DepartmentPresenter;
import cn.zty.recruit.presenter.MajorSettingPresenter;
import cn.zty.recruit.view.DepartmentListView;
import cn.zty.recruit.view.StudyMajorView;

/**
 * 专业报名列表
 * Created by zty on 2017/3/18.
 */

public class StudyMajorActivity extends BaseActivity implements
        ExpandableListView.OnGroupClickListener,
        DepartmentListView,
        StudyMajorView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.expandedMajor)
    ExpandableListView expandedMajor;

    StudyEnrollAdapter adapter;

    private DepartmentPresenter departmentPresenter;

    private MajorSettingPresenter majorSettingPresenter;

    private String schoolId;
    private String office;

    private int handleGroupPosition;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_major_list;
    }

    @Override
    protected void initView() {
        schoolId = getIntent().getStringExtra("schoolId");
        office = getIntent().getStringExtra("office");

        toolbar.setTitle("专业报名");
        initToolbar(toolbar);

        adapter = new StudyEnrollAdapter(this, office);
        expandedMajor.setAdapter(adapter);

        expandedMajor.setOnGroupClickListener(this);

        departmentPresenter = new DepartmentPresenter();
        departmentPresenter.attach(this);
        presenters.add(departmentPresenter);

        majorSettingPresenter = new MajorSettingPresenter();
        majorSettingPresenter.attach(this);
        presenters.add(majorSettingPresenter);
    }

    @Override
    protected void initData() {
        departmentPresenter.getDepartmentList(schoolId);
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        handleGroupPosition = groupPosition;
        boolean expanded = parent.isGroupExpanded(groupPosition);
        if (!expanded) {
            if (adapter.getChildData().get(groupPosition) == null) {
                // 加载child 数据
                majorSettingPresenter.getMajorList(adapter.getData().get(groupPosition).getId());
            } else {
                expandedMajor.expandGroup(handleGroupPosition);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDepartmentSuccess(List<CollegeModel> models) {
        adapter.setData(models);
    }

    @Override
    public void onStudyMajorList(List<StudyMajorModel> majorModels) {
        adapter.setChildData(majorModels, handleGroupPosition);
        expandedMajor.expandGroup(handleGroupPosition);
    }
}
