package cn.zty.recruit.utils;

import android.content.Context;

import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.LoginModel;


/**
 * Created by zty on 2017/2/23.
 */

public class UserUtils {

    public static void saveUser(Context context, LoginModel model, String loginName) {

        SharedPrefUtils.setString(context, SharedPrefUtils.USER_ID, model.getUserId());
        SharedPrefUtils.setString(context, SharedPrefUtils.TOKEN_ID, model.getUserId());
        SharedPrefUtils.setString(context, SharedPrefUtils.LOGIN_NAME, loginName);

        RecruitApplication.getInstance().setLoginName(loginName);
        RecruitApplication.getInstance().setUserId(model.getUserId());
        RecruitApplication.getInstance().setTokenId(model.getTokenId());
    }

    public static void clearUser(Context context) {

        SharedPrefUtils.setString(context, SharedPrefUtils.USER_ID, "");
        SharedPrefUtils.setString(context, SharedPrefUtils.TOKEN_ID, "");

        RecruitApplication.getInstance().setUserId("");
        RecruitApplication.getInstance().setTokenId("");
        RecruitApplication.getInstance().clearUser();
    }
}
