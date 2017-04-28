package cn.zty.recruit.ui.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.MajorAdapter;
import cn.zty.recruit.adapter.UniversityAdapter;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.AdsModel;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.bean.VocationalModel;
import cn.zty.recruit.presenter.GetAdsPresenter;
import cn.zty.recruit.presenter.HotMajorPresenter;
import cn.zty.recruit.presenter.VocationalListPresenter;
import cn.zty.recruit.ui.activity.WebActivity;
import cn.zty.recruit.ui.activity.job.JobActivity;
import cn.zty.recruit.ui.activity.learn.LearnActivity;
import cn.zty.recruit.ui.activity.school.MoreActivity;
import cn.zty.recruit.ui.activity.school.SchoolActivity;
import cn.zty.recruit.utils.BannerUtils;
import cn.zty.recruit.utils.ViewAdaptionUtils;
import cn.zty.recruit.view.AdsView;
import cn.zty.recruit.view.HotMajorView;
import cn.zty.recruit.view.VocationalListView;
import cn.zty.recruit.widget.GradationNestedScrollView;
import cn.zty.recruit.widget.LabView;

/**
 * Created by zty on 2017/3/6.
 */

public class RecruitFragment extends BaseFragment implements
        AdsView,
        HotMajorView,
        VocationalListView,
        SwipeRefreshLayout.OnRefreshListener,
        GradationNestedScrollView.ScrollViewListener,
        OnBannerClickListener {

    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.bannerRecruit)
    Banner bannerRecruit;
    @BindView(R.id.btnRecruitFun1)
    LabView btnRecruitFun1;
    @BindView(R.id.btnRecruitFun2)
    LabView btnRecruitFun2;
    @BindView(R.id.btnRecruitFun3)
    LabView btnRecruitFun3;
    @BindView(R.id.scrollView)
    GradationNestedScrollView scrollView;
    @BindView(R.id.btnMoreUniversity)
    TextView btnMoreUniversity;
    @BindView(R.id.listHotUniversity)
    XRecyclerView listHotUniversity;
    @BindView(R.id.btnMoreMajor)
    TextView btnMoreMajor;
    @BindView(R.id.listHotMajor)
    XRecyclerView listHotMajor;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    UniversityAdapter universityAdapter;
    MajorAdapter majorAdapter;


    private GetAdsPresenter getAdsPresenter;
    private HotMajorPresenter hotMajorPresenter;
    private VocationalListPresenter vocationalListPresenter;

    private int height;

    private List<AdsModel> adsModels = new ArrayList<>();
    private int mIndicatorSelectedResId = R.mipmap.ic_point_select;
    private int mIndicatorUnselectedResId = R.mipmap.ic_point_normal;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_recruit;
    }

    @Override
    protected void initView() {

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        swipeRefreshLayout.setOnRefreshListener(this);

        textTitle.setPadding(0, RecruitApplication.getInstance().getStatusBarHeight(), 0, 0);
        ViewAdaptionUtils.LinearLayoutAdaptation(bannerRecruit, 400);

        universityAdapter = new UniversityAdapter(context, false);
        majorAdapter = new MajorAdapter(context);

        listHotUniversity.setNestedScrollingEnabled(false);
        listHotUniversity.verticalLayoutManager(context)
                .setAdapter(universityAdapter);
        listHotUniversity.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);

        listHotMajor.setNestedScrollingEnabled(false);
        listHotMajor.verticalLayoutManager(context)
                .setAdapter(majorAdapter);
        listHotMajor.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);

        getAdsPresenter = new GetAdsPresenter();
        getAdsPresenter.attach(this);
        presenters.add(getAdsPresenter);

        hotMajorPresenter = new HotMajorPresenter();
        hotMajorPresenter.attach(this);
        presenters.add(hotMajorPresenter);

        vocationalListPresenter = new VocationalListPresenter();
        vocationalListPresenter.attach(this);
        presenters.add(vocationalListPresenter);

        bannerRecruit.setFocusable(true);
        bannerRecruit.setFocusableInTouchMode(true);
        bannerRecruit.requestFocus();

        bannerRecruit.setOnBannerClickListener(this);

        ViewTreeObserver vto = bannerRecruit.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bannerRecruit.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = bannerRecruit.getHeight() - RecruitApplication.getInstance().getStatusBarHeight();

                scrollView.setScrollViewListener(RecruitFragment.this);
            }
        });

        initIndicator();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (adsModels.size() <= 0)
            initData();
    }

    private void initIndicator() {
        try {
            Field mField1 = Banner.class.getDeclaredField("mIndicatorSelectedResId");
            mField1.setAccessible(true);
            Field mField2 = Banner.class.getDeclaredField("mIndicatorUnselectedResId");
            mField2.setAccessible(true);
            mField1.set(bannerRecruit, mIndicatorSelectedResId);
            mField2.set(bannerRecruit, mIndicatorUnselectedResId);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage());
        }
    }

    @Override
    protected void initData() {

        swipeRefreshLayout.setRefreshing(true);

        getAdsPresenter.getAds();

        vocationalListPresenter.getVocationList(null, null, null, null, null, null, 1, 1, Constants.HOT_PAGE_SIZE);

        hotMajorPresenter.getHotMajorList(null, 1, null, 1, Constants.HOT_PAGE_SIZE);
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
                startActivity(new Intent(context, JobActivity.class));
                break;
            case R.id.btnMoreUniversity:
                startActivity(new Intent(context, MoreActivity.class).putExtra("type", MoreActivity.TYPE_HOT_SCHOOL));
                break;
            case R.id.btnMoreMajor:
                startActivity(new Intent(context, MoreActivity.class).putExtra("type", MoreActivity.TYPE_HOT_MAJOR));
                break;
        }
    }

    @Override
    public void onAdsSuccess(List<AdsModel> models) {
        if (models != null && models.size() > 0) {
            adsModels.clear();
            adsModels.addAll(models);
            List<String> images = new ArrayList<>();
            List<String> titles = new ArrayList<>();
            for (AdsModel adsModel : models) {
                images.add(adsModel.getImgUrl());
                titles.add(adsModel.getTitle());
            }
            BannerUtils.initBanner(bannerRecruit, images, titles, 1, 1);
        }
    }

    @Override
    public void onHotMajorSuccess(List<MajorModel> majorModels) {
        majorAdapter.setData(majorModels);
    }

    @Override
    public void onVocationalListSuccess(List<VocationalModel> models) {
        universityAdapter.setData(models);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onScrollChanged(GradationNestedScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            textTitle.setBackgroundColor(Color.argb(0, 240, 72, 72));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            textTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            textTitle.setBackgroundColor(Color.argb((int) alpha, 240, 72, 72));
        } else {    //滑动到banner下面设置普通颜色
            textTitle.setBackgroundColor(Color.argb(255, 240, 72, 72));
        }
    }

    @Override
    public void OnBannerClick(int position) {

        if (adsModels.size() > 0) {
            startActivity(new Intent(context, WebActivity.class)
                    .putExtra("title", adsModels.get((position - 1) % adsModels.size()).getTitle())
                    .putExtra("schoolId", "")
                    .putExtra("type", WebActivity.TYPE3)
                    .putExtra("content", adsModels.get((position - 1) % adsModels.size()).getContent()));

        }
    }
}
