package cn.zty.recruit.manager;


import java.io.File;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.recruit.bean.LoadFileModel;
import cn.zty.recruit.service.UploadService;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by zty on 2017/2/24.
 */

public class LoadFileManager {

    public static final String healthArchives = "healthArchives";
    public static final String proofPicture = "proofPicture";

    private UploadService uploadService;

    public LoadFileManager() {
        this.uploadService = RetrofitHelper.getInstance().getRetrofit().create(UploadService.class);
    }

    public Observable<ResultBean<LoadFileModel>> load(File file, String path) {
        return uploadService.load(file2Part(file), path);
    }

    public MultipartBody.Part file2Part(File file) {

        // 根据类型及File对象创建RequestBody（okhttp的类）
        RequestBody requestBody = RequestBody.create(MultipartBody.FORM, file);

        MultipartBody.Part part = MultipartBody.Part.
                createFormData("attachment", file.getName(), requestBody);

        return part;
    }
}
