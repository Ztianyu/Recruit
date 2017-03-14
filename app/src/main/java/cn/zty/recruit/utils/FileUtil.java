package cn.zty.recruit.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.zty.recruit.pick.SelectPicUtils;

/**
 * 文件操作工具类
 *
 * @author xiaoyang
 */
public class FileUtil {

    /**
     * 默认下载图片文件地址.
     */
    public static String cameraPath = File.separator + "v_f_camera" + File.separator;

    /**
     * 描述：获取默认的图片保存全路径.
     *
     * @return the default image down path dir
     */
    public static String getDefaultImageDownPathDir() {
        String pathDir = null;
        try {
            if (!isCanUseSD()) {
                return null;
            }
            // 初始化图片保存路径
            File fileRoot = Environment.getExternalStorageDirectory();
            File dirFile = new File(fileRoot.getAbsolutePath() + cameraPath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            pathDir = dirFile.getPath();
        } catch (Exception e) {
        }
        return pathDir;
    }

    /**
     * 描述：SD卡是否能用.
     *
     * @return true 可用,false不可用
     */
    public static boolean isCanUseSD() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将bitmap保存到文件中
     *
     * @param file
     * @param bitmap
     * @throws IOException
     */
    public static void saveMyBitmap(File file, Bitmap bitmap) throws IOException {

        if (!SelectPicUtils.PHOTO_CUT_DIR.exists()) {
            SelectPicUtils.PHOTO_CUT_DIR.mkdirs();
        }

        file.createNewFile();
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除此路径名表示的文件或目录。 如果此路径名表示一个目录，则会先删除目录下的内容再将目录删除，所以该操作不是原子性的。
     * 如果目录中还有目录，则会引发递归动作。
     *
     * @param filePath 要删除文件或目录的路径。
     * @return 当且仅当成功删除文件或目录时，返回 true；否则返回 false。
     */
    public static boolean deleteFileByDir(String filePath) {
        if (filePath == null) {
            return true;
        }
        File file = new File(filePath);
        return deleteFile(file);
    }

    private static boolean deleteFile(File file) {
        File[] files = file.listFiles();
        for (File deleteFile : files) {
            if (deleteFile.isDirectory()) {
                // 如果是文件夹，则递归删除下面的文件后再删除该文件夹
                if (!deleteFile(deleteFile)) {
                    // 如果失败则返回
                    return false;
                }
            } else {
                if (!deleteFile.delete()) {
                    // 如果失败则返回
                    return false;
                }
            }
        }
        return file.delete();
    }
}
