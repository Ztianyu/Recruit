package cn.zty.recruit.bean;

/**
 * Created by zty on 2017/3/22.
 */

public class IntroductionModel {


    /**
     * id : 8e4a67d4a0814a26bbde418ed967e0cf
     * schoolId : bdf4e05a8edd4260a7e49732f591169c
     * title : 广东机电职业技术学院
     * content : <p>
     <img src="http://14.29.68.166:8862/attached/image/20170317/20170317151608_3.jpg" alt="" /> 广东机电职业技术学院创办于1963年，是2001年5月经广东省人民政府批准，国家教育部备案改制为高等职业技术学院。
     </p>
     <p>
     学院地处广州市白云区，现有南北两个校区，校园总面积764亩，各类教学仪器设备7093.52万元，纸质图书47.07万册，电子图书3540GB。学院现有全日制在校生16543人，教工668人，学院南北校区规划容纳17000名学生，学院新校区（北校区），2010年9月已正式启用。
     </p>
     <p>
     广东机电职业技术学院前身为创建于1963年的广东省农业机械化技工学校，历经“广东省农业机械技术学校”、“广东省农业机电学校”、“广东省机电学校”等发展阶段。2001年，经广东省人民政府批准，学校升格为高等职业院校，翻开了快速发展的崭新篇章。
     </p>
     */

    private String id;
    private String schoolId;
    private String title;
    private String content;

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
}
