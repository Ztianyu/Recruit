package cn.zty.recruit.base;

import android.app.Application;
import android.text.TextUtils;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import com.lzy.okhttputils.model.HttpHeaders;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.utils.FontUtils;
import cn.zty.recruit.utils.SharedPrefUtils;
import cn.zty.recruit.wxapi.Keys;
import io.vov.vitamio.Vitamio;

/**
 * Created by zty on 2017/3/4.
 */

public class RecruitApplication extends Application implements NetBroadcastReceiver.NetEvent {

    private static RecruitApplication instance;

    private int statusBarHeight;

    private boolean isHaveUser = false;

    private UserModel userModel;
    private String userId;
    private String tokenId;
    private String loginName;

    private IWXAPI api;

    private NetBroadcastReceiver.NetEvent event;

    private int netMobile;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        initHttp();
        getStatusHeight();

        Vitamio.isInitialized(this);

        setCurrentUser();

        FontUtils.setDefaultFont(this, "DEFAULT", "fonts/myFont.ttf");
        FontUtils.setDefaultFont(this, "MONOSPACE", "fonts/myFont.ttf");
        FontUtils.setDefaultFont(this, "SERIF", "fonts/myFont.ttf");
        FontUtils.setDefaultFont(this, "SANS_SERIF", "fonts/myFont.ttf");

        api = WXAPIFactory.createWXAPI(this, null);
        api.registerApp(Keys.APP_ID);

        MyException crashHandler = MyException.getInstance();
        crashHandler.init(getApplicationContext());

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        event = this;
    }

    public static RecruitApplication getInstance() {
        return instance;
    }

    private void initHttp() {

        RetrofitHelper.initUrl(Urls.HOST);

        //必须调用初始化
        OkHttpUtils.init(this);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put(httpHeaders.HEAD_KEY_ACCEPT, "application/json");
        httpHeaders.put(httpHeaders.HEAD_KEY_CONTENT_TYPE, "application/json");

        OkHttpUtils.getInstance()
                .debug("HttpUtils")
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)    //全局的读取超时时间
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)  //全局的写入超时时间
                .setCookieStore(new PersistentCookieStore())
                .setCacheMode(CacheMode.DEFAULT)
                .addCommonHeaders(httpHeaders);

    }

    private void setCurrentUser() {
        userId = SharedPrefUtils.getString(this, SharedPrefUtils.USER_ID);
        tokenId = SharedPrefUtils.getString(this, SharedPrefUtils.TOKEN_ID);
        loginName = SharedPrefUtils.getString(this, SharedPrefUtils.LOGIN_NAME);

        if (!TextUtils.isEmpty(userId))
            setHaveUser(true);
    }

    public void clearUser() {
        this.userModel = null;
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


    public boolean isHaveUser() {
        return isHaveUser;
    }

    public void setHaveUser(boolean haveUser) {
        isHaveUser = haveUser;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public IWXAPI getApi() {
        return api;
    }

    public void setApi(IWXAPI api) {
        this.api = api;
    }

    public NetBroadcastReceiver.NetEvent getEvent() {
        return event;
    }

    public void setEvent(NetBroadcastReceiver.NetEvent event) {
        this.event = event;
    }

    public int getNetMobile() {
        return netMobile;
    }

    public void setNetMobile(int netMobile) {
        this.netMobile = netMobile;
    }

    @Override
    public void onNetChange(int netMobile) {
        this.netMobile = netMobile;
    }
}
