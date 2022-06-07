package com.ruoyi.project.tool.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateHelper {
    /**
     * @author: 纪志强
     * @description: 获取当前日期 最近一周的日期
     * @param: 参数
     * @return: List
     * @throws:
     */
    // public static List<String> getWeekDays(Date todayDate) {
    //     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //     List<String> list = new ArrayList<>();
    //     Calendar calendar = Calendar.getInstance();
    //     calendar.setTime(todayDate);
    //     calendar.add(Calendar.DATE, -8);
    //     for (int i = 0; i < 7; i++) {
    //         calendar.add(Calendar.DATE, 1);
    //         list.add(sdf.format(calendar.getTime()));
    //     }
    //     return list;
    // }

    /**
     * 获取两个日期之间的所有日期
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
    public static List<String> getDays(String startTime, String endTime) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            // tempEnd.add(Calendar.DATE, 1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 获取两个日期之间的所有日期
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
    public static List<String> getMonthDays(String startTime, String endTime) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            // tempEnd.add(Calendar.DATE, 1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.MONTH, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * @author: 纪志强
     * @description: 获取当前日期 最近一个月
     * @param: 参数
     * @return: List
     * @throws:
     */
    // public static List<String> getMonthDays(Date todayDate) {
    //     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //     List<String> list = new ArrayList<>();
    //     Calendar calendar = Calendar.getInstance();
    //     calendar.setTime(todayDate);
    //     calendar.add(Calendar.DATE, -31);
    //     for (int i = 0; i < 30; i++) {
    //         calendar.add(Calendar.DATE, 1);
    //         list.add(sdf.format(calendar.getTime()));
    //     }
    //     return list;
    // }

    /**
     * @author: 纪志强
     * @description: 获取当前日期 最近一年
     * @param: 参数
     * @return: List
     * @throws:
     */
    public static List<String> getYearMonths(Date todayDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todayDate);
        calendar.add(Calendar.MONTH, -12);
        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH, 1);
            list.add(sdf.format(calendar.getTime()));
        }
        return list;
    }

    /**
     * @author: 纪志强
     * @description: 获取当前月第一天
     * @param: 参数
     * @return: String
     * @throws:
     */
    public static String getFirstDayOfMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 获取某月最小天数
        int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
        calendar.set(Calendar.DAY_OF_MONTH, firstDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime()) + " 00:00:00";
    }

    /**
     * @author: 纪志强
     * @description: 获取当前月最后一天
     * @param: 参数
     * @return: String
     * @throws:
     */
    public static String getLastDayOfMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 获取某月最大天数
        int lastDay = 0;
        //2月的平年瑞年天数
        if (month == 2) {
            lastDay = calendar.getLeastMaximum(Calendar.DAY_OF_MONTH);
        } else {
            lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        // 设置日历中月份的最大天数
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime()) + " 23:59:59";
    }

    /**
     * @author: 纪志强
     * @description: 获取周一到周日
     * @param: 参数
     * @return: Map
     * @throws:
     */
    public static Map<String, Integer> getweekOneToFive() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("周一", 0);
        map.put("周二", 0);
        map.put("周三", 0);
        map.put("周四", 0);
        map.put("周五", 0);
        map.put("周六", 0);
        map.put("周日", 0);
        return map;
    }
}
