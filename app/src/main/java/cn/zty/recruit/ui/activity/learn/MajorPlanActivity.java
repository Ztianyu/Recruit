package cn.zty.recruit.ui.activity.learn;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.MajorPlanAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.PlanModel;

/**
 * Created by zty on 2017/3/17.
 */

public class MajorPlanActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textMajorDetailName)
    TextView textMajorDetailName;
    @BindView(R.id.textMajorDetailCount)
    TextView textMajorDetailCount;
    @BindView(R.id.textMajorPrise)
    TextView textMajorPrise;
    @BindView(R.id.textMajorUnit)
    TextView textMajorUnit;
    @BindView(R.id.recyclerMajorPlan)
    XRecyclerView recyclerMajorPlan;
    @BindView(R.id.scrollViewPlan)
    NestedScrollView scrollViewPlan;

    MajorPlanAdapter adapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_major_plan;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("开课计划");
        initToolbar(toolbar);

        recyclerMajorPlan.setNestedScrollingEnabled(false);

        adapter = new MajorPlanAdapter(this);
        recyclerMajorPlan.verticalLayoutManager(this);
        recyclerMajorPlan.setAdapter(adapter);
        recyclerMajorPlan.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
    }

    @Override
    protected void initData() {
        List<PlanModel> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new PlanModel());
        }
        adapter.setData(list);
        scrollViewPlan.post(new Runnable() {
            @Override
            public void run() {
                scrollViewPlan.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }
}
