package cn.zty.recruit.ui.activity.learn;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.widget.ExpandListView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.InstitutionMajorAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.InstitutionMajorModel;

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
    ExpandListView listInstitutionMajor;
    @BindView(R.id.scrollViewInstitution)
    ScrollView scrollViewInstitution;

    InstitutionMajorAdapter adapter;


    @Override
    protected int initLayoutId() {
        return R.layout.activity_institution;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("机构详情");
        initToolbar(toolbar);

        adapter = new InstitutionMajorAdapter(this);
        listInstitutionMajor.setAdapter(adapter);
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
    }

}
