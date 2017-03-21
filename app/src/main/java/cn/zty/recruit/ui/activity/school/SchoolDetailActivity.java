package cn.zty.recruit.ui.activity.school;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
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
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.widget.LabView;

/**
 * Created by zty on 2017/3/13.
 */

public class SchoolDetailActivity extends BaseActivity {
    @BindView(R.id.textTitle)
    TextView textTitle;
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
    @BindView(R.id.labSchool6)
    LabView labSchool6;

    SchoolLabAdapter labAdapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_school_detail;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("院校概况");
        initToolbar(toolbar);

        labAdapter = new SchoolLabAdapter(this, false);
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

    @OnClick({R.id.labSchool1, R.id.labSchool2, R.id.labSchool3, R.id.labSchool4, R.id.labSchool5, R.id.labSchool6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.labSchool1:
                startActivity(new Intent(this, WebActivity.class));
                break;
            case R.id.labSchool2:
                startActivity(new Intent(this, PanoramaActivity.class));
                break;
            case R.id.labSchool3:
                startActivity(new Intent(this, CollegeActivity.class));
                break;
            case R.id.labSchool4:
                startActivity(new Intent(this, WebActivity.class));
                break;
            case R.id.labSchool5:
                startActivity(new Intent(this, CollegeListActivity.class));
                break;
            case R.id.labSchool6:
                DialogUtils.showCall(getSupportFragmentManager(), "0371-573233");
                break;
        }
    }
}
