package cn.zty.recruit.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

import cn.zty.baselib.utils.MyImageLoader;

/**
 * Created by zty on 2016/12/26.
 */

public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        MyImageLoader.load(context, (String) path, imageView);
    }
}
