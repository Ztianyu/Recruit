package cn.zty.recruit.wechat;

import android.content.Context;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.UUID;

import cn.zty.baselib.utils.TimeUtils;
import okhttp3.internal.Util;

/**
 * Created by zty on 2017/4/5.
 */

public class WeChatPayManager {

    private static WeChatPayManager instance = null;

    private Context context;

    private IWXAPI api;

    public static WeChatPayManager getInstance(Context context) {
        if (instance == null)
            instance = new WeChatPayManager(context);
        return instance;
    }

    public WeChatPayManager(Context context) {
        this.context = context;
        api = WXAPIFactory.createWXAPI(context, Keys.APP_ID);
    }

    public void pay(String nonceStr, String timeStamp) {
        PayReq request = new PayReq();
        request.appId = Keys.APP_ID;
        request.partnerId = Keys.PARTNER_ID;
        request.prepayId = "wx2016112309404508902deea40216174377";
        request.nonceStr = nonceStr;
        request.timeStamp = timeStamp;
        request.packageValue = "Sign=WXPay";
        request.sign = MD5.EncoderByMd5("").toUpperCase();
        api.sendReq(request);
    }
}
