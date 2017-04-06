package cn.zty.recruit.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import java.io.File;

import cn.zty.recruit.service.DownloadApkService;

/**
 * Created by zty on 2017/1/6.
 */

public class DownloadUtils {

    public static File file = Environment.getExternalStorageDirectory();

    public static String apk = "/recruit/安装包";

    public static void downApk(Context context, String url, String fileName) {
        Intent updateIntent = new Intent(context, DownloadApkService.class);
        updateIntent.putExtra("downUrl", url);
        updateIntent.putExtra("fileName", fileName);
        context.startService(updateIntent);
    }
}
