package cn.zty.recruit.ui.activity.person;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.zty.linkage.LinkageView;
import cn.zty.linkage.model.ILinkage;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.LeftMenuListAdapter;
import cn.zty.recruit.adapter.RightContentListAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.presenter.GetCityPresenter;
import cn.zty.recruit.presenter.GetProvincePresenter;
import cn.zty.recruit.view.AreaView;

/**
 * Created by zty on 2017/3/15.
 */

public class SetPositionActivity extends BaseActivity implements AreaView{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.linkage)
    LinkageView linkage;

    LeftMenuListAdapter leftMenuListAdapter;
    RightContentListAdapter rightContentListAdapter;

    GetProvincePresenter getProvincePresenter;
    GetCityPresenter getCityPresenter;

    private String selectProvinceKey;
    private String selectCityKey;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_set_position;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("位置");
        initToolbar(toolbar);

        getProvincePresenter = new GetProvincePresenter();
        getProvincePresenter.attach(this);
        presenters.add(getCityPresenter);

        getCityPresenter = new GetCityPresenter();
        getCityPresenter.attach(this);
        presenters.add(getCityPresenter);

        leftMenuListAdapter = new LeftMenuListAdapter(this, new ArrayList<TipModel>());
        linkage.setLeftMenuAdapter(leftMenuListAdapter);

        rightContentListAdapter = new RightContentListAdapter(this, new ArrayList<TipModel>());
        linkage.setRightContentAdapter(rightContentListAdapter);

        linkage.setOnItemClickListener(new ILinkage.OnItemClickListener() {
            @Override
            public void onLeftClick(View itemView, int position) {
                selectProvinceKey = leftMenuListAdapter.getList().get(position).getKey();
                getCityPresenter.getCity(selectProvinceKey);
            }

            @Override
            public void onRightClick(View itemView, int position) {
                selectCityKey = rightContentListAdapter.getList().get(position).getKey();
                callBack();
            }
        });
    }

    @Override
    protected void initData() {
        getProvincePresenter.getProvince();
    }

    private void callBack() {
        Intent intent = new Intent();
        intent.putExtra("province", selectProvinceKey);
        intent.putExtra("city", selectCityKey);
        setResult(1, intent);
        finish();
    }

    @Override
    public void onAreaSuccess(int type, List<TipModel> models) {
        if(type ==0){
            linkage.updateData(models);
        }else{
            rightContentListAdapter.setList(models);
        }
    }
}
