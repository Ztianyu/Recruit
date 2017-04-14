package cn.zty.baselib.utils;


import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zty on 2017/1/9.
 */
public class TimeUtils {
    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;
    private static final long ONE_MONTH = 2592000;
    private static final long ONE_YEAR = 31104000;

    public static Calendar calendar = Calendar.getInstance();

    /**
     * @return yyyy-mm-dd
     * 2012-12-25
     */
    public static String getDate() {
        return getYear() + "-" + getMonth() + "-" + getDay();
    }

    /**
     * @param format
     * @return yyyy年MM月dd HH:mm
     * MM-dd HH:mm 2012-12-25
     */
    public static String getDate(String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        return simple.format(calendar.getTime());
    }

    /**
     * @return yyyy-MM-dd HH:mm
     * 2012-12-29 23:47
     */
    public static String getDateAndMinute() {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simple.format(calendar.getTime());
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss
     * 2012-12-29 23:47:36
     */
    public static String getFullDate() {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simple.format(calendar.getTime());
    }


    public static String getAge(String date) {
        if (!TextUtils.isEmpty(date)) {
            int year = Integer.parseInt(date.substring(0, 4));
            int nowYear = Integer.parseInt(getDateAndMinute().substring(0, 4));

            return (nowYear - year) + "岁";
        } else {
            return "";
        }
    }

    /**
     * 距离今天多久
     *
     * @param date
     * @return
     */
    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + "分钟前";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + "小时前";
//        return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE)
//                + "分钟前";
        else if (ago <= ONE_DAY * 2)
            return "昨天";
//        return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
//                + calendar.get(Calendar.MINUTE) + "分";
        else if (ago <= ONE_DAY * 3)
            return "前天";
        else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            return month + "个月前";
        } else {
            long year = ago / ONE_YEAR;
            int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
            return year + "年前";
        }
    }

    /**
     * 判断聊天时间与当前时间差
     */
    public static long getTimeCount(long l) {
        long now = new Date().getTime() / 1000;
        return (now - l / 1000) / ONE_MINUTE;
    }

    public static String getChatTime(long l) {
        String res = "";

        String strTime = stampToDate(l);

        long time = l / 1000;
        long now = new Date().getTime() / 1000;
        long ago = (now - time) / ONE_MINUTE;
        long todayStart = dateToStamp(getDate() + " 00:00:00") / 1000;

        if (todayStart <= time) {
            res = "今天\n" + strTime.substring(11, 16);
        } else if (todayStart > time && time > (todayStart - ONE_DAY)) {
            res = "昨天\n" + strTime.substring(11, 16);
        } else {
            res = strTime.substring(0, 11);
        }
        return res;
    }


    /**
     * 转换时间格式
     */
    public static String changeTime(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM月dd日");

        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy年MM月dd日");

        if (getYear().equals(time.substring(0, 4))) {
            return simpleDateFormat2.format(date);
        } else {
            return simpleDateFormat3.format(date);
        }
    }

    /**
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s) {
        long res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        res = date.getTime();
        return res;
    }

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 距离截止日期还有多长时间
     *
     * @param date
     * @return
     */
    public static String fromDeadline(Date date) {
        long deadline = date.getTime() / 1000;
        long now = (new Date().getTime()) / 1000;
        long remain = deadline - now;
        if (remain <= ONE_HOUR)
            return "只剩下" + remain / ONE_MINUTE + "分钟";
        else if (remain <= ONE_DAY)
            return "只剩下" + remain / ONE_HOUR + "小时"
                    + (remain % ONE_HOUR / ONE_MINUTE) + "分钟";
        else {
            long day = remain / ONE_DAY;
            long hour = remain % ONE_DAY / ONE_HOUR;
            long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return "只剩下" + day + "天" + hour + "小时" + minute + "分钟";
        }
    }

    /**
     * 距离今天的绝对时间
     *
     * @param date
     * @return
     */
    public static String toToday(Date date) {
        long time = date.getTime() / 1000;
        long now = (new Date().getTime()) / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + "分钟";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟";
        else if (ago <= ONE_DAY * 2)
            return "昨天" + (ago - ONE_DAY) / ONE_HOUR + "点" + (ago - ONE_DAY)
                    % ONE_HOUR / ONE_MINUTE + "分";
        else if (ago <= ONE_DAY * 3) {
            long hour = ago - ONE_DAY * 2;
            return "前天" + hour / ONE_HOUR + "点" + hour % ONE_HOUR / ONE_MINUTE
                    + "分";
        } else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            long hour = ago % ONE_DAY / ONE_HOUR;
            long minute = ago % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return day + "天前" + hour + "点" + minute + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            long hour = ago % ONE_MONTH % ONE_DAY / ONE_HOUR;
            long minute = ago % ONE_MONTH % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return month + "个月" + day + "天" + hour + "点" + minute + "分前";
        } else {
            long year = ago / ONE_YEAR;
            long month = ago % ONE_YEAR / ONE_MONTH;
            long day = ago % ONE_YEAR % ONE_MONTH / ONE_DAY;
            return year + "年前" + month + "月" + day + "天";
        }
    }

    /**
     * 比较两个日期字符串日期大小
     *
     * @return >0 date1新于date2
     * <0 date1旧于date2
     * =0 date1等于date2
     */
    public static int compareDate(String date1, String date2, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取几天前的日期（第几月前的日期）
     *
     * @param time
     * @param dateType （0：获取几天前的日期；1：获取第几月前的日期）
     * @param count    ( 天数（月数）)
     * @return
     */
    public static String getPeriodDate(String time, int dateType,
                                       int count) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.setTime(simpleDate.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int day; // 需要更改的天数(月数)

        switch (dateType) {
            case 0:
                day = c.get(Calendar.DAY_OF_MONTH) - count;
                c.set(Calendar.DAY_OF_MONTH, day);
                break;
            case 1:
                day = c.get(Calendar.MONTH) - count;
                c.set(Calendar.MONTH, day);
                break;
        }

        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        StringBuilder strForwardDate = new StringBuilder().append(mYear)
                .append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                .append("-").append((mDay < 10) ? "0" + mDay : mDay);
        System.out.println("strDate------->" + strForwardDate + "--->"
                + c.getTimeInMillis());
        return strForwardDate.toString();
    }

    public static String getYear() {
        return calendar.get(Calendar.YEAR) + "";
    }

    public static String getMonth() {
        int month = calendar.get(Calendar.MONTH) + 1;
        return month > 10 ? "" + month : "0" + month;
    }

    public static String getDay() {
        return calendar.get(Calendar.DATE) > 10 ? calendar.get(Calendar.DATE) + "" : "0" + calendar.get(Calendar.DATE);
    }

    public static String get24Hour() {
        return calendar.get(Calendar.HOUR_OF_DAY) + "";
    }

    public static String getMinute() {
        return calendar.get(Calendar.MINUTE) + "";
    }

    public static String getSecond() {
        return calendar.get(Calendar.SECOND) + "";
    }

}
