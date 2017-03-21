package cn.zty.recruit.ui.activity.school;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
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
import cn.zty.recruit.bean.UniversityModel;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/14.
 */

public class MoreActivity extends BaseActivity {

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
    int pageSize = 10;

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
                break;
            case TYPE_HOT_MAJOR:
                if (majorAdapter == null)
                    majorAdapter = new MajorAdapter(this);
                adapter = majorAdapter;
                break;
        }

        loadMoreFooter = new LoadMoreFooter(this);

        moreContent.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        moreContent.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(moreContent.getRecyclerView());
        moreContent.refreshState(true);

        moreContent.refreshState(false);
    }

    @Override
    protected void initData() {
        if (type == 0) {
            List<UniversityModel> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(new UniversityModel());
            }
            adapter.setData(list);
        } else {
            List<MajorModel> majorModels = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                majorModels.add(new MajorModel());
            }
            adapter.setData(majorModels);
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
                moreContent.refreshState(false);
            }

            @Override
            public void onLoadMore(int page) {
                currentPage = page;

                moreContent.getRecyclerView().setPage(currentPage, maxPage);
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
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }
}
