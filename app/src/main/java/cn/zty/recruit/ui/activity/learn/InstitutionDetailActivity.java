package cn.zty.recruit.ui.activity.learn;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.InstitutionMajorAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.InstitutionMajorModel;
import cn.zty.recruit.ui.activity.WebActivity;

/**
 * Created by zty on 2017/3/16.
 */

public class InstitutionDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgInstitution)
    ImageView imgInstitution;
    @BindView(R.id.textInstitutionName)
    TextView textInstitutionName;
    @BindView(R.id.textInstitutionSpace)
    TextView textInstitutionSpace;
    @BindView(R.id.textInstitutionAddress)
    TextView textInstitutionAddress;
    @BindView(R.id.textInstitutionAccount)
    TextView textInstitutionAccount;
    @BindView(R.id.textInstitutionStartTime)
    TextView textInstitutionStartTime;
    @BindView(R.id.textInstitutionEndTime)
    TextView textInstitutionEndTime;
    @BindView(R.id.textInstitutionIntroduction)
    TextView textInstitutionIntroduction;
    @BindView(R.id.listInstitutionMajor)
    XRecyclerView listInstitutionMajor;
    @BindView(R.id.scrollViewInstitution)
    NestedScrollView scrollViewInstitution;

    InstitutionMajorAdapter adapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_institution;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("机构详情");
        initToolbar(toolbar);

        listInstitutionMajor.setNestedScrollingEnabled(false);

        adapter = new InstitutionMajorAdapter(this);
        listInstitutionMajor.verticalLayoutManager(this);
        listInstitutionMajor.setAdapter(adapter);
        listInstitutionMajor.horizontalDivider(R.color.colorBackground, R.dimen.diverHeight2);
    }

    @Override
    protected void initData() {

        List<InstitutionMajorModel> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(new InstitutionMajorModel());
        }
        adapter.setData(list);

        scrollViewInstitution.post(new Runnable() {
            @Override
            public void run() {
                scrollViewInstitution.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    @OnClick(R.id.textInstitutionIntroduction)
    public void onClick() {
        startActivity(new Intent(this, WebActivity.class));
    }

}
