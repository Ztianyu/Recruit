package cn.zty.recruit.ui.activity.person;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.AppManager;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.LoginModel;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.presenter.GetUserPresenter;
import cn.zty.recruit.presenter.LoginPresenter;
import cn.zty.recruit.ui.activity.MainActivity;
import cn.zty.recruit.utils.UserUtils;
import cn.zty.recruit.view.LoginView;
import cn.zty.recruit.view.UserView;

/**
 * Created by zty on 2017/3/4.
 */

public class LoginActivity extends BaseActivity implements View.OnFocusChangeListener,
        LoginView,
        UserView {

    @BindView(R.id.editLoginName)
    EditText editLoginName;
    @BindView(R.id.textLoginUserTip)
    TextView textLoginUserTip;
    @BindView(R.id.editLoginPw)
    EditText editLoginPw;
    @BindView(R.id.textLoginPwTip)
    TextView textLoginPwTip;
    @BindView(R.id.btnLogin)
    TextView btnLogin;
    @BindView(R.id.btnRegister)
    TextView btnRegister;
    @BindView(R.id.btnForgetPw)
    TextView btnForgetPw;

    LoginPresenter loginPresenter;

    private GetUserPresenter presenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        loginPresenter = new LoginPresenter();
        loginPresenter.attach(this);
        presenters.add(loginPresenter);

        presenter = new GetUserPresenter();
        presenter.attach(this);
        presenters.add(presenter);

        editLoginName.setOnFocusChangeListener(this);
        editLoginPw.setOnFocusChangeListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        editLoginName.setText(MyTextUtils.notNullStr(RecruitApplication.getInstance().getLoginName()));
    }

    @Override
    public void onLoginSuccess(LoginModel loginModel) {

        UserUtils.saveUser(this, loginModel, editLoginName.getText().toString());

        presenter.getUser(loginModel.getTokenId(), loginModel.getUserId());

        RecruitApplication.getInstance().setHaveUser(true);
        RecruitApplication.getInstance().setUserId(loginModel.getUserId());
        RecruitApplication.getInstance().setTokenId(loginModel.getTokenId());

        finish();
    }

    @OnClick({R.id.btnLogin, R.id.btnRegister, R.id.btnForgetPw, R.id.btnLoginBack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                loginPresenter.login(editLoginName.getText().toString(), editLoginPw.getText().toString());
                break;
            case R.id.btnRegister:
                startActivity(new Intent(this, ConfirmPhoneActivity.class).putExtra("type", 0));
                break;
            case R.id.btnForgetPw:
                startActivity(new Intent(this, ConfirmPhoneActivity.class).putExtra("type", 1));
                break;
            case R.id.btnLoginBack:
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.editLoginName:
                if (hasFocus) {
                    textLoginUserTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
                } else {
                    textLoginUserTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
                }
                break;
            case R.id.editLoginPw:
                if (hasFocus) {
                    textLoginPwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
                } else {
                    textLoginPwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
                }
                break;
        }
    }

    @Override
    public void onUserSuccess(UserModel userModel) {
        if (userModel != null)
            RecruitApplication.getInstance().setUserModel(userModel);
    }
}
