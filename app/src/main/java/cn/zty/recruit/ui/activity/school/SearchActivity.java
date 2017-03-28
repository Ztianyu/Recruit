package cn.zty.recruit.ui.activity.school;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.baselib.utils.AutoLineLayoutManager;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.SearchContentAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.TipModel;

/**
 * Created by zty on 2017/3/13.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.autoLineRecycler)
    XRecyclerView autoLineRecycler;
    @BindView(R.id.btnSearchBack)
    ImageView btnSearchBack;
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;

    SearchContentAdapter adapter;


    @Override
    protected int initLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        AutoLineLayoutManager layoutManager = new AutoLineLayoutManager();
        layoutManager.setAutoMeasureEnabled(true);
        autoLineRecycler.setLayoutManager(layoutManager);
        adapter = new SearchContentAdapter(this);
        autoLineRecycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        List<TipModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new TipModel());
        }
        adapter.setData(list);
    }

    @OnClick({R.id.btnSearchBack, R.id.imgSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearchBack:
                finish();
                break;
            case R.id.imgSearch:
                break;
        }
    }
}
