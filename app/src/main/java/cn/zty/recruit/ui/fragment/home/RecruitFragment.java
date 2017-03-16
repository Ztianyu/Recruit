package cn.zty.recruit.ui.fragment.home;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.widget.ExpandListView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.HotMajorAdapter;
import cn.zty.recruit.adapter.HotUniversityAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.bean.UniversityModel;
import cn.zty.recruit.ui.activity.learn.LearnActivity;
import cn.zty.recruit.ui.activity.school.MoreActivity;
import cn.zty.recruit.ui.activity.school.SchoolActivity;
import cn.zty.recruit.utils.BannerUtils;
import cn.zty.recruit.utils.ViewAdaptionUtils;
import cn.zty.recruit.widget.LabView;

/**
 * Created by zty on 2017/3/6.
 */

public class RecruitFragment extends BaseFragment {
    @BindView(R.id.bannerRecruit)
    Banner bannerRecruit;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.btnRecruitFun1)
    LabView btnRecruitFun1;
    @BindView(R.id.btnRecruitFun2)
    LabView btnRecruitFun2;
    @BindView(R.id.btnRecruitFun3)
    LabView btnRecruitFun3;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.btnMoreUniversity)
    TextView btnMoreUniversity;
    @BindView(R.id.listHotUniversity)
    ExpandListView listHotUniversity;
    @BindView(R.id.btnMoreMajor)
    TextView btnMoreMajor;
    @BindView(R.id.listHotMajor)
    ExpandListView listHotMajor;

    HotUniversityAdapter hotUniversityAdapter;
    HotMajorAdapter hotMajorAdapter;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_recruit;
    }

    @Override
    protected void initView() {
        toolbar.setPadding(0, RecruitApplication.getInstance().getStatusBarHeight(), 0, 0);
        ViewAdaptionUtils.CollapsingToolbarLayoutAdaptation(bannerRecruit, 400);

        hotUniversityAdapter = new HotUniversityAdapter(context, false);
        hotMajorAdapter = new HotMajorAdapter(context);

        listHotUniversity.setAdapter(hotUniversityAdapter);
        listHotMajor.setAdapter(hotMajorAdapter);
    }

    @Override
    protected void initData() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.ic_advise);
        images.add(R.drawable.ic_advise);
        images.add(R.drawable.ic_advise);
        images.add(R.drawable.ic_advise);
        BannerUtils.initBanner1(bannerRecruit, images, 1, 2);

        List<UniversityModel> universityModels = new ArrayList<>();
        universityModels.add(new UniversityModel());
        universityModels.add(new UniversityModel());
        universityModels.add(new UniversityModel());
        hotUniversityAdapter.setData(universityModels);

        List<MajorModel> majorModels = new ArrayList<>();
        majorModels.add(new MajorModel());
        majorModels.add(new MajorModel());
        majorModels.add(new MajorModel());
        hotMajorAdapter.setData(majorModels);
    }

    @OnClick({R.id.btnRecruitFun1, R.id.btnRecruitFun2, R.id.btnRecruitFun3, R.id.btnMoreUniversity, R.id.btnMoreMajor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecruitFun1:
                startActivity(new Intent(context, SchoolActivity.class));
                break;
            case R.id.btnRecruitFun2:
                startActivity(new Intent(context, LearnActivity.class));
                break;
            case R.id.btnRecruitFun3:
                break;
            case R.id.btnMoreUniversity:
                startActivity(new Intent(context, MoreActivity.class).putExtra("type", MoreActivity.TYPE_HOT_SCHOOL));
                break;
            case R.id.btnMoreMajor:
                startActivity(new Intent(context, MoreActivity.class).putExtra("type", MoreActivity.TYPE_HOT_MAJOR));
                break;
        }
    }
}
