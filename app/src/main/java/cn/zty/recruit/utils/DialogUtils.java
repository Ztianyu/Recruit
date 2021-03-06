package cn.zty.recruit.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import cn.zty.recruit.bean.OrderModel;
import cn.zty.recruit.bean.VersionModel;
import cn.zty.recruit.listener.AreaSelectListener;
import cn.zty.recruit.listener.BirthSelectListener;
import cn.zty.recruit.listener.DeleteOrderListener;
import cn.zty.recruit.listener.EducationSelectListener;
import cn.zty.recruit.listener.EnrollTypeSelectListener;
import cn.zty.recruit.listener.IndustrySelectListener;
import cn.zty.recruit.listener.IndustryTypeListener;
import cn.zty.recruit.listener.MajorSelectListener;
import cn.zty.recruit.listener.PayListener;
import cn.zty.recruit.listener.SchoolSelectListener;
import cn.zty.recruit.listener.SendReplayListener;
import cn.zty.recruit.listener.SexSelectListener;
import cn.zty.recruit.listener.StudySchoolListener;
import cn.zty.recruit.listener.ToastSureListener;
import cn.zty.recruit.listener.VisitListener;
import cn.zty.recruit.ui.fragment.AreaSelectFragment;
import cn.zty.recruit.ui.fragment.CommentFragment;
import cn.zty.recruit.ui.fragment.DeleteOrderFragment;
import cn.zty.recruit.ui.fragment.PayFragment;
import cn.zty.recruit.ui.fragment.SelectEducation;
import cn.zty.recruit.ui.fragment.SelectSexFragment;
import cn.zty.recruit.ui.fragment.SelectVideoFragment;
import cn.zty.recruit.ui.fragment.ToastFragment;
import cn.zty.recruit.ui.fragment.VersionFragment;
import cn.zty.recruit.ui.fragment.VisitFragment;
import cn.zty.recruit.ui.fragment.job.IndustryCategoryFragment;
import cn.zty.recruit.ui.fragment.learn.EnrollTypeFragment;
import cn.zty.recruit.ui.fragment.learn.IndustryTypeFragment;
import cn.zty.recruit.ui.fragment.learn.StudySchoolSelect;
import cn.zty.recruit.ui.fragment.school.CallFragment;
import cn.zty.recruit.ui.fragment.school.MajorSelectFragment;
import cn.zty.recruit.ui.fragment.school.SchoolSelectFragment;

/**
 * Created by zty on 2017/3/15.
 */

public class DialogUtils {

    public static final String AREA_SELECT = "areaSelectFragment";
    public static final String SCHOOL_SELECT = "schoolSelectFragment";
    public static final String MAJOR_SELECT = "majorSelectFragment";
    public static final String STUDY_SELECT = "studySchoolSelect";

    public static final String SEX_SELECT = "sexFragment";
    public static final String EDUCATION_SELECT = "selectEducation";
    public static final String ENROLL_TYPE_SELECT = "enrollTypeFragment";

    public static final String CALL = "callFragment";
    public static final String TOAST = "toastFragment";

    public static final String INDUSTRY_TYPE = "industryTypeFragment";

    public static final String PAY = "payFragment";

    public static final String DELETE = "deleteOrderFragment";

    public static final String VISIT = "visitFragment";

    public static final String VERSION = "versionFragment";

    public static final String INDUSTRY = "industryCategoryFragment";

    public static final String COMMENT = "commentFragment";


    /**
     * 选择 省、市（type:0(省)；1（市））
     */
    public static void showAreaSelect(FragmentManager manager, int topHeight, int type, AreaSelectListener listener, String provinceId) {
        Fragment fragment = manager.findFragmentByTag(AREA_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        AreaSelectFragment areaSelectFragment = AreaSelectFragment.newInstance(topHeight, type, provinceId, listener);
        areaSelectFragment.show(manager.beginTransaction(), AREA_SELECT);
    }

    /**
     * 择校
     */
    public static void showSchoolSelect(FragmentManager manager, int topHeight, SchoolSelectListener listener) {
        Fragment fragment = manager.findFragmentByTag(SCHOOL_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        SchoolSelectFragment schoolSelectFragment = SchoolSelectFragment.newInstance(topHeight, listener);
        schoolSelectFragment.show(manager.beginTransaction(), SCHOOL_SELECT);
    }

    /**
     * 择校
     */
    public static void showStudySelect(FragmentManager manager, int topHeight, StudySchoolListener listener) {
        Fragment fragment = manager.findFragmentByTag(STUDY_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        StudySchoolSelect studySchoolSelect = StudySchoolSelect.newInstance(topHeight, listener);
        studySchoolSelect.show(manager.beginTransaction(), STUDY_SELECT);
    }

    /**
     * 选择专业
     */
    public static void showMajorSelect(FragmentManager manager, int topHeight, MajorSelectListener listener) {
        Fragment fragment = manager.findFragmentByTag(MAJOR_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        MajorSelectFragment majorSelectFragment = MajorSelectFragment.newInstance(topHeight, listener);
        majorSelectFragment.show(manager.beginTransaction(), MAJOR_SELECT);
    }

    /**
     * 日期选择控件
     */
    public static void showDataSelect(Context context, final BirthSelectListener listener) {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                StringBuffer sb = new StringBuffer();
                sb.append(year)
                        .append("-")
                        .append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1))
                        .append("-")
                        .append(dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
                listener.onDateSelect(sb.toString());
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * 日期选择控件
     */
    public static void showDataSelect(Context context, final TextView textView) {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                StringBuffer sb = new StringBuffer();
                sb.append(year)
                        .append("-")
                        .append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1))
                        .append("-")
                        .append(dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
                textView.setText(sb.toString());
                Log.i("dialog", sb.toString());
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * 选择性别
     */
    public static void showSexSelect(FragmentManager manager, int type, SexSelectListener listener) {
        Fragment fragment = manager.findFragmentByTag(SEX_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        SelectSexFragment sexFragment = SelectSexFragment.newInstance(type, listener);
        sexFragment.show(manager.beginTransaction(), SEX_SELECT);
    }

    /**
     * 选择学历
     */
    public static void showEducationSelect(FragmentManager manager, String education, EducationSelectListener listener) {
        Fragment fragment = manager.findFragmentByTag(EDUCATION_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        SelectEducation selectEducation = SelectEducation.newInstance(education, listener);
        selectEducation.show(manager.beginTransaction(), EDUCATION_SELECT);
    }

    /**
     * 选择定金类型
     */
    public static void showEnrollTypeSelect(FragmentManager manager, EnrollTypeSelectListener listener, String schoolId) {
        Fragment fragment = manager.findFragmentByTag(ENROLL_TYPE_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        EnrollTypeFragment enrollTypeFragment = EnrollTypeFragment.newInstance(listener, schoolId);
        enrollTypeFragment.show(manager.beginTransaction(), ENROLL_TYPE_SELECT);
    }

    /**
     * 拨打电话
     */
    public static void showCall(FragmentManager manager, String phone, String time) {
        Fragment fragment = manager.findFragmentByTag(CALL);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        CallFragment callFragment = CallFragment.newInstance(phone, time);
        callFragment.show(manager.beginTransaction(), CALL);
    }

    /**
     * 询问对话框
     */
    public static void showToast(FragmentManager manager, String toast, ToastSureListener listener) {
        Fragment fragment = manager.findFragmentByTag(TOAST);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        ToastFragment toastFragment = ToastFragment.newInstance(toast, listener);
        toastFragment.show(manager.beginTransaction(), TOAST);
    }

    /**
     * 培训机构行业类型列表
     */
    public static void showIndustryType(FragmentManager manager, int height, IndustryTypeListener listener) {
        Fragment fragment = manager.findFragmentByTag(INDUSTRY_TYPE);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        IndustryTypeFragment industryTypeFragment = IndustryTypeFragment.newInstance(height, listener);
        industryTypeFragment.show(manager.beginTransaction(), INDUSTRY_TYPE);
    }

    /**
     * 支付
     */
    public static void showPayDialog(FragmentManager manager, OrderModel orderModel, PayListener payListener) {
        Fragment fragment = manager.findFragmentByTag(PAY);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        PayFragment payFragment = PayFragment.newInstance(orderModel, payListener);
        payFragment.show(manager.beginTransaction(), PAY);
    }

    /**
     * 删除订单
     */
    public static void showDelete(FragmentManager manager, int position, DeleteOrderListener listener) {
        Fragment fragment = manager.findFragmentByTag(DELETE);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        DeleteOrderFragment deleteOrderFragment = DeleteOrderFragment.newInstance(position, listener);
        deleteOrderFragment.show(manager.beginTransaction(), DELETE);
    }

    /**
     * 电话回访
     */
    public static void showVisit(FragmentManager manager, VisitListener listener) {
        Fragment fragment = manager.findFragmentByTag(VISIT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        VisitFragment visitFragment = VisitFragment.newInstance(listener);
        visitFragment.show(manager.beginTransaction(), VISIT);
    }

    /**
     * 版本更新
     */
    public static void showVersion(FragmentManager manager, String title, VersionModel versionModel) {
        Fragment fragment = manager.findFragmentByTag(VERSION);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        VersionFragment versionFragment = VersionFragment.newInstance(title, versionModel);
        versionFragment.show(manager.beginTransaction(), VERSION);
    }

    /**
     * 电话回访
     */
    public static void showIndustrySelect(FragmentManager manager, int height, IndustrySelectListener listener) {
        Fragment fragment = manager.findFragmentByTag(INDUSTRY);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        IndustryCategoryFragment industryCategoryFragment = IndustryCategoryFragment.newInstance(height, listener);
        industryCategoryFragment.show(manager.beginTransaction(), INDUSTRY);
    }

    /**
     * 评论
     */
    public static void showReplay(FragmentManager manager, String forumId, int position) {
        Fragment fragment = manager.findFragmentByTag(COMMENT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        CommentFragment commentFragment = CommentFragment.newInstance(forumId, position);
        commentFragment.show(manager.beginTransaction(), COMMENT);
    }
}
