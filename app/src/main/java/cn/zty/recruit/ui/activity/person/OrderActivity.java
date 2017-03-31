package cn.zty.recruit.ui.activity.person;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.ViewPagerAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.ui.fragment.person.OrderFragment;

/**
 * Created by zty on 2017/3/20.
 */

public class OrderActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private List<String> titles = new ArrayList<>();
    private List<BaseFragment> fragments = new ArrayList<>();

    private ViewPagerAdapter adapter;

    public static int page = 0;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("报名订单");
        initToolbar(toolbar);

        titles.add("待付款");
        titles.add("已预订");
        titles.add("已完成");

        fragments.add(OrderFragment.newInstance(0));
        fragments.add(OrderFragment.newInstance(1));
        fragments.add(OrderFragment.newInstance(2));

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        adapter.setTitles(titles);
        viewpager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    protected void initData() {
        viewpager.setCurrentItem(page);
    }
}
