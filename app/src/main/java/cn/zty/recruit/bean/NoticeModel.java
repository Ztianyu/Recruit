package cn.zty.recruit.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zty on 2017/3/21.
 */

public class NoticeModel implements Parcelable{


    /**
     * id : e8df46bd9e4d4853b47e734fa3313e0b
     * title : 版本1.0 主要更新
     * content : 版本1.0 主要更新....
     * state : 1
     * createDate : 2017-03-13 17:16
     * updateDate : 2017-03-13 17:16
     */

    private String id;
    private String title;
    private String content;
    private String state;
    private String createDate;
    private String updateDate;

    protected NoticeModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        content = in.readString();
        state = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
    }

    public static final Creator<NoticeModel> CREATOR = new Creator<NoticeModel>() {
        @Override
        public NoticeModel createFromParcel(Parcel in) {
            return new NoticeModel(in);
        }

        @Override
        public NoticeModel[] newArray(int size) {
            return new NoticeModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(state);
        dest.writeString(createDate);
        dest.writeString(updateDate);
    }
}
