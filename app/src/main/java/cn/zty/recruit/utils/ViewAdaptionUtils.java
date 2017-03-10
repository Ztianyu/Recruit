package cn.zty.recruit.utils;

import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import cn.zty.recruit.base.BaseActivity;


/**
 * Created by zty on 2016/12/26.
 */

public class ViewAdaptionUtils {

    private static int baseWidth = 720;

    private static int baseHeight = 1280;

    /**
     * 父布局是LinearLayout 的高度适配
     */
    public static void LinearLayoutAdaptation(View view, int height) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, (BaseActivity.screenHeight * height / baseHeight));
        view.setLayoutParams(layoutParams);
    }

    /**
     * 父布局是FrameLayout的高度适配
     */
    public static void FrameLayoutAdaptation(View view, int height) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, (BaseActivity.screenHeight * height / baseHeight));
        view.setLayoutParams(layoutParams);
    }

    /**
     * 父布局是FrameLayout的高度适配
     */
    public static void CollapsingToolbarLayoutAdaptation(View view, int height) {
        CollapsingToolbarLayout.LayoutParams layoutParams = new CollapsingToolbarLayout.LayoutParams(
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, (BaseActivity.screenHeight * height / baseHeight));
        view.setLayoutParams(layoutParams);
    }
}
