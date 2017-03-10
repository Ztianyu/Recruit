package cn.zty.recruit.utils;

import android.widget.Toast;

import cn.zty.recruit.base.RecruitApplication;

public class ToastUtils {

    public static void show(String text) {
        Toast.makeText(RecruitApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String text) {
        Toast.makeText(RecruitApplication.getInstance(), text, Toast.LENGTH_LONG).show();
    }
}

