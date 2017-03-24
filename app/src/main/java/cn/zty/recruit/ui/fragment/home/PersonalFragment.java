package cn.zty.recruit.ui.fragment.home;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.MyImageLoader;
import cn.zty.baselib.widget.CircleImageView;
import cn.zty.baselib.widget.StripMenuView;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseFragment;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.listener.ToastSureListener;
import cn.zty.recruit.ui.activity.person.ArchivesActivity;
import cn.zty.recruit.ui.activity.person.IntegralActivity;
import cn.zty.recruit.ui.activity.person.LoginActivity;
import cn.zty.recruit.ui.activity.person.NoticeActivity;
import cn.zty.recruit.ui.activity.person.OrderActivity;
import cn.zty.recruit.ui.activity.person.ResetPwActivity;
import cn.zty.recruit.ui.activity.person.VersionActivity;
import cn.zty.recruit.utils.DialogUtils;
import cn.zty.recruit.utils.ViewAdaptionUtils;
import cn.zty.recruit.widget.LabView;

/**
 * Created by zty on 2017/3/4.
 */

public class PersonalFragment extends BaseFragment implements ToastSureListener {
    @BindView(R.id.imgHeader)
    CircleImageView imgHeader;
    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.textSex)
    TextView textSex;
    @BindView(R.id.labOrder1)
    LabView labOrder1;
    @BindView(R.id.labOrder2)
    LabView labOrder2;
    @BindView(R.id.labOrder3)
    LabView labOrder3;
    @BindView(R.id.labResume1)
    LabView labResume1;
    @BindView(R.id.labResume2)
    LabView labResume2;
    @BindView(R.id.labResume3)
    LabView labResume3;
    @BindView(R.id.strip1)
    StripMenuView strip1;
    @BindView(R.id.strip2)
    StripMenuView strip2;
    @BindView(R.id.strip3)
    StripMenuView strip3;
    @BindView(R.id.strip4)
    StripMenuView strip4;
    @BindView(R.id.strip5)
    StripMenuView strip5;
    @BindView(R.id.layoutUser)
    LinearLayout layoutUser;
    @BindView(R.id.textLogin)
    TextView textLogin;
    @BindView(R.id.layoutHeader)
    LinearLayout layoutHeader;

    private boolean isHaveUser = false;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView() {
        ViewAdaptionUtils.LinearLayoutAdaptation(layoutHeader, 256);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        isHaveUser = RecruitApplication.getInstance().isHaveUser();

        if (isHaveUser) {
            textLogin.setVisibility(View.GONE);
            layoutUser.setVisibility(View.VISIBLE);
        } else {
            textLogin.setVisibility(View.VISIBLE);
            layoutUser.setVisibility(View.INVISIBLE);
            MyImageLoader.load(context, R.mipmap.ic_default_header, imgHeader);
        }
    }

    @OnClick({R.id.imgHeader, R.id.textLogin, R.id.labOrder1, R.id.labOrder2, R.id.labOrder3, R.id.labResume1, R.id.labResume2, R.id.labResume3, R.id.strip1, R.id.strip2, R.id.strip3, R.id.strip4, R.id.strip5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgHeader:
                if (isHaveUser())
                    startActivity(new Intent(context, ArchivesActivity.class));
                break;
            case R.id.textLogin:
                startActivity(new Intent(context, LoginActivity.class));
                break;
            case R.id.labOrder1:
                if (isHaveUser()) {
                    OrderActivity.page = 0;
                    startActivity(new Intent(context, OrderActivity.class));
                }
                break;
            case R.id.labOrder2:
                if (isHaveUser()) {
                    OrderActivity.page = 1;
                    startActivity(new Intent(context, OrderActivity.class));
                }
                break;
            case R.id.labOrder3:
                if (isHaveUser()) {
                    OrderActivity.page = 2;
                    startActivity(new Intent(context, OrderActivity.class));
                }
                break;
            case R.id.labResume1:
                break;
            case R.id.labResume2:
                break;
            case R.id.labResume3:
                break;
            case R.id.strip1:
                if (isHaveUser())
                    startActivity(new Intent(context, IntegralActivity.class));
                break;
            case R.id.strip2:
                if (isHaveUser())
                    startActivity(new Intent(context, NoticeActivity.class));
                break;
            case R.id.strip3:
                startActivity(new Intent(context, VersionActivity.class));
                break;
            case R.id.strip4:
                if (isHaveUser())
                    startActivity(new Intent(context, ResetPwActivity.class));
                break;
            case R.id.strip5:
                if (isHaveUser)
                    DialogUtils.showToast(getFragmentManager(), "是否退出登录", this);
                break;
        }
    }

    private boolean isHaveUser() {
        if (!isHaveUser) {
            startActivity(new Intent(context, LoginActivity.class));
            return false;
        }
        return true;
    }

    @Override
    public void onSure() {
        RecruitApplication.getInstance().setHaveUser(false);
        onResume();
    }
}
