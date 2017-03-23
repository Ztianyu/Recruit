package cn.zty.recruit.utils;

import android.content.Context;
import android.content.Intent;

import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.LoginModel;


/**
 * Created by zty on 2017/2/23.
 */

public class UserUtils {

    public static void saveUser(Context context, LoginModel model, String loginName, String passWord) {

        SharedPrefUtils.setString(context, SharedPrefUtils.USER_ID, model.getUserId());
        SharedPrefUtils.setString(context, SharedPrefUtils.TOKEN_ID, model.getUserId());
        SharedPrefUtils.setString(context, SharedPrefUtils.LOGIN_NAME, loginName);
        SharedPrefUtils.setString(context, SharedPrefUtils.LOGIN_PASSWORD, passWord);

        RecruitApplication.getInstance().setLoginName(loginName);
        RecruitApplication.getInstance().setUserId(model.getUserId());
        RecruitApplication.getInstance().setTokenId(model.getTokenId());
    }

    public static void clearUser(Context context) {

        SharedPrefUtils.setString(context, SharedPrefUtils.USER_ID, "");
        SharedPrefUtils.setString(context, SharedPrefUtils.TOKEN_ID, "");
        SharedPrefUtils.setString(context, SharedPrefUtils.USER_MESSAGE, "");
        SharedPrefUtils.setString(context, SharedPrefUtils.LOGIN_PASSWORD, "");

        RecruitApplication.getInstance().setLoginName("");
        RecruitApplication.getInstance().setUserId("");
        RecruitApplication.getInstance().setUserId("");
        RecruitApplication.getInstance().clearUser();
    }
}
