package cn.zty.recruit.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;

import java.io.File;

import cn.zty.recruit.R;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import static cn.zty.recruit.utils.DownloadUtils.apk;
import static cn.zty.recruit.utils.DownloadUtils.file;


/**
 * Created by zty on 2017/2/18.
 */

public class DownloadApkService extends Service {

    private static final int DOWN_OK = 1; // 下载完成
    private static final int DOWN_ERROR = 0;

    private String downUrl;
    private String fileName;

    // 点击查看
    private Intent intent = null;
    private PendingIntent pendingIntent = null;

    // 通知栏消息
    private int notificationID = 1001;
    private NotificationCompat.Builder builder;
    private NotificationManager messageNotificationManager = null;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            downUrl = intent.getStringExtra("downUrl");
            fileName = intent.getStringExtra("fileName");
            OkHttpUtils.get(downUrl)
                    .tag(this)
                    .execute(new DownloadApkService.DownloadFileCallBack(file + apk, fileName + ".apk"));//保存到sd卡
            // 初始化
            builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("下载")
                    .setContentText("正在下载")
                    .setAutoCancel(true);

            messageNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            showNotification();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showNotification() {
        intent = new Intent();
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        messageNotificationManager.notify(notificationID, builder.build());
    }

    /***
     * 更新UI
     */
    Handler handler = new Handler() {
        @SuppressWarnings("deprecation")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_OK:
                    intent = new Intent();
                    builder.setContentTitle("下载完成");
                    builder.setContentText("请点击安装");
                    builder.setProgress(100, 100, false);
                    messageNotificationManager.notify(notificationID, builder.build());

                    File apkFile = new File(msg.getData().getString("fileName"));
                    if (apkFile.exists()) {
                        Uri uri = Uri.fromFile(apkFile);
                        Intent openIntent = new Intent(Intent.ACTION_VIEW);
                        openIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        openIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                        startActivity(openIntent);
                    }
                    break;
                case DOWN_ERROR:
                    builder.setContentTitle("下载失败");
                    builder.setContentText("失败");
                    messageNotificationManager.notify(notificationID, builder.build());
                    stopService(intent);
                    break;
                default:
                    stopService(intent);
                    messageNotificationManager.cancel(notificationID);
                    break;
            }
        }
    };

    public class DownloadFileCallBack extends FileCallback {

        public DownloadFileCallBack(@NonNull String destFileDir, @NonNull String destFileName) {
            super(destFileDir, destFileName);
        }

        @Override
        public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
            Message message = handler.obtainMessage();
            message.what = DOWN_OK;
            Bundle bundle = new Bundle();
            if (file != null && file.exists())
                bundle.putString("fileName", file.getPath());
            message.setData(bundle);
            handler.sendMessage(message);
        }

        @Override
        public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
            super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
            Message message = progressHandler.obtainMessage();
            message.arg1 = (int) (100 * currentSize / totalSize);
            Log.e("file", (int) (100 * currentSize / totalSize) + "");
//            Bundle bundle = new Bundle();
//            bundle.putDouble("currentSize", (double) currentSize / 1024 / 1024);
//            bundle.putDouble("totalSize", (double) totalSize / 1024 / 1024);
//            message.setData(bundle);
            progressHandler.sendMessage(message);
        }

        @Override
        public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
            super.onError(isFromCache, call, response, e);
            Message message = handler.obtainMessage();
            message.what = DOWN_ERROR;
            handler.sendMessage(message);
        }
    }

    Handler progressHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int progress = msg.arg1;
//            Bundle bundle = msg.getData();
//            double d1 = bundle.getDouble("currentSize");
//            double d2 = bundle.getDouble("totalSize");
            builder.setProgress(100, progress, false);
            messageNotificationManager.notify(notificationID, builder.build());
            builder.setContentText(progress + "%");
//            builder.setContentText(String.format("%.2f", d1) + "/" + String.format("%.2f", d2));
        }
    };
}
