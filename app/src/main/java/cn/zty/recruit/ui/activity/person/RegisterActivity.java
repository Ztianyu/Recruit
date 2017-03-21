package cn.zty.recruit.ui.activity.person;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;

/**
 * Created by zty on 2017/3/21.
 */

public class RegisterActivity extends BaseActivity implements View.OnFocusChangeListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editRegisterPw)
    EditText editRegisterPw;
    @BindView(R.id.textRegisterPwTip)
    TextView textRegisterPwTip;
    @BindView(R.id.editRegisterSurePw)
    EditText editRegisterSurePw;
    @BindView(R.id.textRegisterSurePwTip)
    TextView textRegisterSurePwTip;
    @BindView(R.id.editRegisterCode)
    EditText editRegisterCode;
    @BindView(R.id.textRegisterCode)
    TextView textRegisterCode;
    @BindView(R.id.btnRegister)
    TextView btnRegister;

    private String mobile;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mobile = getIntent().getExtras().getString("mobile");

        toolbar.setTitle("设置密码");
        initToolbar(toolbar);

        editRegisterPw.setOnFocusChangeListener(this);
        editRegisterSurePw.setOnFocusChangeListener(this);
        editRegisterCode.setOnFocusChangeListener(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btnRegister)
    public void onClick() {
        finish();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.editRegisterPw:
                if (hasFocus) {
                    textRegisterPwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
                } else {
                    textRegisterPwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
                }
                break;
            case R.id.editRegisterSurePw:
                if (hasFocus) {
                    textRegisterSurePwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
                } else {
                    textRegisterSurePwTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
                }
                break;
            case R.id.editRegisterCode:
                if (hasFocus) {
                    textRegisterCode.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
                } else {
                    textRegisterCode.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
                }
                break;
        }
    }
}
