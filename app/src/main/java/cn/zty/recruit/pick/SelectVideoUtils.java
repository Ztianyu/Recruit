package cn.zty.recruit.pick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.zty.recruit.ui.fragment.SelectVideoFragment;

/**
 * 视频选择
 * Created by zty on 2017/6/27.
 */

public class SelectVideoUtils {
    public static final int CODE_PICK = 0;
    public static final int CODE_TAKE = 1;

    public static int limitTime = 1800;//视频长短（30分钟）
    public static int size = 300;//视频大小（MB）

    public static final File VIDEO_DIR = new File(
            Environment.getExternalStorageDirectory() + "/recruit/video");// 存储视频地址

    public static void showDialog(FragmentManager fm) {
        Fragment fragment = fm.findFragmentByTag("selectVideoFragment");

        if (fragment != null)
            fm.beginTransaction().remove(fragment);

        SelectVideoFragment selectVideoFragment = new SelectVideoFragment();
        selectVideoFragment.show(fm.beginTransaction(), "selectVideoFragment");
    }

    public static void hidePicFragment(FragmentManager fm) {
        DialogFragment fragment = (DialogFragment) fm.findFragmentByTag("selectVideoFragment");
        if (fragment != null)
            fragment.dismiss();
    }

    // 使用系统当前日期加以调整作为视频的名称
    public static String geVideoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'Video'yyyy_MMdd_HHmmss");
        return dateFormat.format(date) + ".mp4";
    }

    public static void selectVideo(Context context) {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        ((Activity) context).startActivityForResult(intent, CODE_PICK);
    }

    public static void recordVideo(Context context) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, size * 1024 * 1024L);//限制录制大小(10M=10 * 1024 * 1024L)
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, limitTime);//限制录制时间(10秒=10)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(VIDEO_DIR, geVideoFileName())));
        ((Activity) context).startActivityForResult(intent, CODE_TAKE);
    }

    public static Bitmap getVideoBitmap(String filePath, int width, int height,
                                        int kind) {
        //定義一個Bitmap對象bitmap；
        Bitmap bitmap = null;

        //ThumbnailUtils類的截取的圖片是保持原始比例的，但是本人發現顯示在ImageView控件上有时候有部分沒顯示出來；
        //調用ThumbnailUtils類的靜態方法createVideoThumbnail獲取視頻的截圖；
        bitmap = ThumbnailUtils.createVideoThumbnail(filePath, kind);

        //調用ThumbnailUtils類的靜態方法extractThumbnail將原圖片（即上方截取的圖片）轉化為指定大小；
        //最後一個參數的具體含義我也不太清楚，因為是閉源的；
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        //放回bitmap对象；
        return bitmap;
    }
}
