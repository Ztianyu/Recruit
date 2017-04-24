package cn.zty.recruit.utils;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;

/**
 * Created by zty on 2016/12/26.
 */

public class BannerUtils {

    public static void initBanner(Banner banner, List<String> images,List<String> titles, int type, int position) {

        //设置banner样式
        if (type == 0) {//数字指示器
            banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        }
        if (type == 1) {
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        }

        //设置图片加载器
        banner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        banner.update(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(4000);
        //设置指示器位置（当banner模式中有指示器时）
        if (position == 1) {
            banner.setIndicatorGravity(BannerConfig.CENTER);
        }
        if (position == 2) {
            banner.setIndicatorGravity(BannerConfig.RIGHT);
        }

        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    public static void initBanner1(Banner banner, List<Integer> images, int type, int position) {

        //设置banner样式
        if (type == 0) {//数字指示器
            banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        }
        if (type == 1) {
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        }

        //设置图片加载器
        banner.setImageLoader(new BannerImageLoader1());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        if (position == 1) {
            banner.setIndicatorGravity(BannerConfig.CENTER);
        }
        if (position == 2) {
            banner.setIndicatorGravity(BannerConfig.RIGHT);
        }

        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }
}
