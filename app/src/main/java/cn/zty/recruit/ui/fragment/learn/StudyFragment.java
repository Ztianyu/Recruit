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
import cn.zty.recruit.adapter.StudySchoolAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.bean.StudySchoolModel;
import cn.zty.recruit.listener.AreaSelectListener;
import cn.zty.recruit.listener.MajorSelectListener;
import cn.zty.recruit.listener.StudySchoolListener;
import cn.zty.recruit.presenter.StudySchoolListPresenter;
import cn.zty.recruit.ui.activity.learn.StudySearchActivity;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.StudySchoolView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * Created by zty on 2017/3/16.
 */

public class StudyFragment extends BaseFragment implements
        AreaSelectListener,
        MajorSelectListener,
        StudySchoolListener,
        StudySchoolView {

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


    private String areaProvinceId;
    private String areaCityId;
    private String areaDiscipline;
    private String areaMajorId;

    private String provinceId;
    private String schoolType;
    private String studyType;
    private String discipline;
    private String majorId;
    private String tuition;

    private boolean isSearchByArea = true;

    StudySchoolListPresenter studySchoolListPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_school;
    }

    @Override
    protected void initView() {

        studySchoolListPresenter = new StudySchoolListPresenter();
        studySchoolListPresenter.attach(this);
        presenters.add(studySchoolListPresenter);

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
        if (currentPage == 1)
            contentLayoutSchool.refreshState(true);

        if (isSearchByArea) {
            studySchoolListPresenter.getStudySchoolInfoList(null, areaProvinceId, areaCityId, areaDiscipline, areaMajorId, null, null, null, currentPage);
        } else {
            studySchoolListPresenter.getStudySchoolInfoList(null, provinceId, null, discipline, majorId, schoolType, studyType, tuition, currentPage);
        }
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
                startActivity(new Intent(context, StudySearchActivity.class));
                break;
            case R.id.textSelectSchool:
                isSearchByArea = false;
                DialogUtils.showStudySelect(getChildFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), this);
                break;
            case R.id.textProvinceTip:
                isSearchByArea = true;
                DialogUtils.showAreaSelect(getFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 0, this, areaProvinceId);
                break;
            case R.id.textCityTip:
                isSearchByArea = true;
                if (!TextUtils.isEmpty(areaProvinceId)) {
                    DialogUtils.showAreaSelect(getFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 1, this, areaProvinceId);
                } else {
                    ToastUtils.show("请选择省份");
                }
                break;
            case R.id.textMajorTip:
                DialogUtils.showMajorSelect(getFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), this);
                break;
        }
    }

    @Override
    public void onAreaSelect(String code, String value, int type) {
        if (type == 0) {
            if (value.equals("全部")) {
                areaProvinceId = null;
                areaCityId = null;
                textProvinceTip.setText("省份");
                textCityTip.setText("城市");
            } else {
                areaProvinceId = code;
                textProvinceTip.setText(value);
            }
        } else if (type == 1) {
            areaCityId = code;
            textCityTip.setText(value);
        }
        initData();
    }

    @Override
    public void onStudySchoolList(List<StudySchoolModel> models) {
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
            if (currentPage == 1)
                adapter.clearData();
            contentLayoutSchool.getRecyclerView().setPage(currentPage, maxPage);
        }
    }

    @Override
    public void onStudySchool(StudySchoolModel model) {

    }

    @Override
    public void onMajorSelect(MajorModel majorModel) {
        if (majorModel != null) {
            this.areaDiscipline = majorModel.getDiscipline();
            this.areaMajorId = majorModel.getId();
            textMajorTip.setText(majorModel.getName());
        } else {
            this.areaDiscipline = null;
            this.areaMajorId = null;
            textMajorTip.setText("专业");
        }
        initData();
    }

    @Override
    public void onStudySchoolSure(String provinceId, String schoolType, String studyType, String discipline, String majorId, String tuition) {
        this.provinceId = provinceId;
        this.schoolType = schoolType;
        this.studyType = studyType;
        this.discipline = discipline;
        this.majorId = majorId;
        this.tuition = tuition;

        initData();
    }
}
