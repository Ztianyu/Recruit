package cn.zty.baselib.utils;

import android.text.TextUtils;

/**
 * Created by zty on 2017/3/29.
 */

public class MyTextUtils {
    public static String notNullStr(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }
}
