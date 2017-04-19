package cn.zty.recruit.wechat;


import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.lzy.okhttputils.callback.StringCallback;

import cn.zty.recruit.bean.WeChatPayModel;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by zty on 2017/4/6.
 */

public class XmlCallback extends StringCallback {
    private int type;//0: 支付；1：取消订单

    public XmlCallback(int type) {
        this.type = type;
    }


    @Override
    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
        WeChatPayModel weChatPayModel = WeChatPayManager.getInstance().pullParser(s);

        if (type == 0) {
            if (!TextUtils.isEmpty(weChatPayModel.getPrepay_id()))
                WeChatPayManager.getInstance().pay(weChatPayModel.getPrepay_id(),
                        WeChatPayManager.getInstance().getNonceStr(),
                        System.currentTimeMillis() / 1000 + "",
                        weChatPayModel.getSign());
        } else {

        }
    }
}
