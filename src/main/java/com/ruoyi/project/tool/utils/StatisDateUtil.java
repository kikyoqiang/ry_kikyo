package com.ruoyi.project.tool.utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @version: V1.0
 * @author: 纪志强
 * @description: 报警帮助类
 * @date: 2020-11-05
 * @copyright: 北京龙田华远科技有限公司
 */
public class StatisDateUtil {
    public final static StatisDateUtil Instance = new StatisDateUtil();

    /**
     * @author: 纪志强
     * @description: 获取当前日期 最近一周的日期
     * @param: 参数
     * @return: List
     * @throws:
     */
    public static List<String> getWeekDays(Date todayDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todayDate);
        calendar.add(Calendar.DATE, -7);
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);
            list.add(sdf.format(calendar.getTime()));
        }
        return list;
    }

    /**
     * @author: 纪志强
     * @description: 获取当前日期 最近一个月
     * @param: 参数
     * @return: List
     * @throws:
     */
    public static List<String> getMonthDays(Date todayDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todayDate);
        calendar.add(Calendar.DATE, -30);
        for (int i = 0; i < 30; i++) {
            calendar.add(Calendar.DATE, 1);
            list.add(sdf.format(calendar.getTime()));
        }
        return list;
    }

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
    public Map<String, Integer> getweekOneToFive() {
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

    /**
     * 获取今天是 周几
     *
     * @return
     */
    public static String getWeekToday(Date date) {
        String week = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            week = "周日";
        } else if (weekday == 2) {
            week = "周一";
        } else if (weekday == 3) {
            week = "周二";
        } else if (weekday == 4) {
            week = "周三";
        } else if (weekday == 5) {
            week = "周四";
        } else if (weekday == 6) {
            week = "周五";
        } else if (weekday == 7) {
            week = "周六";
        }
        return week;
    }
}
