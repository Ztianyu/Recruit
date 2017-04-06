package cn.zty.recruit.presenter;

import android.util.Xml;

import com.google.gson.GsonBuilder;
import com.lzy.okhttputils.OkHttpUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.WeChatPayModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.UnifiedorderService;
import cn.zty.recruit.utils.HostIPUtils;
import cn.zty.recruit.view.StringView;
import cn.zty.recruit.wechat.Keys;
import cn.zty.recruit.wechat.MD5;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zty on 2017/4/6.
 */

public class UnifiedorderPresenter extends IBasePresenter<StringView> {

    UnifiedorderService service;

    public UnifiedorderPresenter() {
        service = new Retrofit.Builder()
                .baseUrl(Urls.weChatPay)
                .client(OkHttpUtils.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(UnifiedorderService.class);
    }

    private String getNonceStr() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public void unifiedorder(String body, String nonce_str, String out_trade_no, String total_fee) {

        RequestParams params = new RequestParams();
        params.put("appid", Keys.APP_ID);
        params.put("mch_id", Keys.PARTNER_ID);
        params.put("nonce_str", nonce_str);
        params.put("sign", Keys.SIGN);
        params.put("body", body);
        params.put("out_trade_no", out_trade_no);
        params.put("total_fee", total_fee);
        params.put("spbill_create_ip", HostIPUtils.getHostIP());
        params.put("notify_url", "");
        params.put("trade_type", "APP");

        mSubscription = RxManager.getInstance().doSubscribe(service.unifiedorder(params), new RxSubscriber<String>() {
            @Override
            protected void _onNext(String s) {
                mView.onSuccess(s);
            }
        });
    }

    private String getSign(String appid, String body, String mch_id, String nonce_str, String out_trade_no, String total_fee) {

        return MD5.EncoderByMd5("");
    }

    public WeChatPayModel pullParser(String str) {

        WeChatPayModel weChatPayModel = null;

        XmlPullParser xpp = Xml.newPullParser();
        try {
            xpp.setInput(new ByteArrayInputStream(str.getBytes("UTF-8")), "UTF-8");

            // 产生第一个事件
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    // 判断当前事件是否为文档开始事件
                    case XmlPullParser.START_DOCUMENT:
                        weChatPayModel = new WeChatPayModel();
                        break;
                    // 判断当前事件是否为标签元素开始事件
                    case XmlPullParser.START_TAG:
                        if (xpp.getName().equals("return_code")) { // 判断开始标签元素是否是book
                            weChatPayModel.setResult_code(xpp.getText());
                        } else if (xpp.getName().equals("return_msg")) {
                            weChatPayModel.setReturn_msg(xpp.getText());
                        } else if (xpp.getName().equals("appid")) {
                            weChatPayModel.setAppid(xpp.getText());
                        } else if (xpp.getName().equals("mch_id")) {
                            weChatPayModel.setMch_id(xpp.getText());
                        } else if (xpp.getName().equals("nonce_str")) {
                            weChatPayModel.setNonce_str(xpp.getText());
                        } else if (xpp.getName().equals("sign")) {
                            weChatPayModel.setSign(xpp.getText());
                        } else if (xpp.getName().equals("result_code")) {
                            weChatPayModel.setResult_code(xpp.getText());
                        } else if (xpp.getName().equals("prepay_id")) {
                            weChatPayModel.setPrepay_id(xpp.getText());
                        } else if (xpp.getName().equals("trade_type")) {
                            weChatPayModel.setTrade_type(xpp.getText());
                        }
                        break;
                    // 判断当前事件是否为标签元素结束事件
                    case XmlPullParser.END_TAG:
                        break;
                }
                // 进入下一个元素并触发相应事件
                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weChatPayModel;
    }
}
