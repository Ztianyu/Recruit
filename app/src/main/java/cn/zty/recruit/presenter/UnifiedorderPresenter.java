package cn.zty.recruit.presenter;

import com.lzy.okhttputils.OkHttpUtils;

import cn.zty.recruit.base.Urls;
import cn.zty.recruit.wechat.WeChatPayManager;
import cn.zty.recruit.wechat.XmlCallback;

/**
 * Created by zty on 2017/4/6.
 */

public class UnifiedorderPresenter {

    public UnifiedorderPresenter() {
    }


    public void unifiedorder(String body, String nonce_str, String out_trade_no, String total_fee) {

        OkHttpUtils.post(Urls.weChatPay)
                .tag(this)
                .postString(WeChatPayManager.getInstance().createPost(body, nonce_str, out_trade_no, total_fee))
                .execute(new XmlCallback(0));
    }
}
