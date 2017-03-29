package cn.zty.recruit.ui.activity.learn;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.MajorPlanAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.InstitutionMajorModel;
import cn.zty.recruit.bean.PlanModel;
import cn.zty.recruit.presenter.ScheduleSetListPresenter;
import cn.zty.recruit.view.ScheduleSetListView;

/**
 * Created by zty on 2017/3/17.
 */

public class MajorPlanActivity extends BaseActivity implements
        XRecyclerView.OnRefreshAndLoadMoreListener,
        ScheduleSetListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layoutContent)
    XRecyclerContentLayout layoutContent;

    private View header1;
    private View header2;
    private View header3;
    private View footer;

    private ExpandableTextView expandText;
    private TextView textMajorDetailName;
    private TextView textMajorDetailCount;
    private TextView textMajorPrise;
    private TextView textMajorUnit;

    MajorPlanAdapter adapter;

    private InstitutionMajorModel majorModel;

    private ScheduleSetListPresenter presenter;

    @Override
    protected int initLayoutId() {
        return R.layout.view_content;
    }

    @Override
    protected void initView() {
        majorModel = getIntent().getParcelableExtra("model");

        toolbar.setTitle("开课计划");
        initToolbar(toolbar);

        header1 = View.inflate(this, R.layout.view_major_detail_header, null);
        textMajorDetailName = (TextView) header1.findViewById(R.id.textMajorDetailName);
        textMajorDetailCount = (TextView) header1.findViewById(R.id.textMajorDetailCount);
        textMajorPrise = (TextView) header1.findViewById(R.id.textMajorPrise);
        textMajorUnit = (TextView) header1.findViewById(R.id.textMajorUnit);

        header2 = View.inflate(this, R.layout.view_major_detail_introduction, null);
        expandText = (ExpandableTextView) header2.findViewById(R.id.expandText);

        header3 = View.inflate(this, R.layout.view_major_plan, null);

        footer = View.inflate(this, R.layout.view_footer, null);

        layoutContent.getRecyclerView().setRefreshEnabled(true);    //设置是否可刷新
        layoutContent.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorAccent, R.color.colorAccent, R.color.gray);
        initAdapter(layoutContent.getRecyclerView());

        presenter = new ScheduleSetListPresenter();
        presenter.attach(this);
        presenters.add(presenter);
    }

    private void initAdapter(XRecyclerView recyclerView) {
        if (adapter == null) adapter = new MajorPlanAdapter(this);
        recyclerView.verticalLayoutManager(this)
                .setAdapter(adapter);
        recyclerView.setOnRefreshAndLoadMoreListener(this);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);

        recyclerView.addHeaderView(header1);
        recyclerView.addHeaderView(header2);
        recyclerView.addHeaderView(header3);
        recyclerView.addFooterView(footer);
    }

    @Override
    protected void initData() {
        expandText.setText(MyTextUtils.notNullStr(majorModel.getRemarks()));
        textMajorDetailName.setText(majorModel.getName());
        textMajorDetailCount.setText("总学时：" + majorModel.getHours());
        textMajorPrise.setText(majorModel.getMoney() + "");
        String unit = "";
        if (majorModel.getChargeStandard().equals("1")) {
            unit = Constants.chargeStandard1;
        }
        if (majorModel.getChargeStandard().equals("2")) {
            unit = Constants.chargeStandard2;
        }
        textMajorUnit.setText("元/" + unit);

        presenter.getScheduleSetList(majorModel.getId());
    }

    @Override
    public void onRefresh() {
        presenter.getScheduleSetList(majorModel.getId());
    }

    @Override
    public void onLoadMore(int page) {
    }

    @Override
    public void onScheduleSetSuccess(List<PlanModel> models) {
        layoutContent.refreshState(false);
        adapter.setData(models);
    }
}
