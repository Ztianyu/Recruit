package cn.zty.recruit.bean;

/**
 * Created by zty on 2017/3/21.
 */

public class IntegralRecordModel {


    /**
     * id : 390619e1f1fb4525880da2fa52ec222d
     * userId : 2ce29d9ab4b64631a477f02bec8139a8
     * fullNm : 张小川
     * sex : 男
     * age : 36
     * mobile : 13527284017
     * mode : 2
     * modeLabel
     * source : 13570686221
     * integral : 5
     * balance : 5
     * createDate : 2017-04-06 17:01
     */

    private String id;
    private String userId;
    private String fullNm;
    private String sex;
    private String age;
    private String mobile;
    private String mode;
    private String source;
    private int integral;
    private int balance;
    private String createDate;
    private String modeLabel;

    public String getModeLabel() {
        return modeLabel;
    }

    public void setModeLabel(String modeLabel) {
        this.modeLabel = modeLabel;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
