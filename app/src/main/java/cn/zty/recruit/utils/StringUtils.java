package cn.zty.recruit.utils;

/**
 * Created by zty on 2017/3/24.
 */

public class StringUtils {

    /**
     * 去掉括号中内容
     */
    public static String replace(String str) {
        return str.replaceAll("\\(.*?\\)", "");
    }
}
