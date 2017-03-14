package cn.zty.recruit.ui.activity.school;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

/**
 * Created by zty on 2017/3/13.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.autoLineRecycler)
    RecyclerView autoLineRecycler;
    @BindView(R.id.btnSearchBack)
    ImageView btnSearchBack;
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

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
