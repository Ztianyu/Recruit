package cn.zty.recruit.base;

import cn.zty.baselib.http.RequestParams;


/**
 * Created by zty on 2017/2/21.
 */

public class RequestParamsHelper {

    private static RequestParamsHelper helper;

    public static RequestParamsHelper getInstance() {
        if (helper == null)
            helper = new RequestParamsHelper();
        return helper;
    }

    public RequestParamsHelper() {
    }


    public RequestParams getRequestParams() {
        RequestParams params = new RequestParams();
        return params;
    }
}
