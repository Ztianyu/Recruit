package cn.zty.recruit.ui.activity.learn;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.baselib.utils.MyImageLoader;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.SchoolLabAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseData;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.PanoramaModel;
import cn.zty.recruit.bean.StudySchoolModel;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.presenter.PanoramaPresenter;
import cn.zty.recruit.presenter.StudySchoolInfoPresenter;
import cn.zty.recruit.ui.activity.WebActivity;
import cn.zty.recruit.ui.activity.school.CollegeActivity;
import cn.zty.recruit.utils.SnackbarUtils;
import cn.zty.recruit.view.PanoramaView;
import cn.zty.recruit.view.StudySchoolView;
import cn.zty.recruit.widget.LabView;

/**
 * Created by zty on 2017/3/18.
 */

public class StudySchoolDetail extends BaseActivity implements
        StudySchoolView,
        PanoramaView {

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

    private String schoolId;
    private String schoolName;

    private StudySchoolModel studySchoolModel;

    private StudySchoolInfoPresenter presenter;

    private PanoramaPresenter panoramaPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_study_school_detail;
    }

    @Override
    protected void initView() {
        schoolId = getIntent().getStringExtra("schoolId");
        schoolName = getIntent().getStringExtra("schoolName");

        toolbar.setTitle("学校概况");
        initToolbar(toolbar);
        layoutBTime.setVisibility(View.VISIBLE);
        layoutETime.setVisibility(View.VISIBLE);

        presenter = new StudySchoolInfoPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        panoramaPresenter = new PanoramaPresenter();
        panoramaPresenter.attach(this);
        presenters.add(panoramaPresenter);

        labAdapter = new SchoolLabAdapter(this, false);
        autoLineLayout.horizontalLayoutManager(this)
                .setAdapter(labAdapter);

    }

    @Override
    protected void initData() {
        presenter.getStudySchoolInfo(schoolId);

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
                startActivity(new Intent(this, WebActivity.class)
                        .putExtra("schoolId", schoolId)
                        .putExtra("title", schoolName)
                        .putExtra("type", WebActivity.TYPE1));
                break;
            case R.id.labSchool2:
                startActivity(new Intent(this, CollegeActivity.class)
                        .putExtra("schoolId", schoolId));
                break;
            case R.id.labSchool3:
                panoramaPresenter.getSchoolPanorama(schoolId);
                break;
            case R.id.labSchool4:
                startActivity(new Intent(this, WebActivity.class)
                        .putExtra("schoolId", schoolId)
                        .putExtra("title", schoolName)
                        .putExtra("type", WebActivity.TYPE2));
                break;
            case R.id.labSchool5:
                startActivity(new Intent(this, StudyMajorActivity.class)
                        .putExtra("schoolId", schoolId)
                        .putExtra("office", Constants.OFFICE_TYPE2));
                break;
        }
    }

    @Override
    public void onStudySchoolList(List<StudySchoolModel> models) {

    }

    @Override
    public void onStudySchool(StudySchoolModel model) {
        if (model != null) {

            studySchoolModel = model;

            BaseData.studySchoolPhone = model.getContactTel();

            MyImageLoader.load(this, model.getImgUrl(), imgSchool);
            textSchoolName.setText(MyTextUtils.notNullStr(model.getName()));
            textSchoolSpace.setText(MyTextUtils.notNullStr(model.getAreaNm()));
            textSchoolNature.setText(MyTextUtils.notNullStr(model.getSchoolTypeLabel()));
            textSchoolAddress.setText(MyTextUtils.notNullStr(model.getAddr()));
            textSchoolCreateData.setText(MyTextUtils.notNullStr(model.getEstablishDate()));
            textSchoolSignBTime.setText(MyTextUtils.notNullStr(model.getRegistrationDate()));
            textSchoolSignETime.setText(MyTextUtils.notNullStr(model.getRegistrationEndDate()));

            List<TipModel> list = new ArrayList<>();
            String[] educationTypes = model.getSchoolTypeLabel().split(",");
            for (int i = 0; i < educationTypes.length; i++) {
                TipModel tipModel = new TipModel();
                tipModel.setValue(educationTypes[i]);
                list.add(tipModel);
            }
            labAdapter.setData(list);
        }
    }

    @Override
    public void onPanoramaSuccess(List<PanoramaModel> models) {
        if (models != null && models.size() > 0) {
            PanoramaModel panoramaModel = models.get(0);
            if (!TextUtils.isEmpty(panoramaModel.getVideoUrl())) {
                startActivity(new Intent(this, VideoActivity.class)
                        .putExtra("videoUrl", panoramaModel.getVideoUrl())
                        .putExtra("videoName", panoramaModel.getPlace()));
            } else {
                SnackbarUtils.showShort(toolbar,"暂无宣传视频");
            }
        } else {
            SnackbarUtils.showShort(toolbar,"暂无宣传视频");
        }
    }
}
