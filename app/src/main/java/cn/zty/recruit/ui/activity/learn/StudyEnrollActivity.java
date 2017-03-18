package cn.zty.recruit.ui.activity.learn;

import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.EnrollTypeModel;
import cn.zty.recruit.listener.EducationSelectListener;
import cn.zty.recruit.listener.EnrollTypeSelectListener;
import cn.zty.recruit.listener.SexSelectListener;
import cn.zty.recruit.utils.DialogUtils;

/**
 * Created by zty on 2017/3/18.
 */

public class StudyEnrollActivity extends BaseActivity implements
        SexSelectListener,
        EducationSelectListener,
        EnrollTypeSelectListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editEnrollName)
    EditText editEnrollName;
    @BindView(R.id.btnChoseSex)
    TextView btnChoseSex;
    @BindView(R.id.btnChoseAge)
    TextView btnChoseAge;
    @BindView(R.id.editEnrollPhone)
    EditText editEnrollPhone;
    @BindView(R.id.btnChoseEducation)
    TextView btnChoseEducation;
    @BindView(R.id.btnChoseType)
    TextView btnChoseType;
    @BindView(R.id.editEnrollPs)
    EditText editEnrollPs;
    @BindView(R.id.textBillMoney)
    TextView textBillMoney;
    @BindView(R.id.textBillTip)
    TextView textBillTip;
    @BindView(R.id.btnSubmit)
    TextView btnSubmit;
    @BindView(R.id.expandText)
    ExpandableTextView expandText;
    @BindView(R.id.textStudyMajorIntroduction)
    TextView textStudyMajorIntroduction;
    @BindView(R.id.textMajorChildName)
    TextView textMajorChildName;
    @BindView(R.id.textStudyLength)
    TextView textStudyLength;
    @BindView(R.id.textStudyEducation)
    TextView textStudyEducation;
    @BindView(R.id.textStudyPrise)
    TextView textStudyPrise;

    private int sexType = -1;
    private int educationType = -1;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_class_enroll;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("在线报名");
        toolbar.inflateMenu(R.menu.phone);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.takePhone:
                        DialogUtils.showCall(getSupportFragmentManager(), "0371-572333");
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        textStudyMajorIntroduction.setText("专业介绍");
        expandText.setText("培养目标:掌握计算机系统基础知识的基本原理，熟悉计算机系统常用软硬件工具，具有一定的硬件维护能力和较强的软件开发能力的应用型人才。\n" +
                "主要课程：C语言程序设计、VB程序设计、Java面向对象程序设计、数据结构、计算机网络与通讯、网络操作系统、软件工程、多媒体技术与应用、大型数据库处理技术等。\n" +
                "就业方向：可从事企事业一线直接参与计算机应用、软件开发的技术。");
    }

    @OnClick({R.id.btnChoseSex, R.id.btnChoseAge, R.id.btnChoseEducation, R.id.btnChoseType, R.id.btnSubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnChoseSex:
                DialogUtils.showSexSelect(getSupportFragmentManager(), sexType, this);
                break;
            case R.id.btnChoseAge:
                DialogUtils.showDataSelect(this, btnChoseAge);
                break;
            case R.id.btnChoseEducation:
                DialogUtils.showEducationSelect(getSupportFragmentManager(), educationType, this);
                break;
            case R.id.btnChoseType:
                DialogUtils.showEnrollTypeSelect(getSupportFragmentManager(), this);
                break;
            case R.id.btnSubmit:
                break;
        }
    }

    @Override
    public void onSexListener(String sex, int type) {
        sexType = type;
        btnChoseSex.setText(sex);
    }

    @Override
    public void onEducationListener(String education, int type) {
        educationType = type;
        btnChoseEducation.setText(education);
    }

    @Override
    public void onEnrollTypeSelect(EnrollTypeModel enrollTypeModel) {
        btnChoseType.setText("500（可抵1000元）");
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(DialogUtils.ENROLL_TYPE_SELECT);
        dialogFragment.dismiss();
    }
}
