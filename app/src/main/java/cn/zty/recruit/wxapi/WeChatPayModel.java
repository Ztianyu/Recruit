package cn.zty.recruit.wxapi;


/**
 * Created by zty on 2017/4/6.
 */

public class WeChatPayModel {

//   <xml>
//  <return_code><![CDATA[SUCCESS]]></return_code>
//  <return_msg><![CDATA[OK]]></return_msg>
//  <appid><![CDATA[wxdf5a5e8166b65d8e]]></appid>
//  <mch_id><![CDATA[1413719303]]></mch_id>
//  <nonce_str><![CDATA[cCKl1CPUKbc1Viu1]]></nonce_str>
//  <sign><![CDATA[A457DA342972CE8EB86C9BFA46BDFC86]]></sign>
//  <result_code><![CDATA[SUCCESS]]></result_code>
//  <prepay_id><![CDATA[wx20161123112638700583976e0487596811]]></prepay_id>
//  <trade_type><![CDATA[APP]]></trade_type>
//</xml>

    private String return_code;
    private String return_msg;
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String result_code;
    private String prepay_id;
    private String trade_type;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    @Override
    public String toString() {
        return "{return_code=" + return_code + "," +
                "return_msg=" + return_msg + "," +
                "appid=" + appid + "," +
                "mch_id=" + mch_id + "," +
                "nonce_str=" + nonce_str + "," +
                "sign=" + sign + "," +
                "result_code=" + result_code + "," +
                "prepay_id=" + prepay_id + "," +
                "trade_type=" + trade_type + ",}";
    }
}
