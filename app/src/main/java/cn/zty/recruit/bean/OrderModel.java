package cn.zty.recruit.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zty on 2017/3/23.
 */

public class OrderModel implements Parcelable{


    /**
     * id : 378d4c3a38e94f4d838d5436683ad028
     * userId : 7e3793ce63c9465ca1480e3541f5fbd9
     * fullNm : 张潇风
     * sex : 男
     * age : 4
     * mobile : 13023454312
     * photo : http://14.29.68.166:8862/healthArchives/2017/03/58d30f5114bc4441bc100f0ae319252e.jpg
     * education : 6
     * educationLabel : 本科
     * orderCode : 20170330165622
     * courseId : f943adc8aa444eb382abda1325cc7330
     * courseNm : 市场营销
     * departmentNm ：信息学院
     * schoolNm : 广州市蓝天技工学校
     * imgUrl : /trainOrg/2017/03/07f5dd0b8d0547229ee6e11f6e93a86e.jpg
     * thumbImg : /trainOrg/2017/03/07f5dd0b8d0547229ee6e11f6e93a86e_small.jpg
     * schoolAddress : 天河区中山大道珠吉路
     * hours : 0
     * chargeStandard : 2
     * money : 1500.0
     * depositId : 11dde7b4037849d18187d8fc77218a52
     * deductibleAmount : 1000.0
     * discountAmount : 500.0
     * deposit : 500.0
     * actualPayment : 500.0
     * state : 0
     * createDate : 2017-03-30 16:56
     * updateDate : 2017-03-30 16:56
     * remarks ：
     * studyTypeLabel :
     */

    private String id;
    private String userId;
    private String fullNm;
    private String sex;
    private String age;
    private String mobile;
    private String photo;
    private String education;
    private String educationLabel;
    private String orderCode;
    private String courseId;
    private String courseNm;
    private String schoolNm;
    private String imgUrl;
    private String thumbImg;
    private String schoolAddress;
    private int hours;
    private String chargeStandard;
    private double money;
    private String depositId;
    private double deductibleAmount;
    private double discountAmount;
    private double deposit;
    private double actualPayment;
    private String state;
    private String createDate;
    private String updateDate;
    private String departmentNm;
    private String remarks;
    private String studyTypeLabel;

    protected OrderModel(Parcel in) {
        id = in.readString();
        userId = in.readString();
        fullNm = in.readString();
        sex = in.readString();
        age = in.readString();
        mobile = in.readString();
        photo = in.readString();
        education = in.readString();
        educationLabel = in.readString();
        orderCode = in.readString();
        courseId = in.readString();
        courseNm = in.readString();
        schoolNm = in.readString();
        imgUrl = in.readString();
        thumbImg = in.readString();
        schoolAddress = in.readString();
        hours = in.readInt();
        chargeStandard = in.readString();
        money = in.readDouble();
        depositId = in.readString();
        deductibleAmount = in.readDouble();
        discountAmount = in.readDouble();
        deposit = in.readDouble();
        actualPayment = in.readDouble();
        state = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
        departmentNm = in.readString();
        remarks = in.readString();
        studyTypeLabel = in.readString();
    }

    public static final Creator<OrderModel> CREATOR = new Creator<OrderModel>() {
        @Override
        public OrderModel createFromParcel(Parcel in) {
            return new OrderModel(in);
        }

        @Override
        public OrderModel[] newArray(int size) {
            return new OrderModel[size];
        }
    };

    public String getStudyTypeLabel() {
        return studyTypeLabel;
    }

    public void setStudyTypeLabel(String studyTypeLabel) {
        this.studyTypeLabel = studyTypeLabel;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullNm() {
        return fullNm;
    }

    public void setFullNm(String fullNm) {
        this.fullNm = fullNm;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducationLabel() {
        return educationLabel;
    }

    public void setEducationLabel(String educationLabel) {
        this.educationLabel = educationLabel;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseNm() {
        return courseNm;
    }

    public void setCourseNm(String courseNm) {
        this.courseNm = courseNm;
    }

    public String getSchoolNm() {
        return schoolNm;
    }

    public void setSchoolNm(String schoolNm) {
        this.schoolNm = schoolNm;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
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

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public double getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(double deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(double actualPayment) {
        this.actualPayment = actualPayment;
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
        dest.writeString(userId);
        dest.writeString(fullNm);
        dest.writeString(sex);
        dest.writeString(age);
        dest.writeString(mobile);
        dest.writeString(photo);
        dest.writeString(education);
        dest.writeString(educationLabel);
        dest.writeString(orderCode);
        dest.writeString(courseId);
        dest.writeString(courseNm);
        dest.writeString(schoolNm);
        dest.writeString(imgUrl);
        dest.writeString(thumbImg);
        dest.writeString(schoolAddress);
        dest.writeInt(hours);
        dest.writeString(chargeStandard);
        dest.writeDouble(money);
        dest.writeString(depositId);
        dest.writeDouble(deductibleAmount);
        dest.writeDouble(discountAmount);
        dest.writeDouble(deposit);
        dest.writeDouble(actualPayment);
        dest.writeString(state);
        dest.writeString(createDate);
        dest.writeString(updateDate);
        dest.writeString(departmentNm);
        dest.writeString(remarks);
        dest.writeString(studyTypeLabel);
    }
}
