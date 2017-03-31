package cn.zty.recruit.ui.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import cn.zty.baselib.utils.AppManager;
import cn.zty.baselib.widget.BottomNavigationViewEx;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.ui.fragment.home.LifeFragment;
import cn.zty.recruit.ui.fragment.home.LiveFragment;
import cn.zty.recruit.ui.fragment.home.PersonalFragment;
import cn.zty.recruit.ui.fragment.home.RecruitFragment;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation)
    BottomNavigationViewEx navigation;

    RecruitFragment recruitFragment;
    LiveFragment liveFragment;
    LifeFragment lifeFragment;
    PersonalFragment personalFragment;

    BaseFragment currentFragment;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        setTitleBar();

        navigation.setOnNavigationItemSelectedListener(this);
        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
    }

    @Override
    protected void initData() {
        if (recruitFragment == null)
            recruitFragment = new RecruitFragment();
        if (!recruitFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContent, recruitFragment).commit();
            currentFragment = recruitFragment;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_recruit:
                if (recruitFragment == null)
                    recruitFragment = new RecruitFragment();
                addOrShowFragment(getSupportFragmentManager(), recruitFragment);
                return true;
            case R.id.navigation_live:
                if (liveFragment == null)
                    liveFragment = new LiveFragment();
                addOrShowFragment(getSupportFragmentManager(), liveFragment);
                return true;
            case R.id.navigation_life:
                if (lifeFragment == null)
                    lifeFragment = new LifeFragment();
                addOrShowFragment(getSupportFragmentManager(), lifeFragment);
                return true;
            case R.id.navigation_personal:
                if (personalFragment == null)
                    personalFragment = new PersonalFragment();
                addOrShowFragment(getSupportFragmentManager(), personalFragment);
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
            fm.beginTransaction().add(R.id.fragmentContent, fragment).commit();
        } else {
            fm.beginTransaction().show(fragment).commit();
        }
        currentFragment = fragment;
    }

    private boolean bExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (bExit) {
                AppManager.getInstance().AppExit(this, false);
                finish();
                return true;
            }
            bExit = true;
            mHandler.sendEmptyMessageDelayed(0x100, 2000);
            Toast.makeText(this, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            bExit = false;
        }
    };
}
