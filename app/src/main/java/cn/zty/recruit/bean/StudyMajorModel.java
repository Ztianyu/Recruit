package cn.zty.recruit.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zty on 2017/3/18.
 */

public class StudyMajorModel implements Parcelable{

    /**
     * id : e8fd3a0e61a54c8fb02e1868e1ff51db
     * schoolId : 9390238f07f54b97a8cb0e2be003ad7b
     * departmentId : 50442124a7cf441cb0b4ddb5c39c049a
     * majorId : f399f489b0cd45ddb6230779b9789b8f
     * disciplineId : 7
     * studyTypeId : 1
     * studyTypeLabel:
     * schoolLength : 3
     * registrationFee : 10000.0
     * chargeStandardId : 2
     * createDate : 1490769956000
     * updateDate : 1490769956000
     * delFlag : 0
     * remarks : 语文、数学、英语、民航概论、安全检查概论、证件检查、X光机检查、飞机安全与救生、危险品运输、民航服务礼仪、危险品基础常识、出入境管理、安检行政法规、刑事侦查等
     * departmentNm : 航空学院
     * majorNm : 机场安全检查
     * chargeStandardLabel : 按学期收
     */

    private String id;
    private String schoolId;
    private String departmentId;
    private String majorId;
    private String disciplineId;
    private String studyTypeId;
    private String schoolLength;
    private double registrationFee;
    private String chargeStandardId;
    private long createDate;
    private long updateDate;
    private String delFlag;
    private String remarks;
    private String departmentNm;
    private String majorNm;
    private String chargeStandardLabel;
    private String studyTypeLabel;

    protected StudyMajorModel(Parcel in) {
        id = in.readString();
        schoolId = in.readString();
        departmentId = in.readString();
        majorId = in.readString();
        disciplineId = in.readString();
        studyTypeId = in.readString();
        studyTypeLabel = in.readString();
        schoolLength = in.readString();
        registrationFee = in.readDouble();
        chargeStandardId = in.readString();
        createDate = in.readLong();
        updateDate = in.readLong();
        delFlag = in.readString();
        remarks = in.readString();
        departmentNm = in.readString();
        majorNm = in.readString();
        chargeStandardLabel = in.readString();
    }

    public static final Creator<StudyMajorModel> CREATOR = new Creator<StudyMajorModel>() {
        @Override
        public StudyMajorModel createFromParcel(Parcel in) {
            return new StudyMajorModel(in);
        }

        @Override
        public StudyMajorModel[] newArray(int size) {
            return new StudyMajorModel[size];
        }
    };

    public String getStudyTypeLabel() {
        return studyTypeLabel;
    }

    public void setStudyTypeLabel(String studyTypeLabel) {
        this.studyTypeLabel = studyTypeLabel;
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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(String disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getStudyTypeId() {
        return studyTypeId;
    }

    public void setStudyTypeId(String studyTypeId) {
        this.studyTypeId = studyTypeId;
    }

    public String getSchoolLength() {
        return schoolLength;
    }

    public void setSchoolLength(String schoolLength) {
        this.schoolLength = schoolLength;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public String getChargeStandardId() {
        return chargeStandardId;
    }

    public void setChargeStandardId(String chargeStandardId) {
        this.chargeStandardId = chargeStandardId;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDepartmentNm() {
        return departmentNm;
    }

    public void setDepartmentNm(String departmentNm) {
        this.departmentNm = departmentNm;
    }

    public String getMajorNm() {
        return majorNm;
    }

    public void setMajorNm(String majorNm) {
        this.majorNm = majorNm;
    }

    public String getChargeStandardLabel() {
        return chargeStandardLabel;
    }

    public void setChargeStandardLabel(String chargeStandardLabel) {
        this.chargeStandardLabel = chargeStandardLabel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(schoolId);
        dest.writeString(departmentId);
        dest.writeString(majorId);
        dest.writeString(disciplineId);
        dest.writeString(studyTypeId);
        dest.writeString(studyTypeLabel);
        dest.writeString(schoolLength);
        dest.writeDouble(registrationFee);
        dest.writeString(chargeStandardId);
        dest.writeLong(createDate);
        dest.writeLong(updateDate);
        dest.writeString(delFlag);
        dest.writeString(remarks);
        dest.writeString(departmentNm);
        dest.writeString(majorNm);
        dest.writeString(chargeStandardLabel);
    }
}
