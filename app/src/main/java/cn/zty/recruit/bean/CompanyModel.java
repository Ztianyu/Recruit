package cn.zty.recruit.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zty on 2017/6/1.
 */

public class CompanyModel implements Parcelable{

    /**
     * id : 3e210866b9b84da5bea02a537a7318d1
     * office : 4
     * name : 广东澳加医疗软件有限公司
     * contactMan : 陈先生
     * contactTel : 020-87985196
     * industry : 1
     * companyNature : 5
     * companyScale : 2
     * companyUrl : http://www.aokasoft.com
     * province : 440000
     * city : 440100
     * address : 海珠区新港东路2433号（启盛产业园）
     * content : 广东澳加医疗软件有限公司成立于2007年，是一家以医疗管理软件开发及技术服务的专业软件公司.专注于国内医药行业的企业信息化解决方案,致力于解决医药企业内部管理问题、物流过程中的沟通及流通成本问题.
     澳加软件集结了数百家客户的聪明智慧以及澳加人辛勤汗水的共同结晶。历时五载近百家政府、企事业单位的精心锤炼，铸就了澳加软件的完美品质。想客户所想，不断的创造更优秀的软件作品，为客户创造价值，是澳加人不变的追求。创新、诚信、通达，澳加人愿与广大用户在新世纪共创造辉煌。
     因公司规模不断发展壮大,现广州公司总部诚招技术开发等岗位多人。
     公司为员工提供公平的竞争机会，优厚的薪资待遇，完善的培训和福利机制:
     公司依据相关法规为员工缴社会保险；员工享受国家法律规定的婚假、丧假、产假和护理假以及带薪年假；公司严格依照国家法定假日休假，传统节日端午节、中秋节、春节等除假期外，还会发放节日礼品礼金。
     员工可享受公司提供的免费茶点及水果、每工作日的午餐补贴并定期组织员工聚餐，公司每年还会组织员工外出旅游等福利。
     以上仅为公司现行的部分福利待遇，根据目前公司的不断发展壮大，今后将会继续完善员工的福利待遇机制，欢迎相关专业的有识之士加盟澳加！！
     * createDate : 2017-04-01 10:04
     * updateDate : 2017-04-01 14:18
     * areaNm : 广东省•广州市
     * industryLabel : 互联网
     * companyNatureLabel : 民营企业
     * companyScaleLabel : 20-99人
     */

    private String id;
    private String office;
    private String name;
    private String contactMan;
    private String contactTel;
    private String industry;
    private String companyNature;
    private String companyScale;
    private String companyUrl;
    private String province;
    private String city;
    private String address;
    private String content;
    private String createDate;
    private String updateDate;
    private String areaNm;
    private String industryLabel;
    private String companyNatureLabel;
    private String companyScaleLabel;

    protected CompanyModel(Parcel in) {
        id = in.readString();
        office = in.readString();
        name = in.readString();
        contactMan = in.readString();
        contactTel = in.readString();
        industry = in.readString();
        companyNature = in.readString();
        companyScale = in.readString();
        companyUrl = in.readString();
        province = in.readString();
        city = in.readString();
        address = in.readString();
        content = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
        areaNm = in.readString();
        industryLabel = in.readString();
        companyNatureLabel = in.readString();
        companyScaleLabel = in.readString();
    }

    public static final Creator<CompanyModel> CREATOR = new Creator<CompanyModel>() {
        @Override
        public CompanyModel createFromParcel(Parcel in) {
            return new CompanyModel(in);
        }

        @Override
        public CompanyModel[] newArray(int size) {
            return new CompanyModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }

    public String getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getAreaNm() {
        return areaNm;
    }

    public void setAreaNm(String areaNm) {
        this.areaNm = areaNm;
    }

    public String getIndustryLabel() {
        return industryLabel;
    }

    public void setIndustryLabel(String industryLabel) {
        this.industryLabel = industryLabel;
    }

    public String getCompanyNatureLabel() {
        return companyNatureLabel;
    }

    public void setCompanyNatureLabel(String companyNatureLabel) {
        this.companyNatureLabel = companyNatureLabel;
    }

    public String getCompanyScaleLabel() {
        return companyScaleLabel;
    }

    public void setCompanyScaleLabel(String companyScaleLabel) {
        this.companyScaleLabel = companyScaleLabel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(office);
        dest.writeString(name);
        dest.writeString(contactMan);
        dest.writeString(contactTel);
        dest.writeString(industry);
        dest.writeString(companyNature);
        dest.writeString(companyScale);
        dest.writeString(companyUrl);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(address);
        dest.writeString(content);
        dest.writeString(createDate);
        dest.writeString(updateDate);
        dest.writeString(areaNm);
        dest.writeString(industryLabel);
        dest.writeString(companyNatureLabel);
        dest.writeString(companyScaleLabel);
    }
}
