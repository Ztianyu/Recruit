package cn.zty.recruit.utils;

import android.content.Context;

public class SharedPrefUtils {

    /**
     * SharedPreferences xml 名称
     */
    private static final String APP_SHARED_STR = "recruit_Pref";

    public static final String USER_ID = "userId";
    public static final String TOKEN_ID = "tokenId";

    public static final String LOGIN_NAME = "loginName";

    public static final String inviteCode = "inviteCode";

    public static final String schoolSelectArea = "schoolProvinceId";
    public static final String schoolNature = "schoolNature";
    public static final String schoolType = "schoolType";
    public static final String schoolSelectDiscipline = "schoolDiscipline";
    public static final String schoolSelectMajor = "schoolMajorId";
    public static final String schoolSelectTuition = "schoolTuitionType";

    public static final String studySelectArea = "studyProvinceId";
    public static final String studySchoolType = "studySchoolType";
    public static final String studyType = "studyType";
    public static final String studySelectDiscipline = "studyDiscipline";
    public static final String studySelectMajor = "studyMajorId";
    public static final String studySelectTuition = "studyTuitionType";


    /**
     * 从SharedPreferences 获取一个boolean值，默认为false
     */
    public static boolean getBoolean(Context context, String key) {
        return context.getSharedPreferences(APP_SHARED_STR,
                Context.MODE_PRIVATE).getBoolean(key, false);
    }

    /**
     * 设置 一个boolean 值到SharedPreferences
     */
    public static synchronized void setBoolean(Context context, String key,
                                               boolean value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putBoolean(key, value).commit();
    }

    /**
     * 从SharedPreferences 获取一个String值，默认为null
     */
    public static String getString(Context context, String key) {
        return context.getSharedPreferences(APP_SHARED_STR,
                Context.MODE_PRIVATE).getString(key, null);
    }

    /**
     * 设置 一个String 值到SharedPreferences
     */
    public static synchronized void setString(Context context, String key,
                                              String value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putString(key, value).commit();
    }

    /**
     * 更新 一个String 值到SharedPreferences
     */
    public static synchronized void updateString(Context context, String key,
                                                 String value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putString(key, value).apply();
    }

    /**
     * 从SharedPreferences 获取一个int值，默认为null
     */
    public static int getInt(Context context, String key) {
        return context.getSharedPreferences(APP_SHARED_STR,
                Context.MODE_PRIVATE).getInt(key, ~0);
    }

    /**
     * 设置 一个int 值到SharedPreferences
     */
    public static synchronized void setInt(Context context, String key,
                                           int value) {
        context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE)
                .edit().putInt(key, value).commit();
    }


}
