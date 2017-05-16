package cn.zty.recruit.wechat;


import android.util.Log;
import android.util.Xml;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.WeChatPayModel;
import cn.zty.recruit.utils.HostIPUtils;

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
    }

    public void pay(String prepayId, String nonceStr, String timeStamp, String sign) {
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

    /**
     * 下单 sign
     */
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

        String str = stringBuffer.toString();
        return MD5.MD5Encode(str, "UTF-8").toUpperCase();
    }

    /**
     * 统一下单 sign
     */
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

        String str = stringBuffer.toString();
        return MD5.MD5Encode(str, "UTF-8").toUpperCase();
    }

    /**
     * 关闭订单 sign
     */
    public String getCloseOrderSign(String appid,
                                    String mch_id,
                                    String nonce_str,
                                    String out_trade_no) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("appid=").append(appid).append("&")
                .append("mch_id=").append(mch_id).append("&")
                .append("nonce_str=").append(nonce_str).append("&")
                .append("out_trade_no=").append(out_trade_no).append("&")
                .append("key=").append(Keys.SIGN);

        String str = stringBuffer.toString();
        return MD5.MD5Encode(str, "UTF-8").toUpperCase();
    }

    public String createPost(String body, String nonce_str, String out_trade_no, String total_fee) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<xml>")
                .append("<appid>").append(Keys.APP_ID).append("</appid>")
                .append("<body>").append(body).append("</body>")
                .append("<mch_id>").append(Keys.PARTNER_ID).append("</mch_id>")
                .append("<nonce_str>").append(nonce_str).append("</nonce_str>")
                .append("<notify_url>").append(Urls.weChatNotifyUrl).append("</notify_url>")
                .append("<out_trade_no>").append(out_trade_no).append("</out_trade_no>")
                .append("<spbill_create_ip>").append(HostIPUtils.getHostIP()).append("</spbill_create_ip>")
                .append("<total_fee>").append(total_fee).append("</total_fee>")
                .append("<trade_type>").append("APP").append("</trade_type>")
                .append("<sign>").append(getUnifiedOrderSign(Keys.APP_ID, body, Keys.PARTNER_ID, nonce_str, Urls.weChatNotifyUrl, out_trade_no, HostIPUtils.getHostIP(), total_fee, "APP")).append("</sign>")
                .append("</xml>");

        return stringBuffer.toString();
    }

    /**
     * xml解析
     */
    public WeChatPayModel pullParser(String str) {

        WeChatPayModel weChatPayModel = null;
        try {
            XmlPullParser xpp = Xml.newPullParser();
            xpp.setInput(new ByteArrayInputStream(str.getBytes()), "UTF-8");

            // 产生第一个事件
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    // 判断当前事件是否为标签元素开始事件
                    case XmlPullParser.START_TAG:
                        if (xpp.getName().equals("xml")) {
                            weChatPayModel = new WeChatPayModel();
                        } else if (xpp.getName().equals("return_code")) {
                            eventType = xpp.next();
                            weChatPayModel.setResult_code(xpp.getText());
                        } else if (xpp.getName().equals("return_msg")) {
                            eventType = xpp.next();
                            weChatPayModel.setReturn_msg(xpp.getText());
                        } else if (xpp.getName().equals("appid")) {
                            eventType = xpp.next();
                            weChatPayModel.setAppid(xpp.getText());
                        } else if (xpp.getName().equals("mch_id")) {
                            eventType = xpp.next();
                            weChatPayModel.setMch_id(xpp.getText());
                        } else if (xpp.getName().equals("nonce_str")) {
                            eventType = xpp.next();
                            weChatPayModel.setNonce_str(xpp.getText());
                        } else if (xpp.getName().equals("sign")) {
                            eventType = xpp.next();
                            weChatPayModel.setSign(xpp.getText());
                        } else if (xpp.getName().equals("result_code")) {
                            eventType = xpp.next();
                            weChatPayModel.setResult_code(xpp.getText());
                        } else if (xpp.getName().equals("prepay_id")) {
                            eventType = xpp.next();
                            weChatPayModel.setPrepay_id(xpp.getText());
                        } else if (xpp.getName().equals("trade_type")) {
                            eventType = xpp.next();
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
