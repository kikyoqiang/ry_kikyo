package com.ruoyi.project.front.helper;

/**
 * @author 纪志强
 * @version V1.0
 * @description 前台帮助类
 * @date 2022年03月16日
 */
public class FrontHelper {
    public static String convertShift(String input) {
        String result = "";
        switch (input) {
            case "morning":
                result = "早班";
                break;
            case "afternoon":
                result = "中班";
                break;
            case "night":
                result = "晚班";
                break;
            default:
                break;
        }
        return result;
    }
}
