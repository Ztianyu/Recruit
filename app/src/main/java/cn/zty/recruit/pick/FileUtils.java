package cn.zty.recruit.pick;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import cn.zty.recruit.utils.ToastUtils;

public class FileUtils {

    /**
     * 文件是否存在
     *
     * @param context
     * @param fileName
     * @return
     */
    public static boolean exists(Context context, String fileName) {
        return new File(context.getFilesDir(), fileName).exists();
    }

    /**
     * 读取文本数据
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readFile(Context context, String fileName) {
        InputStream fis = null;
        String content = null;
        try {
            fis = context.getResources().getAssets().open(fileName);
            if (fis != null) {

                byte[] buffer = new byte[1024];
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int readLength = fis.read(buffer);
                    if (readLength == -1)
                        break;
                    arrayOutputStream.write(buffer, 0, readLength);
                }
                fis.close();
                arrayOutputStream.close();
                content = new String(arrayOutputStream.toByteArray());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            content = null;
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return content;
    }

    public static String getRealPath(Context mContext, Uri fileUrl) {
        String fileName = null;
        Uri filePathUri = fileUrl;
        if (fileUrl != null) {
            if (fileUrl.getScheme().toString().compareTo("content") == 0) // content://开头的uri
            {

                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = mContext.getContentResolver().query(fileUrl,
                        proj, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int column_index = cursor
                            .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                    fileName = cursor.getString(column_index); // 取出文件路径

                    cursor.close();
                } else {
                    ToastUtils.show("图片路径错误");
                }
            } else if (fileUrl.getScheme().toString().compareTo("file") == 0) // file:///开头的uri
            {
//                fileName = filePathUri.toString();
                fileName = filePathUri.toString().replace("file://", "");
            } else {
                ToastUtils.show("图片路径错误");
            }
        }
        Log.i("fileName", fileName + "");
        return Uri.decode(fileName);
    }

    /**
     * 获取指定文件大小
     */
    public static String getFileSize(String file) {
        long size = 0;
        try {
            FileInputStream fis = new FileInputStream(file);
            size = fis.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.format("%.2f", (double) size / 1024 / 1024) + "MB";
    }
}
