package cn.zty.recruit.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zty on 2017/3/28.
 */

public class DepartmentMajorModel implements Parcelable{

    /**
     * id : 4b5b3ced63dd41c892d47560730c446e
     * schoolId : 65c87d1bd2234908b104ce6c54b362ac
     * majorId : cc3575e9322d441295d3a40a96172bf4
     * majorNm : 工商管理
     * discipline : 12
     * disciplineLabel : 管理学
     * departmentId : 012bccdb5f4d41dd9bf647bb87c999f4
     * departmentNm : 管理学院
     * schoolLength : 3
     * remarks : 业务培养目标：本专业培养具备管理、经济、法律及企业管理方面的知识和能力，能在企、事业单位及政府部门从事管理以及教学、科研方面工作的工商管理学科高级专门人才。
     业务培养要求：本专业学生主要学习管理学、经济学和企业管理的基本理论和基本知识，受到企业管理方法与技巧方面的基本训练，具有分析和解决企业管理问题的基本能力。
     * createDate : 2017-03-24 15:28
     * updateDate : 2017-03-24 15:28
     */

    private String id;
    private String schoolId;
    private String majorId;
    private String majorNm;
    private String discipline;
    private String disciplineLabel;
    private String departmentId;
    private String departmentNm;
    private String schoolLength;
    private String remarks;
    private String createDate;
    private String updateDate;

    protected DepartmentMajorModel(Parcel in) {
        id = in.readString();
        schoolId = in.readString();
        majorId = in.readString();
        majorNm = in.readString();
        discipline = in.readString();
        disciplineLabel = in.readString();
        departmentId = in.readString();
        departmentNm = in.readString();
        schoolLength = in.readString();
        remarks = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
    }

    public static final Creator<DepartmentMajorModel> CREATOR = new Creator<DepartmentMajorModel>() {
        @Override
        public DepartmentMajorModel createFromParcel(Parcel in) {
            return new DepartmentMajorModel(in);
        }

        @Override
        public DepartmentMajorModel[] newArray(int size) {
            return new DepartmentMajorModel[size];
        }
    };

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

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorNm() {
        return majorNm;
    }

    public void setMajorNm(String majorNm) {
        this.majorNm = majorNm;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDisciplineLabel() {
        return disciplineLabel;
    }

    public void setDisciplineLabel(String disciplineLabel) {
        this.disciplineLabel = disciplineLabel;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentNm() {
        return departmentNm;
    }

    public void setDepartmentNm(String departmentNm) {
        this.departmentNm = departmentNm;
    }

    public String getSchoolLength() {
        return schoolLength;
    }

    public void setSchoolLength(String schoolLength) {
        this.schoolLength = schoolLength;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        dest.writeString(schoolId);
        dest.writeString(majorId);
        dest.writeString(majorNm);
        dest.writeString(discipline);
        dest.writeString(disciplineLabel);
        dest.writeString(departmentId);
        dest.writeString(departmentNm);
        dest.writeString(schoolLength);
        dest.writeString(remarks);
        dest.writeString(createDate);
        dest.writeString(updateDate);
    }
}
