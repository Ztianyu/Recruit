package cn.zty.recruit.ui.activity.learn;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.StudyEnrollAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.CollegeModel;
import cn.zty.recruit.bean.StudyMajorModel;

/**
 * Created by zty on 2017/3/18.
 */

public class StudyMajorActivity extends BaseActivity implements ExpandableListView.OnGroupClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.expandedMajor)
    ExpandableListView expandedMajor;

    StudyEnrollAdapter adapter;

    private int handleGroupPosition;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_major_list;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("所有院系");
        initToolbar(toolbar);

        adapter = new StudyEnrollAdapter(this);
        expandedMajor.setAdapter(adapter);

        expandedMajor.setOnGroupClickListener(this);
    }

    @Override
    protected void initData() {
        List<CollegeModel> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new CollegeModel());
        }
        adapter.setData(list);
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        handleGroupPosition = groupPosition;
        boolean expanded = parent.isGroupExpanded(groupPosition);
        if (!expanded) {
            if (adapter.getChildData().get(groupPosition) == null) {
                // 加载child 数据
                List<StudyMajorModel> list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    list.add(new StudyMajorModel());
                }
                adapter.setChildData(list, groupPosition);
            }
            expandedMajor.expandGroup(handleGroupPosition);
            return true;
        }
        return false;
    }
}
