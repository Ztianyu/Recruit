package cn.zty.recruit.ui.activity.person;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.ViewPagerAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.ui.fragment.person.IntegralRecord;
import cn.zty.recruit.ui.fragment.person.MyIntegralFragment;

/**
 * Created by zty on 2017/3/20.
 */

public class IntegralActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private List<String> titles = new ArrayList<>();
    private List<BaseFragment> fragments = new ArrayList<>();

    private ViewPagerAdapter adapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("我的积分");
        toolbar.setNavigationIcon(R.drawable.ic_main_back);
        toolbar.inflateMenu(R.menu.invite_user);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.inviteUser) {
                    startActivity(new Intent(IntegralActivity.this, InviteUserActivity.class));
                    return true;
                }
                return false;
            }
        });

        titles.add("当前积分");
        titles.add("积分记录");

        fragments.add(new MyIntegralFragment());
        fragments.add(new IntegralRecord());

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        adapter.setTitles(titles);
        viewpager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    protected void initData() {

    }
}
