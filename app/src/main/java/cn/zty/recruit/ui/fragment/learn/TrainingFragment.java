package cn.zty.recruit.ui.fragment.learn;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.TrainAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.bean.TrainingModel;
import cn.zty.recruit.listener.AreaSelectListener;
import cn.zty.recruit.listener.IndustryTypeListener;
import cn.zty.recruit.presenter.TrainOrgListPresenter;
import cn.zty.recruit.ui.activity.learn.TrainSearchActivity;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.TrainOrgListView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * 培训机构列表
 * Created by zty on 2017/3/16.
 */

public class TrainingFragment extends BaseFragment implements
        AreaSelectListener,
        IndustryTypeListener,
        TrainOrgListView {

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

    private TrainAdapter adapter;

    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    private String province;
    private String city;
    private String industryId;

    TrainOrgListPresenter trainOrgListPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_school;
    }

    @Override
    protected void initView() {
        textMajorTip.setText("项目分类");
        textSelectSchool.setVisibility(View.GONE);

        trainOrgListPresenter = new TrainOrgListPresenter();
        trainOrgListPresenter.attach(this);
        presenters.add(trainOrgListPresenter);


        loadMoreFooter = new LoadMoreFooter(context);

        adapter = new TrainAdapter(context);

        contentLayoutSchool.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        contentLayoutSchool.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(contentLayoutSchool.getRecyclerView());
    }

    @Override
    protected void initData() {
        if (currentPage == 1)
            contentLayoutSchool.refreshState(true);
        trainOrgListPresenter.getTrainOrgList(null, province, city, industryId, currentPage);
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(context)
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

    @OnClick({R.id.btnSearchBack, R.id.textSearch, R.id.textSelectSchool, R.id.textProvinceTip, R.id.textCityTip, R.id.textMajorTip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearchBack:
                ((BaseActivity) context).finish();
                break;
            case R.id.textSearch:
                startActivity(new Intent(context, TrainSearchActivity.class));
                break;
            case R.id.textProvinceTip:
                DialogUtils.showAreaSelect(getChildFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 0, this, province);
                break;
            case R.id.textCityTip:
                if (!TextUtils.isEmpty(province)) {
                    DialogUtils.showAreaSelect(getChildFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 1, this, province);
                } else {
                    ToastUtils.show("请选择省份");
                }
                break;
            case R.id.textMajorTip:
                DialogUtils.showIndustryType(getChildFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), this);
                break;
        }
    }

    @Override
    public void onAreaSelect(String code, String value, int type) {
        if (type == 0) {
            if (value.equals("全部")) {
                province = null;
                city = null;
                textProvinceTip.setText("省份");
                textCityTip.setText("城市");
            } else {
                province = code;
                textProvinceTip.setText(value);
            }
        } else if (type == 1) {
            city = code;
            textCityTip.setText(value);
        }
        initData();
    }

    @Override
    public void onIndustryTypeSelect(String id, String name) {
        industryId = id;
        textMajorTip.setText(name);
        initData();
    }

    @Override
    public void onTrainOrgListSuccess(List<TrainingModel> models) {
        contentLayoutSchool.refreshState(false);
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
            contentLayoutSchool.getRecyclerView().setPage(currentPage, maxPage);
        } else {
            adapter.clearData();
        }
    }
}
