package cn.zty.recruit.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import cn.zty.baselib.widget.StripViewNoImg;
import cn.zty.recruit.listener.AreaSelectListener;
import cn.zty.recruit.listener.EducationSelectListener;
import cn.zty.recruit.listener.EnrollTypeSelectListener;
import cn.zty.recruit.listener.SexSelectListener;
import cn.zty.recruit.ui.fragment.AreaSelectFragment;
import cn.zty.recruit.ui.fragment.SelectEducation;
import cn.zty.recruit.ui.fragment.SelectSexFragment;
import cn.zty.recruit.ui.fragment.learn.EnrollTypeFragment;
import cn.zty.recruit.ui.fragment.school.MajorSelectFragment;
import cn.zty.recruit.ui.fragment.school.SchoolSelectFragment;

/**
 * Created by zty on 2017/3/15.
 */

public class DialogUtils {

    public static final String AREA_SELECT = "areaSelectFragment";
    public static final String SCHOOL_SELECT = "schoolSelectFragment";
    public static final String MAJOR_SELECT = "majorSelectFragment";

    public static final String SEX_SELECT = "sexFragment";
    public static final String EDUCATION_SELECT = "selectEducation";
    public static final String ENROLL_TYPE_SELECT = "enrollTypeFragment";

    /**
     * 选择 省、市（type:0(省)；1（市））
     */
    public static void showAreaSelect(FragmentManager manager, int topHeight, int type, AreaSelectListener listener) {
        Fragment fragment = manager.findFragmentByTag(AREA_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        AreaSelectFragment areaSelectFragment = AreaSelectFragment.newInstance(topHeight, type, listener);
        areaSelectFragment.show(manager.beginTransaction(), AREA_SELECT);
    }

    /**
     * 择校
     */
    public static void showSchoolSelect(FragmentManager manager, int topHeight) {
        Fragment fragment = manager.findFragmentByTag(SCHOOL_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        SchoolSelectFragment schoolSelectFragment = SchoolSelectFragment.newInstance(topHeight);
        schoolSelectFragment.show(manager.beginTransaction(), SCHOOL_SELECT);
    }

    /**
     * 选择专业
     */
    public static void showMajorSelect(FragmentManager manager, int topHeight) {
        Fragment fragment = manager.findFragmentByTag(MAJOR_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        MajorSelectFragment majorSelectFragment = MajorSelectFragment.newInstance(topHeight);
        majorSelectFragment.show(manager.beginTransaction(), MAJOR_SELECT);
    }

    /**
     * 日期选择控件
     */
    public static void showDataSelect(Context context, final StripViewNoImg editText) {
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
                editText.setAdditionText(sb.toString());
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
    public static void showEducationSelect(FragmentManager manager, int type, EducationSelectListener listener) {
        Fragment fragment = manager.findFragmentByTag(EDUCATION_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        SelectEducation selectEducation = SelectEducation.newInstance(type, listener);
        selectEducation.show(manager.beginTransaction(), EDUCATION_SELECT);
    }

    /**
     * 选择学历
     */
    public static void showEnrollTypeSelect(FragmentManager manager, EnrollTypeSelectListener listener) {
        Fragment fragment = manager.findFragmentByTag(ENROLL_TYPE_SELECT);
        if (fragment != null)
            manager.beginTransaction().remove(fragment);
        EnrollTypeFragment enrollTypeFragment = EnrollTypeFragment.newInstance(listener);
        enrollTypeFragment.show(manager.beginTransaction(), ENROLL_TYPE_SELECT);
    }
}
