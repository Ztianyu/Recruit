package cn.zty.baselib.bean;

/**
 * Created by zty on 2017/3/1.
 */

public class ResultHeader {


    /**
     * msg : 成功
     * ret : 0
     */

    private String msg;
    private int ret;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }
}
