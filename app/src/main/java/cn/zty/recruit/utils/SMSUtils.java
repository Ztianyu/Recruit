package cn.zty.recruit.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import cn.zty.baselib.utils.ValidateUtil;
import cn.zty.recruit.R;

/**
 * Created by zty on 2017/3/6.
 */

public class SMSUtils {

    static int i = 30;
    static TextView btnSendCode;

    static int enable = R.drawable.bg_get_code;

    static int unEnable = R.drawable.bg_no_get_code;

    /**
     * 发送验证码按钮显示信息 获取验证码------>重新发送(i)
     */
    static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                btnSendCode.setText("重新发送(" + i + ")");
            } else {
                if (msg.what == -8) {
                    btnSendCode.setText("获取验证码");
                    btnSendCode.setClickable(true);
                    btnSendCode.setBackgroundResource(enable);
                    i = 30;
                }
            }
        }
    };

    /**
     * 点击发送验证码
     */
    public static void getCode(Context context, TextView btn, String phone) {

        btnSendCode = btn;
        i = 30;

        // 1. 通过规则判断手机号
        if (!ValidateUtil.isPhone(phone)) {
            return;
        }
//        // 2. 通过sdk发送短信验证
//        AppLoginService.getCode(context, phone);

        // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
        btn.setClickable(false);
        btn.setBackgroundResource(unEnable);
        btn.setText("重新发送(" + i + ")");

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
}
