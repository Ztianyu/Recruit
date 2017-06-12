package cn.zty.recruit.base;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.baselib.utils.AppManager;

/**
 * Created by zty on 2017/2/21.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected Unbinder unbinder;

    public static boolean isGetSize = false;
    public static int screenHeight = 0;
    public static int screenWidth = 0;

    protected List<IBasePresenter> presenters;

    protected abstract int initLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initLayoutId();
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());

        presenters = new ArrayList<>();

        mContext = this;
        unbinder = ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);

        getScreenSize();
        setPermissions();
        initView();
        initData();
    }

    public void setTitleBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明状态栏
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();

        if (presenters.size() > 0) {
            for (IBasePresenter presenter : presenters) {
                presenter.detach();
            }
        }
        AppManager.getInstance().finishActivity(this);

        super.onDestroy();
    }

    private String[] permissions = {"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    /**
     * 检察每个权限是否申请
     *
     * @return true 需要申请权限,false 已申请权限
     */
    private boolean checkEachSelfPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    public void setPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkEachSelfPermission(permissions)) {// 检查是否声明了权限
                ActivityCompat.requestPermissions(BaseActivity.this, permissions, 1);
            }
        }
    }

    private void getScreenSize() {
        if (!isGetSize) {
            DisplayMetrics dm = new DisplayMetrics();
            // 获取屏幕信息
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            screenHeight = dm.heightPixels;
            screenWidth = dm.widthPixels;
            isGetSize = true;
        }
    }

    public void initToolbar(Toolbar toolbar) {
//        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
