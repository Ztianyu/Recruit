package cn.zty.recruit.ui.activity.person;

import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.LoginModel;
import cn.zty.recruit.presenter.LoginPresenter;
import cn.zty.recruit.view.LoginView;

/**
 * Created by zty on 2017/3/4.
 */

public class LoginActivity extends BaseActivity implements LoginView{


    LoginPresenter loginPresenter;
    @Override
    protected int initLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        loginPresenter = new LoginPresenter();
        loginPresenter.attach(this);
        presenters.add(loginPresenter);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onSuccess(LoginModel loginModel) {

    }
}
