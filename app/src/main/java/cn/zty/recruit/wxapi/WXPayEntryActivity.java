package cn.zty.recruit.wxapi;

import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cn.zty.baselib.utils.AppManager;
import cn.zty.recruit.R;
import cn.zty.recruit.ui.activity.PayActivity;
import cn.zty.recruit.ui.activity.person.OrderActivity;
import cn.zty.recruit.ui.activity.person.OrderDetailActivity;
import cn.zty.recruit.utils.ToastUtils;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, Keys.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case 0:
                    AppManager.getInstance().finishActivity(OrderActivity.class);
                    AppManager.getInstance().finishActivity(OrderDetailActivity.class);
                    AppManager.getInstance().finishActivity(PayActivity.class);
                    startActivity(new Intent(this, OrderActivity.class));
                    finish();
                    ToastUtils.show("支付成功");
                    break;
                case -1:
//                    OkHttpUtils.post(Urls.colseOrder)
//                            .tag(this)
//                            .postString(WeChatPayManager.getInstance().getCloseOrderSign(Keys.APP_ID, Keys.PARTNER_ID, WeChatPayManager.getInstance().getNonceStr(), ""))
//                            .execute(new XmlCallback(1));
//                    break;
                case -2:
                    OrderActivity.page = 0;
                    finish();
                    ToastUtils.show("支付失败");
                    break;
            }
        }
    }

}