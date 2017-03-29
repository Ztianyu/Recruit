package cn.zty.recruit.ui.activity.learn;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.InstitutionMajorModel;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.widget.LabView;

/**
 * Created by zty on 2017/3/17.
 */

public class MajorDetailActivity extends BaseActivity {
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
    @BindView(R.id.labMajorFun1)
    LabView labMajorFun1;
    @BindView(R.id.labMajorFun2)
    LabView labMajorFun2;
    @BindView(R.id.labMajorFun3)
    LabView labMajorFun3;
    @BindView(R.id.expandText)
    ExpandableTextView expandText;

    private InstitutionMajorModel majorModel;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_major_detail;
    }

    @Override
    protected void initView() {
        majorModel = getIntent().getParcelableExtra("model");

        toolbar.setTitle("课程详情");
        initToolbar(toolbar);
    }

    @Override
    protected void initData() {
        expandText.setText(MyTextUtils.notNullStr(majorModel.getRemarks()));
        textMajorDetailName.setText(majorModel.getName());
        textMajorDetailCount.setText("总学时：" + majorModel.getHours());
        textMajorPrise.setText(majorModel.getMoney() + "");

        String unit = "";
        if (majorModel.getChargeStandard().equals("1")) {
            unit = Constants.chargeStandard1;
        }
        if (majorModel.getChargeStandard().equals("2")) {
            unit = Constants.chargeStandard2;
        }
        textMajorUnit.setText("元/" + unit);
    }

    @OnClick({R.id.labMajorFun1, R.id.labMajorFun2, R.id.labMajorFun3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.labMajorFun1:
                startActivity(new Intent(this, MajorPlanActivity.class).putExtra("model", majorModel));
                break;
            case R.id.labMajorFun2:
                if (!TextUtils.isEmpty(majorModel.getVideoUrl())) {
                    startActivity(new Intent(this, AuditionActivity.class));
                } else {
                    ToastUtils.show("暂无试听视频");
                }
                break;
            case R.id.labMajorFun3:
                startActivity(new Intent(this, EnrollActivity.class).putExtra("model", majorModel));
                break;
        }
    }
}
