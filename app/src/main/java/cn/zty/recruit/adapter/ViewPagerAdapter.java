package cn.zty.recruit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * viewpager适配器
 * <p/>
 * Created by zty on 2016/5/25.
 */
public class ViewPagerAdapter<T> extends FragmentStatePagerAdapter {

    List<T> fragments = new ArrayList<T>();
    FragmentManager fm;
    List<String> titles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public void setFragments(List<T> fragments) {
        this.fragments = fragments;
        this.notifyDataSetChanged();
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
