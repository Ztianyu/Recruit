package cn.zty.recruit.ui.activity.school;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.baselib.utils.AppManager;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.UniversityAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.bean.VocationalModel;
import cn.zty.recruit.listener.AreaSelectListener;
import cn.zty.recruit.listener.MajorSelectListener;
import cn.zty.recruit.listener.SchoolSelectListener;
import cn.zty.recruit.presenter.VocationalListPresenter;
import cn.zty.recruit.ui.activity.MainActivity;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.SnackbarUtils;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.VocationalListView;
import cn.zty.recruit.widget.LoadMoreFooter;

/**
 * 学校
 * Created by zty on 2017/3/9.
 */

public class SchoolActivity extends BaseActivity implements
        AreaSelectListener,
        MajorSelectListener,
        VocationalListView,
        SchoolSelectListener,
        XRecyclerView.OnRefreshAndLoadMoreListener {

    @BindView(R.id.textProvinceTip)
    TextView textProvinceTip;
    @BindView(R.id.textCityTip)
    TextView textCityTip;
    @BindView(R.id.textMajorTip)
    TextView textMajorTip;
    @BindView(R.id.contentLayoutSchool)
    XRecyclerContentLayout contentLayoutSchool;
    @BindView(R.id.layoutSchoolSelect)
    LinearLayout layoutSchoolSelect;
    @BindView(R.id.btnSearchBack)
    ImageView btnSearchBack;
    @BindView(R.id.textSearch)
    TextView textSearch;
    @BindView(R.id.textSelectSchool)
    TextView textSelectSchool;
    @BindView(R.id.layoutSearchSchool)
    LinearLayout layoutSearchSchool;

    LoadMoreFooter loadMoreFooter;

    int currentPage = 1;
    int maxPage = 1;
    int pageSize = 10;

    UniversityAdapter adapter;

    private String areaProvinceId;
    private String areaCityId;
    private String areaDiscipline;
    private String areaMajorId;

    private String provinceId;
    private String schoolNature;
    private String schoolType;
    private String discipline;
    private String majorId;
    private String score;
    private String tuitionType;

    private VocationalListPresenter presenter;

    private boolean isSearchByArea = true;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_school;
    }

    @Override
    protected void initView() {
        presenter = new VocationalListPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        loadMoreFooter = new LoadMoreFooter(this);

        adapter = new UniversityAdapter(this, false);

        contentLayoutSchool.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        contentLayoutSchool.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(contentLayoutSchool.getRecyclerView());
    }

    private void initAdapter(XRecyclerView recyclerView) {
        recyclerView.verticalLayoutManager(this)
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);
        recyclerView.setOnRefreshAndLoadMoreListener(this);
        recyclerView.setLoadMoreView(loadMoreFooter);
        recyclerView.setLoadMoreUIHandler(loadMoreFooter);
    }

    @Override
    protected void initData() {

        if (currentPage == 1)
            contentLayoutSchool.refreshState(true);

        if (isSearchByArea) {
            if (areaCityId != null) {
                presenter.getVocationList(null, areaCityId, null, null, areaDiscipline, areaMajorId, null, null, -1, currentPage, Constants.DEFAULT_PAGE_SIZE);
            } else {
                presenter.getVocationList(null, areaProvinceId, null, null, areaDiscipline, areaMajorId, null, null, -1, currentPage, Constants.DEFAULT_PAGE_SIZE);
            }
        } else {
            currentPage = 1;
            presenter.getVocationList(null, provinceId, schoolNature, schoolType, discipline, majorId, tuitionType, score, -1, currentPage, Constants.DEFAULT_PAGE_SIZE);
        }
    }

    @OnClick({R.id.textProvinceTip, R.id.textCityTip, R.id.textMajorTip, R.id.btnSearchBack, R.id.textSearch, R.id.textSelectSchool})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearchBack:
                backEvent();
                break;
            case R.id.textSearch:
                startActivity(new Intent(this, SchoolSearchActivity.class));
                break;
            case R.id.textSelectSchool:
                isSearchByArea = false;
                DialogUtils.showSchoolSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), this);
                break;
            case R.id.textProvinceTip:
                isSearchByArea = true;
                DialogUtils.showAreaSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 0, this, areaProvinceId);
                break;
            case R.id.textCityTip:
                isSearchByArea = true;
                if (!TextUtils.isEmpty(areaProvinceId)) {
                    DialogUtils.showAreaSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), 1, this, areaProvinceId);
                } else {
                    SnackbarUtils.showShort(contentLayoutSchool, "请选择省份");
                }
                break;
            case R.id.textMajorTip:
                isSearchByArea = true;
                DialogUtils.showMajorSelect(getSupportFragmentManager(), layoutSchoolSelect.getHeight() + layoutSearchSchool.getHeight(), this);
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
        } else {
            areaCityId = code;
            textCityTip.setText(value);
        }
        initData();
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
    public void onVocationalListSuccess(List<VocationalModel> models) {
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
    public void onRefresh() {
        currentPage = 1;
        initData();
    }

    @Override
    public void onLoadMore(int page) {
        currentPage = page;
        initData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backEvent();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void backEvent() {
        if (AppManager.getInstance().isHasActivity(MainActivity.class)) {
            finish();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onSchoolSelect(String provinceId, String nature, String type, String discipline, String majorId, String score, String tuition) {
        this.provinceId = provinceId;
        this.schoolNature = nature;
        this.schoolType = type;
        this.discipline = discipline;
        this.majorId = majorId;
        this.score = score;
        this.tuitionType = tuition;

        initData();
    }
}
