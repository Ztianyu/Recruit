package cn.zty.recruit.ui.activity.learn;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.SchoolLabAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.ui.activity.WebActivity;
import cn.zty.recruit.ui.activity.school.CollegeActivity;
import cn.zty.recruit.widget.LabView;

/**
 * Created by zty on 2017/3/18.
 */

public class StudySchoolDetail extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgSchool)
    ImageView imgSchool;
    @BindView(R.id.textSchoolName)
    TextView textSchoolName;
    @BindView(R.id.autoLineLayout)
    XRecyclerView autoLineLayout;
    @BindView(R.id.textSchoolSpace)
    TextView textSchoolSpace;
    @BindView(R.id.textSchoolNature)
    TextView textSchoolNature;
    @BindView(R.id.textSchoolCreateData)
    TextView textSchoolCreateData;
    @BindView(R.id.textSchoolAddress)
    TextView textSchoolAddress;
    @BindView(R.id.textSchoolSignBTime)
    TextView textSchoolSignBTime;
    @BindView(R.id.layoutBTime)
    LinearLayout layoutBTime;
    @BindView(R.id.textSchoolSignETime)
    TextView textSchoolSignETime;
    @BindView(R.id.layoutETime)
    LinearLayout layoutETime;
    @BindView(R.id.labSchool1)
    LabView labSchool1;
    @BindView(R.id.labSchool2)
    LabView labSchool2;
    @BindView(R.id.labSchool3)
    LabView labSchool3;
    @BindView(R.id.labSchool4)
    LabView labSchool4;
    @BindView(R.id.labSchool5)
    LabView labSchool5;

    SchoolLabAdapter labAdapter;


    @Override
    protected int initLayoutId() {
        return R.layout.activity_study_school_detail;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("学校概况");
        initToolbar(toolbar);
        layoutBTime.setVisibility(View.VISIBLE);
        layoutETime.setVisibility(View.VISIBLE);

        labAdapter = new SchoolLabAdapter(this);
        autoLineLayout.horizontalLayoutManager(this)
                .setAdapter(labAdapter);

    }

    @Override
    protected void initData() {
        List<TipModel> list = new ArrayList<>();
        list.add(new TipModel());
        list.add(new TipModel());
        list.add(new TipModel());
        labAdapter.setData(list);
    }

    @OnClick({R.id.labSchool1, R.id.labSchool2, R.id.labSchool3, R.id.labSchool4, R.id.labSchool5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.labSchool1:
                startActivity(new Intent(this, WebActivity.class));
                break;
            case R.id.labSchool2:
                startActivity(new Intent(this, CollegeActivity.class));
                break;
            case R.id.labSchool3:
                startActivity(new Intent(this, StudyVideoActivity.class));
                break;
            case R.id.labSchool4:
                startActivity(new Intent(this, WebActivity.class));
                break;
            case R.id.labSchool5:
                startActivity(new Intent(this, StudyMajorActivity.class));
                break;
        }
    }
}
