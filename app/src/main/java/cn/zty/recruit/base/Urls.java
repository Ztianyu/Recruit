package cn.zty.recruit.base;

/**
 * Created by zty on 2017/3/4.
 */

public class Urls {

//    public static final String HOST = "http://www.gdkqvip.com/ztm/";

    public static final String HOST = "http://14.29.68.166:8181/recruitStudents/";

//    public static final String HOST = "http://112.74.190.31/ztm/";

    public static final String HOST_ADMIN = "admin/ws/";

    public static final String HOST_ADMIN_1 = "admin/";

    public static final String login = HOST_ADMIN + "user/sysLogin";
    public static final String register = HOST_ADMIN + "user/register";
    public static final String updatePwd = HOST_ADMIN + "user/updatePwd";
    public static final String setNewPwd = HOST_ADMIN + "user/setNewPwd";
    public static final String getUserDataDetail = HOST_ADMIN + "userData/getUserDataDetail";
    public static final String update = HOST_ADMIN + "userData/update";

    //获取邀请人列表
    public static final String getInviteUserList = HOST_ADMIN + "userData/getInviteUserList";

    //验证邀请码
    public static final String checkInviteCode = HOST_ADMIN + "user/checkInviteCode";

    //热门专业列表
    public static final String getHotMajorList = HOST_ADMIN + "hotMajor/getHotMajorList";
    //职业学校列表
    public static final String getVocationalSchoolList = HOST_ADMIN + "vocationalSchool/getVocationalSchoolList";

    //职业学校详情
    public static final String getVocationalSchool = HOST_ADMIN + "vocationalSchool/getVocationalSchool";
    //职业学校简介
    public static final String getSchoolIntroduction = HOST_ADMIN + "schoolIntroduction/getSchoolIntroduction";
    //学校全景
    public static final String getSchoolPanorama = HOST_ADMIN + "schoolPanorama/getSchoolPanoramaList";
    //学校院系
    public static final String getSchoolDepartmentList = HOST_ADMIN + "schoolDepartment/getSchoolDepartmentList";
    //师资力量
    public static final String getSchoolFaculty = HOST_ADMIN + "schoolFaculty/getSchoolFaculty";
    //院校专业列表
    public static final String getSchoolMajorList = HOST_ADMIN + "schoolMajor/getSchoolMajorList";

    //获取行业类型列表
    public static final String getIndustryTypeList = HOST_ADMIN + "industryType/getIndustryTypeList";
    //获取培训机构列表
    public static final String getTrainOrgList = HOST_ADMIN + "trainOrg/getTrainOrgList";

    //获取培训机构详情
    public static final String getTrainOrg = HOST_ADMIN + "trainOrg/getTrainOrg";

    //获取培训机构课程设置列表
    public static final String getCourseSetList = HOST_ADMIN + "courseSet/getCourseSetList";

    //获取培训机构课程详情
    public static final String getCourseSet = HOST_ADMIN + "courseSet/getCourseSet";

    //获取培训机构课程课目列表
    public static final String getScheduleSetList = HOST_ADMIN + "scheduleSet/getScheduleSetList";

    //获取报名订单列表
    public static final String getOrderList = HOST_ADMIN + "order/getOrderList";

    //获取报名订单详情
    public static final String getOrder = HOST_ADMIN + "order/getOrder";

    //提交报名订单
    public static final String submitOrder = HOST_ADMIN + "order/submitOrder";

    //删除订单
    public static final String deleteOrder = HOST_ADMIN + "order/deleteOrder";

    //报名定金类型
    public static final String getDepositSystemList = HOST_ADMIN + "depositSystem/getDepositSystemList";

    //进修学校信息列表
    public static final String getStudySchoolInfoList = HOST_ADMIN + "studySchoolInfo/getStudySchoolInfoList";

    //进修学校信息
    public static final String getStudySchoolInfo = HOST_ADMIN + "studySchoolInfo/getStudySchoolInfo";

    //进修学校 学院专业设置
    public static final String getMajorSettingList = HOST_ADMIN + "majorSetting/getMajorSettingList";


    //获取省份
    public static final String getProvinceArea = HOST_ADMIN + "area/getProvinceArea";
    //获取地区
    public static final String getCityArea = HOST_ADMIN + "area/getCityArea";

    //首页广告
    public static final String getHomepageAdsList = HOST_ADMIN + "homepageAds/getHomepageAdsList";

    //系统公告
    public static final String getNoticeList = HOST_ADMIN + "notice/getNoticeList";

    //积分记录
    public static final String getIntegralRecordList = HOST_ADMIN + "integralRecord/getIntegralRecordList";

    //数据字典
    public static final String getDictList = HOST_ADMIN + "dict/getDictList";

    //电话回访
    public static final String visit = HOST_ADMIN + "returnRecord/submitReturnRecord";

    //招聘信息列表
    public static final String getCompanyJobList = HOST_ADMIN + "companyJob/getCompanyJobList";

    //获取企业详情
    public static final String getRecruitCompany = HOST_ADMIN + "recruitCompany/getRecruitCompany";


    //上传文件（语音文件、图片文件）
    public static final String uploadAndroidServlet = "uploadAndroidServlet";

    //版本更新
    public static final String requestApp = HOST_ADMIN + "user/requestApp";


    public static final String weChatPay = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static final String colseOrder = " https://api.mch.weixin.qq.com/pay/closeorder";

    public static final String unifiedorder = "unifiedorder";

    public static final String weChatNotifyUrl = HOST + "notifyServlet";

    public static final String aliNotifyUrl = HOST + HOST_ADMIN + "order/orderNotify";

}
