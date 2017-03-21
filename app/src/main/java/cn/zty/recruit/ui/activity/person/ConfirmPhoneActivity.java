package cn.zty.recruit.ui.activity.person;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.baselib.utils.ValidateUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.utils.ToastUtils;

/**
 * Created by zty on 2017/3/20.
 */

public class ConfirmPhoneActivity extends BaseActivity implements View.OnFocusChangeListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editRegisterPhone)
    EditText editRegisterPhone;
    @BindView(R.id.textRegisterPhoneTip)
    TextView textRegisterPhoneTip;
    @BindView(R.id.editRegisterCode)
    EditText editRegisterCode;
    @BindView(R.id.btnGetCode)
    TextView btnGetCode;
    @BindView(R.id.textRegisterCodeTip)
    TextView textRegisterCodeTip;
    @BindView(R.id.btnConfirmPhone)
    TextView btnConfirmPhone;

    private int type;//0:注册；1：忘记密码

    @Override
    protected int initLayoutId() {
        return R.layout.activity_confirm_phone;
    }

    @Override
    protected void initView() {
        type = getIntent().getIntExtra("type", 0);
        if (type == 0) {
            toolbar.setTitle("注册");
        } else {
            toolbar.setTitle("忘记密码");
        }
        initToolbar(toolbar);

        editRegisterPhone.setOnFocusChangeListener(this);
        editRegisterCode.setOnFocusChangeListener(this);

        SMSSDK.initSDK(this, Constants.MOB_APP_KEY, Constants.MOB_APP_SECRET);
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btnGetCode, R.id.btnConfirmPhone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGetCode:
                if (ValidateUtil.isPhone(editRegisterPhone.getText().toString())) {
                    SMSSDK.getVerificationCode("86", editRegisterPhone.getText().toString());
                } else {
                    ToastUtils.show("请输入正确的手机号码");
                }
                break;
            case R.id.btnConfirmPhone:
                finish();
                if (type == 0) {
                    startActivity(new Intent(this, RegisterActivity.class).putExtra("mobile", editRegisterPhone.getText().toString()));
                } else {
                    startActivity(new Intent(this, SetNewPwActivity.class).putExtra("mobile", editRegisterPhone.getText().toString()));
                }
                break;
        }
    }

    private void sendCode() {
        btnGetCode.setClickable(false);
        btnGetCode.setBackgroundResource(R.drawable.bg_no_get_code);
        btnGetCode.setText("重新发送(" + i + ")");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i > 0; i--) {
                    handler.sendEmptyMessage(-9);
                    if (i <= 0) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(-8);
            }
        }).start();
    }

    int i = 30;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == -9) {
                btnGetCode.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                btnGetCode.setBackgroundResource(R.drawable.bg_get_code);
                btnGetCode.setText("获取验证码");
                btnGetCode.setClickable(true);
                i = 30;
            }
        }
    };

    EventHandler eventHandler = new EventHandler() {

        @Override
        public void afterEvent(int event, int result, Object data) {
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            codeHandler.sendMessage(msg);
        }
    };

    Handler codeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            //回调完成
            if (result == SMSSDK.RESULT_COMPLETE) {
                //提交验证码成功
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //获取验证码成功
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    sendCode();
                    //返回支持发送验证码的国家列表
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {

                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.editRegisterPhone:
                if (hasFocus) {
                    textRegisterPhoneTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
                } else {
                    textRegisterPhoneTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
                }
                break;
            case R.id.editRegisterCode:
                if (hasFocus) {
                    textRegisterCodeTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.colorAccent));
                } else {
                    textRegisterCodeTip.setBackgroundColor(ResourceUtil.resToColor(this, R.color.gray));
                }
                break;
        }
    }

}
