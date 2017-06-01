package cn.zty.recruit.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zty on 2017/5/31.
 */

public class CompanyJobModel implements Parcelable{
    /**
     * id : 11474451a3c2408fbfdaf2ea175572b5
     * companyId : 3e210866b9b84da5bea02a537a7318d1
     * name : .Net 高级开发工程师
     * jobNature : 1
     * number : 2
     * salary : 6
     * education : 5
     * experience : 4
     * province : 440000
     * city : 440100
     * lighten : 五险一金,年底双薪,绩效奖金,加班补助,全勤奖,餐补,带薪年假,定期体检,节日福利
     * remarks : 技能知识：
     1）精通C#语言，熟悉WinForm或WebForm技术，熟练使用VS2008、VS2010、VS2012之一， 熟悉.NET多层开发和分布式应用体系结构；
     2）熟悉HTML、JavaScript、XML 、CSS、Ajax等相关WEB技术，精通JQuery或相关JS库开发及扩展经验；
     3）有Mvc架构经验，有主持软件技术架构或参与开发架构经验；
     4）精通SQL语言，掌握SqlServer2005/2008的设计与开发，了解数据库管理、性能优化等基本操作技能； 会Oracle/MySql数据库尤佳
     5）深入理解B/S架构，掌握.net平台的开发模型与框架；
     6）熟悉设计模式，具有良好的系统分析和设计能力；
     7）有供应链管理、企业资源计划、客户关系管理、电子商务网站业务经验，有数个类似项目经验；
     8）具备强烈的团队精神和责任心、能主动应对工作压力和变化；具备良好的计划制订执行和表达能力，能按项目要求交付高质量的文档和代码。

     岗位职责：
     1）负责参与医药流通行业供应链平台的开发；带领数个程序员承担业务模块的开发；
     2）参与项目的需求分析，制定相应的解决方案；
     3）参与系统架构设计, 详细设计数据库、逻辑、结构等，提供并维护详细技术设计文档, 保持产品技术文档的完整性、连续性和可追溯性；
     4）负责程序开发与代码编写、单元测试。

     咨询电话：020－34397277
     * state : 1
     * createDate : 2017-04-07 14:57
     * updateDate : 2017-04-07 14:57
     * companyNm : 广东澳加医疗软件有限公司
     * jobNatureLabel : 全职
     * salaryLabel : 8000-10000
     * educationLabel : 大专以上
     * experienceLabel : 3年以上
     * areaNm : 广东省•广州市
     */

    private String id;
    private String companyId;
    private String name;
    private String jobNature;
    private String number;
    private String salary;
    private String education;
    private String experience;
    private String province;
    private String city;
    private String lighten;
    private String remarks;
    private String state;
    private String createDate;
    private String updateDate;
    private String companyNm;
    private String jobNatureLabel;
    private String salaryLabel;
    private String educationLabel;
    private String experienceLabel;
    private String areaNm;

    protected CompanyJobModel(Parcel in) {
        id = in.readString();
        companyId = in.readString();
        name = in.readString();
        jobNature = in.readString();
        number = in.readString();
        salary = in.readString();
        education = in.readString();
        experience = in.readString();
        province = in.readString();
        city = in.readString();
        lighten = in.readString();
        remarks = in.readString();
        state = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
        companyNm = in.readString();
        jobNatureLabel = in.readString();
        salaryLabel = in.readString();
        educationLabel = in.readString();
        experienceLabel = in.readString();
        areaNm = in.readString();
    }

    public static final Creator<CompanyJobModel> CREATOR = new Creator<CompanyJobModel>() {
        @Override
        public CompanyJobModel createFromParcel(Parcel in) {
            return new CompanyJobModel(in);
        }

        @Override
        public CompanyJobModel[] newArray(int size) {
            return new CompanyJobModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNature() {
        return jobNature;
    }

    public void setJobNature(String jobNature) {
        this.jobNature = jobNature;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLighten() {
        return lighten;
    }

    public void setLighten(String lighten) {
        this.lighten = lighten;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getJobNatureLabel() {
        return jobNatureLabel;
    }

    public void setJobNatureLabel(String jobNatureLabel) {
        this.jobNatureLabel = jobNatureLabel;
    }

    public String getSalaryLabel() {
        return salaryLabel;
    }

    public void setSalaryLabel(String salaryLabel) {
        this.salaryLabel = salaryLabel;
    }

    public String getEducationLabel() {
        return educationLabel;
    }

    public void setEducationLabel(String educationLabel) {
        this.educationLabel = educationLabel;
    }

    public String getExperienceLabel() {
        return experienceLabel;
    }

    public void setExperienceLabel(String experienceLabel) {
        this.experienceLabel = experienceLabel;
    }

    public String getAreaNm() {
        return areaNm;
    }

    public void setAreaNm(String areaNm) {
        this.areaNm = areaNm;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(companyId);
        dest.writeString(name);
        dest.writeString(jobNature);
        dest.writeString(number);
        dest.writeString(salary);
        dest.writeString(education);
        dest.writeString(experience);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(lighten);
        dest.writeString(remarks);
        dest.writeString(state);
        dest.writeString(createDate);
        dest.writeString(updateDate);
        dest.writeString(companyNm);
        dest.writeString(jobNatureLabel);
        dest.writeString(salaryLabel);
        dest.writeString(educationLabel);
        dest.writeString(experienceLabel);
        dest.writeString(areaNm);
    }
}
