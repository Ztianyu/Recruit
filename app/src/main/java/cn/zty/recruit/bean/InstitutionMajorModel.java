package cn.zty.recruit.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zty on 2017/3/16.
 */

public class InstitutionMajorModel implements Parcelable{


    /**
     * id : ce613112f09d4b19a16a7296e604303e
     * schoolId : 8c67bcb6ee454d3e974022299ed86c6d
     * name : B/S软件开发-JAVA方向
     * hours : 288
     * chargeStandard : 2
     * money : 8000.0
     * videoUrl:xxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     * remarks : B/S软件开发-JAVA方向
     */

    private String id;
    private String schoolId;
    private String name;
    private int hours;
    private String chargeStandard;
    private double money;
    private String remarks;
    private String videoUrl;

    protected InstitutionMajorModel(Parcel in) {
        id = in.readString();
        schoolId = in.readString();
        name = in.readString();
        hours = in.readInt();
        chargeStandard = in.readString();
        money = in.readDouble();
        remarks = in.readString();
        videoUrl = in.readString();
    }

    public static final Creator<InstitutionMajorModel> CREATOR = new Creator<InstitutionMajorModel>() {
        @Override
        public InstitutionMajorModel createFromParcel(Parcel in) {
            return new InstitutionMajorModel(in);
        }

        @Override
        public InstitutionMajorModel[] newArray(int size) {
            return new InstitutionMajorModel[size];
        }
    };

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getChargeStandard() {
        return chargeStandard;
    }

    public void setChargeStandard(String chargeStandard) {
        this.chargeStandard = chargeStandard;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
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
        dest.writeString(id);
        dest.writeString(schoolId);
        dest.writeString(name);
        dest.writeInt(hours);
        dest.writeString(chargeStandard);
        dest.writeDouble(money);
        dest.writeString(remarks);
        dest.writeString(videoUrl);
    }
}
