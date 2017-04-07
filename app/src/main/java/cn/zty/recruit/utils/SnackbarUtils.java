package cn.zty.recruit.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by zty on 2017/4/7.
 */

public class SnackbarUtils {

    public static void showShort(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
