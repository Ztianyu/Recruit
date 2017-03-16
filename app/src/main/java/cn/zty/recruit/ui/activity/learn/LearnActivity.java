package cn.zty.recruit.ui.activity.learn;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import butterknife.BindView;
import cn.zty.baselib.widget.BottomNavigationViewEx;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.ui.fragment.learn.StudyFragment;
import cn.zty.recruit.ui.fragment.learn.TrainingFragment;

/**
 * Created by zty on 2017/3/15.
 */

public class LearnActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.learnNavigation)
    BottomNavigationViewEx learnNavigation;

    TrainingFragment trainingFragment;
    StudyFragment studyFragment;

    BaseFragment currentFragment;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_learn;
    }

    @Override
    protected void initView() {
        learnNavigation.setOnNavigationItemSelectedListener(this);
        learnNavigation.enableAnimation(false);
        learnNavigation.enableShiftingMode(false);
        learnNavigation.enableItemShiftingMode(false);
    }

    @Override
    protected void initData() {
        if (trainingFragment == null)
            trainingFragment = new TrainingFragment();
        if (!trainingFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentLearnContent, trainingFragment).commit();
            currentFragment = trainingFragment;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_training:
                if (trainingFragment == null)
                    trainingFragment = new TrainingFragment();
                addOrShowFragment(getSupportFragmentManager(), trainingFragment);
                return true;
            case R.id.navigation_study:
                if (studyFragment == null)
                    studyFragment = new StudyFragment();
                addOrShowFragment(getSupportFragmentManager(), studyFragment);
                return true;
        }
        return false;
    }

    /**
     * 添加或者显示 fragment
     */
    private void addOrShowFragment(FragmentManager fm, BaseFragment fragment) {
        if (currentFragment == fragment)
            return;

        fm.beginTransaction().hide(currentFragment).commit();
        if (!fragment.isAdded()) {
            fm.beginTransaction().add(R.id.fragmentLearnContent, fragment).commit();
        } else {
            fm.beginTransaction().show(fragment).commit();
        }
        currentFragment = fragment;
    }
}
