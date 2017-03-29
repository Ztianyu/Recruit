package cn.zty.recruit.bean;

/**
 * Created by zty on 2017/3/17.
 */

public class PlanModel {

    /**
     * id : fd3c6e1cef47490cbdb1e0f580780e51
     * courseId : f943adc8aa444eb382abda1325cc7330
     * name : 商务谈判
     * codeNm : 00003
     * hours : 40
     */

    private String id;
    private String courseId;
    private String name;
    private String codeNm;
    private int hours;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeNm() {
        return codeNm;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
