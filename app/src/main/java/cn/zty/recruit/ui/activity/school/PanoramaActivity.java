package cn.zty.recruit.ui.activity.school;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.MyImageLoader;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.baselib.widget.StripMenuView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.PanoramaModel;
import cn.zty.recruit.presenter.PanoramaPresenter;
import cn.zty.recruit.utils.SnackbarUtils;
import cn.zty.recruit.utils.ViewAdaptionUtils;
import cn.zty.recruit.view.PanoramaView;

/**
 * Created by zty on 2017/3/15.
 */

public class PanoramaActivity extends BaseActivity implements
        PanoramaView {
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
    @BindView(R.id.panoramaStrip7)
    StripMenuView panoramaStrip7;

    private String schoolId;
    private String schoolGate;

    PanoramaPresenter presenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_panorama;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("校园全景");
        initToolbar(toolbar);

        schoolId = getIntent().getStringExtra("schoolId");
        schoolGate = getIntent().getStringExtra("schoolGate");

        ViewAdaptionUtils.LinearLayoutAdaptation(imgSchoolGate, 400);

        presenter = new PanoramaPresenter();
        presenter.attach(this);
        presenters.add(presenter);
    }

    @Override
    protected void initData() {
        MyImageLoader.load(this, schoolGate, imgSchoolGate);

        presenter.getSchoolPanorama(schoolId);
    }

    @OnClick({R.id.panoramaStrip1, R.id.panoramaStrip2, R.id.panoramaStrip3, R.id.panoramaStrip4, R.id.panoramaStrip5, R.id.panoramaStrip6, R.id.panoramaStrip7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.panoramaStrip1:
                if (panoramaModel1 != null && !TextUtils.isEmpty(panoramaModel1.getWebUrl())) {
                    startActivity(new Intent(this, PanoramaWebActivity.class)
                            .putExtra("url", panoramaModel1.getWebUrl()));
                } else {
                    SnackbarUtils.showShort(toolbar, "暂无资源");
                }
                break;
            case R.id.panoramaStrip2:
                toDetail(panoramaModel2);
                break;
            case R.id.panoramaStrip3:
                toDetail(panoramaModel3);
                break;
            case R.id.panoramaStrip4:
                toDetail(panoramaModel4);
                break;
            case R.id.panoramaStrip5:
                toDetail(panoramaModel5);
                break;
            case R.id.panoramaStrip6:
                toDetail(panoramaModel6);
                break;
            case R.id.panoramaStrip7:
                toDetail(panoramaModel7);
                break;
        }
    }

    private void toDetail(PanoramaModel panoramaModel) {
        if (panoramaModel != null) {
            startActivity(new Intent(this, PanoramaDetailActivity.class).putExtra("panoramaModel", panoramaModel));
        } else {
            SnackbarUtils.showShort(toolbar, "暂无资源");
        }
    }

    @Override
    public void onPanoramaSuccess(List<PanoramaModel> models) {
        if (models != null && models.size() > 0) {
            for (PanoramaModel panoramaModel : models) {
                if (panoramaModel.getPlace().equals(ResourceUtil.resToStr(this, R.string.panoramaStrip1))) {
                    panoramaModel1 = panoramaModel;
                }
                if (panoramaModel.getPlace().equals(ResourceUtil.resToStr(this, R.string.panoramaStrip2))) {
                    panoramaModel2 = panoramaModel;
                }
                if (panoramaModel.getPlace().equals(ResourceUtil.resToStr(this, R.string.panoramaStrip3))) {
                    panoramaModel3 = panoramaModel;
                }
                if (panoramaModel.getPlace().equals(ResourceUtil.resToStr(this, R.string.panoramaStrip4))) {
                    panoramaModel4 = panoramaModel;
                }
                if (panoramaModel.getPlace().equals(ResourceUtil.resToStr(this, R.string.panoramaStrip5))) {
                    panoramaModel5 = panoramaModel;
                }
                if (panoramaModel.getPlace().equals(ResourceUtil.resToStr(this, R.string.panoramaStrip6))) {
                    panoramaModel6 = panoramaModel;
                }
                if (panoramaModel.getPlace().equals(ResourceUtil.resToStr(this, R.string.panoramaStrip7))) {
                    panoramaModel7 = panoramaModel;
                }
            }
        }
    }

    private PanoramaModel panoramaModel1;
    private PanoramaModel panoramaModel2;
    private PanoramaModel panoramaModel3;
    private PanoramaModel panoramaModel4;
    private PanoramaModel panoramaModel5;
    private PanoramaModel panoramaModel6;
    private PanoramaModel panoramaModel7;
}
