package cn.zty.recruit.ui.activity.school;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.MajorAdapter;
import cn.zty.recruit.adapter.UniversityAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.bean.VocationalModel;
import cn.zty.recruit.presenter.HotMajorPresenter;
import cn.zty.recruit.presenter.VocationalListPresenter;
import cn.zty.recruit.view.HotMajorView;
import cn.zty.recruit.view.VocationalListView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/14.
 */

public class MoreActivity extends BaseActivity implements
        VocationalListView,
        HotMajorView {

    public static final int TYPE_HOT_SCHOOL = 1;
    public static final int TYPE_HOT_MAJOR = 2;

    @BindView(R.id.moreContent)
    XRecyclerContentLayout moreContent;
    @BindView(R.id.btnSearchBack)
    ImageView btnSearchBack;
    @BindView(R.id.textSearch)
    TextView textSearch;

    LoadMoreFooter loadMoreFooter;

    private int type;

    UniversityAdapter universityAdapter;
    MajorAdapter majorAdapter;
    RecyclerAdapter adapter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 15;

    private VocationalListPresenter vocationalListPresenter;
    private HotMajorPresenter hotMajorPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_more;
    }

    @Override
    protected void initView() {
        type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case TYPE_HOT_SCHOOL:
                if (universityAdapter == null)
                    universityAdapter = new UniversityAdapter(this, true);
                adapter = universityAdapter;

                vocationalListPresenter = new VocationalListPresenter();
                vocationalListPresenter.attach(this);
                presenters.add(vocationalListPresenter);
                break;
            case TYPE_HOT_MAJOR:
                if (majorAdapter == null)
                    majorAdapter = new MajorAdapter(this);
                adapter = majorAdapter;

                hotMajorPresenter = new HotMajorPresenter();
                hotMajorPresenter.attach(this);
                presenters.add(hotMajorPresenter);
                break;
        }

        loadMoreFooter = new LoadMoreFooter(this);

        moreContent.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        moreContent.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(moreContent.getRecyclerView());
    }

    @Override
    protected void initData() {
        if (currentPage == 1)
            moreContent.refreshState(true);

        if (type == TYPE_HOT_SCHOOL) {
            vocationalListPresenter.getVocationList(null, null, null, null, null, null, 1, currentPage, pageSize);
        } else if (type == TYPE_HOT_MAJOR) {
            hotMajorPresenter.getHotMajorList(null, 1, null, currentPage, pageSize);
        }
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(this)
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
        recyclerView.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                initData();
            }

            @Override
            public void onLoadMore(int page) {
                currentPage = page;
                initData();
            }
        });
        recyclerView.setLoadMoreView(loadMoreFooter);
        recyclerView.setLoadMoreUIHandler(loadMoreFooter);
    }

    @OnClick({R.id.btnSearchBack, R.id.textSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearchBack:
                finish();
                break;
            case R.id.textSearch:
                if (type == TYPE_HOT_SCHOOL) {
                    startActivity(new Intent(this, SchoolSearchActivity.class));
                } else {
                    startActivity(new Intent(this, MajorSearchActivity.class));
                }
                break;
        }
    }

    @Override
    public void onVocationalListSuccess(List<VocationalModel> models) {
        moreContent.refreshState(false);
        if (models != null && models.size() > 0) {
            if (currentPage == 1) {
                adapter.setData(models);
            } else {
                adapter.addData(models);
            }

            if (models.size() < pageSize) {
                maxPage = currentPage;
            } else {
                maxPage = currentPage + 1;
            }
            moreContent.getRecyclerView().setPage(currentPage, maxPage);
        } else {
            moreContent.getRecyclerView().setPage(currentPage, currentPage);
        }
    }

    @Override
    public void onHotMajorSuccess(List<MajorModel> majorModels) {
        moreContent.refreshState(false);
        if (majorModels != null && majorModels.size() > 0) {
            if (currentPage == 1) {
                adapter.setData(majorModels);
            } else {
                adapter.addData(majorModels);
            }

            if (majorModels.size() < pageSize) {
                maxPage = currentPage;
            } else {
                maxPage = currentPage + 1;
            }
            moreContent.getRecyclerView().setPage(currentPage, maxPage);
        } else {
            moreContent.getRecyclerView().setPage(currentPage, currentPage);
        }
    }
}
