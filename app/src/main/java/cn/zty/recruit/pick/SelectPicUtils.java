package cn.zty.recruit.pick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import cn.zty.recruit.ui.fragment.SelectPicFragment;

/**
 * Created by zty on 2017/1/4.
 */

public class SelectPicUtils {

    public static final int PHOTO_REQUEST_TAKE_PHOTO = 100;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 200;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 300;// 结果

    public static final File PHOTO_DIR = new File(
            Environment.getExternalStorageDirectory() + "/DCIM/Camera");// 存储图片地址

    public static final File PHOTO_CUT_DIR = new File(
            Environment.getExternalStorageDirectory() + "/recruit/icon");// 存储图片地址

    public static File tempFile;

    //获取图片路径
    public static File getFilePath() {
        tempFile = new File(PHOTO_DIR, getPhotoFileName());
        return tempFile;
    }

    public static void showDialog(FragmentManager fm) {
        Fragment fragment = fm.findFragmentByTag("selectPicFragment");

        if (fragment != null)
            fm.beginTransaction().remove(fragment);

        SelectPicFragment commentFragment = new SelectPicFragment();
        commentFragment.show(fm.beginTransaction(), "selectPicFragment");
    }

    public static void hidePicFragment(FragmentManager fm) {
        DialogFragment fragment = (DialogFragment) fm.findFragmentByTag("selectPicFragment");
        if (fragment != null)
            fragment.dismiss();
    }


    public static void takePhoto(Context context) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        // 指定调用相机拍照后照片的储存路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getFilePath()));
        ((Activity) context).startActivityForResult(intent, PHOTO_REQUEST_TAKE_PHOTO);

    }

    /**
     * 选择照片
     */
    public static void pickPicture(Context context) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((Activity) context).startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    // 使用系统当前日期加以调整作为照片的名称
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'yyyy_MMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    // 裁剪之后图片的文件名
    public static String setHeaderFileName() {
        return UUID.randomUUID().toString() + ".jpg";
    }


    private static int size = 200;

    /**
     * 对图片进行裁剪
     */
    public static void startPhotoZoom(Context context, Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);

        ((Activity) context).startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}
