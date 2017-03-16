package cn.zty.recruit.ui.activity.person;

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
import cn.zty.recruit.bean.LeftMenuEntity;
import cn.zty.recruit.bean.RightContentEntity;

/**
 * Created by zty on 2017/3/15.
 */

public class SetPositionActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.linkage)
    LinkageView linkage;

    LeftMenuListAdapter leftMenuListAdapter;
    RightContentListAdapter rightContentListAdapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_set_position;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("位置");
        initToolbar(toolbar);
    }

    @Override
    protected void initData() {
        leftMenuListAdapter = new LeftMenuListAdapter(this, new ArrayList<LeftMenuEntity>());
        linkage.setLeftMenuAdapter(leftMenuListAdapter);

        rightContentListAdapter = new RightContentListAdapter(this, new ArrayList<RightContentEntity>());
        linkage.setRightContentAdapter(rightContentListAdapter);

        linkage.setOnItemClickListener(new ILinkage.OnItemClickListener() {
            @Override
            public void onLeftClick(View itemView, int position) {
                List<RightContentEntity> rightContentEntities = new ArrayList<>();
                rightContentEntities.add(new RightContentEntity());
                rightContentEntities.add(new RightContentEntity());
                rightContentEntities.add(new RightContentEntity());
                rightContentEntities.add(new RightContentEntity());
                rightContentEntities.add(new RightContentEntity());
                rightContentEntities.add(new RightContentEntity());

                rightContentListAdapter.setList(rightContentEntities);
            }

            @Override
            public void onRightClick(View itemView, int position) {

            }
        });


        List<LeftMenuEntity> leftMenuEntities = new ArrayList<>();
        leftMenuEntities.add(new LeftMenuEntity());
        leftMenuEntities.add(new LeftMenuEntity());
        leftMenuEntities.add(new LeftMenuEntity());
        linkage.updateData(leftMenuEntities);
    }
}
