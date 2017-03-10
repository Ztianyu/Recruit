package cn.zty.recruit.base;

import android.app.Application;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;

import cn.zty.baselib.http.RetrofitHelper;

/**
 * Created by zty on 2017/3/4.
 */

public class RecruitApplication extends Application {

    private static RecruitApplication instance;

    private int statusBarHeight;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        initHttp();
        getStatusHeight();
    }

    public static RecruitApplication getInstance() {
        return instance;
    }

    private void initHttp() {

        RetrofitHelper.initUrl(Urls.HOST);

        //必须调用初始化
        OkHttpUtils.init(this);
        //以下都不是必须的，根据需要自行选择
        OkHttpUtils.getInstance()
                .debug("HttpUtils")
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)    //全局的读取超时时间
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)  //全局的写入超时时间
                .setCookieStore(new PersistentCookieStore());     //cookie持久化存储，如果cookie不过期，则一直有效;
    }

    private void getStatusHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            statusBarHeight = (int) getResources().getDimension(identifier);
        }
    }

    public int getStatusBarHeight() {
        return statusBarHeight;
    }

    public void setStatusBarHeight(int statusBarHeight) {
        this.statusBarHeight = statusBarHeight;
    }
}
