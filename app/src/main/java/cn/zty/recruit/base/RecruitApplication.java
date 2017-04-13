package cn.zty.recruit.base;

import android.app.Application;
import android.text.TextUtils;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.utils.FontUtils;
import cn.zty.recruit.utils.SharedPrefUtils;
import io.vov.vitamio.Vitamio;

/**
 * Created by zty on 2017/3/4.
 */

public class RecruitApplication extends Application {

    private static RecruitApplication instance;

    private int statusBarHeight;

    private boolean isHaveUser = false;

    private UserModel userModel;
    private String userId;
    private String tokenId;
    private String loginName;

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

//        MyException crashHandler = MyException.getInstance();
//        crashHandler.init(getApplicationContext());
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

}
