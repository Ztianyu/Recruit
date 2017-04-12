package cn.zty.recruit.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zty on 2017/3/22.
 */

public class PanoramaModel implements Parcelable {

    /**
     * id : 58d60adaa100454ab13ff78a9d31c996
     * schoolId : bdf4e05a8edd4260a7e49732f591169c
     * place : 周边环境
     * videoUrl:
     * content : 周边环境全景图。。。
     * createDate : 2017-03-27 20:14
     * updateDate : 2017-03-27 20:14
     * webUrl:
     */

    private String id;
    private String schoolId;
    private String place;
    private String content;
    private String createDate;
    private String updateDate;
    private String videoUrl;
    private String webUrl;

    protected PanoramaModel(Parcel in) {
        id = in.readString();
        schoolId = in.readString();
        place = in.readString();
        content = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
        videoUrl = in.readString();
        webUrl = in.readString();
    }

    public static final Creator<PanoramaModel> CREATOR = new Creator<PanoramaModel>() {
        @Override
        public PanoramaModel createFromParcel(Parcel in) {
            return new PanoramaModel(in);
        }

        @Override
        public PanoramaModel[] newArray(int size) {
            return new PanoramaModel[size];
        }
    };

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(schoolId);
        dest.writeString(place);
        dest.writeString(content);
        dest.writeString(createDate);
        dest.writeString(updateDate);
        dest.writeString(videoUrl);
        dest.writeString(webUrl);
    }
}
