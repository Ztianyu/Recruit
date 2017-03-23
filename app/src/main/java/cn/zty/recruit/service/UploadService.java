package cn.zty.recruit.service;


import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.LoadFileModel;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 上传图片
 * Created by zty on 2017/2/24.
 */

public interface UploadService {

    @Multipart
    @POST(Urls.uploadAndroidServlet)
    Observable<ResultBean<LoadFileModel>> load(@Part MultipartBody.Part file, @Query("uploadPath") String uploadPath);
}
