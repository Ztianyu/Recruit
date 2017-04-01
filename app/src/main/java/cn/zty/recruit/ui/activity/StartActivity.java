package cn.zty.recruit.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.presenter.CheckInviteCodePresenter;
import cn.zty.recruit.presenter.GetUserPresenter;
import cn.zty.recruit.ui.activity.school.SchoolActivity;
import cn.zty.recruit.utils.SharedPrefUtils;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.StringView;
import cn.zty.recruit.view.UserView;

/**
 * 启动页
 * Created by zty on 2017/3/24.
 */

public class StartActivity extends BaseActivity implements
        View.OnFocusChangeListener,
        UserView,
        StringView {

    @BindView(R.id.editStartCode)
    EditText editStartCode;
    @BindView(R.id.textStartCodeTip)
    TextView textStartCodeTip;
    @BindView(R.id.btnSureCode)
    TextView btnSureCode;
    @BindView(R.id.btnSkip)
    TextView btnSkip;

    private GetUserPresenter presenter;

    private CheckInviteCodePresenter checkInviteCodePresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        presenter = new GetUserPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        checkInviteCodePresenter = new CheckInviteCodePresenter();
        checkInviteCodePresenter.attach(this);
        presenters.add(checkInviteCodePresenter);

        setTitleBar();

        editStartCode.setOnFocusChangeListener(this);
    }

    @Override
    protected void initData() {

        if (!TextUtils.isEmpty(RecruitApplication.getInstance().getTokenId()))
            presenter.getUser(RecruitApplication.getInstance().getTokenId(), RecruitApplication.getInstance().getUserId());

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i > 0; i--) {
                    handler.sendEmptyMessage(-1);
                    if (i <= 0) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    int i = 7;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
            } else if (msg.what == 2) {
                startActivity(new Intent(StartActivity.this, SchoolActivity.class));
                finish();
            } else {
                if (btnSkip != null)
                    btnSkip.setText("跳( " + i + " )过");
            }
        }
    };

    @Override
    public void onUserSuccess(UserModel userModel) {
        if (userModel != null)
            RecruitApplication.getInstance().setUserModel(userModel);
    }

    @OnClick({R.id.btnSureCode, R.id.btnSkip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSureCode:
                if (!TextUtils.isEmpty(editStartCode.getText().toString())) {
                    checkInviteCodePresenter.check(editStartCode.getText().toString());
                } else {
                    ToastUtils.show("请输入邀请码");
                }
                break;
            case R.id.btnSkip:
                handler.sendEmptyMessage(1);
                break;
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.editStartCode)
            if (hasFocus) {
                i = 30;
                textStartCodeTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
            } else {
                textStartCodeTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
            }
    }

    @Override
    public void onSuccess(String msg) {
        SharedPrefUtils.setString(this, SharedPrefUtils.inviteCode, editStartCode.getText().toString());
        handler.sendEmptyMessage(2);
    }
}
