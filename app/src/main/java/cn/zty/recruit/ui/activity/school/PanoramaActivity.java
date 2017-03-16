package cn.zty.recruit.ui.activity.school;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.widget.StripMenuView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.utils.ViewAdaptionUtils;

/**
 * Created by zty on 2017/3/15.
 */

public class PanoramaActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgSchoolGate)
    ImageView imgSchoolGate;
    @BindView(R.id.panoramaStrip1)
    StripMenuView panoramaStrip1;
    @BindView(R.id.panoramaStrip2)
    StripMenuView panoramaStrip2;
    @BindView(R.id.panoramaStrip3)
    StripMenuView panoramaStrip3;
    @BindView(R.id.panoramaStrip4)
    StripMenuView panoramaStrip4;
    @BindView(R.id.panoramaStrip5)
    StripMenuView panoramaStrip5;
    @BindView(R.id.panoramaStrip6)
    StripMenuView panoramaStrip6;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_panorama;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("校园全景");
        initToolbar(toolbar);

        ViewAdaptionUtils.LinearLayoutAdaptation(imgSchoolGate, 400);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.panoramaStrip1, R.id.panoramaStrip2, R.id.panoramaStrip3, R.id.panoramaStrip4, R.id.panoramaStrip5, R.id.panoramaStrip6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.panoramaStrip1:
                break;
            case R.id.panoramaStrip2:
                break;
            case R.id.panoramaStrip3:
                break;
            case R.id.panoramaStrip4:
                break;
            case R.id.panoramaStrip5:
                break;
            case R.id.panoramaStrip6:
                break;
        }
    }
}
