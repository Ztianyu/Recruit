package cn.zty.recruit.wechat;


import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.UUID;

import cn.zty.recruit.base.RecruitApplication;

/**
 * Created by zty on 2017/4/5.
 */

public class WeChatPayManager {

    private static WeChatPayManager instance = null;

    private IWXAPI api;

    public static WeChatPayManager getInstance() {
        if (instance == null)
            instance = new WeChatPayManager();
        return instance;
    }

    public WeChatPayManager() {
        api = WXAPIFactory.createWXAPI(RecruitApplication.getInstance().getApplicationContext(), Keys.APP_ID);
        api.registerApp(Keys.APP_ID);
    }

    public void pay(String prepayId, String nonceStr, String timeStamp) {
        PayReq request = new PayReq();
        request.appId = Keys.APP_ID;
        request.partnerId = Keys.PARTNER_ID;
        request.prepayId = prepayId;
        request.nonceStr = nonceStr;
        request.timeStamp = timeStamp;
        request.packageValue = "Sign=WXPay";
        request.sign = getPaySign(Keys.APP_ID, nonceStr, "Sign=WXPay", Keys.PARTNER_ID, prepayId, timeStamp);
        api.sendReq(request);
    }

    public String getNonceStr() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public String getPaySign(String appId,
                             String nonceStr,
                             String packageValue,
                             String partnerId,
                             String prepayId,
                             String timeStamp) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("appid=").append(appId).append("&")
                .append("noncestr=").append(nonceStr).append("&")
                .append("package=").append(packageValue).append("&")
                .append("partnerid=").append(partnerId).append("&")
                .append("prepayid=").append(prepayId).append("&")
                .append("timestamp=").append(timeStamp).append("&")
                .append("key=").append(Keys.SIGN);

        return MD5.EncoderByMd5(stringBuffer.toString()).toUpperCase();
    }

    public String getUnifiedOrderSign(String appid,
                                      String body,
                                      String mch_id,
                                      String nonce_str,
                                      String notify_url,
                                      String out_trade_no,
                                      String spbill_create_ip,
                                      String total_fee,
                                      String trade_type) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("appid=").append(appid).append("&")
                .append("body=").append(body).append("&")
                .append("mch_id=").append(mch_id).append("&")
                .append("nonce_str=").append(nonce_str).append("&")
                .append("notify_url=").append(notify_url).append("&")
                .append("out_trade_no=").append(out_trade_no).append("&")
                .append("spbill_create_ip=").append(spbill_create_ip).append("&")
                .append("total_fee=").append(total_fee).append("&")
                .append("trade_type=").append(trade_type).append("&")
                .append("key=").append(Keys.SIGN);

        return MD5.EncoderByMd5(stringBuffer.toString()).toUpperCase();
    }
}
