package cn.zty.recruit.ui.fragment.learn;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.StudySchoolAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.bean.StudySchoolModel;
import cn.zty.recruit.listener.AreaSelectListener;
import cn.zty.recruit.ui.activity.school.SearchActivity;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/16.
 */

public class StudyFragment extends BaseFragment implements AreaSelectListener {

    @BindView(R.id.btnSearchBack)
    ImageView btnSearchBack;
    @BindView(R.id.textSearch)
    TextView textSearch;
    @BindView(R.id.textSelectSchool)
    TextView textSelectSchool;
    @BindView(R.id.textProvinceTip)
    TextView textProvinceTip;
    @BindView(R.id.textCityTip)
    TextView textCityTip;
    @BindView(R.id.textMajorTip)
    TextView textMajorTip;
    @BindView(R.id.layoutSchoolSelect)
    LinearLayout layoutSchoolSelect;
    @BindView(R.id.contentLayoutSchool)
    XRecyclerContentLayout contentLayoutSchool;
    @BindView(R.id.layoutSearchSchool)
    LinearLayout layoutSearchSchool;

    StudySchoolAdapter adapter;

    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    private String provinceId;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_school;
    }

    @Override
    protected void initView() {

        loadMoreFooter = new LoadMoreFooter(context);

        adapter = new StudySchoolAdapter(context, false);

        contentLayoutSchool.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        contentLayoutSchool.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(contentLayoutSchool.getRecyclerView());
        contentLayoutSchool.refreshState(true);
        contentLayoutSchool.refreshState(false);
    }

    @Override
    protected void initData() {
        List<StudySchoolModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new StudySchoolModel());
        }
        adapter.setData(list);
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(context)
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
        recyclerView.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                contentLayoutSchool.refreshState(false);
            }

            @Override
            public void onLoadMore(int page) {
                currentPage = page;
                contentLayoutSchool.getRecyclerView().setPage(currentPage, maxPage);
            }
        });
        recyclerView.setLoadMoreView(loadMoreFooter);
        recyclerView.setLoadMoreUIHandler(loadMoreFooter);
    }

    @OnClick({R.id.btnSearchBack, R.id.textSearch, R.id.textSelectSchool, R.id.textProvinceTip, R.id.textCityTip, R.id.textMajorTip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearchBack:
                ((BaseActivity) context).finish();
                break;
            case R.id.textSearch:
                startActivity(new Intent(context, SearchActivity.class));
                break;
            case R.id.textSelectSchool:
                DialogUtils.showStudySelect(getChildFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight());
                break;
            case R.id.textProvinceTip:
                DialogUtils.showAreaSelect(getFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 0, this, provinceId);
                break;
            case R.id.textCityTip:
                DialogUtils.showAreaSelect(getFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 1, this, provinceId);
                break;
            case R.id.textMajorTip:
                DialogUtils.showAreaSelect(getFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 2, this, provinceId);
                break;
        }
    }

    @Override
    public void onAreaSelect(String code, String value, int type) {
        if (type == 0) {
            textProvinceTip.setText(value);
        } else if (type == 1) {
            textCityTip.setText(value);
        } else if (type == 2) {
            textMajorTip.setText(value);
        }
    }
}
