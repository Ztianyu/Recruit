package cn.zty.recruit.pay;

/**
 * 支付宝 商品数据
 * Created by zty on 2017/4/11.
 */

public class PayModel {

    private String out_trade_no;
    private String body;
    private String subject;
    private String total_amount;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    @Override
    public String toString() {
        return "{\"timeout_express\":\"30m\"," +
                "\"product_code\":\"QUICK_MSECURITY_PAY\"," +
                "\"total_amount\":\"" + total_amount + "\"," +
                "\"subject\":\"" + subject + "\"," +
                "\"body\":\"" + body + "\"," +
                "\"out_trade_no\":\"" + out_trade_no + "\"}";
    }
}
