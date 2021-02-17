package com.zih.booking.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 *
 * @author shahy
 */
@Slf4j
public class DateUtils {

    /**
     * 根据固定的格式，将字符串转化为Date
     */
    public static Date parseDate(String str, String ftm) {
        if (str == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(ftm).parse(str);
        } catch (ParseException e) {
            return null;
        }

    }

    /**
     * 根据固定的格式，将日期转化为字符串
     */
    public static String parseStr(Date date, String ftm) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(ftm).format(date);
    }

    public static String parseStr(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 获取过去的天数
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数，Date类型
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获取两个日期之间的天数，字符串格式
     */
    public static double getDistanceOfTwoDate(String before, String after, String fmt) {
        Date beforeD = parseDate(before, fmt);
        Date afterD = parseDate(after, fmt);
        return getDistanceOfTwoDate(beforeD, afterD);
    }

    /**
     * @Description: 获取两个日期之间的小时数
     */
    public static double getDistHoursOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60);
    }

    /**
     * @Description: 获取两个时间相差的分钟数
     */
    public static Integer diffMinute(Date start, Date end) {
        return (int) Math.ceil((double) (end.getTime() - start.getTime()) / (1000 * 60));
    }

    /**
     * 通过毫秒时间戳获取小时数和分钟数
     */
    public static String getHourAndMinute(long time) {
        int minute = (int) Math.ceil((double) (time) / (1000 * 60));
        int hours = (int) Math.floor((double) minute / 60);
        minute = minute % 60;
        StringBuilder sb = new StringBuilder();
        if (hours > 0) {
            sb.append(hours).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        return sb.toString();
    }

    //获取往前最近n天的日期
    public static List<String> getDaysBetwwen(int days) {
        List<String> dayss = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.setTime(getDateAdd(days));
        Long startTIme = start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.setTime(new Date());
        end.add(Calendar.DATE, -1);
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24L;
        Long time = startTIme;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            dayss.add(df.format(d));
            time += oneDay;
        }
        log.info(String.valueOf(dayss));
        return dayss;
    }

    public static Date getDateAdd(int days) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        return c.getTime();
    }

    //获取指定日期下个月的第一天
    public static Date getNextMonthByDate(Date byDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(byDate);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 含有字符的字符串转int
     * @param a
     * @return
     */
    public static int reInt(String a) {
        String reg="[^0-9]";//定义正则表达式即：0-9的所有数
        //Pattern调用静态方法compile返回Pattern实例。
        //也可以说是将正则表达式定义为编译规则
        Pattern pattern= Pattern.compile(reg);
        //此处代表是通过编译规则筛选字符串a里的所有数字
        Matcher matcher=pattern.matcher(a);
        //去掉所有的空格和空串
        Integer c=Integer.parseInt(matcher.replaceAll("").trim());
        return c;//将最终得到的 整数返回前台
    }

    public static void main(String args[]) {
        log.info(getDaysBetwwen(29)+"");
    }
}
