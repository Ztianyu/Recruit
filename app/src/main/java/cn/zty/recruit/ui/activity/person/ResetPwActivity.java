package cn.zty.recruit.ui.activity.person;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.presenter.UpdatePwdPresenter;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.utils.UserUtils;
import cn.zty.recruit.view.StringView;

/**
 * Created by zty on 2017/3/21.
 */

public class ResetPwActivity extends BaseActivity implements
        View.OnFocusChangeListener,
        StringView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editOldPw)
    EditText editOldPw;
    @BindView(R.id.textOldPwTip)
    TextView textOldPwTip;
    @BindView(R.id.editNewPw)
    EditText editNewPw;
    @BindView(R.id.textNewPwTip)
    TextView textNewPwTip;
    @BindView(R.id.editSurePw)
    EditText editSurePw;
    @BindView(R.id.textSurePwTip)
    TextView textSurePwTip;
    @BindView(R.id.btnReset)
    TextView btnReset;

    private UpdatePwdPresenter updatePwdPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_reset_pw;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("修改密码");
        initToolbar(toolbar);

        updatePwdPresenter = new UpdatePwdPresenter();
        updatePwdPresenter.attach(this);
        presenters.add(updatePwdPresenter);

        editOldPw.setOnFocusChangeListener(this);
        editNewPw.setOnFocusChangeListener(this);
        editSurePw.setOnFocusChangeListener(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btnReset)
    public void onClick() {
        updatePwdPresenter.updatePwd(editOldPw.getText().toString(),
                editNewPw.getText().toString(),
                editSurePw.getText().toString());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.editOldPw:
                if (hasFocus) {
                    textOldPwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
                } else {
                    textOldPwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
                }
                break;
            case R.id.editNewPw:
                if (hasFocus) {
                    textNewPwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
                } else {
                    textNewPwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
                }
                break;
            case R.id.editSurePw:
                if (hasFocus) {
                    textSurePwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
                } else {
                    textSurePwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
                }
                break;
        }

    }

    @Override
    public void onSuccess(String msg) {
        ToastUtils.show("修改成功！");
        UserUtils.clearUser(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
