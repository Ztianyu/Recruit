package cn.zty.recruit.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zty on 2016/6/6.
 */
public class VersionModel implements Parcelable{

    private String appAddress;
    private String appName;
    private String remarks;
    private int appVersion;

    protected VersionModel(Parcel in) {
        appAddress = in.readString();
        appName = in.readString();
        remarks = in.readString();
        appVersion = in.readInt();
    }

    public static final Creator<VersionModel> CREATOR = new Creator<VersionModel>() {
        @Override
        public VersionModel createFromParcel(Parcel in) {
            return new VersionModel(in);
        }

        @Override
        public VersionModel[] newArray(int size) {
            return new VersionModel[size];
        }
    };

    public String getAppAddress() {
        return appAddress;
    }

    public void setAppAddress(String appAddress) {
        this.appAddress = appAddress;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(appAddress);
        dest.writeString(appName);
        dest.writeString(remarks);
        dest.writeInt(appVersion);
    }
}
