package cn.zty.recruit.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.recruit.bean.OrderModel;
import cn.zty.recruit.listener.PayListener;
import cn.zty.recruit.utils.ToastUtils;


/**
 * 支付宝支付接口
 */
public class Payment {

    private static final int SDK_PAY_FLAG = 1;

    private Activity mContext;

    private PayModel payModel;

    private PayListener payListener;

    public Payment(Activity context, PayListener payListener) {
        mContext = context;
        this.payListener = payListener;
    }

    public void payNow() {

        boolean rsa2 = (Keys.RSA2_PRIVATE.length() > 0);

        PayTask payTask = new PayTask(mContext);
        String version = payTask.getVersion();

        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(Keys.APP_ID, payModel, rsa2, version);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String sign = OrderInfoUtil2_0.getSign(params, Keys.RSA2_PRIVATE, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {

                PayTask alipay = new PayTask(mContext);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        payListener.onPaySuccess();
                        ToastUtils.show("支付成功");
                    } else {
                        ToastUtils.show("支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    public void setPayModel(OrderModel orderModel) {
        payModel = new PayModel();

        String orderState = orderModel.getState();
        if (orderState.equals("0")) {
            payModel.setOut_trade_no(orderModel.getOrderCode());
            payModel.setTotal_amount(orderModel.getDeposit() + "");
        } else if (orderState.equals("1")) {
            payModel.setOut_trade_no(orderModel.getId());
            payModel.setTotal_amount(orderModel.getActualPayment() + "");
        }
//        payModel.setTotal_amount("0.01");
        payModel.setBody(orderModel.getCourseNm());
        payModel.setSubject("报名：" + orderModel.getCourseNm() + " " + MyTextUtils.notNullStr(orderModel.getDepartmentNm()));
    }
}
